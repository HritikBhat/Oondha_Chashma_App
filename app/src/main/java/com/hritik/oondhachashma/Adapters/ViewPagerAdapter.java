package com.hritik.oondhachashma.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hritik.oondhachashma.Fragments.BrowseFragment;
import com.hritik.oondhachashma.Fragments.FavouritesFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 2;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BrowseFragment();
            case 1:
                return new FavouritesFragment();
        }
        return new BrowseFragment();
    }

    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}
