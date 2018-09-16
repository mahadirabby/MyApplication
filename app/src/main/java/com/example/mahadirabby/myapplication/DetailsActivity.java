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

public class DetailsActivity extends AppCompatActivity {

    RecyclerView mBlogList2;
    DatabaseReference mDatabase;
    TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        String data = getIntent().getStringExtra("KEY");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("category").child(data);
        mDatabase.keepSynced(true);
        mBlogList2 = (RecyclerView) findViewById(R.id.mBlogList2);
        mBlogList2.setHasFixedSize(true);
        mBlogList2.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<details, mahadiDetails> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<details, mahadiDetails>(
                details.class,
                R.layout.details_design,
                mahadiDetails.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(mahadiDetails viewHolder, final details model, int position) {
                viewHolder.setmDetails(model.getName());
                viewHolder.mDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Toast.makeText(DetailsActivity.this, "" + model.getName(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DetailsActivity.this, ThirdActivity.class);
                        i.putExtra("KEY", model.getName());
                        startActivity(i);


                    }
                });
            }
        };

        mBlogList2.setAdapter(firebaseRecyclerAdapter);

    }


    public static class mahadiDetails extends RecyclerView.ViewHolder {

        View view;
        TextView mDetails;

        public mahadiDetails(View itemView) {
            super(itemView);
            view = itemView;
            mDetails = (TextView) view.findViewById(R.id.mDetails);
        }

        public void setmDetails(String details) {
            TextView mmDetails = (TextView) view.findViewById(R.id.mDetails);
            mmDetails.setText(details);
        }


    }
}
