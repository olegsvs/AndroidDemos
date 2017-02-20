package cm.android.demo;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("ggg", "Main: " + getDeviceAdmin(this.getPackageName()));
    }

    public ComponentName getDeviceAdmin(String packageName) {
        PackageManager packageManager = MainActivity.this.getPackageManager();
        Intent intent = new Intent(DeviceAdminReceiver.ACTION_DEVICE_ADMIN_ENABLED);
        List<ResolveInfo> list = packageManager.queryBroadcastReceivers(intent, 0);
        ComponentName componentName = null;
        for (ResolveInfo resolveInfo : list) {
            String pkg = resolveInfo.activityInfo.packageName;
            String cls = resolveInfo.activityInfo.name;
            if (packageName.equals(pkg)) {
                componentName = new ComponentName(packageName, cls);
                return componentName;
            }
        }
        return componentName;
    }
}
