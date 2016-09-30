package com.platzi.platzigram.login;

import com.platzi.platzigram.login.ui.LoginView;

/**
 * Created by lucy on 30/09/16.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginTaskListener {
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null){
            loginView.disableInputs();
            loginView.showProgressBar();
        }

        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void loginSuccess() {
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    @Override
    public void loginError(String error) {
        if(loginView != null){
            loginView.loginError(error);
            loginView.enableInputs();
            loginView.hideProgressBar();
        }

    }
}
