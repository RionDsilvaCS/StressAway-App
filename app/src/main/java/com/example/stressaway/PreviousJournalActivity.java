package com.example.stressaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreviousJournalActivity extends AppCompatActivity {

    String content, date, docId;
    ImageView back_button;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<JournalCard> JournalCardArrayList;
    JournalAdapter journalAdapter;
    TextView t1;
    ArrayList<String> id = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_journal);
//        FirebaseApp.initializeApp(this);

        back_button = findViewById(R.id.back_button);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        t1 = findViewById(R.id.textView2);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView journalHistory = findViewById(R.id.journalHistory);

        JournalCardArrayList = new ArrayList<JournalCard>();
        JournalCardArrayList.add(new JournalCard("Hello good morning", "15 March", "Trial"));

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        journalAdapter = new JournalAdapter (PreviousJournalActivity.this, JournalCardArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PreviousJournalActivity.this, LinearLayoutManager.VERTICAL, false);
        journalHistory.setLayoutManager(linearLayoutManager);
        journalHistory.setAdapter(journalAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                db.collection("journal").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                            @SuppressLint("NotifyDataSetChanged")
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                if (!queryDocumentSnapshots.isEmpty()) {

                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d : list) {
                                        if(!id.contains(d.getId())) {
                                            docId = d.getId();
                                            content = d.getString("content");
                                            date = d.getString("date");
                                            JournalCardArrayList.add(new JournalCard(content, date, docId));
                                            journalAdapter.notifyDataSetChanged();
                                            id.add(d.getId());
                                        }
                                    }
                                } else {

                                    Toast.makeText(PreviousJournalActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {

                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PreviousJournalActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                            }
                        });
                Toast.makeText(getApplicationContext(),"Up to date",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

//    private void loadData() {
//
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//
//        Gson gson = new Gson();
//
//        String json = sharedPreferences.getString("array",null);
//
//        Type type = new TypeToken<ArrayList<JournalCard>>() {}.getType();
//
//        JournalCardArrayList = gson.fromJson(json, type);
//
//        if (JournalCardArrayList == null) {
//
//            JournalCardArrayList = new ArrayList<>();
//        }
//    }
//
//    private void saveData() {
//
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        Gson gson = new Gson();
//
//        String json = gson.toJson(JournalCardArrayList);
//
//        editor.putString("array", json);
//
//        editor.apply();
//
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
//    }
//
//    private void deleteData(){
//        SharedPreferences pref = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.clear().commit();
//        Toast.makeText(this, "Deleted Array List", Toast.LENGTH_SHORT).show();
//    }


}