package pcol.client.security;

import pcol.shared.User;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEvent.Handler> {
	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void onUserAuthenticated(LoginEvent event);
	}

	public LoginEvent(User user) {
		this.user = user;
	}

	private User user;

	public User getUser() {
		return user;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onUserAuthenticated(this);
	}
}
