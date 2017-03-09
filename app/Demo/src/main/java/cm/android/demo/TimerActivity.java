package cm.android.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {

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
    private Button start;
    private Button stop;

    private Timer timer;

    private ScheduledExecutorService scheduled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        handlerPostDelayed();
        timerTask();
        scheduleExecutorService();
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Message msg = mHandler.obtainMessage(0);
            mHandler.sendMessage(msg);
        }
    };

    private void handlerPostDelayed() {
        mHandler.postDelayed(mRunnable, 0);
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
