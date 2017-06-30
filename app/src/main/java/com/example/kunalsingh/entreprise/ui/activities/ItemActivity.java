package com.example.kunalsingh.entreprise.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.R;
import com.example.kunalsingh.entreprise.api.service.ApiUtils;
import com.example.kunalsingh.entreprise.api.service.HostAddItemService;
import com.example.kunalsingh.entreprise.api.service.HostDeleteItemService;
import com.example.kunalsingh.entreprise.api.service.HostUpdateItemService;
import com.example.kunalsingh.entreprise.models.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ItemActivity extends AppCompatActivity {

    @BindView(R.id.et_item_name)
    EditText etItemName;

    @BindView(R.id.et_item_price)
    EditText etItemPrice;

    @BindView(R.id.et_item_quantity)
    EditText etItemQuantity;

    @BindView(R.id.button_item_submit)
    Button btnItemSubmit;

    private HostAddItemService mHostAddItemService;
    private HostUpdateItemService mHostUpdateItemService;
    private HostDeleteItemService mHostDeleteItemService;
    private Observable<Result> mObservable;
    private static final String MY_FILE = "my_file";
    private static final String TAG = "ItemActivity";
    private ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        name = intent.getStringArrayListExtra("item");

        etItemName.setText(name.get(0));
        etItemPrice.setText(name.get(1));
        etItemQuantity.setText(name.get(2));

        btnItemSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    updateItem(name.get(4),name.get(3));
                }else{
                    Toast.makeText(ItemActivity.this, "Invalid Entries", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateItem(String sel , final String id) {
        final ProgressDialog mDialog = new ProgressDialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE,MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token","");
        if(!accessToken.equals("")) {
            if (sel.equals("new")) {
                mDialog.setMessage("Adding");
                mHostAddItemService = ApiUtils.getHostAddItemService();
                mObservable = mHostAddItemService.addItemHost(accessToken,etItemName.getText().toString(),
                                                                            Integer.parseInt(etItemPrice.getText().toString()),
                                                                            Integer.parseInt(etItemQuantity.getText().toString()));

            } else if (sel.equals("update")) {
                mDialog.setMessage("Updating");
                mHostUpdateItemService = ApiUtils.getHostUpdateItemService();
                mObservable = mHostUpdateItemService.updateItemHost(accessToken,Integer.parseInt(id),
                                                                                etItemName.getText().toString(),
                                                                                Integer.parseInt(etItemPrice.getText().toString()),
                                                                                Integer.parseInt(etItemQuantity.getText().toString()));

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

            if(mObservable!=null){
                mObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Result>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Result value) {
                                    
                                            Intent intent = new Intent(ItemActivity.this,SellerMainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            ItemActivity.this.finish();
                                    mDialog.dismiss();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d(TAG,e.getMessage());
                                    mDialog.dismiss();
                                }

                                @Override
                                public void onComplete() {
                                        mObservable.unsubscribeOn(Schedulers.io());
                                }
                            });
            }

        }else{
            Toast.makeText(this, "Invalid access Token", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValid() {

        if(!etItemName.getText().toString().equals("")&&
                !etItemPrice.getText().toString().equals("")&&
                !etItemQuantity.getText().toString().equals("")){
            return true;
        }else
            return false;

    }

    @Override
    protected void onDestroy() {
        if(mObservable!=null)
            mObservable.unsubscribeOn(Schedulers.io());
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.delete_item:
                                deleteItem();
                                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteItem() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Deleting");
        SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE,MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token","");
        if(!accessToken.equals("")) {
            mHostDeleteItemService = ApiUtils.getHostDeleteItemService();
            mObservable = mHostDeleteItemService.deleteItemHost(accessToken,Integer.parseInt(name.get(3)));
            mObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Result>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Result value) {
                                Intent intent = new Intent(ItemActivity.this,SellerMainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                ItemActivity.this.finish();
                                mDialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                    Log.d(TAG,"message: "+e.getMessage());
                                mDialog.dismiss();
                            }

                            @Override
                            public void onComplete() {
                                mObservable.unsubscribeOn(Schedulers.io());
                            }
                        });
        }else{
            Toast.makeText(this, "Invalid access token", Toast.LENGTH_SHORT).show();
        }
    }
}
