package com.example.kunalsingh.entreprise.ui.fragments;

import android.content.Context;
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
import com.example.kunalsingh.entreprise.api.service.GetHostClientMapping;
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
 * Created by kunalsingh on 27/06/17.
 */

public class FragmentThreeSeller extends Fragment{

    @BindView(R.id.rv_index_seller)
    RecyclerView rvIndexSeller;

    public FragmentThreeSeller() {
    }

    private GetHostClientMapping mgetHostClientMapping;
    Observable<Result> mObservable;
    private RecyclerViewAdapterIndex recyclerViewAdapterIndex;
    private ArrayList<Host> hosts = new ArrayList<>();
    private static final String MY_FILE = "my_file";
    private static final String TAG = "FragmentThreeSeller";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_three_seller,container,false);
        ButterKnife.bind(this,view);
        rvIndexSeller.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapterIndex = new RecyclerViewAdapterIndex(hosts,getContext());
        rvIndexSeller.setAdapter(recyclerViewAdapterIndex);
        recyclerViewAdapterIndex.notifyDataSetChanged();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token","");
        mgetHostClientMapping = ApiUtils.getHostClientMapping();
        if(!accessToken.equals("")) {
            mObservable = mgetHostClientMapping.getHostClientMapping(accessToken);
            mObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Result>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Result value) {
                                for(HashMap<String,String> map:value.getArrayData()){
                                    hosts.add(new Host(map.get("client_name"),Integer.parseInt(map.get("client_id"))));
                                    Log.d(TAG,"client: "+map.get("client_id")+" host: "+map.get("host_id"));
                                }
                                recyclerViewAdapterIndex.notifyDataSetChanged();
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
            Toast.makeText(getContext(), "Invalid Access Token", Toast.LENGTH_SHORT).show();
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
