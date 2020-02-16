package com.example.reliance.real_time_crime_monitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class googlemap extends AppCompatActivity implements OnMapReadyCallback {


   Location currentlocation;
   FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment supportMapFragment;
    Button mpbtn;
    private GoogleMap mMap;
    private ChildEventListener mChildEventListener;
    private DatabaseReference mUsers;
    Marker marker;
    private static final int  REQUEST_CODE=01;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        mpbtn=(Button)findViewById(R.id.mapbtn);
        FirebaseApp.initializeApp(this);
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        ChildEventListener mChildEventListener;
        mUsers = FirebaseDatabase.getInstance().getReference().child("Test");

    }
    private void fetchLastLocation()
    {
        if (ActivityCompat.checkSelfPermission(googlemap.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }




        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>()
        {
            @Override
            public void onSuccess(Location location)
            {
                if(location!=null)
                {
                    currentlocation=location;
                    Toast.makeText(googlemap.this,currentlocation.getLatitude()+" "+currentlocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapAPI);
                    supportMapFragment.getMapAsync(googlemap.this);


//
                }

            }
        });
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {



//        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//                mUsers.addListenerForSingleValueEvent(new ValueEventListener()
//                {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                    {
//
//                        for (DataSnapshot s : dataSnapshot.getChildren()){
//                            make_public makepublic12 = s.getValue(make_public.class);
//////
//                    boolean ans=makepublic12.getVal();
//////
////                    if(ans==true)
//                           latilongi user = s.getValue(latilongi.class);
//                            LatLng location = new LatLng(user.getLatitude(),user.getLongitude());
//                            mMap.addMarker(new MarkerOptions().position(location));
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,10));
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                return true;
//            }
//        });

        LatLng latLng=new LatLng(currentlocation.getLatitude(),currentlocation.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("IIT dharwad");
////        LatLng latLng2=new LatLng();

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
        googleMap.addMarker(markerOptions);

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        mUsers.addListenerForSingleValueEvent(new ValueEventListener( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()){
                     latilongi makepublic12 = s.getValue(latilongi.class);
//
                    boolean ans=makepublic12.isMakepublic();
//
                    if(ans==true)
                    {

                        LatLng latLng2=new LatLng( 15.5197288, 74.923824);
                        googleMap.addMarker(new MarkerOptions().title("Dharwad court Fire 15-02-20 3.30pm").position(latLng2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng2));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2,18));


                        LatLng latLng3=new LatLng( 15.4502803,74.9959935);

                        googleMap.addMarker(new MarkerOptions().title("State Bank Robbery 14-01-2020 12.00am").position(latLng3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng3));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng3,18));

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

  }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_CODE:if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                fetchLastLocation();
            }
                     break;

        }
    }
}



