package com.example.kunalsingh.entreprise.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kunalsingh.entreprise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kunalsingh on 28/06/17.
 */

public class RecyclerViewHolderItem extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewItemName)
    TextView itemName;

    @BindView(R.id.textViewItemPrice)
    TextView itemPrice;

    @BindView(R.id.textViewItemQuantity)
    TextView itemQuantity;

    public RecyclerViewHolderItem(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}
