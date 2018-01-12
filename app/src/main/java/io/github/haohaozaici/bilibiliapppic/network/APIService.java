package io.github.haohaozaici.bilibiliapppic.network;

import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by haohao on 2017/11/8.
 */

public interface APIService {

  String BiliBili_API_HOST = "http://app.bilibili.com/";


  @GET("/x/splash?plat=0&width=1080&height=1920")
  Flowable<SplashPicRes> getSplashPic();

}
