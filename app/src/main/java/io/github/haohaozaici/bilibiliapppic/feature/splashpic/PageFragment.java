package io.github.haohaozaici.bilibiliapppic.feature.splashpic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import io.github.haohaozaici.bilibiliapppic.R;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class PageFragment extends Fragment {

  public static PageFragment newInstance() {

    Bundle args = new Bundle();

    PageFragment fragment = new PageFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.img_layout, container, false);
    ButterKnife.bind(this, view);
    return view;
  }
}
