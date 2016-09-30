package com.platzi.platzigram.login;

/**
 * Created by lucy on 30/09/16.
 */

public interface LoginRepository {
    void signIn(String email, String password);
}
