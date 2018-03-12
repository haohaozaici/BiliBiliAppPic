package io.github.haohaozaici.bilibiliapppic;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.yanzhenjie.permission.Permission;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.BiliPicDatabase;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class App extends Application {

  public static BiliPicDatabase mBiliPicDatabase;


  @Override
  public void onCreate() {
    super.onCreate();
    Utils.init(this);
    XLog.init(new LogConfiguration.Builder().tag("bilibili_app_pic").build());

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (checkSelfPermission(Permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        mBiliPicDatabase = BiliPicDatabase.getInstance(this);
      }
    }
  }


  public static BiliPicDatabase getBiliPicDatabase() {
    return mBiliPicDatabase;
  }
}
