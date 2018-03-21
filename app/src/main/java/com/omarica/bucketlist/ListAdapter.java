package com.omarica.bucketlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by omarica on 3/21/18.
 */

public class ListAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<BucketItem> mBucketItems;
    private DatabaseReference myRef;

    public ListAdapter(Activity context,
                       ArrayList<BucketItem> mBucketItems, DatabaseReference myRef) {
        super(context, R.layout.list_item, mBucketItems);
        this.context = context;
        this.mBucketItems = mBucketItems;
        this.myRef = myRef;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemTextView);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.itemImageView);
        final CheckBox checkBox = rowView.findViewById(R.id.itemCheckbox);

        /*
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                myRef.child(mBucketItems.get(position).getKey()).child("status").setValue(b);
                String message = mBucketItems.get(position).getName()+ " is completed.";

                if(b) {
                    Snackbar.make(context.findViewById(R.id.myCoordinatorLayout), message,
                            Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        }); */

        checkBox.setChecked(mBucketItems.get(position).isStatus());
        txtTitle.setText(mBucketItems.get(position).getName());
        Picasso.get().load(mBucketItems.get(position).getImgUrl()).into(imageView);

        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
