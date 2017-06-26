package com.example.kunalsingh.entreprise.ui.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.api.service.ApiUtils;
import com.example.kunalsingh.entreprise.api.service.HostSignUpService;
import com.example.kunalsingh.entreprise.models.Result;
import com.example.kunalsingh.entreprise.ui.activities.ClientMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpHostFragment extends Fragment {


    private HostSignUpService mHostSignUpService;

    @BindView(R.id.et_host_name)
    EditText etHostName;

    @BindView(R.id.et_host_email)
    EditText etHostEmail;

    @BindView(R.id.et_host_address)
    EditText etHostAddress;

    @BindView(R.id.et_host_password)
    EditText etHostPassword;

    @BindView(R.id.et_host_cotact)
    EditText etHostContact;

    @BindView(R.id.button_sign_up_host)
    Button btnSignUpHost;

    private static final String TAG = "SignUpHostFragment";

    private Observable<Result> observable;

    public static final String MY_FILE = "my_file";

    public SignUpHostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_host,container,false);

        ButterKnife.bind(this,view);

        mHostSignUpService = ApiUtils.getHostSignUpService();

        btnSignUpHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpHost();
            }
        });



        return view;
    }

    private void signUpHost() {

        if (valid()) {

            senSignUpRequest();


        }else{
            Toast.makeText(getContext(), "Invalid Entries", Toast.LENGTH_SHORT).show();
        }

    }

    private void senSignUpRequest() {
        observable = mHostSignUpService.signUpHost(etHostName.getText().toString(),
                etHostEmail.getText().toString(),
                etHostAddress.getText().toString(),
                etHostContact.getText().toString(),
                etHostPassword.getText().toString());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result value) {

                        if(value.getData().get("access_token")!=null) {

                            Intent intent = new Intent(getContext(), ClientMainActivity.class);
                            intent.putExtra("access_token", value.getData().get("access_token"));
                            intent.putExtra("selector", 2);
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences(MY_FILE,getContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("access_token",value.getData().get("access_token"));
                            editor.putInt("selector",2);
                            editor.commit();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"message : "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        observable.unsubscribeOn(Schedulers.io());
                    }
                });
    }

    private boolean valid() {


                if(!etHostName.getText().toString().equals("")&&
                        !etHostEmail.getText().toString().equals("")&&
                        !etHostPassword.getText().toString().equals("")&&
                        !etHostAddress.getText().toString().equals("")&&
                        !etHostContact.getText().equals("")){
                    return true;
                }else{
                    return false;
                }
    }

    @Override
    public void onStop() {

        if(observable!=null) {
            observable.unsubscribeOn(Schedulers.io());
        }
        super.onStop();
    }
}
