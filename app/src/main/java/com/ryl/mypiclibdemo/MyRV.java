package com.ryl.mypiclibdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by rongyile on 2018/1/30.
 */

public class MyRV extends RecyclerView {
    public MyRV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MyRV(Context context) {
        super(context);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);


    }
}
