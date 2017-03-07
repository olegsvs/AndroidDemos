package cm.android.provider;

import android.content.Context;

public class Util {

    private static Context sAppContext;

    public static final void init(Context appContext) {
        sAppContext = appContext.getApplicationContext();
    }

    public static Context getAppContext() {
        return sAppContext;
    }

}
