package com.example.uidemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    private TextView textView;
    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;

    private static final int MSG_UPDATE_INFO = 0x110;
    private boolean isUpdateInfo=false;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_activtiy);

        initBackThread();
        textView = (TextView) findViewById(R.id.textView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }


    @Override
    protected void onPause() {
        super.onPause();
        isUpdateInfo=false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCheckMsgThread.quit();
    }



    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();

        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isUpdateInfo) {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };
    }

    private void checkForUpdate() {
        try {
            Thread.sleep(1000);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result = "实时更新中， 当前大盘指数:<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 3000 + 1000));
                    textView.setText(Html.fromHtml(result));
                    Log.e("ggg", "run: "+result);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
