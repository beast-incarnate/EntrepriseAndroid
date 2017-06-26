package com.example.kunalsingh.entreprise.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.ui.adapters.RecyclerViewAdapterMessage;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.example.kunalsingh.entreprise.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {


    @BindView(R.id.editText_message)
    EditText etMessage;

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    @BindView(R.id.imageButton_send_message)
    ImageButton sendMessage;

    private RecyclerViewAdapterMessage mAdapter;
    private static final String TAG = "ChatActivity";

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://192.168.43.151:8000");
        }catch(URISyntaxException uri){
            Log.d(TAG,"Error: "+uri.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        mSocket.connect();

        mSocket.emit("details",7);


        mSocket.on("message",handleIncomingMessages);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString();
                mSocket.emit("message",message,7,5);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
    }

    private Emitter.Listener handleIncomingMessages = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String sender;
                    try{
                        message = data.getString("message").toString();
                        sender = data.getString("sender").toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    };
}
