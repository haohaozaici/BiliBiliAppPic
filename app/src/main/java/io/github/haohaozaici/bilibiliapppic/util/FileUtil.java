package io.github.haohaozaici.bilibiliapppic.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;

/**
 * Created by haoyuan on 2018/2/27.
 */

public class FileUtil {

  public static String humanReadableByteCount(long bytes, boolean si) {
    int unit = si ? 1000 : 1024;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  }


  /**
   * 通知媒体库更新文件
   *
   * @param context context
   * @param filePath 文件全路径
   */
  public static void scanFile(Context context, String filePath) {
    Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    scanIntent.setData(Uri.fromFile(new File(filePath)));
    context.sendBroadcast(scanIntent);
  }

}
