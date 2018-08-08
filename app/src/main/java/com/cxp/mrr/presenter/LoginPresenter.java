package com.cxp.mrr.presenter;

import android.util.Log;

import com.cxp.mrr.api.MRRApi;
import com.cxp.mrr.api.RxClient;
import com.cxp.mrr.base.BasePresenter;
import com.cxp.mrr.model.LoginModel;
import com.cxp.mrr.utils.ConstantUtils;
import com.cxp.mrr.view.LoginView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 文 件 名: LoginPresenter
 * 创 建 人: CXP
 * 创建日期: 2017-12-04 16:14
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    //新建订阅
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void attachView(LoginView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    //@QueryMap 传递多个参数
    public void login() {
        RxClient.createApi(MRRApi.class).
                login(getMvpView().login())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        getMvpView().getLogin(loginModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(ConstantUtils.TAG_ERROR, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    //取消订阅
    public void unSubscribe() {
        mDisposable.clear();
    }

}
