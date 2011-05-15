package pcol.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.server.domain.Resource;

/**
 * Servlet responsabil de uploadarea unui 
 * fisier si creerea resursei corespunzatoare
 *
 */
public class FileUpload extends HttpServlet {
	private static final String DENUMIRE = "denumire";
	private static final String UPLOADS = "uploads";
	private static final int MAXFILESIZE = 1024 * 1024 * 1;// 10 megs
	
	private static Logger log = Logger.getLogger(FileUpload.class.getName());

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// daca ii un upload
		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload upload = new ServletFileUpload();
			String denumire = null;
			//in gwt rasponsul ajunge intr-un iframe
			//fara content type browserele imbraca in <pre> sau alt tag raspunsul
			response.setContentType("text/html");
			
			// parseaza multipartu
			FileItemIterator iter;
			try {
				iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();

					String name = item.getFieldName();
					InputStream stream = item.openStream();

					if (item.isFormField()) {
						String value = Streams.asString(stream);
						if (DENUMIRE.equals(name)) {
							denumire = value;
						} else {
							log.warning("unexpected form field in upload "
									+ name);
						}
					} else { // un fisier
						String filename = item.getName();
						if (denumire == null || denumire.trim().isEmpty()) {
							denumire = FilenameUtils.getName(filename);;
						}
						String resName = processIstream(denumire, stream);
						
						PrintWriter out = response.getWriter();
						out.print("ok:"+resName);
						out.close();
					}
				}
			} catch (Exception ex) {
				PrintWriter out = response.getWriter();
				out.print("err:");
				ex.printStackTrace(out);
				out.close();
			}

		}
	}

	/**
	 * creeaza un fisier nou. daca fisierul exista deja creeaza unul cu nume
	 * asemanator threadsafe: Daca e apelata de 2 threaduri diferite e garantat
	 * ca nu va creea un fisier cu acelasi nume
	 * 
	 * @throws IOException
	 */
	private static File getUniqueSequentialFileName(String filename)
			throws IOException {
		File file;
		int nr = 0;
		String cfilename = filename;
		do {
			file = new File(UPLOADS, cfilename);
			cfilename = filename + "(" + nr + ")";
			nr += 1;
		} while (!file.createNewFile());// check & creation is atomice

		return file;
	}

	private static void copyStream(InputStream in, OutputStream out)
			throws IOException {
		int totalBytes = 0;
		int len;
		byte[] buffer = new byte[1024 * 8];

		try {
			while ((len = in.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
				totalBytes += len;
				if (totalBytes >= MAXFILESIZE) { // protejam serverul
					throw new IOException("file too big");
				}
			}
		} finally {
			in.close();
			out.close();
		}
	}

	private String processIstream(String denumire, InputStream in)
			throws IOException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		File file = getUniqueSequentialFileName(denumire);
		FileOutputStream out = new FileOutputStream(file);

		try {
			session.beginTransaction();
			Resource r = new Resource(file.getName(),denumire);
			session.persist(r);
			copyStream(in, out);
			session.getTransaction().commit();
			return r.getNumefisier();
		}catch (HibernateException ex){
			file.delete();
			session.getTransaction().rollback();
			throw ex;
		} catch (IOException ex) {
			file.delete();
			session.getTransaction().rollback();
			throw ex;
		} finally {
			session.close();
		}
	}
}
