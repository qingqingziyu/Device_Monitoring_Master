package com.cf.ecs.http;

import android.content.Context;

/**
 * @Params:随处调用当前的View的Activity
 * @开发者:陈飞
 * @日期:2019/3/21 09:37
 **/
public class ContextHolder {
    private static Context ApplicationContext;

    public static void initial(Context context) {
        ApplicationContext = context;
    }

    public static Context getContext() {
        return ApplicationContext;
    }
}
