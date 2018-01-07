package io.github.haohaozaici.bilibiliapppic.network.httpdns;

import android.support.annotation.NonNull;
import android.util.Log;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import okhttp3.Dns;

/**
 * Created by haohao on 2017/12/20.
 */

public class HttpDns implements Dns {

  private static final String TAG = "HttpDns";

  @Override
  public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {

    // TODO: 2017/12/20 getHttpDns
    String httpDns = "114.55.82.180";
//    httpDns = "114.55.82.18";

    if (httpDns != null && !httpDns.equals("")) {
      InetAddress[] inetAddresses = InetAddress.getAllByName(httpDns);
      Log.d(TAG, "lookup:" + inetAddresses[0]);

      return Arrays.asList(inetAddresses);
    } else {
      return SYSTEM.lookup(hostname);
    }

  }
}
