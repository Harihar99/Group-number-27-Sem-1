package com.example.bookcave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CustomerLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        getSupportActionBar().hide();
    }
}
