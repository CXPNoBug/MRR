package com.cxp.mrr.api;


import com.cxp.mrr.model.LoginModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

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

    //@QueryMap 传递多个参数  相当于在url后面跟上?key={key}&key1={key1}&key2={key2}...   例如：http://192.168.1.109:8080/CxpWeb/user/getUserNameGetQuery.jhtml?key={key}&key1={key1}&key2={key2}...
    @POST("member/logInAPP")
    Observable<LoginModel> login(@QueryMap Map<String, Object> map);
}
