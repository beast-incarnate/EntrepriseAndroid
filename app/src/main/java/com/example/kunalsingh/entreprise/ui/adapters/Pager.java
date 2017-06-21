package com.example.kunalsingh.entreprise.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.kunalsingh.entreprise.ui.fragments.FragmentOneClient;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentThreeClient;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentTwoClient;

/**
 * Created by kunalsingh on 21/06/17.
 */

public class Pager extends FragmentPagerAdapter {

    private String[] tabs ;

    private static final String TAG = "Pager";

    public Pager(FragmentManager fm, String[] tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentOneClient();

            case 1 :
                Log.d(TAG,"came in two");
                return  new FragmentTwoClient();

            case 2 :
                return new FragmentThreeClient();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
