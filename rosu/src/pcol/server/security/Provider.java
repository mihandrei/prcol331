package pcol.server.security;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pcol.server.HibernateUtil;
import pcol.server.domain.Logins;
import pcol.shared.User;

/**
 * offers authentication and authorisation.
 */
public class Provider {

    public static final String SESSION_USER = "user";
	private static final Provider INSTANCE = new Provider();

	public User doAuthenticate(String login,String pwd){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Logins user = (Logins) session.get(Logins.class, login); //da-mi userul cu numele asta
			if( user!=null && pwd.equals(user.getPasswordHash())){
				user.setLastlogin(new Date().toString());
				session.update(user);
				
				//daca-i student
				//FIXME:hardcoded roles
				if(user.getStudentis().iterator().hasNext()){ 
					 int nrmatr = user.getStudentis().iterator().next().getNrMatr();
					 return new User(login, 
							 user.getPersoane().getNume() + " " + user.getPersoane().getPrenume(),
							 nrmatr,User.Role.STUDENT,"");
				}else{ 
					 return new User(login, 
							 user.getPersoane().getNume() + " " + user.getPersoane().getPrenume(),
							 0, User.Role.ADMIN ,"");

				}
			
			}
			session.getTransaction().commit();
			return null;
		} finally {
			session.close();
		}
	}
	
	//FIXME hardcoded ; check with db
    public boolean isAuthorised(User user, String resource) {
    	if(resource.startsWith("/pcol/rpc/auth")){
    		return true;
    	}
    	else {
//    		if(resource.startsWith("/pcol/rpc/tweets") || resource.startsWith("/pcol/rpc/contract")){
    	
    		return user!=null;
    	}
//        return false;
    }

	public static Provider getInstance() {		
		return INSTANCE;
	}
}
