package io.github.haohaozaici.bilibiliapppic.feature.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.haohaozaici.bilibiliapppic.MainActivity;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service.AlarmReceiver;
import java.util.UUID;

/**
 * Created by haoyuan on 2018/2/27.
 */

public class NotificationFragment extends Fragment {

  public static final String CHANNEL_0 = "0";
  private String ACTION_ALARM = "io.github.haohaozaici.bilibiliapppic.alarm";


  @BindView(R.id.create_notification) TextView createNotification;
  @BindView(R.id.create_progress_notification) TextView create_progress_notification;

  public static NotificationFragment newInstance() {
    return new NotificationFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.notification_layout, container, false);
    ButterKnife.bind(this, view);

    createNotification.setOnClickListener(v -> {

      Intent intent = new Intent(v.getContext(), MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(v.getContext(), 0, intent, 0);

      NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(v.getContext())
          .setSmallIcon(R.drawable.ic_cloud_circle_24dp)
          .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pic_demo_120px))
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

      NotificationManagerCompat manager = NotificationManagerCompat.from(v.getContext());
      manager.notify(UUID.randomUUID().hashCode(), mBuilder.build());

      Intent snoozeIntent = new Intent(v.getContext(), AlarmReceiver.class);
      snoozeIntent.setAction(ACTION_ALARM);
//      snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
      PendingIntent snoozePendingIntent =
          PendingIntent.getBroadcast(v.getContext(), 0, snoozeIntent, 0);

    });

    create_progress_notification.setOnClickListener(v -> {

      NotificationManagerCompat notificationManager = NotificationManagerCompat.from(v.getContext());
      NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(v.getContext());
      mBuilder.setContentTitle("Picture Download")
          .setContentText("Download in progress")
          .setSmallIcon(R.drawable.ic_cloud_circle_24dp)
          .setPriority(NotificationCompat.PRIORITY_LOW);

      int PROGRESS_MAX = 100;
      int PROGRESS_CURRENT = 0;
      mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
      notificationManager.notify(0, mBuilder.build());

      // Do the job here that tracks the progress.
      // Usually, this should be in a worker thread
      // To show progress, update PROGRESS_CURRENT and update the notification with:
      // mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
      // notificationManager.notify(notificationId, mBuilder.build());

      // When done, update the notification one more time to remove the progress bar
      mBuilder.setContentText("Download complete")
          .setProgress(0,0,false);
      notificationManager.notify(0, mBuilder.build());


    });

    return view;
  }
}
