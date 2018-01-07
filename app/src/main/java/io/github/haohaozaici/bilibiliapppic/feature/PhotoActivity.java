package io.github.haohaozaici.bilibiliapppic.feature;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import io.github.haohaozaici.bilibiliapppic.R;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class PhotoActivity extends AppCompatActivity {

  private static final String PHOTO_URL = "url";

  @BindView(R.id.photo_view) PhotoView mPhotoView;


  public static void start(Context context, String url) {
    Intent starter = new Intent(context, PhotoActivity.class);
    starter.putExtra(PHOTO_URL, url);
    context.startActivity(starter);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.photo_view_layout);
    ButterKnife.bind(this);

    View decorView = getWindow().getDecorView();
    int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    decorView.setSystemUiVisibility(option);
    getWindow().setNavigationBarColor(Color.TRANSPARENT);
    getWindow().setStatusBarColor(Color.TRANSPARENT);

    String url = getIntent().getStringExtra(PHOTO_URL);
    if (!StringUtils.isEmpty(url)) {
      Glide.with(this)
          .load(url)
          .into(mPhotoView);
    }
  }
}
