package io.github.haohaozaici.bilibiliapppic.util;

import com.blankj.utilcode.util.ConvertUtils;

/**
 * Created by haoyuan on 2018/3/12.
 */

public class ImageUtil {

  public static String getWebp(String imgUrl, float width, float height) {
    return String.format(imgUrl + "@%sw_%sh.webp",
        ConvertUtils.dp2px(width),
        ConvertUtils.dp2px(height));
  }
}
