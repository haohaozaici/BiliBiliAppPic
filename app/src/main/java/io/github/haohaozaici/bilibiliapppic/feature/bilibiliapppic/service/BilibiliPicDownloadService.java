package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;

import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes.DataBean;
import io.github.haohaozaici.bilibiliapppic.model.database.MyDatabase;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

import org.reactivestreams.Subscription;

/**
 * Created by haoyuan on 2018/1/8.
 */

public class BilibiliPicDownloadService extends Service {

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
                            for (DataBean bean : dataList) {
                                BiliBiliAppPic biliAppPic = new BiliBiliAppPic();
                                biliAppPic.bilibiliId = bean.getId();
                                biliAppPic.startTime = TimeUtils.millis2String(bean.getStart_time() * 1000L);
                                biliAppPic.endTime = TimeUtils.millis2String(bean.getEnd_time() * 1000L);
                                biliAppPic.image = bean.getImage();
                                biliAppPic.download = false;

                                XLog.tag("BilibiliPicDownloadService").json(new Gson().toJson(biliAppPic));
                                MyDatabase.getInstance(getApplicationContext()).mBiliPicDao().saveOrReplace(biliAppPic);
                            }

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
