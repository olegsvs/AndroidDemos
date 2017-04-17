package com.example.anrdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("smallSohoSolo", "Hello Handler");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("smallSohoSolo", "Running");
//            }
//        }, 2 * 60 * 10); //10分钟之后执行
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.fixInputMethodManagerLeak(this.getApplicationContext());
    }
}
