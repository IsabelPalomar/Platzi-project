package com.platzi.platzigram.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.platzi.platzigram.R;
import com.platzi.platzigram.login.LoginPresenter;
import com.platzi.platzigram.login.LoginPresenterImpl;
import com.platzi.platzigram.view.ContainerActivity;
import com.platzi.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{


    Button btnLogin;
    TextInputEditText etEmail;
    TextInputEditText etPassword;
    TextView tvCreateAccount;
    ProgressBar progressBarLogin;

    LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setElementsAndEvents();

        loginPresenter = new LoginPresenterImpl(this);

    }

    public void setElementsAndEvents(){
        etEmail = (TextInputEditText) findViewById(R.id.username);
        etPassword = (TextInputEditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.login);
        tvCreateAccount = (TextView) findViewById(R.id.createHere);

        progressBarLogin = (ProgressBar) findViewById(R.id.progresbarLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignIn();
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCreateAccount();
            }
        });

    }


    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void handleSignIn() {


        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.equals("")){
            Toast.makeText(LoginActivity.this, "Proporcione un nombre de usuario válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals("")){
            Toast.makeText(LoginActivity.this, "Proporcione una contraseña de usuario válida", Toast.LENGTH_SHORT).show();
            return;
        }

        loginPresenter.validateLogin(email, password);

    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {

        String errorMsg = String.format(getString(R.string.login_error_message_signin), error);
        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();

    }

    private void setInputs(boolean enabled){
        etEmail.setEnabled(enabled);
        etPassword.setEnabled(enabled);
        btnLogin.setEnabled(enabled);
        tvCreateAccount.setEnabled(enabled);
    }

}
