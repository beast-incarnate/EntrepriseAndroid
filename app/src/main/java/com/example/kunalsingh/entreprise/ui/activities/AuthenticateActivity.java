package com.example.kunalsingh.entreprise.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.api.service.ApiUtils;
import com.example.kunalsingh.entreprise.api.service.ClientSignInService;
import com.example.kunalsingh.entreprise.api.service.HostSignInService;
import com.example.kunalsingh.entreprise.models.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthenticateActivity extends AppCompatActivity {

    private HostSignInService mHostSignInService;
    private ClientSignInService mClientSignInService;
    private Observable<Result> observable;
    private int selector;
    private static final String TAG = "AuthenticateActivity";
    @BindView(R.id.editTextEmail)
    EditText etEmail;

    @BindView(R.id.editText_Password)
    EditText etPassword;

    @BindView(R.id.buttonSignIn)
    Button btnSignIn;

    @BindView(R.id.textViewSignUp)
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        ButterKnife.bind(this);



        Intent intent = getIntent();
        selector = intent.getIntExtra("selector" , 0);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                signIn(email,password);
            }
        });

    }

    private void signUp() {

        Intent intent = new Intent(AuthenticateActivity.this,SignUpActivity.class);
        intent.putExtra("selector",selector);
        startActivity(intent);

    }

    @Override
    protected void onStop() {
        if(observable!=null)
        observable.unsubscribeOn(Schedulers.io());
        super.onStop();
    }

    private void signIn(String email, String password) {

            if(!email.equals("")&&!password.equals("")){

                if(selector==1){
                    mClientSignInService = ApiUtils.getClientSignInService();


                    observable = mClientSignInService.signInCLient(email,password);

                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Result>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Result value) {
                                    if(value.getData().get("access_token")==null){
                                        Toast.makeText(AuthenticateActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                                    }else{
                                            Intent intent = new Intent(AuthenticateActivity.this,ClientMainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.putExtra("access_token",value.getData().get("access_token"));
                                            intent.putExtra("selector",selector);
                                            startActivity(intent);
                                    }


                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d(TAG,"message: "+e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    observable.unsubscribeOn(Schedulers.io());
                                }
                            });

                }else if(selector==2){
                    mHostSignInService = ApiUtils.getHostSignInService();


                    observable = mHostSignInService.signInHost(email,password);

                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Result>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Result value) {

                                    if(value.getData().get("access_token")==null){
                                        Toast.makeText(AuthenticateActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent intent = new Intent(AuthenticateActivity.this,ClientMainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("access_token",value.getData().get("access_token"));
                                        intent.putExtra("selector",selector);
                                        startActivity(intent);
                                    }


                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d(TAG,"message: "+e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    observable.unsubscribeOn(Schedulers.io());
                                }
                            });
                }else{
                    Log.d(TAG,"Invalid Selector");
                }


            }else{
                Toast.makeText(this, "Please fill form", Toast.LENGTH_SHORT).show();
            }

    }
}