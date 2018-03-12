package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.service.BiliPicDownloadUtil;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by haoyuan on 2018/1/12.
 */

public class PicInfoFragment extends Fragment {

  @BindView(R.id.pic_info_recycler_view) RecyclerView mRecyclerView;

  private MultiTypeAdapter mAdapter = new MultiTypeAdapter();

  private PicInfoViewModel model;


  public static PicInfoFragment newInstance() {
    Bundle args = new Bundle();
    PicInfoFragment fragment = new PicInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.app_pic_info_layout, container, false);
    ButterKnife.bind(this, view);
    setHasOptionsMenu(true);

    // mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter.register(BiliBiliAppPic.class, new BiliPicItemViewBinder());
    mRecyclerView.setAdapter(mAdapter);

    return view;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    model = ViewModelProviders.of(this).get(PicInfoViewModel.class);

    model.getPicListLiveData().observe(this, biliAppPics -> {
      if (biliAppPics != null && !biliAppPics.isEmpty()) {
        mAdapter.setItems(biliAppPics);
        mAdapter.notifyDataSetChanged();
      }
    });

    model.updatePicData();
  }


  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.pic_info_menu, menu);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_sync:
        model.updatePicData();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
