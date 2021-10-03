package com.example.bookcave;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1700;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //Checked signed
        fAuth = FirebaseAuth.getInstance();
        mAuthStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {

                    //User of customer start
                    userid = mFirebaseUser.getUid();
                    DocumentReference typeref = db.collection("Users").document(userid);
                    typeref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String type = documentSnapshot.getString("usertype");
                                String nam = documentSnapshot.getString("firstname");
                                Toast.makeText(MainActivity.this, "Logged in Successfully as " + nam, Toast.LENGTH_SHORT).show();
                                if (type.equals("Customer")) {
                                    Intent intent = new Intent(MainActivity.this, HomeCustomer.class);
                                    startActivity(intent);
                                    finish();
                                } else if (type.equals("Seller")) {
                                    Intent intent = new Intent(MainActivity.this, HomeSeller.class);
                                    intent.putExtra("user_id" ,userid);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    });
                    //END
                } else {
                    Intent i = new Intent(MainActivity.this, CustomerLogin.class);
                    startActivity(i);
                    finish();
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logincheck();
            }
        }, SPLASH_TIME_OUT);
    }

    public void logincheck(){
        fAuth.addAuthStateListener(mAuthStateListener);
    }

}
