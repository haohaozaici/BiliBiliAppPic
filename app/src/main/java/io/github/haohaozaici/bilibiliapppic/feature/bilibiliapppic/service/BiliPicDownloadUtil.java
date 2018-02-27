package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service;

import android.content.Context;
import android.os.Environment;
import com.blankj.utilcode.util.FileIOUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import io.github.haohaozaici.bilibiliapppic.util.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyuan on 2018/2/27.
 */

public class BiliPicDownloadUtil {

  private static final String PATH =
      Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
          .getAbsolutePath() + File.separator + "BilibiliAppPic" + File.separator;

  public BiliPicDownloadUtil(Context context) {
    FileDownloader.setup(context);
  }

  public void getPicTotalBytes(String url, PicTotalBytes picTotalBytes) {
    FileDownloader.getImpl().create(url)
        .setPath(PATH)
        .setListener(new FileDownloadListener() {
          @Override
          protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
          }

          @Override
          protected void connected(BaseDownloadTask task, String etag, boolean isContinue,
              int soFarBytes, int totalBytes) {
            task.pause();
            picTotalBytes.picSize(FileUtil.humanReadableByteCount(totalBytes, true));
          }

          @Override
          protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
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

  public interface PicTotalBytes {

    void picSize(String picSize);
  }

  //下载单个图片
  public void downloadPic(String url) {
    FileDownloader.getImpl().create(url)
        .setPath(PATH)
        .setListener(new FileDownloadListener() {
          @Override
          protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
          }

          @Override
          protected void connected(BaseDownloadTask task, String etag, boolean isContinue,
              int soFarBytes, int totalBytes) {
            task.pause();
          }

          @Override
          protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
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
