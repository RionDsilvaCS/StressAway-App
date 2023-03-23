package com.example.stressaway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class JournalActivity extends AppCompatActivity {

    GridView previewJournal;
    String countries[]={"Algeria", "Argentina", "Australia", "Brazil", "Cote d'Ivoire", "Cameroon", "India","Chile",

            "Costa Rica", "Denmark", "England", "France", "Germany", "Ghana", "Greece", "Honduras", "Italy", "Japan",

            "Netherlands", "New Zealand", "Nigeria", "North Korea", "Paraguay", "Portugal","Serbia", "Slovakia", "Slovenia",

            "South Africa", "South Korea", "Spain", "Switzerland", "United States", "Uruguay" };

    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        previewJournal = findViewById(R.id.previewJournal);
        back_button = findViewById(R.id.back_button);

        previewJournal.setAdapter((new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries)));

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}