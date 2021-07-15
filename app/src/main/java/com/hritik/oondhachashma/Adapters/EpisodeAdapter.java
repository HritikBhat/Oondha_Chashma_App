package com.hritik.oondhachashma.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.hritik.oondhachashma.Activities.EpisodesActivity;
import com.hritik.oondhachashma.Database.DBInterface;
import com.hritik.oondhachashma.Database.TMKOCDatabase;
import com.hritik.oondhachashma.Database.Tables.Pointer;
import com.hritik.oondhachashma.Model.Story;
import com.hritik.oondhachashma.R;

import java.util.ArrayList;


public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder>{
    private ArrayList<String> cListFiltered=new ArrayList<>();
    Context context;
    DBInterface dbInterface;
    TMKOCDatabase myAppDatabase;
    Story story;
    int ind_pos;

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

    public EpisodeAdapter(Context context, Story story) {
        this.story=story;
        this.cListFiltered = story.getEvidid();
        this.context = context;
        this.myAppDatabase = Room.databaseBuilder(context, TMKOCDatabase.class,"tmkoc").allowMainThreadQueries().build();
        this.dbInterface =myAppDatabase.myDao();
        if(dbInterface.checkPointer(String.valueOf(story.getSid()))){
            this.ind_pos=Integer.parseInt(dbInterface.getPointer(String.valueOf(story.getSid())).getPos());
        }
        else{
            this.ind_pos=-1;
        }

    }

    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.ep_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    public void setInd_pos(){
        if(dbInterface.checkPointer(String.valueOf(story.getSid()))){
            this.ind_pos=Integer.parseInt(dbInterface.getPointer(String.valueOf(story.getSid())).getPos());
        }
        else{
            this.ind_pos=-1;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.imageView.setVisibility(View.INVISIBLE);
        String content = cListFiltered.get(position);
        holder.name_tv.setText("Episode "+(position+1));


        if(ind_pos==position){
            holder.imageView.setVisibility(View.VISIBLE);
        }

        holder.ep_cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dbInterface.checkPointer(String.valueOf(story.getSid()))){
                    Pointer pointer = dbInterface.getPointer(String.valueOf(story.getSid()));
                    pointer.setPos(String.valueOf(position));
                    dbInterface.updatePointer(pointer);
                }
                else{
                    Pointer pointer = new Pointer();
                    pointer.setSid(String.valueOf(story.getSid()));
                    pointer.setPos(String.valueOf(position));
                    dbInterface.addPointer(pointer);
                }


                watchYoutubeVideo(context,content);
            }
        });
    }

    public void updateList(ArrayList<String> list){
        cListFiltered = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return cListFiltered.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name_tv;
        public ImageView imageView;
        public CardView ep_cd;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.showPointer);
            this.name_tv = itemView.findViewById(R.id.ep_tv);
            this.ep_cd= itemView.findViewById(R.id.ep_card);
        }
    }
}
