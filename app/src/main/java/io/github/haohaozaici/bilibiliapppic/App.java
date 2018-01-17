package io.github.haohaozaici.bilibiliapppic;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service.BilibiliPicDownloadService;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);

        // 在其它任何地方：



    }
}
