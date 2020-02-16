package com.example.reliance.real_time_crime_monitoring;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class dialog2 extends  AppCompatDialogFragment {




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Help").setMessage("Issue complaint:-User can issue a complaint by giving details of crime incident  " +
                "Hitmap:-User can view nearby crime locations"+
                "View posts:-User can view posts about crime incidents in city")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
