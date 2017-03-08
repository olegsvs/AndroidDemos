package cm.android.provider;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ProviderBroadcast providerBroadcast = new ProviderBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("send:1");
        intentFilter.addAction("send:2");
        intentFilter.addAction("send:3");
        Util.getAppContext().registerReceiver(providerBroadcast, intentFilter);

        final Uri uri = Uri.parse("content://cm.android.provider");

        Log.e("sssss", "onCreate: " + Main2Activity.this.getContentResolver().call(uri, "3", null, null).getInt("sum"));


//        Thread one = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Main2Activity.this.getContentResolver().call(uri, "1", null, null).getInt("one:");
//            }
//        });
//        Thread two = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Main2Activity.this.getContentResolver().call(uri, "2", null, null).getInt("one:");
//            }
//        });
//        one.start();
//        two.start();
    }
}
