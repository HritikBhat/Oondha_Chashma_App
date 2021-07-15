package com.hritik.oondhachashma.Fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    EditText searchBar;
    boolean isSearchVisible=false;

    void filter(String text){
        ArrayList<Story> temp = new ArrayList();
        for(Story d: stories){
            if(d.getSname().toLowerCase().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        storyAdapter.updateList(temp);
    }

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


    void animateView(View view,float alpha,float yValues){
        ObjectAnimator move=ObjectAnimator.ofFloat(view, "translationY",yValues);
        move.setDuration(2500);
        ObjectAnimator alpha1=ObjectAnimator.ofFloat(view, "alpha",alpha);
        alpha1.setDuration(1000);
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.play(alpha1).with(move);
        animatorSet.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search){
            if (isSearchVisible){
                //searchBar.setVisibility(View.INVISIBLE);
                animateView(searchBar,0f,-20f);
                isSearchVisible=false;
            }
            else {
                //searchBar.setVisibility(View.VISIBLE);
                animateView(searchBar,1f,20f);
                isSearchVisible=true;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getActivity();
        storyData = new StoryData();

        stories=storyData.getStoriesDataList();

        searchBar = (EditText) view.findViewById(R.id.searchBarEditText);
        searchBar.setAlpha(0f);

        recyclerView = view.findViewById(R.id.browse_rc);
        Collections.shuffle(stories);
        storyAdapter = new StoryAdapter(context, stories);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(storyAdapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        isSearchVisible=false;
        searchBar.setAlpha(0f);
    }

    @Override
    public void onResume() {
        super.onResume();
        storyAdapter.notifyDataSetChanged();
    }
}