package com.example.reliance.real_time_crime_monitoring;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

   View mview;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mview=itemView;


    }
    public  void setDetails(Context ctx,String description,String image)
    {

        TextView mdetailview=mview.findViewById(R.id.description_tv);
        ImageView img=mview.findViewById(R.id.rImageview);

        mdetailview.setText(description);
        Picasso.get().load(image).into(img);

    }

}
