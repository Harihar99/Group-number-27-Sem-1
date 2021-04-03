package com.example.bookcave.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bookcave.R;

import java.util.ArrayList;

public class ContactDeveloper extends AppCompatActivity {

    Spinner spinner;
    String severity,a="username";
    EditText username,problemdesc;
    Button sendmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_developer);

        spinner = findViewById(R.id.severityspinner);
        username = findViewById(R.id.user_name);
        problemdesc = findViewById(R.id.problem_description);
        sendmail = findViewById(R.id.sendmail);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Very Low");
        arrayList.add("Low");
        arrayList.add("Medium");
        arrayList.add("High");
        arrayList.add("Severe");
        arrayList.add("Urgent");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ContactDeveloper.this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                severity = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                severity ="Very Low";
            }
        });

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store text
                String descrip= problemdesc.getText().toString().trim();

                if(TextUtils.isEmpty(descrip)){
                    problemdesc.setError("Details are required");
                    return;
                }

                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.setType("message/rfc822");
                // emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"atharva464@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Report Severity : "+severity+" reported by "+a);
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, descrip);
                startActivity(Intent.createChooser(emailIntent, "Choose an email client"));

            }
        });
    }
}
