package com.example.anrdemo;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

/**
 * Created by sivanliu on 2017/4/16.
 */

public class Util {
    public static void fixInputMethodManagerLeak(Context context) {
        if (null == context) {
            return;
        }
        try {
            // 对 mCurRootView mServedView mNextServedView 进行置空...
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) {
                return;
            }
            Object objGet = null;
            Log.e("hhh", "fixInputMethodManagerLeak: 0");
            Field fMCurRootView = imm.getClass().getDeclaredField("mCurRootView");
            Log.e("hhh", "fixInputMethodManagerLeak: 1");
            Field fMServedView = imm.getClass().getDeclaredField("mServedView");
            Log.e("hhh", "fixInputMethodManagerLeak: 2");
            Field fMNextServedView = imm.getClass().getDeclaredField("mNextServedView");
            Log.e("hhh", "fixInputMethodManagerLeak: 3");
            //华为5c

            if (isHwEmuiSupported()) {
                Field fMSrView = imm.getClass().getDeclaredField("mLastSrvView");
                Log.e("hhh", "fixInputMethodManagerLeak: 4");
                if (!fMSrView.isAccessible()) {
                    fMSrView.setAccessible(true);
                }
                objGet = fMSrView.get(imm);
                if (objGet != null) {// 不为null则置为空
                    fMSrView.set(imm, null);
                }
            }

            if (!fMCurRootView.isAccessible()) {
                fMCurRootView.setAccessible(true);
            }
            objGet = fMCurRootView.get(imm);
            if (objGet != null) { // 不为null则置为空
                fMCurRootView.set(imm, null);
            }

            Log.e("hhh", "fixInputMethodManagerLeak: 5");
            if (!fMServedView.isAccessible()) {
                fMServedView.setAccessible(true);
            }
            objGet = fMServedView.get(imm);
            if (objGet != null) { // 不为null则置为空
                fMServedView.set(imm, null);
            }

            Log.e("hhh", "fixInputMethodManagerLeak: 6");

            if (!fMNextServedView.isAccessible()) {
                fMNextServedView.setAccessible(true);
            }
            objGet = fMNextServedView.get(imm);
            if (objGet != null) { // 不为null则置为空
                fMNextServedView.set(imm, null);
            }
            Log.e("hhh", "fixInputMethodManagerLeak: 7");
        } catch (Throwable t) {
            Log.e("hhh", "fixInputMethodManagerLeak: error");
            t.printStackTrace();
        }
    }

    public static boolean isHwEmuiSupported() {
        try {
            Class clazz = Class.forName("com.huawei.android.app.admin.DeviceRestrictionManager");
            if (null != clazz) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            Log.e("error = {}", e.getMessage());
        }
        return false;
    }
}
