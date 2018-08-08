package com.cxp.mrr.view;


import com.cxp.mrr.model.LoginModel;

import java.util.Map;

/**
 * 文 件 名: LoginView
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 7:42
 * 描    述: 示例
 * 修 改 人:
 * 修改时：
 * 修改备注：
 */
public interface LoginView extends MvpView {

    //登录请求参
    Map<String,Object> login();

    //登录返回参
    void getLogin(LoginModel loginModel);
}
