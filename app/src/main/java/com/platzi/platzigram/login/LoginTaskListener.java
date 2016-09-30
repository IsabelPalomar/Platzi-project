package com.platzi.platzigram.login;

/**
 * Created by lucy on 30/09/16.
 */

public interface LoginTaskListener {
    void loginSuccess();
    void loginError(String error);
}
