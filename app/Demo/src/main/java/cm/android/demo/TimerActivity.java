package cm.android.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {


    private static class LooperHandler extends Handler {
        private static final String TAG = "ggg";
        WeakReference<TimerActivity> mainActivityWeakReference;

        LooperHandler(TimerActivity activity) {
            mainActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.e(TAG, "handleMessage: " + msg.what);
                    break;
                case 1:
                    Log.e(TAG, "handleMessage: " + msg.what);
                    break;
                case 2:
                    Log.e(TAG, "handleMessage: " + msg.what);
                    break;
                default:
                    break;
            }
        }
    }

    private LooperHandler mHandler = new LooperHandler(TimerActivity.this);
    private Button handlerTask;
    private Button timerTask;
    private Button scheduleExecutorService;

    private Timer timer;

    private ScheduledExecutorService scheduled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerTask = (Button) findViewById(R.id.HandlerTask);
        timerTask = (Button) findViewById(R.id.TimerTask);
        scheduleExecutorService = (Button) findViewById(R.id.scheduleExecutorService);

        handlerTask.setOnClickListener(this);
        timerTask.setOnClickListener(this);
        scheduleExecutorService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.HandlerTask:
                handlerTask();
                break;
            case R.id.TimerTask:
                timerTask();
                break;
            case R.id.scheduleExecutorService:
                scheduleExecutorService();
                break;
            default:
                break;
        }
    }

    private void handlerTask() {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage(0);
                mHandler.sendMessage(msg);
            }
        };

        mHandler.postDelayed(mRunnable, 2000);
    }


    private void timerTask() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage(2);
                mHandler.sendMessage(msg);
            }
        };
        timer.schedule(task, 0, 2000);
    }

    private void scheduleExecutorService() {
        scheduled = new ScheduledThreadPoolExecutor(1);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Message msg = mHandler.obtainMessage(1);
                mHandler.sendMessage(msg);
            }
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }
}
