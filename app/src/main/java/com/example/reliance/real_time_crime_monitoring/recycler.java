package com.example.reliance.real_time_crime_monitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recycler extends AppCompatActivity {

    public static final String TAG=".recycler";
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    StorageReference storageReference;
    private ArrayList<images> imagesArrayList;
    private Context context=recycler.this;
    recycleradapter rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rakshak");
        Log.d(TAG,"Oncreate started");
        FirebaseApp.initializeApp(this);
        recyclerView=(RecyclerView)findViewById(R.id.newrecycler);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        storageReference=FirebaseStorage.getInstance().getReference();
        imagesArrayList=new ArrayList<>();
        init();




    }
    public void init()
    {
//        clearAll();
//        Query query
        Query query=databaseReference.child("Posts");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        images img = new images();
                        img.setUrl(snapshot.child("imageUrl").getValue().toString());
                        img.setDescription(snapshot.child("description").getValue().toString());
                        imagesArrayList.add(img);
                    }
                    rec = new recycleradapter(context, imagesArrayList);
                    recyclerView.setAdapter(rec);
                    rec.notifyDataSetChanged();
                }

                catch (IllegalStateException e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void clearAll()
    {
        if(imagesArrayList!=null)
        {
            imagesArrayList.clear();
        }
        if(rec!=null)
        {
            rec.notifyDataSetChanged();
        }
        imagesArrayList=new ArrayList<>();
    }
}
