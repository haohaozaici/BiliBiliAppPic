package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import com.elvishew.xlog.XLog;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao.PicDao;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;

/**
 * Created by haoyuan on 2018/2/24.
 */

@Database(entities = { BiliBiliAppPic.class }, version = 1)
public abstract class BiliPicDatabase extends RoomDatabase {

  private static BiliPicDatabase sInstance;


  public abstract PicDao picDao();


  public synchronized static BiliPicDatabase getInstance(final Context context) {
    if (sInstance == null) {
      sInstance = Room.databaseBuilder(context, BiliPicDatabase.class, "database-name")
          .addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db1) {
              super.onCreate(db1);
              XLog.d("BiliPicDatabase 创建成功  version=%s", db1.getVersion());
            }


            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db1) {
              super.onOpen(db1);
              XLog.d("BiliPicDatabase 已装载  version=%s", db1.getVersion());
            }
          })
          .fallbackToDestructiveMigration()
          .build();
    }
    return sInstance;
  }


  static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      // Since we didn't alter the table, there's nothing else to do here.
    }
  };

}
