package pcol.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppResources extends ClientBundle {
	public final static AppResources INSTANCE = GWT.create(AppResources.class);

	public interface AppResourcesCss extends CssResource {
	}

	@Source("pcol.css")
	AppResourcesCss pcolcss();
}
