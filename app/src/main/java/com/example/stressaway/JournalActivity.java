package com.example.stressaway;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

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

        Date currentTime = Calendar.getInstance().getTime();

        previewJournal = findViewById(R.id.previous_journal);
        back_button = findViewById(R.id.back_button);
        save = findViewById(R.id.save_journal_button);
        contentBox = findViewById(R.id.journal_text);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = contentBox.getText().toString();
                String email = user.getEmail();
                int mailLength = email.length();
                int regNoIndex = mailLength - 28;
                String regNo = email.substring(regNoIndex, regNoIndex+9);
                int nameCount = mailLength - 29;
                String name = email.substring(0,nameCount);

                if(content.matches("")){
                    Toast.makeText(getApplicationContext(),"Are you not in the mood of writing", Toast.LENGTH_SHORT).show();

                }
                else{

                    String date = String.valueOf(currentTime);

                    date  = date.substring(0, Math.min(date.length(), 10));
                    String docRef = regNo + date;

                    Map<String, Object> data = new HashMap<>();
                    data.put("content", content);
                    data.put("date", date);
                    data.put("feel", "analyzing...");

                    Toast toast = Toast.makeText(getApplicationContext(), "Good Job " + name, Toast.LENGTH_SHORT);

                    db.collection(regNo).document(docRef)
                            .set(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
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