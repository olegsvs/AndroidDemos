package cm.android.provider;

import android.content.Intent;
import android.util.Log;

/**
 * Created by Sivan on 2017/2/27 0027.
 */

public class Send {
    public static void onSend() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setAction("send:");
                Util.getAppContext().sendBroadcast(intent);
                Log.e("ggg", "run: " + Thread.currentThread().getName());
            }
        });
        try {
            thread.start();
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void onSend1() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setAction("send1:");
                Util.getAppContext().sendBroadcast(intent);
                Log.e("ggg", "run: " + Thread.currentThread().getName());
            }
        });
        try {
            thread.start();
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void onSend2() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setAction("send2:");
                Util.getAppContext().sendBroadcast(intent);
                Log.e("ggg", "run: " + Thread.currentThread().getName());
            }
        });
        try {
            thread.start();
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
