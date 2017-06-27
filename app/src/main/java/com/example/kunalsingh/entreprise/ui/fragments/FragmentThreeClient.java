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
import com.example.kunalsingh.entreprise.api.service.ClientGetAllHostService;
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

    @BindView(R.id.rv_index)
    RecyclerView rvIndex;

    public FragmentThreeClient() {
    }

    private Observable<Result> mObservable;
    private RecyclerViewAdapterIndex rvAdapter;
    private ArrayList<Host> name = new ArrayList<Host>();
    private static final String TAG = "FragmentThreeClient";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_three_client,container,false);

        ButterKnife.bind(this,view);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_file",0);
        String access_token = sharedPreferences.getString("access_token","");
        int selector = sharedPreferences.getInt("selector",0);
        rvAdapter = new RecyclerViewAdapterIndex(name,getContext());
        rvIndex.setLayoutManager(new LinearLayoutManager(getContext()));
        rvIndex.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
        if(selector==1){
            onClientSelected(view,access_token);
        }else if(selector==2){
            onSellerSelected(access_token);
        }else{
            Toast.makeText(getContext(), "Invalid Selector", Toast.LENGTH_SHORT).show();
        }

        return view;
    }



    @Override
    public void onDestroyView() {
        if(mObservable!=null)
            mObservable.unsubscribeOn(Schedulers.io());
        super.onDestroyView();
    }
    
    private void onClientSelected(View view , String access_token){

       
        if(!access_token.equals("")) {

        }else{
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
        
    }

    private void onSellerSelected(String access_token) {

        if(!access_token.equals("")){

        }else{
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }


    }
}
