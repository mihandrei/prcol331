package pcol.client.security;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AppDoneEvent extends GwtEvent<AppDoneEvent.Handler> {
	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void onAppTerminated(AppDoneEvent event);
	}

	public AppDoneEvent() {
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onAppTerminated(this);
	}
}
