package com.example.reliance.real_time_crime_monitoring;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class View_posts extends AppCompatActivity {

    RecyclerView recyclerView;
     FirebaseDatabase mfirebaseDatabase;
     DatabaseReference mref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);
        FirebaseApp.initializeApp(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Posts");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mfirebaseDatabase.getReference("Posts");
//        onStart();


    }

    protected void onStart() {
        super.onStart();
        Toast.makeText(View_posts.this,"onstart called",Toast.LENGTH_LONG).show();

        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mref

                        )
                {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int i) {
                       if(  model.getDesciption()!=null && model.getImage()!=null) {
                           viewHolder.setDetails(getApplicationContext(), model.getDesciption(), model.getImage());
                           Toast.makeText(View_posts.this,"method called",Toast.LENGTH_LONG).show();
                       }
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}












