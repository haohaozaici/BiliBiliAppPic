package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import java.util.List;

/**
 * Created by haoyuan on 2018/2/24.
 */

@Dao
public interface PicDao {

  @Query("SELECT * FROM bilibili_pics ORDER BY bilibiliId DESC")
  List<BiliBiliAppPic> getAllPics();

  @Query("SELECT * FROM bilibili_pics where hide != 1 ORDER BY bilibiliId DESC")
  LiveData<List<BiliBiliAppPic>> getAllPicsLiveData();

  @Query("SELECT * FROM bilibili_pics WHERE bilibiliId = :bilibiliId")
  BiliBiliAppPic getPicById(int bilibiliId);

  @Insert()
  void insert(BiliBiliAppPic pic);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertOrReplace(BiliBiliAppPic pic);

  @Update
  void update(BiliBiliAppPic pic);

  @Delete
  void delete(BiliBiliAppPic pic);

}
