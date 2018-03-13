/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.request.target.Target;
import com.elvishew.xlog.XLog;
import io.github.haohaozaici.bilibiliapppic.GlideApp;
import io.github.haohaozaici.bilibiliapppic.MainActivity;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.PicInfoRepo;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.BiliPicDatabaseInfo;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.github.haohaozaici.bilibiliapppic.util.ImageUtil;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.ExecutionException;
import org.reactivestreams.Subscription;

public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    // TODO: 2018/1/29 sync remote data and show database info
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
            PicInfoRepo repo = new PicInfoRepo(context);
            repo.savePicData(splashPicRes);

            BiliPicDatabaseInfo databaseInfo = repo.getBiliPicDataBaseInfo();
            String infoString = String.format("已记录:%s张  已下载:%s张", databaseInfo.allCount, databaseInfo.download);
            String bigInfoString = String.format(infoString + "\n" + "同步图片:%s张  不感兴趣:%s张",
                splashPicRes.getData().size(), databaseInfo.hide);

            Intent tapIntent = new Intent(context, MainActivity.class);
            tapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, tapIntent, 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_cloud_circle_24dp)
                .setContentTitle("哔哩哔哩封面同步")
                .setContentText(infoString)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigInfoString))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC);
            // .addAction(0, "同步", null);

            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            if (StringUtils.isEmpty(databaseInfo.recentPicUrl)) {
              mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_demo_120px));
              manager.notify(0, mBuilder.build());
            } else {
              String formatPicUrl = ImageUtil.getWebp(databaseInfo.recentPicUrl, 64, 0);
              try {
                Bitmap bitmap = GlideApp.with(context)
                    .asBitmap()
                    .load(formatPicUrl)
                    .fitCenter()
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
                mBuilder.setLargeIcon(bitmap);
                manager.notify(0, mBuilder.build());
              } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
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
}