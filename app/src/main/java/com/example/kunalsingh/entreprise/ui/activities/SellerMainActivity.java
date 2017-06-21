package com.example.kunalsingh.entreprise.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.ui.adapters.Pager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellerMainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main);

        ButterKnife.bind(this);

        Pager pagerAdapter = new Pager(getSupportFragmentManager(), new String[]{"CLIENTS", "ITEMS", "CHATS"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        pagerAdapter.notifyDataSetChanged();

    }
}
