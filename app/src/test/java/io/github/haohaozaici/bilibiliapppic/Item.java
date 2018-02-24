package io.github.haohaozaici.bilibiliapppic;

/**
 * Created by haoyuan on 2018/2/12.
 */

public class Item {

  public static class Factory implements io.github.haohaozaici.bilibiliapppic.Factory<Item> {

    @Override
    public Item create() {
      return new Item();
    }
  }

}
