package com.hritik.oondhachashma.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hritik.oondhachashma.Activities.EpisodesActivity;
import com.hritik.oondhachashma.Database.DBInterface;
import com.hritik.oondhachashma.Database.TMKOCDatabase;
import com.hritik.oondhachashma.Database.Tables.Favorites;
import com.hritik.oondhachashma.Model.Story;
import com.hritik.oondhachashma.R;

import java.util.ArrayList;
import java.util.List;

public class FavStoryAdapter extends RecyclerView.Adapter<FavStoryAdapter.ViewHolder>{
    private ArrayList<Favorites> cListFiltered=new ArrayList<>();
    Context context;
    DBInterface dbInterface;
    TMKOCDatabase myAppDatabase;

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    // RecyclerView recyclerView;
    public FavStoryAdapter(Context context, List<Favorites> cList) {

        this.cListFiltered.addAll(cList);
        this.context= context;
        myAppDatabase = Room.databaseBuilder(context, TMKOCDatabase.class,"tmkoc").allowMainThreadQueries().build();
        dbInterface =myAppDatabase.myDao();
        //Log.i("Array check",""+cList.size());
    }

    @Override
    public FavStoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.favlist_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        Favorites content = cListFiltered.get(position);
        holder.name_tv.setText(content.getSname());


        Glide.with(context)
                .load(content.getUrl())
                .centerCrop()
                .override(300,200)
                .listener(new RequestListener<Drawable>() {


                              @Override
                              public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                  holder.progressBar.setVisibility(View.INVISIBLE);
                                  return false;
                              }
                          }
                )
                .into(holder.st_img);

        holder.st_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), EpisodesActivity.class);
                intent.putExtra("sid",content.getSid());
                intent.putExtra("sname",content.getSname());
                context.startActivity(intent);
            }
        });

        holder.remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbInterface.deleteFav(content.getSid());
                cListFiltered.remove(position);
                notifyDataSetChanged();
            }
        });



    }

    public void updateList(ArrayList<Favorites> list){
        cListFiltered = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return cListFiltered.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name_tv;
        public ImageView st_img;
        public Button st_btn;
        public ImageButton remove_btn;
        public ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name_tv = itemView.findViewById(R.id.epname);
            this.st_img = itemView.findViewById(R.id.storyImage);
            this.st_btn = itemView.findViewById(R.id.watchbtn);
            this.remove_btn = itemView.findViewById(R.id.removeBtn);
            this.progressBar = itemView.findViewById(R.id.floader);
        }
    }
}
