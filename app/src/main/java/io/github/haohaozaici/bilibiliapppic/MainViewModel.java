package io.github.haohaozaici.bilibiliapppic;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * Created by haoyuan on 2018/1/29.
 */

public class MainViewModel extends AndroidViewModel {

  private MainRepo mRepo = new MainRepo();


  public MainViewModel(@NonNull Application application) {
    super(application);

  }

}
