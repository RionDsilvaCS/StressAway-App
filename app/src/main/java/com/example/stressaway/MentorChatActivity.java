package com.example.stressaway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MentorChatActivity extends AppCompatActivity {
    ImageView backButton, mailButton;
    ClipboardManager clipboard = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_chat);
        backButton = findViewById(R.id.back_button);
        mailButton = findViewById(R.id.mail_button);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        ClipData clip = ClipData.newPlainText("mail", "rion.21bce8083@vitapstudent.ac.in");

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipboard.setPrimaryClip(clip);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MentorChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}