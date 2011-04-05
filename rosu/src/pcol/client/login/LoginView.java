package pcol.client.login;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget{

		public interface Presenter {

			void login(String text, String text2);
		}

		void setPresenter(Presenter p);

		void showLoginError();

}
