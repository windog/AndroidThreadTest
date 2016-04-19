package com.windog.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView text;
    Button btnChangeText;

    public static final int UPDATE_TEXT = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    text.setText("Nice to meet you!");
                    break;
                default:
                    break;
            }
        }

    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         text = (TextView) findViewById(R.id.text);

        btnChangeText = (Button) findViewById(R.id.btnChangeText);
        btnChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }
        });
    }
}
