package io.github.haohaozaici.bilibiliapppic.feature.splashpic.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import io.github.haohaozaici.bilibiliapppic.feature.splashpic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.feature.splashpic.bean.SplashPicRes.DataBean;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.reactivestreams.Subscription;

/**
 * Created by haoyuan on 2018/1/8.
 */

public class SplashPicSyncService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    MyRetrofit.getApiService().getSplashPic()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(new FlowableSubscriber<SplashPicRes>() {
          @Override
          public void onSubscribe(Subscription s) {
            s.request(1);
          }

          @Override
          public void onNext(SplashPicRes splashPicRes) {
            if (splashPicRes.getCode() == 0) {
              // TODO: 2018/1/8 save to database
              List<DataBean> dataList = splashPicRes.getData();


            }

          }

          @Override
          public void onError(Throwable t) {

          }

          @Override
          public void onComplete() {

          }
        });

    return super.onStartCommand(intent, flags, startId);
  }

}
