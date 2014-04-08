package de.metalcon.middleware.view;

/**
 * this View is needed in particular, when a login request was not successful.
 * In this case there should be some stages being rendered for password
 * retrieval
 */
public class LoginView extends BaseView {

    public LoginView() {
        super("login");
    }

}
