package com.example.stressaway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    ImageView journalIcon, profilePic;
    TextView appId, regId, mailId, school, journalCount, name;
    String photoLink;

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
        journalCount = view.findViewById(R.id.journal_count);
        appId = view.findViewById(R.id.app_number);
        regId = view.findViewById(R.id.reg_id);
        mailId = view.findViewById(R.id.mail_id);
        school = view.findViewById(R.id.school);
        name = view.findViewById(R.id.name);
        profilePic = view.findViewById(R.id.profile_pic);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();


        db.collection("student-details").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @SuppressLint("NotifyDataSetChanged")
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                if(d.getString("reg-number").equals("21BCE8083")){
                                    name.setText(d.getString("name"));
                                    appId.setText(d.getString("app-number"));
                                    regId.setText(d.getString("reg-number"));
                                    mailId.setText(d.getString("e-mail"));
                                    school.setText(d.getString("school"));
                                    journalCount.setText(String.valueOf(d.get("num-journal")));
                                    photoLink = d.getString("reg-number");
                                }
                            }
                            DatabaseReference getImage = databaseReference.child("21BCE8083");
                            getImage.addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(
                                                @NonNull DataSnapshot dataSnapshot) {
                                            String link = dataSnapshot.getValue(String.class);
                                            Picasso.get().load(link).into(profilePic);
                                        }
                                        @Override
                                        public void onCancelled(
                                                @NonNull DatabaseError databaseError) {
                                            Toast.makeText(getActivity(), "Error Loading Image", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {

                            Toast.makeText(getActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {

                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });

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