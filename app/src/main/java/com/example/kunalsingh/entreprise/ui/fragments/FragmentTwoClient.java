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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two_client,container,false);

        HostGetAllItemsService hostGetAllItemsService = ApiUtils.getHostAllItemsService();

        Log.d(TAG,"first");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_file",0);
        String s = sharedPreferences.getString("access_token","");
        if(!s.equals("")){
            Log.d(TAG,"second");
            final Observable<Result> observable = hostGetAllItemsService.getAllItemsHost(s);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result value) {

                            Toast.makeText(getContext(), "see: "+value.getArrayData(), Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG,e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                                    observable.unsubscribeOn(Schedulers.io());
                        }
                    });
        }else{
            Log.d(TAG,"third");
        }



        Log.d(TAG,"fourth");
        return view;
    }

}
