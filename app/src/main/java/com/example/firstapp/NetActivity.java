package com.example.firstapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NetActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "Net";
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_net);

        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //处理返回
                if (msg.what==5){
                    String str=(String) msg.obj;
                    Log.i(TAG,"handleMessage:str="+ str);
                }
                super.handleMessage(msg);
            }
        };

        Log.i(TAG,"onCreat: start Thread");
        Thread t=new Thread(this);
        t.start();//this.run()

//        Thread t2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//          t2.start();
//          Thread t3=new Thread(()->{
//              //执行方法
//          });
//          t3.start();
    }

    @Override
    public void run() {
        Log.i(TAG,"run: 子线程run()......");
        //发送消息
        Message msg=handler.obtainMessage(5,"Hello");
        handler.sendMessage(msg);
    }
}