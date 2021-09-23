package com.example.bookcave;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BuyBook extends AppCompatActivity {

    public TextView oname,onumber,oaddress,obname,obauthorname,ogenre,orpice,oitemprice,odeliprice,ototalprice,famount;
    public Button changeaddress,order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_book);

        oname=findViewById(R.id.oname);
        onumber=findViewById(R.id.onumber);
        oaddress=findViewById(R.id.oaddress);
        obname=findViewById(R.id.obname);
        obauthorname=findViewById(R.id.obauthorname);
    }
}
