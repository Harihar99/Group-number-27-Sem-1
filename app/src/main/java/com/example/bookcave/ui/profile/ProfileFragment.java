package com.example.bookcave.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookcave.CustomerLogin;
import com.example.bookcave.R;
import com.example.bookcave.profile.AboutApp;
import com.example.bookcave.profile.ContactDeveloper;
import com.example.bookcave.profile.Favourites;
import com.example.bookcave.profile.OrderHistoryC;
import com.example.bookcave.profile.PaymentHistoryC;
import com.example.bookcave.profile.ProfileCustomer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private LinearLayout linearl0,linearl2,linearl3,linearl4,linearl5,linearl6,linearl7;
    private TextView profileName;
    //Firebase
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = root.findViewById(R.id.profileName);
        linearl0=root.findViewById(R.id.linearl0);
        linearl2=root.findViewById(R.id.linearl2);
        linearl3=root.findViewById(R.id.linearl3);
        linearl4=root.findViewById(R.id.linearl4);
        linearl5=root.findViewById(R.id.linearl5);
        linearl6=root.findViewById(R.id.linearl6);
        linearl7=root.findViewById(R.id.linearl7);

        //Checked signed
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        DocumentReference typeref = db.collection("Users").document(userid);
        typeref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String fullName = documentSnapshot.getString("firstname")+" "+documentSnapshot.getString("lastname");
                    profileName.setText(fullName);
                }
            }
        });


        linearl0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //My profile
                Intent i = new Intent(getActivity(), ProfileCustomer.class);
                startActivity(i);
            }
        });

        linearl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Favourites
                Intent i = new Intent(getActivity(), Favourites.class);
                startActivity(i);
            }
        });

        linearl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Order history
                Intent i = new Intent(getActivity(), OrderHistoryC.class);
                startActivity(i);
            }
        });

        linearl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Payment History
                Intent i = new Intent(getActivity(), PaymentHistoryC.class);
                startActivity(i);
            }
        });

        linearl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Contact Developer
                Intent i = new Intent(getActivity(), ContactDeveloper.class);
                startActivity(i);
            }
        });

        linearl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Help
                Intent i = new Intent(getActivity(), AboutApp.class);
                startActivity(i);
            }
        });

        linearl7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log out
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(), CustomerLogin.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }

}
