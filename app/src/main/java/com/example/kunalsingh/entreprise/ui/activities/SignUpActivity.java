package com.example.kunalsingh.entreprise.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.ui.fragments.SignUpClientFragment;
import com.example.kunalsingh.entreprise.ui.fragments.SignUpHostFragment;

public class SignUpActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment;
        switch (getIntent().getIntExtra("selector",0)){
            case 1:
                    fragment = new SignUpClientFragment();
                    transaction.replace(R.id.container,fragment,null);
                    transaction.commit();
                    break;

            case 2:
                    fragment = new SignUpHostFragment();
                    transaction.replace(R.id.container,fragment,null);
                    transaction.commit();
                    break;

            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

        }


    }


}
