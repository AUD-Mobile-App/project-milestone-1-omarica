package com.omarica.bucketlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Intent intent = getIntent();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Toast.makeText(this, user.getUid(), Toast.LENGTH_SHORT).show();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(user.getUid());


    }
}
