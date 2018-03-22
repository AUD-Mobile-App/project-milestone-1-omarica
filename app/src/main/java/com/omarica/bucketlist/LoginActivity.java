package com.omarica.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EMAIL_PW";
    private EditText mEmailField;
    private EditText mPasswordField;
    private FirebaseAuth mAuth;
    private Button loginButton;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailField = findViewById(R.id.emailEditText);
        mPasswordField = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginButton.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                if(!mEmailField.getText().toString().equals("") && !mPasswordField.getText().toString().equals("") ) {
                    loginUser(mEmailField.getText().toString(), mPasswordField.getText().toString());
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                    loginButton.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void loginUser(String email, String password) {

        Log.d(TAG, "signIn:" + email);

        // Login with firebase
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                loginButton.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    Log.d(TAG, "Successfully logged in");

                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    intent.putExtra("User", user.getUid());
                    startActivity(intent);

                } else {

                    Log.w(TAG, "Failed to login", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }

                if (!task.isSuccessful()) {
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}
