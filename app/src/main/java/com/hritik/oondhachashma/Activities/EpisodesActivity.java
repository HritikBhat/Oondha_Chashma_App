package com.hritik.oondhachashma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.hritik.oondhachashma.Adapters.EpisodeAdapter;
import com.hritik.oondhachashma.Adapters.StoryAdapter;
import com.hritik.oondhachashma.Assets.StoryData;
import com.hritik.oondhachashma.R;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StoryData storyData;
    Context context = this;
    EpisodeAdapter episodeAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        storyData = new StoryData();

        Intent intent = getIntent();
        String sid = intent.getStringExtra("sid");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}