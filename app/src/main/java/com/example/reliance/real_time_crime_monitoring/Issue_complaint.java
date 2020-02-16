package com.example.reliance.real_time_crime_monitoring;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
//import androidx.support.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.snapshot.ChildKey;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Issue_complaint extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btnchoose, btnupload,btnsubmit;
    EditText editText,edittext2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref1;
    User user;
    static int index = 2;
    public  double lat,longi;

    private FusedLocationProviderClient client;

    FirebaseStorage storage;
      StorageReference ref; //ref used for img_upload

    public final int PICK_IMAGE_REQUEST = 1;
     long str=System.currentTimeMillis();

    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_complaint);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rakshak");

        FirebaseApp.initializeApp(this);
        client = LocationServices.getFusedLocationProviderClient(this);
        btnchoose = (Button) findViewById(R.id.btnchoose);
        btnupload = (Button) findViewById(R.id.btnupload);


        spinner = findViewById(R.id.spinner);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        editText = (EditText) findViewById(R.id.edit_desc);
        edittext2=(EditText)findViewById(R.id.contact);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        Firebase.setAndroidContext(this.getApplicationContext());
        firebaseDatabase=FirebaseDatabase.getInstance(); //new
        ref1=firebaseDatabase.getReference().child("Crime_type_desc");
//        user=new User();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.crime_incidents, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                uploadImage();
//
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                insertData(v);




//                uploadImage();
                if (ActivityCompat.checkSelfPermission(Issue_complaint.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
                {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>()
                {
                    @Override
                    public void onSuccess(Location location)
                    {

                        if(location!=null)
                        {
                            lat=location.getLatitude();
                            longi=location.getLongitude();
//                            Toast.makeText(Issue_complaint.this,"lat"+lat,Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Issue_complaint.this,"lat"+longi,Toast.LENGTH_SHORT).show();

                            ref1.child("str").child("longitude").setValue(longi);
                            ref1.child("str").child("latitude").setValue(lat);
                            String out=location.toString();
//                            Toast.makeText(Issue_complaint.this,out,Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }

        });

    }
    private void getValues()
    {
//      Location location=new Location("gps");
        ref1.child("str").child("contact").setValue(edittext2.getText().toString());
        ref1.child("str").child("description").setValue(editText.getText().toString());
        ref1.child("str").child("type").setValue(spinner.getSelectedItem().toString());
//        user.setDesc(editText.getText().toString());
//        user.setType(spinner.getSelectedItem().toString());
//        user.setLongitude(location.getLongitude());
//        user.setLatitude(location.getLatitude());
//        user.setContact(edittext2.getText().toString());
//        user.setLatitude(123.33);
//        user.setLongitude(99.90);


    }
    public void insertData(View view)
    {
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getValues();
//                ref1.child("01").setValue(user);


                Toast.makeText(Issue_complaint.this,"Data inserted successfully",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public  void uploadImage()
    {
        if (filepath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("uploading");
            progressDialog.show();
            StorageReference reference = ref.child("images/" + UUID.randomUUID().toString());
            reference.putFile(filepath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override

                public void onComplete( Task<UploadTask.TaskSnapshot> task) {
                    progressDialog.dismiss();
                    Toast.makeText(Issue_complaint.this, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure( Exception e) {
                    Toast.makeText(Issue_complaint.this, "Failed to upload", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploading");
                }
            });

        }
    }
    public  void chooseImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Choose Picture"),PICK_IMAGE_REQUEST);


    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data.getData()!=null && data!=null)

        {
            filepath=data.getData();
            try
            {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
//                imgview.setImageBitmap(bitmap);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        index++;
    }

}

