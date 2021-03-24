package com.example.bookcave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgot_password extends AppCompatActivity {

    EditText resetemailInput;
    Button sentemailButton,backtolgLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //ViewBinding
        resetemailInput = findViewById(R.id.resetemailInput);
        sentemailButton = findViewById(R.id.sentmailButton);
        backtolgLink = findViewById(R.id.backtolgLink);

        backtolgLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(forgot_password.this, CustomerLogin.class);
                startActivity(i);
            }
        });
    }
}
