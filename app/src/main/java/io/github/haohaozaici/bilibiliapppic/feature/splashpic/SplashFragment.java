package io.github.haohaozaici.bilibiliapppic.feature.splashpic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.TimeUtils;
import io.github.haohaozaici.bilibiliapppic.R;
import io.github.haohaozaici.bilibiliapppic.feature.splashpic.bean.SplashPicRes;
import io.github.haohaozaici.bilibiliapppic.feature.splashpic.bean.SplashPicRes.DataBean;
import io.github.haohaozaici.bilibiliapppic.network.MyRetrofit;
import io.github.haohaozaici.bilibiliapppic.util.TabLayoutUtil;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.reactivestreams.Subscription;

/**
 * Created by haoyuan on 2018/1/7.
 */

public class SplashFragment extends Fragment {

  @BindView(R.id.splash_pic_tabs) TabLayout mTabLayout;
  @BindView(R.id.splash_pic_view_pager) ViewPager mViewPager;

  public static SplashFragment newInstance() {
    Bundle args = new Bundle();
    SplashFragment fragment = new SplashFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.splash_pic_layout, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    MyRetrofit.getApiService().getSplashPic()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new FlowableSubscriber<SplashPicRes>() {
          @Override
          public void onSubscribe(Subscription s) {
            s.request(1);
          }

          @Override
          public void onNext(SplashPicRes splashPicRes) {
            if (splashPicRes.getCode() == 0) {
              List<DataBean> dataList = splashPicRes.getData();

              mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                  return PageFragment.newInstance(dataList.get(position).getImage());
                }

                @Override
                public int getCount() {
                  return dataList.size();
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                  String startTime = TimeUtils
                      .millis2String(dataList.get(position).getStart_time() * 1000L, sdf);

                  return startTime;
                }
              });
              mTabLayout.setupWithViewPager(mViewPager);
              TabLayoutUtil.reflex(mTabLayout);
            }

          }

          @Override
          public void onError(Throwable t) {

          }

          @Override
          public void onComplete() {

          }
        });

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }
}
