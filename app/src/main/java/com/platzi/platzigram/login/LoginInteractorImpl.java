package com.platzi.platzigram.login;

/**
 * Created by lucy on 30/09/16.
 */

public class LoginInteractorImpl implements LoginInteractor {

    LoginRepository loginRepository;

    public LoginInteractorImpl(LoginTaskListener listener) {
        loginRepository = new LoginRepositoryImpl(listener);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}
