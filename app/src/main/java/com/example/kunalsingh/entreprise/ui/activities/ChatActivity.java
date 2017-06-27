package com.example.kunalsingh.entreprise.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunalsingh.entreprise.models.Message;
import com.example.kunalsingh.entreprise.ui.adapters.RecyclerViewAdapterMessage;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.example.kunalsingh.entreprise.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {


    @BindView(R.id.editText_message)
    EditText etMessage;

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    @BindView(R.id.imageButton_send_message)
    ImageButton sendMessage;

    private RecyclerViewAdapterMessage rvAdapter;
    private ArrayList<Message> messages = new ArrayList<>();
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

        rvAdapter = new RecyclerViewAdapterMessage(messages,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        rvChat.setLayoutManager(llm);
        rvChat.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();

        mSocket.on("message_one",handleIncomingMessages);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString();
                if(!message.equals("")) {
                    mSocket.emit("message_one", message, 7, 5);
                    messages.add(messages.size(),new Message(message, DateFormat.getDateTimeInstance().format(new Date()),1));
                    rvAdapter.notifyDataSetChanged();
                    rvChat.scrollToPosition(rvAdapter.getItemCount()-1);
                    etMessage.setText("");
                }else{
                    Toast.makeText(ChatActivity.this, "Enter message", Toast.LENGTH_SHORT).show();
                }
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
                    int sender;
                    try{
                        message = data.getString("message_one").toString();
                        sender = Integer.parseInt(data.getString("sender").toString());
                        messages.add(new Message(message,DateFormat.getDateTimeInstance().format(new Date()),0));
                        rvAdapter.notifyDataSetChanged();
                        rvChat.scrollToPosition(rvAdapter.getItemCount()-1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    };
}
