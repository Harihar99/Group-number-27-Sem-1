package com.example.bookcave;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookInfoOrder extends AppCompatActivity {

    ImageView bthumbnail;
    TextView btitle,bcategory,bprice,bauthor,bdesc,sname,rp,dc,sadd;
    Button bshow;
    String sn,sa;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_order);

        bthumbnail=findViewById(R.id.bthumbnail);
        btitle=findViewById(R.id.btitle);
        bcategory=findViewById(R.id.bcategory);
        bprice=findViewById(R.id.bprice);
        bauthor=findViewById(R.id.bauthor);
        bdesc=findViewById(R.id.bdesc);
        bshow=findViewById(R.id.bshow);
        sname=findViewById(R.id.sname);
        sadd=findViewById(R.id.sadd);
        rp=findViewById(R.id.rp);
        dc=findViewById(R.id.dc);


        Intent intent=getIntent();
        final String book_id =intent.getStringExtra("book_id");
        final String book_title =intent.getStringExtra("book_title");
        final String image =intent.getStringExtra("book_thumbnail");
        final String book_author = intent.getStringExtra("book_author");
        final String book_desc = intent.getStringExtra("book_desc");
        final String preview = intent.getStringExtra("link");
        final String book_cat = intent.getStringExtra("book_cat");

        final String sellerid=intent.getStringExtra("seller");
        final String sprice = intent.getStringExtra("sp");
        final String rprice = intent.getStringExtra("rp");
        final String dprice = intent.getStringExtra("dc");


        bshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(preview);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        DocumentReference docRef = db.collection("Users").document(sellerid);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                        sn = (String) document.getString("shop");
                        sa = (String) document.getString("address");

                }
            }
        });

        sname.setText(sn);
        sadd.setText(sa);
        btitle.setText(book_title);
        bcategory.setText(book_cat);
        bprice.setText("Selling @"+sprice);
        bauthor.setText(book_author);
        bdesc.setText(book_desc);
        rp.setText("Renting price:"+rprice+" INR per day");
        dc.setText("Delivery charges:"+dprice+" INR");

        Glide.with(BookInfoOrder.this).load(image).placeholder(R.drawable.loading_shape).dontAnimate().into(bthumbnail);
    }
}
