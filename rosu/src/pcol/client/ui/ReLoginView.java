package pcol.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface ReLoginView extends IsWidget{

		public interface Presenter {
			void relogin(String pwd);
		}

		void setPresenter(Presenter p);

		void showLoginError();

		void hide();

		void showscreen();

		void reset();

}
