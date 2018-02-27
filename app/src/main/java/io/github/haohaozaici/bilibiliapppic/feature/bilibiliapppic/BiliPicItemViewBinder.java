package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.bili_pic_item_iv) ImageView pic;
    @BindView(R.id.bili_pic_item_id) TextView id;
    @BindView(R.id.bili_pic_item_start_time) TextView start_time;
    @BindView(R.id.bili_pic_item_size_text) TextView size;
    @BindView(R.id.bili_pic_item_download) TextView download;
    @BindView(R.id.bili_pic_item_detail) TextView detail;


    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      detail.setOnClickListener(v -> {
        // TODO: 2018/2/27 to detail web

      });
    }
  }
}
