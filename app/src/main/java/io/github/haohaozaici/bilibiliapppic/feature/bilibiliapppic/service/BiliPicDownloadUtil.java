package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.content.Context;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import io.github.haohaozaici.bilibiliapppic.App;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import io.github.haohaozaici.bilibiliapppic.util.FileUtil;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyuan on 2018/2/27.
 */

public class BiliPicDownloadUtil {

  private Context mContext;

  private static final String PATH =
      Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
          .getAbsolutePath() + File.separator + "biliSplash" + File.separator;


  public BiliPicDownloadUtil(Context context) {
    mContext = context;
    FileDownloader.setup(context);
  }


  //下载单个图片
  public void downloadPic(BiliBiliAppPic appPic) {

    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
    mBuilder.setContentTitle("封面图片下载")
        .setContentText("正在连接")
        .setSmallIcon(R.drawable.ic_cloud_circle_24dp)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    mBuilder.setProgress(0, 0, true);
    notificationManager.notify(appPic.getBilibiliId(), mBuilder.build());

    String[] fileNames = appPic.getImageUrl().split("/");
    String fileName = fileNames[fileNames.length - 1];

    FileDownloader.getImpl().create(appPic.getImageUrl())
        .setPath(PATH + fileName)
        // .setWifiRequired(true)
        .setListener(new FileDownloadListener() {
          @Override
          protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
          }


          @Override
          protected void connected(BaseDownloadTask task, String etag, boolean isContinue,
                                   int soFarBytes, int totalBytes) {
            int PROGRESS_MAX = 100;
            int PROGRESS_CURRENT = 0;
            mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
            notificationManager.notify(appPic.getBilibiliId(), mBuilder.build());
          }


          @Override
          protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            mBuilder.setContentText(String.format("%s/%s",
                FileUtil.humanReadableByteCount(soFarBytes, true),
                FileUtil.humanReadableByteCount(totalBytes, true)));
            mBuilder.setProgress(totalBytes, soFarBytes, false);
            notificationManager.notify(appPic.getBilibiliId(), mBuilder.build());
          }


          @Override
          protected void blockComplete(BaseDownloadTask task) {
          }


          @Override
          protected void retry(final BaseDownloadTask task, final Throwable ex,
                               final int retryingTimes, final int soFarBytes) {
          }


          @Override
          protected void completed(BaseDownloadTask task) {
            mBuilder.setContentText(String.format("图片%s下载完成  %s", appPic.getBilibiliId(), FileUtil.humanReadableByteCount(task.getSmallFileTotalBytes(), true)))
                .setProgress(0, 0, false);
            // .addAction(0, "完成", null);
            notificationManager.notify(appPic.getBilibiliId(), mBuilder.build());
            ToastUtils.showShort("已下载至%s", PATH);

            Observable.just(appPic)
                .subscribeOn(Schedulers.io())
                .subscribe(pic -> {
                  BiliBiliAppPic biliAppPic = App.getBiliPicDatabase().picDao().getPicById(pic.getBilibiliId());
                  biliAppPic.setDownload(BiliBiliAppPic.DOWNLOAD);
                  App.getBiliPicDatabase().picDao().insertOrReplace(biliAppPic);
                });

          }


          @Override
          protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
          }


          @Override
          protected void error(BaseDownloadTask task, Throwable e) {
          }


          @Override
          protected void warn(BaseDownloadTask task) {
          }
        }).start();

  }


  //开启全部下载服务
  public void downloadAllPics(List<BiliBiliAppPic> biliPicList) {

    final FileDownloadListener downloadListener = new FileDownloadListener() {
      @Override
      protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
      }


      @Override
      protected void connected(BaseDownloadTask task, String etag, boolean isContinue,
                               int soFarBytes, int totalBytes) {
      }


      @Override
      protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
      }


      @Override
      protected void blockComplete(BaseDownloadTask task) {
      }


      @Override
      protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes,
                           final int soFarBytes) {
      }


      @Override
      protected void completed(BaseDownloadTask task) {
      }


      @Override
      protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
      }


      @Override
      protected void error(BaseDownloadTask task, Throwable e) {
      }


      @Override
      protected void warn(BaseDownloadTask task) {
      }
    };

    final FileDownloadQueueSet queueSet = new FileDownloadQueueSet(downloadListener);
    final List<BaseDownloadTask> tasks = new ArrayList<>();
    for (BiliBiliAppPic pic : biliPicList) {
      String[] fileNames = pic.getImageUrl().split("/");
      String fileName = fileNames[fileNames.length - 1];
      tasks.add(FileDownloader.getImpl().create(pic.getImageUrl())
          .setPath(PATH + pic.getBilibiliId() + "_" + pic.getStartTime().substring(0, 10) + "_"
              + fileName)
          .setTag(pic.getBilibiliId()));
    }
    queueSet.disableCallbackProgressTimes();
    queueSet.setAutoRetryTimes(1);
    queueSet.downloadTogether(tasks);
    queueSet.start();
  }

}
