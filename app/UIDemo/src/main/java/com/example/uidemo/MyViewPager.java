package com.example.uidemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedHashMap;

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

    public MyViewPager(Context context) {
        super(context);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);

        float effectOffset = isSmall(offset) ? 0 : offset;
        mLeft=findViewFromObject(position);

        mRight=findViewFromObject(position+1);

    }

    private boolean isSmall(float positionOffset) {
        return Math.abs(positionOffset) < 0.0001;
    }

    private View findViewFromObject(int position){
        return mChildrenViews.get(position);
    }
}
