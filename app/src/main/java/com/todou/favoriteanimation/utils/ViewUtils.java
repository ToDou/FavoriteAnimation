package com.todou.favoriteanimation.utils;

import android.view.View;

/**
 * Created by loopeer on 2015/7/13.
 */
public class ViewUtils {

    public static int[] getNumLocation(View view) {
        int[] location = new int[2] ;
        //view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
        view.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
        return location;
    }
}
