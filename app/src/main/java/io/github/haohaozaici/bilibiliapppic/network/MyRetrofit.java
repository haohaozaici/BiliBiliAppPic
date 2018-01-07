package io.github.haohaozaici.bilibiliapppic.network;

import io.github.haohaozaici.bilibiliapppic.BuildConfig;
import io.github.haohaozaici.bilibiliapppic.Constants;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haohao on 2017/11/8.
 */

public class MyRetrofit {

  private static Retrofit sRetrofit;

  private static Retrofit getRetrofit() {
    if (sRetrofit == null) {

      OkHttpClient.Builder builder = new OkHttpClient.Builder()
          .readTimeout(10, TimeUnit.SECONDS)
          .connectTimeout(10, TimeUnit.SECONDS);

      if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);
        builder.addInterceptor(interceptor);
//        builder.addInterceptor(new LoggingInterceptor());
      }

      sRetrofit = new Retrofit.Builder()
          .client(builder.build())
          .baseUrl(Constants.API_HOST)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return sRetrofit;
  }

  public static APIService getApiService(){
    return getRetrofit().create(APIService.class);
  }
}
