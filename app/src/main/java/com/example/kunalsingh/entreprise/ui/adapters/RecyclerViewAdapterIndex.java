package com.example.kunalsingh.entreprise.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.models.Host;
import com.example.kunalsingh.entreprise.ui.activities.ChatActivity;

import java.util.ArrayList;

/**
 * Created by kunalsingh on 24/06/17.
 */

public class RecyclerViewAdapterIndex extends RecyclerView.Adapter<RecyclerViewHolderIndex> {

    private ArrayList<Host> mHost;
    private Context mContext;
    private static final String TAG = "RecyclerViewAdapterInde";
    
    public RecyclerViewAdapterIndex(ArrayList<Host> mHost , Context mContext) {
        this.mHost = mHost;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewHolderIndex onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new RecyclerViewHolderIndex(li.inflate(R.layout.chat_hosts_index, null));
        }catch(NullPointerException n){
            return null;
        }
        }

    @Override
    public void onBindViewHolder(RecyclerViewHolderIndex holder, final int position) {

       try {
           holder.tvHost.setText(mHost.get(position).getHostName());
           Log.d(TAG,"id: "+mHost.get(position).getId());
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(mContext, ChatActivity.class);
                   intent.putExtra("id",mHost.get(position).getId());
                   mContext.startActivity(intent);
               }
           });
       }catch(Exception n){
           Log.d(TAG,"Error: "+n.getMessage());
       }
    }

    @Override
    public int getItemCount() {
        return mHost.size();
    }
}
