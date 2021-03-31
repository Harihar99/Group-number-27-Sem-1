package com.example.bookcave.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookcave.CustomerLogin;
import com.example.bookcave.R;
import com.example.bookcave.forgot_password;
import com.example.bookcave.profile.ContactDeveloper;
import com.example.bookcave.profile.Favourites;
import com.example.bookcave.profile.HelpC;
import com.example.bookcave.profile.OrderHistoryC;
import com.example.bookcave.profile.PaymentHistoryC;
import com.example.bookcave.profile.ProfileCustomer;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private LinearLayout linearl0,linearl1,linearl2,linearl3,linearl4,linearl5,linearl6,linearl7;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        linearl0=root.findViewById(R.id.linearl0);
        linearl1=root.findViewById(R.id.linearl1);
        linearl2=root.findViewById(R.id.linearl2);
        linearl3=root.findViewById(R.id.linearl3);
        linearl4=root.findViewById(R.id.linearl4);
        linearl5=root.findViewById(R.id.linearl5);
        linearl6=root.findViewById(R.id.linearl6);
        linearl7=root.findViewById(R.id.linearl7);


        linearl0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //My profile
                Intent i = new Intent(getActivity(), ProfileCustomer.class);
                startActivity(i);
            }
        });

        linearl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //My cart
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
                Intent i = new Intent(getActivity(), HelpC.class);
                startActivity(i);
            }
        });

        linearl7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log out
                Intent i = new Intent(getActivity(), CustomerLogin.class);
                startActivity(i);
            }
        });
        return root;
    }
}
