package com.cxp.mrr.ui.system.activity;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.cxp.mrr.R;
import com.cxp.mrr.base.BaseActivity;
import com.cxp.mrr.mvp.presenter.LoginPresenter;
import com.cxp.mrr.mvp.view.LoginView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * 文 件 名: LoginActivity
 * 创 建 人: CXP
 * 创建日期: 2017-12-04 16:40
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoginActivity extends BaseActivity implements LoginView {


    @BindView(R.id.login_img)
    ImageView mLoginImg;

    private LoginPresenter mLoginPresenter;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mLoginImg.setImageBitmap((Bitmap) msg.obj);

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化视图
        initView();

    }

    //初始化视图
    private void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);

//        urlToFile("http://trevorqt.xicp.net:56293/ernie/user/generateCode.ernie");


        //登录接口
//        loadLogin();

    }

    //登录
    private void loadLogin() {
        mLoginPresenter.login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //必须写，防止内存泄漏
        if (mLoginPresenter != null) {
            mLoginPresenter.unSubscribe();
            mLoginPresenter.detachView();
        }
    }

    @Override
    public void getCode(ResponseBody responseBody) {

    }

    private File urlToFile(final String url) {
        File file = null;
        URL imageurl = null;
        try {
            imageurl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();

            Message message = new Message();
            message.obj = bitmap;
            mHandler.sendMessage(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void clickLis(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                urlToFile("http://trevorqt.xicp.net:56293/ernie/user/generateCode.ernie");
            }
        }).start();
    }
}
