package com.example.bookcave;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerLogin extends AppCompatActivity {

    EditText emailInput,passwordInput;
    Button forgotpassLink,createaccLink,createaccsellerLink,loginButton;
    ProgressBar LogInProgress;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        //Hide the ActionBar
        getSupportActionBar().hide();
        //ViewBinding
        emailInput = findViewById(R.id.emailInput);
        LogInProgress = findViewById(R.id.LogInProgress);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        createaccLink = findViewById(R.id.createaccLink);
        createaccsellerLink = findViewById(R.id.createselleraccLink);
        forgotpassLink = findViewById(R.id.forgotpassLink);

        fAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(CustomerLogin.this, "Welcome back", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CustomerLogin.this, HomeCustomer.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        forgotpassLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerLogin.this, forgot_password.class);
                startActivity(i);
            }
        });

        createaccLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerLogin.this, CreateAccountCustomer.class);
                startActivity(i);
            }
        });

        createaccsellerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Intent i = new Intent(CustomerLogin.this, CreatAccountSeller.class);*/
                Intent i = new Intent(CustomerLogin.this, CreatAccountSeller.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Email ID is Required.");
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    passwordInput.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    passwordInput.setError("Password Must be more than 6 Characters");
                    return;
                }

                LogInProgress.setVisibility(View.VISIBLE);
                loginButton.setText("Signing in...");
                // authenticate the user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CustomerLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CustomerLogin.this, HomeCustomer.class);
                            startActivity(intent);
                            finish();
                            loginButton.setText("Sign In");
                        } else {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(CustomerLogin.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                        }
                        loginButton.setText("Sign In");
                        LogInProgress.setVisibility(View.GONE);
                    }
                });

            }
        });

    }
}
