package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by haoyuan on 2018/1/8.
 */

@Entity(tableName = "bilibili_pics")
public class BiliBiliAppPic {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int bilibiliId;
  private String startTime;
  private String endTime;
  private String imageUrl;
  private String linkedUrl;
  private boolean download;

  public BiliBiliAppPic(int bilibiliId, String startTime, String endTime, String imageUrl,
      String linkedUrl, boolean download) {
    this.bilibiliId = bilibiliId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.imageUrl = imageUrl;
    this.linkedUrl = linkedUrl;
    this.download = download;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBilibiliId() {
    return bilibiliId;
  }

  public void setBilibiliId(int bilibiliId) {
    this.bilibiliId = bilibiliId;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getLinkedUrl() {
    return linkedUrl;
  }

  public void setLinkedUrl(String linkedUrl) {
    this.linkedUrl = linkedUrl;
  }

  public boolean isDownload() {
    return download;
  }

  public void setDownload(boolean download) {
    this.download = download;
  }
}
