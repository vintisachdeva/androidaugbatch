package com.bmpl.firebaseservice_nisha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText)findViewById(R.id.emailLoginEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordLoginEditText);

        loginButton = (Button)findViewById(R.id.loginActivityButton);

        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Authentication Successful", Toast.LENGTH_LONG).show();

                } else{
                    Log.d("LoginActivity","Exception= " + task.getException().getMessage());
                    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}