package com.hritik.oondhachashma.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hritik.oondhachashma.R;
import com.hritik.oondhachashma.Adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;


    public static void watchYoutubeVideo(Context context, String id){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(createCardAdapter());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Tab " + (position + 1));
                        switch (position) {
                            case 0:
                                tab.setText("Browse");
                                break;
                            case 1:
                                tab.setText("Favourites");
                        }
                    }
                }).attach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.search:
            //add the function to perform here
            return(true);
        case R.id.aboutUs:
            watchYoutubeVideo(getApplicationContext(),"5_zRun2jd2M");
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbarbtnsmenu, menu);
        return true;
    }




    private ViewPagerAdapter createCardAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        return adapter;
    }
}