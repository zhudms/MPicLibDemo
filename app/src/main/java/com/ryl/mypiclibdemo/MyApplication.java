package com.ryl.mypiclibdemo;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;

import com.ryl.commonlib.utils.WindowU;

/**
 * Created by rongyile on 2018/1/29.
 */

public class MyApplication extends Application {
    public static int winWidth;
    public static int winHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        initWindowMessage(MyApplication.this);
    }


    private void initWindowMessage(Context context) {
//WindowMessage.
        Point p = WindowU.getWindowMessage(context);
        MyApplication.winHeight = p.y;
        MyApplication.winWidth = p.x;
    }

}
