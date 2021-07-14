package com.hritik.oondhachashma.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hritik.oondhachashma.Adapters.StoryAdapter;
import com.hritik.oondhachashma.Model.Story;
import com.hritik.oondhachashma.R;
import com.hritik.oondhachashma.Assets.StoryData;

import java.util.ArrayList;
import java.util.Collections;


public class BrowseFragment extends Fragment {
    RecyclerView recyclerView;
    StoryAdapter storyAdapter;
    ArrayList<Story> stories;
    StoryData storyData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbarbtnsmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search){
            Toast.makeText(getActivity(),"Browse Search",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getActivity();
        storyData = new StoryData();

        stories=storyData.getStoriesDataList();

        recyclerView = view.findViewById(R.id.browse_rc);
        Collections.shuffle(stories);
        storyAdapter = new StoryAdapter(context, stories);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(storyAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        storyAdapter.notifyDataSetChanged();
    }
}