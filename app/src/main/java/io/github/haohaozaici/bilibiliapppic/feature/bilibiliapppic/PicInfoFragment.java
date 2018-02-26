package io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.entity.BiliBiliAppPic;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by haoyuan on 2018/1/12.
 */

public class PicInfoFragment extends Fragment {

  @BindView(R.id.pic_info_recycler_view) RecyclerView mRecyclerView;

  private MultiTypeAdapter mAdapter = new MultiTypeAdapter();
  private Items mItems = new Items();

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

    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
        mItems.addAll(biliAppPics);
        mAdapter.notifyDataSetChanged();
      }

    });

    Observable.create((ObservableOnSubscribe<Items>) e -> {
      Items items = new Items();
      for (int i = 0; i < 10; i++) {
        items.add(new BiliBiliAppPic(100, "", "", "", "", false));
      }

      e.onNext(items);

    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Items>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Items items) {
            mAdapter.setItems(items);
            mAdapter.notifyDataSetChanged();

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
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
        // TODO: 2018/2/26 加载数据

    }

    return super.onOptionsItemSelected(item);
  }
}
