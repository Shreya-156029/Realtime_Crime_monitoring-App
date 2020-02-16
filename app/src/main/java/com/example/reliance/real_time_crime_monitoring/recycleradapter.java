package com.example.reliance.real_time_crime_monitoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recycleradapter extends  RecyclerView.Adapter<recycleradapter.ViewHolder>
{

  private Context context;
  private ArrayList<images> arrayList;

  public recycleradapter(Context context,ArrayList<images> imagelist)
  {
      this.context=context;
      this.arrayList=imagelist;
  }

    @NonNull
    @Override
    public recycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.setSingletonInstance(new Picasso.Builder(context).build());
        holder.textView.setText(arrayList.get(position).getDescription());
        Picasso.get().load(arrayList.get(position).getUrl())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
  {
      ImageView imageView;
      TextView textView;

      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          imageView=(ImageView)itemView.findViewById(R.id.newimageview);
          textView=(TextView)itemView.findViewById(R.id.newdescription);


      }
  }

}
