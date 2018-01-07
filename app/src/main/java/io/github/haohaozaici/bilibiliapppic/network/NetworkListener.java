package io.github.haohaozaici.bilibiliapppic.network;

/**
 * Created by haohao on 2017/11/9.
 */

public interface NetworkListener {

  void onError(Throwable e);

  void onNext(Object object);

}
