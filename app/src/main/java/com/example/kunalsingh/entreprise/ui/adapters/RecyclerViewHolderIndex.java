package com.example.kunalsingh.entreprise.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kunalsingh.entreprise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kunalsingh on 24/06/17.
 */

public class RecyclerViewHolderIndex extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewHost)
    TextView tvHost;

    public RecyclerViewHolderIndex(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}
