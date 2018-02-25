package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.github.haohaozaici.bilibiliapppic.App;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import java.util.List;

/**
 * Created by haoyuan on 2018/2/24.
 */

public class PicInfoViewModel extends ViewModel {

  private LiveData<List<BiliBiliAppPic>> mPicListLiveData = new MutableLiveData<>();

  public PicInfoViewModel() {
    mPicListLiveData = App.mBiliPicDatabase.picDao().getAllPics();
  }

  public LiveData<List<BiliBiliAppPic>> getPicListLiveData() {
    return mPicListLiveData;
  }
}
