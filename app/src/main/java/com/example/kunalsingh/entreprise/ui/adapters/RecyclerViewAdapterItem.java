package com.example.kunalsingh.entreprise.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.models.Item;
import com.example.kunalsingh.entreprise.ui.activities.ItemActivity;

import java.util.ArrayList;

/**
 * Created by kunalsingh on 28/06/17.
 */

public class RecyclerViewAdapterItem extends RecyclerView.Adapter<RecyclerViewHolderItem> {

    private ArrayList<Item> items = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterItem(ArrayList<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new RecyclerViewHolderItem(layoutInflater.inflate(R.layout.item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderItem holder, final int position) {
            holder.itemName.setText(items.get(position).getItemName());
            holder.itemPrice.setText(String.valueOf(items.get(position).getItemPrice()));
            holder.itemQuantity.setText(String.valueOf(items.get(position).getItemQuantity()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ItemActivity.class);
                    ArrayList<String> al = new ArrayList<String>();
                    al.add(items.get(position).getItemName());
                    al.add(String.valueOf(items.get(position).getItemPrice()));
                    al.add(String.valueOf(items.get(position).getItemQuantity()));
                    al.add(String.valueOf(items.get(position).getItemId()));
                    al.add("update");
                    intent.putExtra("item",al);
                    mContext.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
