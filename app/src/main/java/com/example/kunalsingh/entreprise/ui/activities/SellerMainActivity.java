package com.example.kunalsingh.entreprise.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.models.Result;
import com.example.kunalsingh.entreprise.ui.adapters.Pager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellerMainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private static final String MY_FILE = "my_file";
    private Result resultMapping;
    private Result resultItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_main);

        ButterKnife.bind(this);

        Pager pagerAdapter = new Pager(getSupportFragmentManager(), new String[]{"STATUS", "ITEMS", "CHATS"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        pagerAdapter.notifyDataSetChanged();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE,MODE_PRIVATE);
                sharedPreferences.edit().remove("selector").commit();
                sharedPreferences.edit().remove("access_token").commit();
                Intent intent = new Intent(this,SelectorActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
