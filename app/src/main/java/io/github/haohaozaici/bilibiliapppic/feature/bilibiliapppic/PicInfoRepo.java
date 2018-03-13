package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

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

  private PicDao mPicDao;


  public PicInfoRepo() {
    mPicDao = App.getBiliPicDatabase().picDao();
  }


  //同步最新图片信息
  public void syncPicInfo() {

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
            savePicData(splashPicRes);
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


  public void savePicData(SplashPicRes splashPicRes) {
    XLog.d("---------同步最新图片信息成功---------");
    if (!splashPicRes.getData().isEmpty()) {
      for (SplashPicRes.DataBean biliPic : splashPicRes.getData()) {
        BiliBiliAppPic existPic = mPicDao.getPicById(biliPic.getId());
        if (existPic == null) {
          BiliBiliAppPic pic = new BiliBiliAppPic(biliPic.getId(),
              TimeUtils.millis2String(biliPic.getStart_time() * 1000L),
              TimeUtils.millis2String(biliPic.getEnd_time() * 1000L),
              biliPic.getImage(),
              biliPic.getParam(),
              null,
              0,
              0);
          mPicDao.insert(pic);
          XLog.json(new Gson().toJson(pic));
        }
      }
    }
  }


  //获取数据库统计信息
  public BiliPicDatabaseInfo getBiliPicDataBaseInfo() {
    List<BiliBiliAppPic> biliPicList = mPicDao.getAllPics();

    BiliPicDatabaseInfo picDatabaseInfo = new BiliPicDatabaseInfo();
    if (!biliPicList.isEmpty()) {
      picDatabaseInfo.allCount = biliPicList.size();
      picDatabaseInfo.recentPicUrl = biliPicList.get(0).getImageUrl();
      for (BiliBiliAppPic pic : biliPicList) {
        if (pic.getDownload() == BiliBiliAppPic.DOWNLOAD) {
          picDatabaseInfo.download++;
        }
        if (pic.getHide() == BiliBiliAppPic.HIDED) {
          picDatabaseInfo.hide++;
        }
      }
    }
    return picDatabaseInfo;
  }

}
