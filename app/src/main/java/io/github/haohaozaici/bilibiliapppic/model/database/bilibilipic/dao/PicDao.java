package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import java.util.List;

/**
 * Created by haoyuan on 2018/2/24.
 */

@Dao
public interface PicDao {

  @Query("SELECT * from bilibili_pics")
  List<BiliBiliAppPic> getPics();
}
