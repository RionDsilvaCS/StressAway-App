package com.example.stressaway;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MentorDetailsFragment extends Fragment {

    ImageView mailButton, chatButton;
    public MentorDetailsFragment() {

    }

    public static MentorDetailsFragment newInstance(String param1, String param2) {
        MentorDetailsFragment fragment = new MentorDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mentor_details, container, false);
        mailButton = view.findViewById(R.id.mail_icon_profile);
        chatButton = view.findViewById(R.id.chat_icon_profile);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MentorChatActivity.class);
                startActivity(intent);
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
        return view;
    }

}