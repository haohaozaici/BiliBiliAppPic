package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import io.github.haohaozaici.bilibiliapppic.App;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import java.util.List;

/**
 * Created by haoyuan on 2018/2/24.
 */

public class PicInfoViewModel extends AndroidViewModel {

  private LiveData<List<BiliBiliAppPic>> mPicListLiveData;
  private PicInfoRepo picInfoRepo;


  public PicInfoViewModel(@NonNull Application application) {
    super(application);
    mPicListLiveData = App.getBiliPicDatabase().picDao().getAllPicsLiveData();
    picInfoRepo = new PicInfoRepo();
  }


  public void updatePicData() {
    picInfoRepo.syncPicInfo();
  }


  public LiveData<List<BiliBiliAppPic>> getPicListLiveData() {
    return mPicListLiveData;
  }

}
