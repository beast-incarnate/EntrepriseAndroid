package com.example.kunalsingh.entreprise.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kunalsingh.entreprise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectorActivity extends AppCompatActivity {


    private int selector=0;

    @BindView(R.id.buttonClient)
    Button btnClient;

    @BindView(R.id.buttonSeller)
    Button btnSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        ButterKnife.bind(this);
        final Intent intent = new Intent(this,AuthenticateActivity.class);

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selector = 1;
                intent.putExtra("selector",selector);
                startActivity(intent);
            }
        });

        btnSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selector = 2;
                intent.putExtra("selector",selector);
                startActivity(intent);
            }
        });

    }
}
