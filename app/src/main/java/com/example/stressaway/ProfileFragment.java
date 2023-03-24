package com.example.stressaway;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    ImageView journalIcon;
    TextView journalCountText;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        journalIcon = view.findViewById(R.id.journal_icon);
        journalCountText = view.findViewById(R.id.journal_count);

//        String journalCount;
//        if(journalAdapter.getItemCount() != 0){
//            journalCount = String.valueOf(journalAdapter.getItemCount());
//            journalCountText.setText(journalCount);
//        }

        journalIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PreviousJournalActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}