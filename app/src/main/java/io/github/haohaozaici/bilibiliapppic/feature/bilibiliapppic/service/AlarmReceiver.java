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
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import io.github.haohaozaici.bilibiliapppic.MainActivity;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.PicInfoRepo;
import java.util.UUID;

/**
 * Created by drakeet on 7/1/15.
 */
public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    // TODO: 2018/1/29 sync remote data and show database info
    PicInfoRepo repo = new PicInfoRepo(context);


    Intent tapIntent = new Intent(context, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, tapIntent, 0);

    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
        .setSmallIcon(R.drawable.ic_cloud_circle_24dp)
        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_demo_120px))
        .setContentTitle("哔哩哔哩封面同步")
        .setContentText("已记录:100张  已下载:10张")
//          .setStyle(new NotificationCompat.BigTextStyle()
//              .bigText("已记录:100张  已下载:10张\n"
//                  + "已记录:100张  已下载:10张"))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setVisibility(Notification.VISIBILITY_PUBLIC)
        .addAction(0, "同步", null);

    NotificationManagerCompat manager = NotificationManagerCompat.from(context);
    manager.notify(UUID.randomUUID().hashCode(), mBuilder.build());

    context.startService(new Intent(context, BiliPicDownloadService.class));

//    PreferencesLoader loader = new PreferencesLoader(context);
//    if (loader.getBoolean(R.string.action_notifiable, true)) {
//      HeadsUps.show(context, MainActivity.class,
//          context.getString(R.string.heads_up_title),
//          context.getString(R.string.heads_up_content),
//          R.mipmap.ic_meizhi_150602, R.mipmap.ic_female, 123123);
//    }
  }
}