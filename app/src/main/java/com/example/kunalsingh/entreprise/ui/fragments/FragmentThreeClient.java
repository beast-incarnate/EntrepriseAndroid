package com.example.kunalsingh.entreprise.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.api.service.ApiUtils;
import com.example.kunalsingh.entreprise.api.service.GetClientHostMapping;
import com.example.kunalsingh.entreprise.models.Host;
import com.example.kunalsingh.entreprise.models.Result;
import com.example.kunalsingh.entreprise.ui.adapters.RecyclerViewAdapterIndex;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kunalsingh on 21/06/17.
 */

public class FragmentThreeClient extends Fragment {

    @BindView(R.id.rv_index_client)
    RecyclerView rvIndex;

    public FragmentThreeClient() {
    }

    private Observable<Result> mObservable;
    private GetClientHostMapping getClientHostMapping;
    private RecyclerViewAdapterIndex rvAdapter;
    private ArrayList<Host> hosts = new ArrayList<Host>();
    private static final String TAG = "FragmentThreeClient";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_three_client,container,false);

        ButterKnife.bind(this,view);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_file",0);
        String access_token = sharedPreferences.getString("access_token","");
        rvAdapter = new RecyclerViewAdapterIndex(hosts,getContext());
        rvIndex.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIndex.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
        getClientHostMapping = ApiUtils.getClientHostMapping();
        if(!access_token.equals("")) {
            mObservable = getClientHostMapping.getClientHostMapping(access_token);

            mObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Result>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Result value) {
                                for(HashMap<String,String> map:value.getArrayData()){
                                    hosts.add(new Host(map.get("host_name"),Integer.parseInt(map.get("host_id"))));
                                }
                                rvAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                    Log.d(TAG,"Error: "+e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                mObservable.unsubscribeOn(Schedulers.io());
                            }
                        });

        }else{
            Toast.makeText(getContext(), "Invalid access Token", Toast.LENGTH_SHORT).show();
        }
        return view;
    }



    @Override
    public void onDestroyView() {
        if(mObservable!=null)
            mObservable.unsubscribeOn(Schedulers.io());
        super.onDestroyView();
    }

}
