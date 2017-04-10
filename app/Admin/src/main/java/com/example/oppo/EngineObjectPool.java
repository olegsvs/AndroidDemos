package com.example.oppo;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * Created by Sivan on 2017/4/5 0005.
 */

public class EngineObjectPool {
    private static Context appContext;

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }

    public static ComponentName getAdmin() {
        return getDeviceAdmin(appContext.getPackageName());
    }

    public static boolean checkAdmin() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) appContext.getSystemService(appContext.DEVICE_POLICY_SERVICE);
        return devicePolicyManager.isAdminActive(getAdmin());
    }

    public static ComponentName getDeviceAdmin(String packageName) {
        PackageManager packageManager = EngineObjectPool.getContext().getPackageManager();
        Intent intent = new Intent(DeviceAdminReceiver.ACTION_DEVICE_ADMIN_ENABLED);
        List<ResolveInfo> list = packageManager.queryBroadcastReceivers(intent, 0);
        ComponentName componentName = null;
        for (ResolveInfo resolveInfo : list) {
            String pkg = resolveInfo.activityInfo.packageName;
            String cls = resolveInfo.activityInfo.name;
            if (packageName.equals(pkg)) {
                return componentName;
            }
        }
//        ComponentName componentName = new ComponentName(EngineObjectPool.getContext(), DefaultAdmin.class);
        return componentName;
    }
}
