package io.github.haohaozaici.bilibiliapppic;

import android.app.Application;
import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.BiliPicDatabase;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class App extends Application {

  private static BiliPicDatabase mBiliPicDatabase;

  @Override
  public void onCreate() {
    super.onCreate();
    Utils.init(this);
    XLog.init(new LogConfiguration.Builder().tag("bilibili_app_pic").build());

    mBiliPicDatabase = BiliPicDatabase.getInstance(this);

  }

  public static BiliPicDatabase getBiliPicDatabase() {
    return mBiliPicDatabase;
  }
}
