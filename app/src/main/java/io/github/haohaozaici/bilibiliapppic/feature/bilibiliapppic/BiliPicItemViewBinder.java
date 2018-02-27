package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.SnackbarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import io.github.haohaozaici.bilibiliapppic.GlideApp;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service.BiliPicDownloadUtil;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service.BiliPicDownloadUtil.PicTotalBytes;
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
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BiliBiliAppPic biliPicItem) {

    GlideApp.with(holder.pic)
        .load(biliPicItem.getImageUrl())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(holder.pic);

    holder.id.setText("id = " + biliPicItem.getBilibiliId());
    holder.start_time.setText(biliPicItem.getStartTime().substring(0, 10));
    holder.size.setText(biliPicItem.getSize());
    if (biliPicItem.isDownload()) {
      holder.download.setText("已下载");
    } else {
      holder.download.setText("下载");
      holder.download.setTextColor(
          holder.download.getContext().getResources().getColor(R.color.colorPrimaryDark));
    }

    holder.detail.setOnClickListener(v -> {
      // TODO: 2018/2/27 to detail web
      SnackbarUtils.with(v).setMessage(biliPicItem.getLinkedUrl()).show();
    });

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
    }
  }
}
