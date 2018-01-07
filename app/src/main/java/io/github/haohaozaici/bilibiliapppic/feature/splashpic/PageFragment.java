package io.github.haohaozaici.bilibiliapppic.feature.splashpic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.PhotoActivity;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class PageFragment extends Fragment {

  private static final String PHOTO_URL = "url";

  @BindView(R.id.page_image_view) ImageView mImageView;

  public static PageFragment newInstance(String url) {
    Bundle args = new Bundle();
    args.putString(PHOTO_URL, url);
    PageFragment fragment = new PageFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.img_layout, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    String url = getArguments().getString(PHOTO_URL);
    if (!StringUtils.isEmpty(url)) {
      Glide.with(this)
          .load(url)
          .into(mImageView);

      mImageView.setOnClickListener(v -> {
        PhotoActivity.start(v.getContext(), url);
      });
    }


  }
}
