package com.cxp.mrr.presenter;

import android.util.Log;

import com.cxp.mrr.api.RxApi;
import com.cxp.mrr.api.RxClient;
import com.cxp.mrr.base.BasePresenter;
import com.cxp.mrr.model.ResponseModel;
import com.cxp.mrr.utils.ConstantUtils;
import com.cxp.mrr.view.RxView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 文 件 名: RxPresenter
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 7:41
 * 描    述: 逻辑处理 示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxPresenter extends BasePresenter<RxView> {

    //新建订阅
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void attachView(RxView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    //普通请求
    public void loadGetListUsers() {
        RxClient.createApi(RxApi.class)
                .getListUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
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

    //占位符请求
    public void loadGetUserQuery() {
        RxClient.createApi(RxApi.class)
                .getUserQuery("getUserNameGet.jhtml")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
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

    //@Query 传参请求
    public void loadGetUserQuerys() {
        RxClient.createApi(RxApi.class)
                .getUserQuerys("CXP")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
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

    //@Filed 表单查询
    public void loadFiledQuery() {
        RxClient.createApi(RxApi.class)
                .filedQuery("程小鹏")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
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

    //@FiledMap 表单多个参数查询
    public void loadFiledQueryMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "程小鹏");
        map.put("title", "哈哈。");
        map.put("pwd", "123456asdfadsf");
        RxClient.createApi(RxApi.class)
                .filedQueryMap(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseModel.toString());
                        getMvpView().getData(responseModel.toString());
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

    //@QueryMap 传递多个参数
    public void loadGetUserQueryMap() {
        RxClient.createApi(RxApi.class)
                .getUserQueryMap(getMvpView().mapDatas())

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.e(ConstantUtils.TAG_SUCCESS, responseBody.toString());
                        try {
                            getMvpView().getData(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

    //单文件上传
    public void loadFileUpload() {
        RxClient.createApi(RxApi.class)
                .uploadFile(getMvpView().uploadFile(), "CXP")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(ConstantUtils.TAG_SUCCESS, s);
                        getMvpView().getData(s);
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

    //多文件上传
    public void loadFileUploadMap() {
        RxClient.createApi(RxApi.class)
                .uploadFiles(getMvpView().uploadFiles(), "CXP")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(ConstantUtils.TAG_SUCCESS, s);
                        getMvpView().getData(s);
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
