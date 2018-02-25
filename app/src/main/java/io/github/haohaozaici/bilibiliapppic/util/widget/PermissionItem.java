package io.github.haohaozaici.bilibiliapppic.util.widget;

import android.support.annotation.DrawableRes;

/**
 * Created by haoyuan on 2018/1/17.
 */
public class PermissionItem {

  @DrawableRes int icon;
  String title;
  String description;
  boolean allowed;

  public PermissionItem(@DrawableRes int icon, String title, String description, boolean allowed) {
    this.icon = icon;
    this.title = title;
    this.description = description;
    this.allowed = allowed;
  }
}