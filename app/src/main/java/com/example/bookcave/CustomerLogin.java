package com.example.bookcave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomerLogin extends AppCompatActivity {

    EditText emailInput,passwordInput;
    Button forgotpassLink,signupLink,loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        //Hide the ActionBar
        getSupportActionBar().hide();
        //ViewBinding
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        signupLink = findViewById(R.id.signupLink);
        forgotpassLink = findViewById(R.id.forgotpassLink);

        forgotpassLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerLogin.this, forgot_password.class);
                startActivity(i);
            }
        });

    }
}
