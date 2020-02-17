package com.example.reliance.real_time_crime_monitoring;

import android.Manifest;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
//mport android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST = 1;
    public static final String mystr = "New notification";

    private Button btn1, btn2, btn3, btn4;
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference ref1;
//    private FusedLocationProviderClient client;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rakshak");



//        client = LocationServices.getFusedLocationProviderClient(this);
//        if(Build.VERSION.SDK_INT>=Build.VERSION )

        btn1 = (Button) findViewById(R.id.issue_complaint);
        btn2 = (Button) findViewById(R.id.hitmap);
        btn3 = (Button) findViewById(R.id.view_posts);
        btn4 = (Button) findViewById(R.id.getlocationbtn);
//        firebaseDatabase=FirebaseDatabase.getInstance(); //new
//        ref1=firebaseDatabase.getReference().child("Crime_type_desc");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Issue_complaint.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, googlemap.class);
                startActivity(intent);
                displaynotification();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, recycler.class);
                startActivity(intent);

            }
        });


    }

    private void startTrackerService() {
//        startService(new Intent(this, googlem.class));
//        finish();
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            Toast.makeText(MainActivity.this,"Up working",Toast.LENGTH_SHORT).show();
            displaynotification();
            return true;

        }
        return false;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            Toast.makeText(MainActivity.this,"Down working",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_module_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       int id=item.getItemId();

         if(id==R.id.view_pro)
         {
             Intent intent=new Intent(MainActivity.this,user_profile_v2.class);
             startActivity(intent);
             return  false;
         }
        if(id==R.id.update_profile)
        {
            Intent intent=new Intent(MainActivity.this,uploadprofile.class);
            startActivity(intent);
            return  false;
        }
        if(id==R.id.aboutus)
        {
            openDialog();
        }
        if(id==R.id.help)
        {
            openDialog2();
        }
        return  super.onOptionsItemSelected(item);
    }
    public void openDialog2()
    {
        dialog2 dia=new dialog2();
        dia.show(getSupportFragmentManager(),"Help");
    }

    public void openDialog()
    {
       mydialog dialog=new mydialog();
       dialog.show(getSupportFragmentManager(),"Example dialog");
    }
    private void displaynotification()
    {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,mystr).
                setSmallIcon(R.drawable.marker)
                .setContentTitle("hurray it is working")
                .setContentText("Your first notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,builder.build());


    }




}
