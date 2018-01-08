package io.github.haohaozaici.bilibiliapppic.model.database;

import android.arch.persistence.room.RoomDatabase;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao.BiliBiliPicDao;

/**
 * Created by haoyuan on 2018/1/8.
 */


public abstract class MyDatabase extends RoomDatabase {

  public abstract BiliBiliPicDao mBiliPicDao();

}
