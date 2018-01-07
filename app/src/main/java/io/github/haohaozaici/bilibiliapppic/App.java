package io.github.haohaozaici.bilibiliapppic;

import android.app.Application;
import com.blankj.utilcode.util.Utils;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Utils.init(this);

  }
}
