package com.example.bookcave;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerLogin extends AppCompatActivity {

    EditText emailInput,passwordInput;
    Button forgotpassLink,createaccLink,createaccsellerLink,loginButton;
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
        createaccLink = findViewById(R.id.createaccLink);
        createaccsellerLink = findViewById(R.id.createselleraccLink);
        forgotpassLink = findViewById(R.id.forgotpassLink);

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
                Intent i = new Intent(CustomerLogin.this, HomeCustomer.class);
                startActivity(i);
            }
        });

    }
}
