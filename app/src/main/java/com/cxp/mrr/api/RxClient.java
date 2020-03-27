package com.cxp.mrr.api;

import android.util.Log;

import com.cxp.mrr.base.MyApplication;
import com.cxp.mrr.utils.NetUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文 件 名: RxClient
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 4:59
 * 描    述: 网路请求配置
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxClient {

    //请求连接
    public static final String BASE_URL = "http://trevorqt.xicp.net:56293/";

    //另一个请求连接
    public static final String WEATHER_URL = "http://jisutianqi.market.alicloudapi.com/weather/";

    private static OkHttpClient mOkHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();


    //默认网络请求时间
    private static final int DEFAULT_TIMEOUT = 10;
    //缓存大小 10M
    private static final int CACHE_SIZE = 10 * 1024 * 1024;


    //拦截器 用来设置缓存
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetUtils.isConnected(MyApplication.getAppContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周   only-if-cached 仅仅使用缓存
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    //初始化配置
    private static void initConfig() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //设置超时时间
            //链接超时时间设置
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            //读取超时时间设置
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            //写入超时时间设置
            builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            //添加缓存
            File cacheFile = new File(MyApplication.getAppContext().getCacheDir(), "okHttpCache");
            builder.cache(new Cache(cacheFile, CACHE_SIZE));

            // 添加拦截器
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();
                    if (!NetUtils.isConnected(MyApplication.getAppContext())) {
                        //没有网络连接  CacheControl.FORCE_CACHE 仅仅使用缓存
                        request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                    }
                    Response response = chain.proceed(request);

                    Log.e("response返回参数 = ", "" + response.toString());
                    //添加打印服务器返回的数据
                    ResponseBody responseBody = response.body();
                    long contentLength = responseBody.contentLength();
                    BufferedSource source = responseBody.source();
                    source.request(Integer.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();

                    if (contentLength != 0) {
                        Log.e("服务器返回数据： ", "" + buffer.clone().readString(Charset.forName("UTF-8")));
                    }
                    return response;
                }
            });

            builder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addNetworkInterceptor(new StethoInterceptor())   //抓取数据
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            mOkHttpClient = builder.build();
        }
    }

    public static <T> T createApi(Class<T> clazz) {
        //初始化配置
        initConfig();
        Retrofit retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        return retrofit.create(clazz);
    }

    public static <T> T weatherCreateApi(Class<T> clazz) {
        //初始化配置
        initConfig();
        Retrofit retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(WEATHER_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        return retrofit.create(clazz);
    }

}
