package io.github.haohaozaici.bilibiliapppic.util;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.ConvertUtils;
import java.lang.reflect.Field;

/**
 * Created by haohao on 2017/12/1.
 */

public class TabLayoutUtil {

  /**
   * 通过反射修改TabLayout Indicator的宽度（仅在Android 4.2及以上生效）
   */
  public static void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
    Class<?> tabLayoutClass = tabLayout.getClass();
    Field tabStrip = null;
    try {
      tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
      tabStrip.setAccessible(true);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }

    LinearLayout layout = null;
    try {
      if (tabStrip != null) {
        layout = (LinearLayout) tabStrip.get(tabLayout);
      }
      for (int i = 0; i < layout.getChildCount(); i++) {
        View child = layout.getChildAt(i);
        child.setPadding(0, 0, 0, 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
            LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMarginStart(ConvertUtils.dp2px(marginLeft));
        params.setMarginEnd(ConvertUtils.dp2px(marginRight));
        child.setLayoutParams(params);
        child.invalidate();
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }


  /**
   * 根据text自动获取宽度
   */
  public static void reflex(final TabLayout tabLayout) {
    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
    tabLayout.post(new Runnable() {
      @Override
      public void run() {
        try {
          //拿到tabLayout的mTabStrip属性
          LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

          int dp10 = ConvertUtils.dp2px(10);

          for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            View tabView = mTabStrip.getChildAt(i);

            //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
            Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
            mTextViewField.setAccessible(true);

            TextView mTextView = (TextView) mTextViewField.get(tabView);

            tabView.setPadding(0, 0, 0, 0);

            //字多宽线就多宽，所以测量mTextView的宽度
            int width = 0;
            width = mTextView.getWidth();
            if (width == 0) {
              mTextView.measure(0, 0);
              width = mTextView.getMeasuredWidth();
            }

            //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView
                .getLayoutParams();
            params.width = width;
            params.leftMargin = dp10;
            params.rightMargin = dp10;
            tabView.setLayoutParams(params);

            tabView.invalidate();
          }

        } catch (NoSuchFieldException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });

  }

}
