package com.example.kunalsingh.entreprise.ui.fragments;


import android.app.ProgressDialog;
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
import com.example.kunalsingh.entreprise.api.service.ClientSignUpService;
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
public class SignUpClientFragment extends Fragment {


    private ClientSignUpService mClientSignUpService;

    @BindView(R.id.et_client_name)
    EditText etNameClient;

    @BindView((R.id.et_client_email))
    EditText etEmailClient;

    @BindView(R.id.et_client_contact)
    EditText etContactClient;

    @BindView(R.id.et_client_password)
    EditText etPasswordClient;

    @BindView(R.id.button_sign_up_client)
    Button btnSignUpClient;

    private static final String TAG = "SignUpClientFragment";
    public static final String MY_FILE = "my_file";
    private Observable<Result> observable;

    public SignUpClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_client,container,false);

        ButterKnife.bind(this,view);

        btnSignUpClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                  sendSignUpRequest();
                }else{
                    Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void sendSignUpRequest() {
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Signing up as a client");
        mClientSignUpService = ApiUtils.getClientSignUpService();
        observable = mClientSignUpService.signUpClient(etNameClient.getText().toString(),
                etEmailClient.getText().toString(),
                etPasswordClient.getText().toString(),
                etContactClient.getText().toString());
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
                            intent.putExtra("selector", 1);
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences(MY_FILE,getContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("access_token",value.getData().get("access_token"));
                            editor.putInt("selector",1);
                            editor.putInt("id",Integer.parseInt(value.getData().get("id")));
                            editor.commit();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                        mDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"message_one : "+e.getMessage());
                        mDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        observable.unsubscribeOn(Schedulers.io());
                    }
                });
    }

    private boolean isValid() {

        if(!etNameClient.getText().toString().equals("")&&
                !etEmailClient.getText().toString().equals("")&&
                !etContactClient.getText().toString().equals("")&&
                !etPasswordClient.getText().toString().equals("")){
            return true;
        }else
            return false;
    }

    @Override
    public void onStop() {

        if(observable!=null){
            observable.unsubscribeOn(Schedulers.io());
        }

        super.onStop();
    }
}
