package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import org.reactivestreams.Subscription;

/**
 * Created by haoyuan on 2018/1/8.
 */

public class BiliPicDownloadService extends Service {

  private static final String TAG = "biliPicDownloadService";


  @Override
  public void onCreate() {
    super.onCreate();
    FileDownloader.setup(this);
  }


  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }


  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    BiliPicDownloadUtil util = new BiliPicDownloadUtil(this);
    int id = intent.getIntExtra("id", 0);
    String url = intent.getStringExtra("url");
    // util.downloadPic(id, url);
    // stopSelf();

    return super.onStartCommand(intent, flags, startId);
  }

}
