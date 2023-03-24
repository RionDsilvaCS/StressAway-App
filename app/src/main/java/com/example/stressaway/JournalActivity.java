package com.example.stressaway;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class JournalActivity extends AppCompatActivity {

    TextView previewJournal;
    Button save;
    EditText contentBox;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        previewJournal = findViewById(R.id.previous_journal);
        back_button = findViewById(R.id.back_button);
        save = findViewById(R.id.save_journal_button);
        contentBox = findViewById(R.id.journal_text);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = contentBox.getText().toString();

                if(content.matches("")){
                    Toast.makeText(getApplicationContext(),"Are you not in the mood of writing", Toast.LENGTH_SHORT).show();
                }else{
                    String date = "24 March";
                    Map<String, Object> data = new HashMap<>();
                    data.put("content", content);
                    data.put("date", date);
                    Toast toast = Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);
                    db.collection("journal")
                            .add(data)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                    toast.show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        previewJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PreviousJournalActivity.class);
                startActivity(intent);
            }
        });

    }
}