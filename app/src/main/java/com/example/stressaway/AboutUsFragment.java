package com.example.stressaway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutUsFragment extends Fragment {

    Button anieshL, anieshG, puneethL, puneethG, rionL, rionG;
    TextView website;
    public AboutUsFragment() {
        // Required empty public constructor
    }

    public static AboutUsFragment newInstance(String param1, String param2) {
        AboutUsFragment fragment = new AboutUsFragment();
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

        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        anieshL = view.findViewById(R.id.linkedin_button1);
        anieshG = view.findViewById(R.id.github_button1);
        puneethL = view.findViewById(R.id.linkedin_button2);
        puneethG = view.findViewById(R.id.github_button2);
        rionL = view.findViewById(R.id.linkedin_button3);
        rionG = view.findViewById(R.id.github_button3);
        website = view.findViewById(R.id.website);


        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://stress-away-web-app.vercel.app/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        anieshL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://www.linkedin.com/in/aniesh-reddy-gundam-016365232/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        anieshG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://www.linkedin.com/in/aniesh-reddy-gundam-016365232/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        puneethL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://www.linkedin.com/in/puneeth-reddy-75069824b/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        puneethG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://github.com/puneethx");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        rionL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://www.linkedin.com/in/rion-dsilva-043464229/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        rionG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse("https://github.com/RionDsilvaCS");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });



        return view;
    }
}