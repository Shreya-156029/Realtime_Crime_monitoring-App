package com.example.reliance.real_time_crime_monitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class user_profile_v2 extends AppCompatActivity {

    TextView address,contact,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_v2);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rakshak");
        address=(TextView)findViewById(R.id.profile_address);
        contact=(TextView)findViewById(R.id.profile_contact);
        email=(TextView)findViewById(R.id.profile_email);


    }
}
