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


        //以下为用两队英雄同时互相攻击的例子，来理解多线程。四个英雄两两互攻，就是四个线程。
        final Hero a = new Hero(1000,80,"盖伦");
        final Hero b = new Hero(500,200,"提莫");

        final Hero c = new Hero(800,100,"盲僧");
        final Hero d = new Hero(700,250,"男枪");

        //盖伦攻击提莫
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!b.isDead() && !a.isDead()) {
                    a.attack(b);
                }
            }
        }).start();

        //提莫攻击盖伦
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!b.isDead() && !a.isDead()) {
                    b.attack(a);
                }
            }
        }).start();


        //盲僧攻击男枪
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!d.isDead() && !c.isDead()) {
                    c.attack(d);
                }
            }
        }).start();

        //男枪攻击盲僧
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!d.isDead() && !c.isDead()) {
                    d.attack(c);
                }
            }
        }).start();
    }
}
