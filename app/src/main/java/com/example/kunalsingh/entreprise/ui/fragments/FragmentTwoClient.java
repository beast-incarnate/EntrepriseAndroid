package com.example.kunalsingh.entreprise.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.api.service.ApiUtils;
import com.example.kunalsingh.entreprise.api.service.HostGetAllItemsService;
import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kunalsingh on 21/06/17.
 */

public class FragmentTwoClient extends Fragment {

    private static final String TAG = "FragmentTwoClient";
    public FragmentTwoClient() {
    }
    private Observable<Result> observable;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two_client,container,false);
        return view;
    }


    @Override
    public void onDestroyView() {
        if(observable!=null)
            observable.unsubscribeOn(Schedulers.io());
        super.onDestroyView();
    }
}
