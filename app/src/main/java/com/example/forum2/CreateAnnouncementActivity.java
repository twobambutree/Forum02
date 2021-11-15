package com.example.forum2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forum2.Models.Announcement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class CreateAnnouncementActivity extends AppCompatActivity {

    Button submitButton;
    Button clearButton;
    EditText enterSubject;
    EditText enterDetail;

    private DatabaseReference reference;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_announcement_01);

        submitButton = findViewById(R.id.submitButton);
        clearButton = findViewById(R.id.clearButton);
        enterSubject = findViewById(R.id.enterSubject);
        enterDetail = findViewById(R.id.enterDetail);


        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Announcements");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                String subjectEnter = enterSubject.getText().toString();
                String detailEnter = enterDetail.getText().toString();
                int curDate = (int) (new Date().getTime()/1000);

                if (subjectEnter.isEmpty()) {
                    enterSubject.setError("The subject is required!");
                    enterSubject.requestFocus();
                }

                if (detailEnter.isEmpty()) {
                    enterDetail.setError("The discription is required!");
                    enterDetail.requestFocus();
                }

                mAuth.createUserWithEmailAndPassword(subjectEnter, detailEnter)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Announcement announcement = new Announcement(subjectEnter, detailEnter, curDate);

                                    FirebaseDatabase.getInstance().getReference("Announcement")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(announcement).addOnCompleteListener(new OnCompleteListener<Void>(){

                                        @Override
                                        public void onComplete(@NonNull Task<Void> Task){
                                            if (task.isSuccessful()) {
                                                Toast.makeText(CreateAnnouncementActivity.this, "An announcerment has been added!", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(CreateAnnouncementActivity.this, SecondFragment.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(CreateAnnouncementActivity.this, "Failled to to add an announcement!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                                }else{
                                    Toast.makeText(CreateAnnouncementActivity.this, "Failled to register user!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            }
        });

//        loginButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });

//        createButton2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                String emailEnter = enterEmail2.getText().toString();
//                String passwordEnter = enterPassword2.getText().toString();
//                String nameEnter = enterName.getText().toString();
//
//                if (nameEnter.isEmpty()){
//                    enterName.setError("Your name is required!");
//                    enterName.requestFocus();
//                }
//
//                if (emailEnter.isEmpty()){
//                    enterEmail2.setError("Your email is required!");
//                    enterEmail2.requestFocus();
//                    return;
//                }
//
//                if (passwordEnter.isEmpty()){
//                    enterPassword2.setError("You need to set a password!");
//                    enterPassword2.requestFocus();
//                    return;
//                }
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(emailEnter).matches()){
//                    enterEmail2.setError("Please provide a valid email! ");
//                    enterEmail2.requestFocus();
//                    return;
//                }
//
//                if (passwordEnter.length() < 6){
//                    enterPassword2.setError("Password length is to short. Needs to be more than 6 characters!");
//                    enterPassword2.requestFocus();
//                    return;
//                }
//
//                mAuth.createUserWithEmailAndPassword(emailEnter, passwordEnter)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//
//                            User user = new User(emailEnter, nameEnter);
//
//                            FirebaseDatabase.getInstance().getReference("User")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
//
//                                @Override
//                                public void onComplete(@NonNull Task<Void> Task){
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(CreateAccountActivity.this, "Account has been created!", Toast.LENGTH_LONG).show();
//                                        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
//                                        startActivity(intent);
//                                    } else {
//                                        Toast.makeText(CreateAccountActivity.this, "Failled to register user!", Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//
//                        }else{
//                            Toast.makeText(CreateAccountActivity.this, "Failled to register user!", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                });
//            }
//        });

    }
}