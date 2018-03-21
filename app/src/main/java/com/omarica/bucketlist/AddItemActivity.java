package com.omarica.bucketlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {

    EditText nameEditText, descriptionEditText;
    Button addButton;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEditText = findViewById(R.id.itemNameEditText);
        descriptionEditText = findViewById(R.id.itemDescriptionEditText);
        addButton = findViewById(R.id.addButton);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(user.getUid()).push();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child("name").setValue(nameEditText.getText().toString());
                myRef.child("description").setValue(descriptionEditText.getText().toString());
                myRef.child("status").setValue(false);
                myRef.child("location").setValue("Lat,Lng");
                myRef.child("imgUrl").setValue("https://cdn-4.nikon-cdn.com/e/Q5NM96RZZo-YRYNeYvAi9beHK4x3L-8h09FYyKWnWU6L2l14O7STBw==/Views/1585_D850_front.png");

                finish();
            }
        });


    }
}
