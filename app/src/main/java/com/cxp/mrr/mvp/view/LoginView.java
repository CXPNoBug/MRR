package com.cxp.mrr.mvp.view;


import okhttp3.ResponseBody;

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


    //返回参
    void getCode(ResponseBody responseBody);
}
