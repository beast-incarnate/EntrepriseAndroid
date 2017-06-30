package com.example.kunalsingh.entreprise.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kunalsingh.entreprise.R;

/**
 * Created by kunalsingh on 27/06/17.
 */

public class FragmentOneSeller extends Fragment {

    public FragmentOneSeller() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_seller,null);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
