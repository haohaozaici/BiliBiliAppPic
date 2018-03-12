package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.liulishuo.filedownloader.FileDownloader;

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
