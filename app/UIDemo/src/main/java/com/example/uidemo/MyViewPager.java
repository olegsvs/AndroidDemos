package com.example.uidemo;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.jar.Attributes;

/**
 * Created by Sivan on 2017/3/8 0008.
 */

public class MyViewPager extends ViewPager {
    private float mTrans;
    private float mScale;

    //最大缩放比
    private static final float SCALE_MAX = 0.5f;
    private static final String TAG = "MyViewPager";

    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<>();
    private View mLeft;
    private View mRight;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        float effectOffset = isSmall(offset) ? 0 : offset;
        mLeft = findViewFromObject(position);

        mRight = findViewFromObject(position + 1);

        animateStack(mLeft, mRight, effectOffset, offsetPixels);
        super.onPageScrolled(position, offset, offsetPixels);

    }


    private boolean isSmall(float positionOffset) {
        return Math.abs(positionOffset) < 0.0001;
    }

    private View findViewFromObject(int position) {
        return mChildrenViews.get(position);
    }

    public void setObjectForPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }

    private void animateStack(View left, View right, float effectOffset, int positionOffsetPixels) {
        if (right != null) {
            mScale = (1 - SCALE_MAX) * effectOffset + SCALE_MAX;
            mTrans = -getWidth() - getPageMargin() + positionOffsetPixels;
            ViewHelper.setScaleX(right, mScale);
            ViewHelper.setScaleY(right, mScale);
            ViewHelper.setTranslationX(right, mTrans);
        }

        if (left != null) {
            left.bringToFront();
        }
    }
}
