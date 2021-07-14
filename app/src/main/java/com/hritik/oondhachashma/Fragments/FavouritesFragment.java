package com.hritik.oondhachashma.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hritik.oondhachashma.Adapters.FavStoryAdapter;
import com.hritik.oondhachashma.Adapters.StoryAdapter;
import com.hritik.oondhachashma.Database.DBInterface;
import com.hritik.oondhachashma.Database.TMKOCDatabase;
import com.hritik.oondhachashma.Database.Tables.Favorites;
import com.hritik.oondhachashma.Model.Story;
import com.hritik.oondhachashma.R;
import com.hritik.oondhachashma.Assets.StoryData;

import java.util.ArrayList;
import java.util.List;


public class FavouritesFragment extends Fragment {

    RecyclerView recyclerView;
    FavStoryAdapter storyAdapter;
    List<Favorites> favoritesList;
    DBInterface dbInterface;
    TMKOCDatabase myAppDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
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
            Toast.makeText(getActivity(),"Favorite Search",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getActivity();
        assert context != null;
        myAppDatabase = Room.databaseBuilder(context, TMKOCDatabase.class,"tmkoc").allowMainThreadQueries().build();
        dbInterface =myAppDatabase.myDao();

        favoritesList=dbInterface.getFavorites();


        recyclerView = view.findViewById(R.id.favorite_rc);
        storyAdapter = new FavStoryAdapter(context,favoritesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(storyAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        favoritesList=dbInterface.getFavorites();
        storyAdapter = new FavStoryAdapter(getActivity(),favoritesList);
        recyclerView.setAdapter(storyAdapter);
    }
}