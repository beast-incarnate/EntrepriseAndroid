package com.example.kunalsingh.entreprise.ui.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kunalsingh.entreprise.ui.fragments.FragmentOneClient;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentOneSeller;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentThreeClient;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentThreeSeller;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentTwoClient;
import com.example.kunalsingh.entreprise.ui.fragments.FragmentTwoSeller;

/**
 * Created by kunalsingh on 21/06/17.
 */

public class Pager extends FragmentPagerAdapter {

    private String[] tabs ;
    private Context mContext;
    private static final String TAG = "Pager";
    private static final String MY_FILE = "my_file";

    public Pager(FragmentManager fm, String[] tabs , Context mContext) {
        super(fm);
        this.tabs = tabs;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_FILE,Context.MODE_PRIVATE);
        int selector = sharedPreferences.getInt("selector",0);
        switch (position){
            case 0:
                if(selector==1)
                return new FragmentOneClient();
                else if(selector==2)
                    return new FragmentOneSeller();
                else
                    return null;

            case 1 :
                if(selector==1)
                    return new FragmentTwoClient();
                else if(selector==2)
                    return new FragmentTwoSeller();
                else
                    return null;

            case 2 :
                if(selector==1)
                    return new FragmentThreeClient();
                else if(selector==2)
                    return new FragmentThreeSeller();
                else
                    return null;

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
