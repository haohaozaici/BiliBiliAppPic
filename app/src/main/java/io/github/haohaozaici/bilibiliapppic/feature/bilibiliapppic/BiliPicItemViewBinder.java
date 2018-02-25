package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by haoyuan on 2018/2/24.
 */
public class BiliPicItemViewBinder extends
    ItemViewBinder<BiliBiliAppPic, BiliPicItemViewBinder.ViewHolder> {

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_bili_pic_item, parent, false);
    return new ViewHolder(root);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BiliBiliAppPic bIliPicItem) {

  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
