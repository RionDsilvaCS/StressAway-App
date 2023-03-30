package com.example.stressaway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    ImageView journal, books, music, movie, diet, yoga, location;

    public HomeFragment() {
        super(R.layout.fragment_home);

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        journal = view.findViewById(R.id.journalButton);
        books = view.findViewById(R.id.bookButton);
        music = view.findViewById(R.id.musicButton);
        movie = view.findViewById(R.id.movieButton);
        diet = view.findViewById(R.id.dietButton);
        yoga = view.findViewById(R.id.yogaButton);
        location = view.findViewById(R.id.locationButton);
        Toast addSoonToast = Toast.makeText(getActivity(),"Feature will be added soon", Toast.LENGTH_SHORT);

        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JournalActivity.class);
                startActivity(intent);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // books intent
                addSoonToast.show();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // music intent
                addSoonToast.show();
            }
        });

        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // movie intent
                addSoonToast.show();
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // diet intent
                addSoonToast.show();
            }
        });

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // yoga intent
                addSoonToast.show();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // location intent
                addSoonToast.show();
            }
        });

        return view;

    }
    public interface OnFragmentInteractionListener {

        void messageFromParentFragment(Uri uri);
    }

}