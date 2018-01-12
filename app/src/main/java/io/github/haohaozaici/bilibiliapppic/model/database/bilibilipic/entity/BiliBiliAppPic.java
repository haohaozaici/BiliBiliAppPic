package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by haoyuan on 2018/1/8.
 */

@Entity(indices = {@Index(value = "bilibiliId", unique = true)})
public class BiliBiliAppPic {

  @PrimaryKey public int id;
  public int bilibiliId;
  public int animate;
  public int duration;
  public String startTime;
  public String endTime;
  public String image;
  public String key;
  public int times;
  public int type;
  public String param;
  public int skip;
  public boolean download;

}
