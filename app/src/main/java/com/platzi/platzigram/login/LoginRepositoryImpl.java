package com.platzi.platzigram.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.PlatzigramApplication;
import com.platzi.platzigram.login.LoginRepository;
import com.platzi.platzigram.login.LoginTaskListener;
import com.platzi.platzigram.login.ui.LoginActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by lucy on 30/09/16.
 */

public class LoginRepositoryImpl implements LoginRepository {

    FirebaseAuth firebaseAuth;
    LoginTaskListener listener;
    PlatzigramApplication application;
    Context context;

    public LoginRepositoryImpl(LoginTaskListener listener) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.listener = listener;
    }

    @Override
    public void signIn(String email, String password) {

        application = (PlatzigramApplication) getApplicationContext();

        context = application.getContext();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser user = task.getResult().getUser();
                        SharedPreferences.Editor sharedPreferences = context.getSharedPreferences("USER", MODE_PRIVATE).edit();
                        sharedPreferences.putString("email", user.getEmail());
                        sharedPreferences.commit();

                        if(!task.isSuccessful()){
                            listener.loginError(task.getException().toString());
                        }
                        else {
                            listener.loginSuccess();
                        }
                    }
                });

    }
}
