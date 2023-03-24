package com.example.stressaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PreviousJournalActivity extends AppCompatActivity {

    String content, date;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_journal);
//        FirebaseApp.initializeApp(this);

        back_button = findViewById(R.id.back_button);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView journalHistory = findViewById(R.id.journalHistory);

        ArrayList<JournalCard> JournalCardArrayList = new ArrayList<JournalCard>();
        JournalCardArrayList.add(new JournalCard("Hello good morning", "15 March"));

        db.collection("journal").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                content = d.getString("content");
                                date = d.getString("date");
                                JournalCardArrayList.add(new JournalCard(content, date));
                            }

                            JournalAdapter journalAdapter = new JournalAdapter (PreviousJournalActivity.this, JournalCardArrayList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PreviousJournalActivity.this, LinearLayoutManager.VERTICAL, false);
                            journalHistory.setLayoutManager(linearLayoutManager);
                            journalHistory.setAdapter(journalAdapter);

                        } else {

                            Toast.makeText(PreviousJournalActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {

                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PreviousJournalActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
//        JournalAdapter journalAdapter = new JournalAdapter (PreviousJournalActivity.this, JournalCardArrayList);
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PreviousJournalActivity.this, LinearLayoutManager.VERTICAL, false);
//                            journalHistory.setLayoutManager(linearLayoutManager);
//                            journalHistory.setAdapter(journalAdapter);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}