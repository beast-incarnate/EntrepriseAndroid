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


public class RecyclerViewHolderMessage extends RecyclerView.ViewHolder{

   @BindView(R.id.textViewMessage)
   TextView tvMessage;

    @BindView(R.id.textViewTime)
    TextView tvTime;

    public RecyclerViewHolderMessage(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }


}
