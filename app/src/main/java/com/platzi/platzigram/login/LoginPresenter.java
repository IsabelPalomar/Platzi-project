package com.platzi.platzigram.login;

/**
 * Created by lucy on 30/09/16.
 */

public interface LoginPresenter {
    void onDestroy();
    void validateLogin(String email, String password);
}
