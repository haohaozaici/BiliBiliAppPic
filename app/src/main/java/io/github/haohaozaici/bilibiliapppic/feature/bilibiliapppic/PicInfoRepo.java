package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.content.Context;
import com.blankj.utilcode.util.TimeUtils;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import io.github.haohaozaici.bilibiliapppic.App;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.BiliPicDatabaseInfo;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao.PicDao;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.reactivestreams.Subscription;

/**
 * Created by haoyuan on 2018/2/27.
 */

public class PicInfoRepo {

  private Context mContext;

  public PicInfoRepo(Context context) {
    mContext = context;
  }

  private PicDao mPicDao = App.getBiliPicDatabase().picDao();

  //同步最新图片信息
  public void syncPicInfo() {

    MyRetrofit.getApiService().getSplashPic().subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(new FlowableSubscriber<SplashPicRes>() {
          @Override
          public void onSubscribe(Subscription s) {
            s.request(1);
          }

          @Override
          public void onNext(SplashPicRes splashPicRes) {
            if (!splashPicRes.getData().isEmpty()) {
              for (SplashPicRes.DataBean bilibiliPic : splashPicRes.getData()) {
                BiliBiliAppPic existPic = mPicDao.getPicById(bilibiliPic.getId());
                if (existPic == null) {
                  BiliBiliAppPic pic = new BiliBiliAppPic(bilibiliPic.getId(),
                      TimeUtils.millis2String(bilibiliPic.getStart_time() * 1000L),
                      TimeUtils.millis2String(bilibiliPic.getEnd_time() * 1000L),
                      bilibiliPic.getImage(),
                      bilibiliPic.getParam(),
                      null,
                      false);
                  mPicDao.insert(pic);
                  XLog.d("---------同步最新图片信息成功---------");
                  XLog.json(new Gson().toJson(pic));
                }
              }
            }
          }

          @Override
          public void onError(Throwable t) {
            XLog.d("---------同步最新图片信息失败---------", t);
          }

          @Override
          public void onComplete() {

          }
        });

  }

  //获取数据库统计信息
  public BiliPicDatabaseInfo getBiliPicDataBaseInfo() {
    List<BiliBiliAppPic> biliPicList = mPicDao.getAllPics();

    BiliPicDatabaseInfo picDatabaseInfo = new BiliPicDatabaseInfo();
    picDatabaseInfo.allCount = biliPicList.size();

    for (BiliBiliAppPic pic : biliPicList) {
      if (!pic.isDownload()) {
        picDatabaseInfo.notDownload++;
      }
    }

    return picDatabaseInfo;
  }


}
