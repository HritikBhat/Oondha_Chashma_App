package com.hritik.oondhachashma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hritik.oondhachashma.Adapters.EpisodeAdapter;
import com.hritik.oondhachashma.Adapters.StoryAdapter;
import com.hritik.oondhachashma.Assets.StoryData;
import com.hritik.oondhachashma.Database.DBInterface;
import com.hritik.oondhachashma.Database.TMKOCDatabase;
import com.hritik.oondhachashma.R;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StoryData storyData;
    Context context = this;
    EpisodeAdapter episodeAdapter;
    Toolbar toolbar;
    DBInterface dbInterface;
    TMKOCDatabase myAppDatabase;
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        myAppDatabase = Room.databaseBuilder(context, TMKOCDatabase.class,"tmkoc").allowMainThreadQueries().build();
        dbInterface =myAppDatabase.myDao();

        storyData = new StoryData();

        Intent intent = getIntent();
        sid = intent.getStringExtra("sid");
        String name = intent.getStringExtra("sname");

        toolbar = findViewById(R.id.toolbar_ep);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.episode_rc);
        episodeAdapter = new EpisodeAdapter(context,storyData.getStoryIndexAt(Integer.parseInt(sid)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(episodeAdapter);
    }

    void setPointer(){
        episodeAdapter.setInd_pos();
        episodeAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.reset :
                dbInterface.deletePointer(sid);
                setPointer();
                Toast.makeText(getApplicationContext(), "Pointer got reset",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPointer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.episodetoolbarbtnsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}