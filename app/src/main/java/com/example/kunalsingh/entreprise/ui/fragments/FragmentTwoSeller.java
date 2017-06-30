package com.example.kunalsingh.entreprise.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.api.service.ApiUtils;
import com.example.kunalsingh.entreprise.api.service.HostGetAllItemsService;
import com.example.kunalsingh.entreprise.models.Item;
import com.example.kunalsingh.entreprise.models.Result;
import com.example.kunalsingh.entreprise.ui.activities.ItemActivity;
import com.example.kunalsingh.entreprise.ui.adapters.RecyclerViewAdapterItem;

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

public class FragmentTwoSeller extends Fragment {

    public FragmentTwoSeller() {
        setHasOptionsMenu(true);
    }

    private HostGetAllItemsService hostGetAllItemsService;
    private Observable<Result> mObservable;
    private ArrayList<Item> items = new ArrayList<>();
    private RecyclerViewAdapterItem recyclerViewAdapterItem;
    private static final String MY_FILE = "my_file";
    private static final String TAG = "FragmentTwoSeller";

    @BindView(R.id.rv_item)
    RecyclerView rvItem;
    private void getAllItems(String accessToken) {
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Getting Items");
        mObservable = hostGetAllItemsService.getAllItemsHost(accessToken);
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result value) {
                        for(HashMap<String,String>map:value.getArrayData()){
                            items.add(new Item(map.get("name"),Integer.parseInt(map.get("price")),
                                    Integer.parseInt(map.get("amount")),Integer.parseInt(map.get("id"))));
                            Log.d(TAG,"items: "+map.get("name")+" "+map.get("price")+" "+map.get("amount"));
                        }
                        recyclerViewAdapterItem.notifyDataSetChanged();
                        mDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                            mObservable.unsubscribeOn(Schedulers.io());
                    }
                });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two_seller,container,false);
        ButterKnife.bind(this,view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token","");
        hostGetAllItemsService = ApiUtils.getHostAllItemsService();
        getAllItems(accessToken);
        rvItem.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapterItem = new RecyclerViewAdapterItem(items,getContext());
        rvItem.setAdapter(recyclerViewAdapterItem);
        recyclerViewAdapterItem.notifyDataSetChanged();
        Log.d(TAG,"onCreateView");
        return view;
    }

    @Override
    public void onDestroyView() {
        if(mObservable!=null)
            mObservable.unsubscribeOn(Schedulers.io());
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.item,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.add_item :
                Intent intent = new Intent(getContext(), ItemActivity.class);
                ArrayList<String> al = new ArrayList<>();
                al.add("");
                al.add("");
                al.add("");
                al.add("-1");
                al.add("new");
                intent.putExtra("item",al);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
