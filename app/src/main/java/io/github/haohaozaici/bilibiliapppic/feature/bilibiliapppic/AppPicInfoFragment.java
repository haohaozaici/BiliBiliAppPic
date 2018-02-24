package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import io.github.haohaozaici.bilibiliapppic.R;

/**
 * Created by haoyuan on 2018/1/12.
 */

public class AppPicInfoFragment extends Fragment {


  public static AppPicInfoFragment newInstance() {
    Bundle args = new Bundle();
    AppPicInfoFragment fragment = new AppPicInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.app_pic_info_layout, container, false);
    ButterKnife.bind(this, view);
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);


  }
}
