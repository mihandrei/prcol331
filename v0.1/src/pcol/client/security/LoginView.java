package pcol.client.security;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget{

		public interface Presenter {

			void login(String usr, String pwd);
		}

		void setPresenter(Presenter p);

		void showLoginError();

		void hide();

		void showscreen();

		void reset();

}
