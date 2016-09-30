package com.platzi.platzigram.login.ui;

/**
 * Created by lucy on 30/09/16.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();

    void handleSignIn();

    void navigateToMainScreen();
    void navigateToCreateAccount();

    void loginError(String error);
}
