package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import java.util.List;

/**
 * Created by haoyuan on 2018/1/8.
 */

@Dao
public interface BiliBiliPicDao {

  @Insert(onConflict = REPLACE)
  void saveOrReplace(BiliBiliAppPic pic);

  @Update
  void update(BiliBiliAppPic... pics);

  @Delete
  void delete(BiliBiliAppPic... pics);

  @Query("SELECT * FROM BiliBiliAppPic WHERE id = :bilibiliId")
  LiveData<BiliBiliAppPic> loadById(String bilibiliId);

  @Query("SELECT * FROM BILIBILIAPPPIC")
  LiveData<List<BiliBiliAppPic>> loadAll();
}
