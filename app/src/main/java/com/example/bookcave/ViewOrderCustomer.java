
package com.example.bookcave;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class ViewOrderCustomer extends AppCompatActivity {
    String orderid,bookid,bookname,thumbnail,mailid,fullname;
    TextView ssellerid,sstatus,sbookname,sorderon,sname,sphno,semail,saddress,spin,stotalamount,sotp;
    ImageView bvthumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_customer);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        orderid = getIntent().getStringExtra("orderid");
        ssellerid=findViewById(R.id.ssellerid);
        sotp=findViewById(R.id.sotp);
        stotalamount=findViewById(R.id.stotamount);
        spin=findViewById(R.id.spin);
        saddress=findViewById(R.id.saddress);
        semail=findViewById(R.id.semail);
        sphno=findViewById(R.id.sphno);
        sname=findViewById(R.id.sname);
        sorderon=findViewById(R.id.sorderon);
        sbookname=findViewById(R.id.sbookname);
        sstatus=findViewById(R.id.sstatus);
        bvthumbnail=findViewById(R.id.bvthumbnail);

        ssellerid.setText(String.valueOf(orderid));
        DocumentReference docRef = db.collection("Orders").document(orderid);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String phno = documentSnapshot.getString("phno");
                    String pc = documentSnapshot.getString("pincode");
                    String ad = documentSnapshot.getString("address");
                    mailid = documentSnapshot.getString("email");
                    String oa = documentSnapshot.getString("orderedat");
                    String s = documentSnapshot.getString("status");
                    String otp = documentSnapshot.getString("otp");
                    int ta = documentSnapshot.getLong("totalamount").intValue();
                    bookid = documentSnapshot.getString("bookid");

                    sotp.setText(String.valueOf(otp));
                    stotalamount.setText("Total amount: "+String.valueOf(ta));
                    spin.setText(pc);
                    saddress.setText(ad);
                    semail.setText(mailid);
                    sphno.setText(phno);
                    sorderon.setText(oa);
                    sstatus.setText(s+": ");
                }
            }
        });

        //For book details
        db.collection("SellingList").whereEqualTo("bookid",bookid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        bookname = document.getString("title");
                        thumbnail = document.getString("thumbnail");
                    }
                }
            }
        });
        sbookname.setText(bookname);
        Glide.with(ViewOrderCustomer.this).load(thumbnail).placeholder(R.drawable.loading_shape).dontAnimate().into(bvthumbnail);

        db.collection("Users").whereEqualTo("email",mailid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        fullname = document.getString("firstname")+document.getString("lastname");
                    }
                }
            }
        });
        sname.setText(fullname);
    }
}
