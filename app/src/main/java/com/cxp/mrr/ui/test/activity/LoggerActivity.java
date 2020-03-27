package com.cxp.mrr.ui.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cxp.mrr.R;
import com.cxp.mrr.base.BaseActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文 件 名: LoggerActivity
 * 创 建 人: CXP
 * 创建日期: 2018-05-31 13:21
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoggerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        //初始化logger日志
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(false)  //（可选）是否显示线程信息。默认值true
//                .methodCount(0)         //（可选）要显示多少个方法行。默认2
//                .methodOffset(7)        //（可选）将内部方法调用隐藏到偏移量。默认
//                .logStrategy(customLog) //（可选）更改日志策略以打印输出。默认LogCat
                .tag("CP")   //（可选）每个日志的全局标记。默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


        Logger.d("debug");
        Logger.e("error");
        Logger.w("warning");
        Logger.v("verbose");
        Logger.i("information");
        Logger.wtf("What a Terrible Failure");

        //动态传参
        Logger.d("hello %s", "CXP");

        //Map日志
        mapLog();

        //Map日志
        listLog();

        //Map日志
        jsonLog();

    }


    //Map日志
    private void mapLog() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "CXP");
        map.put("age", 25);
        Logger.d(map);
    }

    //List日志
    private void listLog() {
        List<String> list = new ArrayList<>();
        list.add("CXP");
        list.add("程小鹏");
        Logger.d(list);
    }

    //Json日志
    private void jsonLog() {
        String json = "{\"name\": \"CXP\",\"age\": 26}";
        Logger.json(json);
    }

}
