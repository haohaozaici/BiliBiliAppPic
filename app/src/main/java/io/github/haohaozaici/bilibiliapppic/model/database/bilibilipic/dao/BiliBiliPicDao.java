package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliSplashPic;

/**
 * Created by haoyuan on 2018/1/8.
 */

@Dao
public interface BiliBiliPicDao {

  @Insert(onConflict = REPLACE)
  void save(BiliBiliSplashPic pic);

  @Query("SELECT * FROM bilibilisplashpic WHERE id = :bilibiliId")
  LiveData<BiliBiliSplashPic> load(String bilibiliId);
}
