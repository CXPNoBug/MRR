package com.cxp.mrr.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.cxp.mrr.utils.AppUtils;
import com.cxp.mrr.utils.CrashHandler;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: MyApplication
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 5:08
 * 描    述: 初始化数据
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends Application {

    private static Context appContext;
    private static List<Activity> activityList;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        activityList = new ArrayList<>();
        appContext = getApplicationContext();

        //谷歌调试工具
        Stetho.initializeWithDefaults(this);

        //崩溃捕获
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());

        //logger日志
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(false)  //（可选）是否显示线程信息。默认值true
//                .methodCount(0)         //（可选）要显示多少个方法行。默认2
//                .methodOffset(7)        //（可选）将内部方法调用隐藏到偏移量。默认
//                .logStrategy(customLog) //（可选）更改日志策略以打印输出。默认LogCat
                .tag(AppUtils.TAG)   //（可选）每个日志的全局标记。默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


    }

    //添加一个Activity
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    //删除一个Activity
    public static void removeActivity(Activity activity) {
        if (activityList != null && activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    //关闭所有Activity
    public static void clearActivity() {
        if (activityList != null) {
            for (Activity activity : activityList) {
                activity.finish();
            }
            activityList = null;
        }
    }

    /**
     * 退出软件
     */
    private static long exitTime = 0;

    public static void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(appContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //退出应用
            clearActivity();
        }
    }

}
