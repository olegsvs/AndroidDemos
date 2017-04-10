package com.example.oppo;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;

/**
 * Created by Sivan on 2017/4/5 0005.
 */

public class DefaultAdmin extends DeviceAdminReceiver {
    @Override
    public DevicePolicyManager getManager(Context context) {
        return super.getManager(context);
    }
}

