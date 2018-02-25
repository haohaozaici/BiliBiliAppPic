package io.github.haohaozaici.bilibiliapppic;

import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.bean.SplashPicRes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by haoyuan on 2018/2/13.
 */

public class Holder<T> {

  private T type;
  {
    String[] strings =  {"1","2"};
     Arrays.asList(strings);

    Set<SplashPicRes> picResSet = new HashSet<>();
    picResSet.add(new SplashPicRes());
  }

}
