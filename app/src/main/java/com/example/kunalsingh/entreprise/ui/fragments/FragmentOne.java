package com.example.kunalsingh.entreprise.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kunalsingh.entreprise.R;

/**
 * Created by kunalsingh on 21/06/17.
 */

public class FragmentOne extends Fragment {

    public FragmentOne() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one_client,container,false);

        return view;
    }
}
