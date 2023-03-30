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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class MentorDetailsFragment extends Fragment {

    ImageView mailButton, chatButton;
    TextView facultyId, school, cabinNo, facultyDept, mentorName;

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

        facultyId = view.findViewById(R.id.faculty_id);
        school = view.findViewById(R.id.school_def);
        cabinNo = view.findViewById(R.id.cabin_no);
        facultyDept = view.findViewById(R.id.department_def);
        mentorName = view.findViewById(R.id.mentor_name);


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("mentor-details").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @SuppressLint("NotifyDataSetChanged")
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                if(d.getString("student-1").equals("21BCE8083")){
                                    mentorName.setText(d.getString("name"));
                                    facultyId.setText(d.getString("id"));
                                    school.setText(d.getString("school"));
                                    cabinNo.setText(d.getString("cabin"));
                                    facultyDept.setText(d.getString("department"));
                                }
                            }
                        } else {

                            Toast.makeText(getActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {

                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });

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