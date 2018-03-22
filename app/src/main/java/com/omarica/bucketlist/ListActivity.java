package com.omarica.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef ;
    FloatingActionButton fab;
    ArrayList<BucketItem> mBucketItems;
    ListAdapter mListAdapter;
    ListView mListView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Intent intent = getIntent();

        mBucketItems = new ArrayList<>();
        fab = findViewById(R.id.fab);
        mListView = findViewById(R.id.listView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        //Toast.makeText(this, user.getUid(), Toast.LENGTH_SHORT).show();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(user.getUid());
        //myRef.child(user.getUid()).push().child("Camera").setValue("Nikon");
        mListAdapter = new ListAdapter(this, mBucketItems, myRef);
        mListView.setAdapter(mListAdapter);

        /*
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, i + "", Toast.LENGTH_SHORT).show();
            }
        }); */
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getBucketList(dataSnapshot);
                mListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //getBucketList(dataSnapshot);
              //  mListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });



    }

    private void getBucketList(DataSnapshot dataSnapshot) {

        mBucketItems.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            BucketItem item = new BucketItem(ds.getValue(BucketItem.class).getName(),
                    ds.getValue(BucketItem.class).getDescription(),
                    ds.getValue(BucketItem.class).getImgUrl(),
                    ds.getValue(BucketItem.class).getLocation(),
                    ds.getValue(BucketItem.class).isStatus(),
                    ds.getKey(),
                    ds.getValue(BucketItem.class).getDueDate());
            mBucketItems.add(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
