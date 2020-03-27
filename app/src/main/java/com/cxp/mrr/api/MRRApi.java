package com.cxp.mrr.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * 文 件 名: MRRApi
 * 创 建 人: CXP
 * 创建日期: 2017-12-04 16:11
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface MRRApi {

    @GET("ernie/user/generateCode.ernie")
    Observable<ResponseBody> getCode();
}
