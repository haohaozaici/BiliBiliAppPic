package io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by haoyuan on 2018/1/8.
 */

@Entity(tableName = "bilibili_pics")
public class BiliBiliAppPic {

  public static final int DOWNLOAD = 1;
  public static final int HIDED = 1;

  @PrimaryKey
  private int bilibiliId;
  private String startTime;
  private String endTime;
  private String imageUrl;
  private String linkedUrl;
  private String size;
  private int download;
  private int hide;


  public BiliBiliAppPic(int bilibiliId, String startTime, String endTime, String imageUrl, String linkedUrl, String size, int download, int hide) {
    this.bilibiliId = bilibiliId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.imageUrl = imageUrl;
    this.linkedUrl = linkedUrl;
    this.size = size;
    this.download = download;
    this.hide = hide;
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


  public String getSize() {
    return size;
  }


  public void setSize(String size) {
    this.size = size;
  }


  public int getDownload() {
    return download;
  }


  public void setDownload(int download) {
    this.download = download;
  }


  public int getHide() {
    return hide;
  }


  public void setHide(int hide) {
    this.hide = hide;
  }
}
