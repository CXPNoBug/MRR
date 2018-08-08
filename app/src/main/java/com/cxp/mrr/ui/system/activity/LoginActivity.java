package com.cxp.mrr.ui.system.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.cxp.mrr.R;
import com.cxp.mrr.base.BaseActivity;
import com.cxp.mrr.model.LoginModel;
import com.cxp.mrr.presenter.LoginPresenter;
import com.cxp.mrr.view.LoginView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @BindView(R.id.login_info)
    TextView mLoginInfo;

    private LoginPresenter mLoginPresenter;

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

        //登录接口
        loadLogin();

    }

    @Override
    public Map<String, Object> login() {
        Map<String, Object> map = new HashMap<>();
        map.put("loginType", "1");
        map.put("member_password", "111111qq");
        map.put("member_login_account", "18310140797");
        return map;
    }

    @Override
    public void getLogin(LoginModel loginModel) {
        mLoginInfo.setText("Res_Code:" + loginModel.getRes_Code() + "\nbANK_NAME:" + loginModel.getMember().getbANK_NAME());
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
}
