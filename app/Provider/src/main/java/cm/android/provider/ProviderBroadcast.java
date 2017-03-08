package cm.android.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Sivan on 2017/2/27 0027.
 */

public class ProviderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.e("ggg", "onReceive: " + intent.getAction());
    }
}
