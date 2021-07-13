package com.hritik.oondhachashma.Adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
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
                .into(holder.st_img);

        holder.st_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYoutubeVideo(context,content.getEvidid());
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
                    favorites.setEvidid(content.getEvidid());
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
        public ViewHolder(View itemView) {
            super(itemView);
            this.name_tv = itemView.findViewById(R.id.epname);
            this.st_img = itemView.findViewById(R.id.storyImage);
            this.st_btn = itemView.findViewById(R.id.watchbtn);
            this.fav_btn = itemView.findViewById(R.id.favoriteBtn);
        }
    }
}

