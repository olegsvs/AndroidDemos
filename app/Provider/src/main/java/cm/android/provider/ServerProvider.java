package cm.android.provider;

import android.os.Bundle;
import android.util.Log;

public class ServerProvider extends BaseContentProvider {
    public static int count = 0;

    @Override
    public boolean onCreate() {

        Log.e("ggg", "onCreate: !!!!!");
        return super.onCreate();
    }


    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        Bundle resultBundle = new Bundle();
        if ("1".equals(method)) {
            //log m1 1
            Log.e("ggg1 m1 1", "call: "+count);
            count++;
            resultBundle.putInt("one:", count);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //log m1 2
            Log.e("ggg1 m1 2", "call: "+count);


        } else if ("2".equals(method)) {
            //log m1 1
            Log.e("ggg2 m2 1", "call: "+count);
            count--;
            resultBundle.putInt("one:", count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //log m2 2
            Log.e("ggg2 m2 2", "call: "+count);
        }else if("3".equals(method)){
            onCreate();
            addSUm();
            resultBundle.putInt("sum", addSUm());
        }
        return resultBundle;
    }

    private int addSUm(){
        return 8;
    }
}
