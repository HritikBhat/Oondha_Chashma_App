package com.hritik.oondhachashma.Adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.hritik.oondhachashma.Fragments.BrowseFragment;
import com.hritik.oondhachashma.Model.Story;
import com.hritik.oondhachashma.R;

import java.util.ArrayList;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{
    private ArrayList<Story> cListFiltered;
    Context context;
    DBInterface dbInterface;
    TMKOCDatabase myAppDatabase;

    // RecyclerView recyclerView;
    public StoryAdapter(Context context,ArrayList<Story> cList) {
        this.cListFiltered = cList;
        this.context= context;
        myAppDatabase = Room.databaseBuilder(context, TMKOCDatabase.class,"tmkoc").allowMainThreadQueries().build();
        dbInterface =myAppDatabase.myDao();
        Log.i("Array check",""+cList.size());
    }

    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    private String joinArrayString(ArrayList<String> arr){
        StringBuilder text = new StringBuilder();
        if(arr.size()==0){
            return "None";
        }
        for(String s:arr){
            text.append(s+", ");
        }
        return text.toString().substring(0,text.length()-2);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        Story content = cListFiltered.get(position);
        holder.name_tv.setText(content.getSname());

        content.setFav(dbInterface.checkFav(String.valueOf(content.getSid())));
        boolean isFav = content.getFav();
        if(isFav){
            holder.fav_btn.setImageResource(R.drawable.ic_fav);
        }
        else{
            holder.fav_btn.setImageResource(R.drawable.ic_unfav);
        }

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
                intent.putExtra("sid",String.valueOf(content.getSid()));
                intent.putExtra("sname",String.valueOf(content.getSname()));
                context.startActivity(intent);
            }
        });

        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(content.getFav()){
                    holder.fav_btn.setImageResource(R.drawable.ic_unfav);
                    content.setFav(false);

                    dbInterface.deleteFav(String.valueOf(content.getSid()));
                }
                else{
                    holder.fav_btn.setImageResource(R.drawable.ic_fav);
                    content.setFav(true);

                    Favorites favorites = new Favorites();
                    favorites.setSid(String.valueOf(content.getSid()));
                    favorites.setSname(content.getSname());
                    favorites.setUrl(content.getUrl());
                    dbInterface.addFavorites(favorites);
                }
            }
        });


    }

    public void updateList(ArrayList<Story> list){
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
        public ImageButton fav_btn;
        public ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name_tv = itemView.findViewById(R.id.epname);
            this.st_img = itemView.findViewById(R.id.storyImage);
            this.st_btn = itemView.findViewById(R.id.watchbtn);
            this.fav_btn = itemView.findViewById(R.id.favoriteBtn);
            this.progressBar = itemView.findViewById(R.id.sloader);
        }
    }
}

