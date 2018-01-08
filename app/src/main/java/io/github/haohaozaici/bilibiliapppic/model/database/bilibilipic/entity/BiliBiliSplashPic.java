package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by haoyuan on 2018/1/8.
 */

@Entity
public class BiliBiliSplashPic {

  @PrimaryKey public int id;
  public int bilibiliId;
  public int animate;
  public int duration;
  public int startTime;
  public int endTime;
  public String image;
  public String key;
  public int times;
  public int type;
  public String param;
  public int skip;

}
