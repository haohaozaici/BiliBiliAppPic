package io.github.haohaozaici.bilibiliapppic;

import android.app.Application;
import android.content.Intent;

import com.blankj.utilcode.util.Utils;

import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service.BilibiliPicDownloadService;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);

        startService(new Intent(this, BilibiliPicDownloadService.class));
    }
}
