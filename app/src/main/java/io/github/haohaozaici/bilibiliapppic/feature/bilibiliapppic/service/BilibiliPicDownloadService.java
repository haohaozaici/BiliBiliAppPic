package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import org.reactivestreams.Subscription;

/**
 * Created by haoyuan on 2018/1/8.
 */

public class BilibiliPicDownloadService extends Service {

  private static final String TAG = "BilibiliPicDownloadServ";

  public static final String PATH =
      Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
          .getAbsolutePath() + File.separator + "BilibiliAppPic" + File.separator;


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
//            if (splashPicRes.getCode() == 0) {
//              List<DataBean> dataList = splashPicRes.getData();
//              for (DataBean bean : dataList) {
//
//
//                BiliBiliAppPic biliAppPic = new BiliBiliAppPic();
//                biliAppPic.bilibiliId = bean.getId();
//                biliAppPic.startTime = TimeUtils.millis2String(bean.getStart_time() * 1000L);
//                biliAppPic.endTime = TimeUtils.millis2String(bean.getEnd_time() * 1000L);
//                biliAppPic.image = bean.getImage();
//                biliAppPic.download = false;
//
//                XLog.tag("BilibiliPicDownloadService").json(new Gson().toJson(biliAppPic));
//              }
//
//            }
//
//            // TODO: 2018/1/17 check download
//            if (NetworkUtils.isWifiConnected()) {
//              List<BiliBiliAppPic> biliPicList = biliBiliPicDao.loadAll();
//
//              if (!biliPicList.isEmpty()) {
//                final FileDownloadQueueSet queueSet = new FileDownloadQueueSet(queueTarget);
//
//                final List<BaseDownloadTask> tasks = new ArrayList<>();
//                for (BiliBiliAppPic pic : biliPicList) {
//                  if (pic.download) {
//                  } else {
//                    String[] fileNames = pic.image.split("/");
//                    String fileName = fileNames[fileNames.length - 1];
//
//                    tasks.add(FileDownloader.getImpl().create(pic.image)
//                        .setPath(PATH + pic.bilibiliId + "_" + pic.startTime.substring(0, 10) + "_"
//                            + fileName)
//                        .setTag(pic.bilibiliId));
//                  }
//
//                }
//
//                if (!tasks.isEmpty()) {
//                  // 由于是队列任务, 这里是我们假设了现在不需要每个任务都回调`FileDownloadListener#progress`,
//                  // 我们只关系每个任务是否完成, 所以这里这样设置可以很有效的减少ipc.
//                  queueSet.disableCallbackProgressTimes();
//                  queueSet.setAutoRetryTimes(1);
//                  queueSet.downloadTogether(tasks);
//                  queueSet.start();
//                } else {
//                  // TODO: 2018/1/17 all download completed
//                }
//
//              } else {
//                // TODO: 2018/1/17 database has no record
//
//              }
//
//            } else {
//              // TODO: 2018/1/17 wifi is not connected
//
//            }

          }

          @Override
          public void onError(Throwable t) {

          }

          @Override
          public void onComplete() {

          }
        });

    return super.onStartCommand(intent, flags, startId);
  }


  final FileDownloadListener queueTarget = new FileDownloadListener() {
    @Override
    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes,
        int totalBytes) {
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
      Log.d(TAG, "completed: " + task.getTag());

//      Observable.create((ObservableOnSubscribe<BiliBiliAppPic>) e -> {
//        BiliBiliAppPic pic = biliBiliPicDao.loadById(task.getTag() + "");
//        pic.download = true;
//        biliBiliPicDao.update(pic);
//        e.onNext(pic);
//      }).subscribeOn(Schedulers.io())
//          .observeOn(AndroidSchedulers.mainThread())
//          .subscribe(new Observer<BiliBiliAppPic>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(BiliBiliAppPic pic) {
//              // TODO: 2018/1/17 notify download completed
//
//              File file = new File(task.getTargetFilePath() + File.separator + task.getFilename());
//              Uri uri = Uri.fromFile(file);
//              // 通知图库更新
//              Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
//              sendBroadcast(scannerIntent);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//          });

    }

    @Override
    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
      Log.d(TAG, "error: " + task.getTag() + "   " + e.getMessage());
    }

    @Override
    protected void warn(BaseDownloadTask task) {
    }
  };

}
