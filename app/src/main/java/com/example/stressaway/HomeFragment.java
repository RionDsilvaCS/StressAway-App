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


public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";

    private static final String ARG_PARAM2 = "param2";

    private FragmentOnAttachListener listener;


    private String mParam1;
    private String mParam2;


    ImageView journal, books, music, movie, diet, yoga, location;


    public HomeFragment() {
        super(R.layout.fragment_home);

    }

    public interface FragmentOnAttachListener{
        void onInputHomeSent(CharSequence input);
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        journal = view.findViewById(R.id.journalButton);
        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = "journal";
                listener.onInputHomeSent(input);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromParentFragment(Uri uri);
    }

}