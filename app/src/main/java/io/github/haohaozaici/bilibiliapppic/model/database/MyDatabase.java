package io.github.haohaozaici.bilibiliapppic.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import io.github.haohaozaici.bilibiliapppic.BuildConfig;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.dao.BiliBiliPicDao;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;

/**
 * Created by haoyuan on 2018/1/8.
 */


@Database(entities = {BiliBiliAppPic.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract BiliBiliPicDao mBiliPicDao();

    private static volatile MyDatabase INSTANCE;


    public static MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Builder<MyDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "bilibili-app-pic.db");

                    if (BuildConfig.DEBUG) {
                        builder.fallbackToDestructiveMigration();
                    } else {
                        builder.addMigrations();
                    }

                    INSTANCE = builder.build();
                }
            }
        }
        return INSTANCE;
    }


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_ANY = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Create the new table
            database.execSQL("CREATE TABLE users_new (userid TEXT, username TEXT, last_update INTEGER, PRIMARY KEY(userid))");

            // Copy the data
            database.execSQL("INSERT INTO users_new (userid, username, last_update) SELECT userid, username, last_update FROM users");

            // Remove the old table
            database.execSQL("DROP TABLE users");

            // Change the table name to the correct one
            database.execSQL("ALTER TABLE users_new RENAME TO users");
        }
    };

}
