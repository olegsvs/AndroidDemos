package com.example.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "ggg MainActivity::";
    private ICalcAIDL mICalcAIDL;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: ");
            mICalcAIDL = ICalcAIDL.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: ");
            mICalcAIDL = null;
        }
    };


    private Button BindService;
    private Button UnBindService;
    private Button Add;
    private Button Sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindService = (Button) findViewById(R.id.BindService);
        UnBindService = (Button) findViewById(R.id.UnBindService);

        Add = (Button) findViewById(R.id.Add);
        Sub = (Button) findViewById(R.id.Sub);

        BindService.setOnClickListener(this);
        UnBindService.setOnClickListener(this);

        Add.setOnClickListener(this);
        Sub.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BindService:
                Intent intent = new Intent(MainActivity.this, CalcService.class);
                intent.setAction("com.example.aidldemo.calc");
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.UnBindService:
                unbindService(serviceConnection);
                break;
            case R.id.Add:
                if (mICalcAIDL != null) {
                    try {
                        int addRes = mICalcAIDL.add(12, 13);
                        Log.e(TAG, "add:: " + addRes);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "add service has been killed!");
                }

                break;
            case R.id.Sub:
                if (mICalcAIDL != null) {
                    try {
                        int subRes = mICalcAIDL.min(5, 3);
                        Log.e(TAG, "Sub: " + subRes);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Sub service had been killed!");
                }
                break;
            default:
                break;

        }
    }
}
