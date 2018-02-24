package io.github.haohaozaici.bilibiliapppic.util;

/**
 * Created by haoyuan on 2018/2/7.
 */

public class DiffUtil extends android.support.v7.util.DiffUtil.Callback {

  @Override
  public int getOldListSize() {
    return 0;
  }

  @Override
  public int getNewListSize() {
    return 0;
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return false;
  }

  @Override
  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return false;
  }
}
