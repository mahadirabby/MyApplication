package com.example.mahadirabby.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {

    RecyclerView mBlogList;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("book_subject");
        mDatabase.keepSynced(true);
        mBlogList = (RecyclerView) findViewById(R.id.mBlogList);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<book_list, mahadi> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<book_list, mahadi>(
                book_list.class,
                R.layout.book_list_layout,
                mahadi.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(mahadi viewHolder, final book_list model, int position) {

                viewHolder.setmName(model.getName());

                viewHolder.mName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(SecondActivity.this, "" + model.getName(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SecondActivity.this, DetailsActivity.class);
                        i.putExtra("KEY", model.getName());
                        startActivity(i);


                    }
                });


            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }


    public static class mahadi extends RecyclerView.ViewHolder {

        View view;
        TextView mName;

        public mahadi(View itemView) {
            super(itemView);

            view = itemView;
            mName = (TextView) view.findViewById(R.id.mName);

        }


        public void setmName(String name) {
            TextView mmName = (TextView) view.findViewById(R.id.mName);
            mmName.setText(name);
        }
    }
}
