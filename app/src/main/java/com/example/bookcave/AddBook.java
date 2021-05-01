package com.example.bookcave;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class AddBook extends AppCompatActivity {

    TextView title;
    ImageView thumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Intent intent=getIntent();
        String name =intent.getStringExtra("book_title");
        String image =intent.getStringExtra("book_thumbnail");
        title=findViewById(R.id.title);
        thumbnail=findViewById(R.id.thumbnail);

        title.setText(name);
        Glide.with(AddBook.this).load(image).placeholder(R.drawable.loading_shape).dontAnimate().into(thumbnail);
    }
}
