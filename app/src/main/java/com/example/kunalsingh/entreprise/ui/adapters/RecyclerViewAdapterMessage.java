package com.example.kunalsingh.entreprise.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.models.Message;

import java.util.ArrayList;

public class RecyclerViewAdapterMessage extends RecyclerView.Adapter<RecyclerViewHolderMessage> {


    private ArrayList<Message> mMessage;
    private Context mContext;

    public RecyclerViewAdapterMessage(ArrayList<Message> mMessage, Context mContext) {
        this.mMessage = mMessage;
        this.mContext = mContext;
    }


    @Override
    public RecyclerViewHolderMessage onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new RecyclerViewHolderMessage(layoutInflater.inflate(R.layout.message,null));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderMessage holder, int position) {

        holder.tvMessage.setText(mMessage.get(position).getMessage());
        holder.tvTime.setText(mMessage.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }
}


