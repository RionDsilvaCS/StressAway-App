package com.example.stressaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity<EmailPasswordActivity> extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button signUp;
    EditText emailBox, passwordBox, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.sign_up);
        emailBox = findViewById(R.id.email);
        passwordBox = findViewById(R.id.password);
        username = findViewById(R.id.username);

        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailBox.getText().toString();
                String password = passwordBox.getText().toString();
                String check = email.substring(email.lastIndexOf('@') + 1);
//                Toast.makeText(getApplicationContext(),check,Toast.LENGTH_SHORT).show();

                if(check.equals("vitapstudent.ac.in")  && password.length() >= 8){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
    }
}