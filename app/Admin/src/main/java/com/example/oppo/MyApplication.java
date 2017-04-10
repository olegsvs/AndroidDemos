package com.example.oppo;

import android.app.Application;

/**
 * Created by Sivan on 2017/4/5 0005.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EngineObjectPool.init(this);
    }
}
