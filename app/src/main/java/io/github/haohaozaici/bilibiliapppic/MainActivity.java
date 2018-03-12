package io.github.haohaozaici.bilibiliapppic;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.PicInfoFragment;
import io.github.haohaozaici.bilibiliapppic.feature.notification.NotificationFragment;
import io.github.haohaozaici.bilibiliapppic.model.database.bilibilipic.BiliPicDatabase;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.drawer_layout)
  DrawerLayout mDrawerLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(item -> {

      // Handle navigation view item clicks here.
      int id = item.getItemId();

      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

      if (id == R.id.nav_camera) {
        // Handle the camera action
        ft.replace(R.id.main_container, PicInfoFragment.newInstance()).commit();
      } else if (id == R.id.nav_gallery) {
        ft.replace(R.id.main_container, NotificationFragment.newInstance()).commit();
      } else if (id == R.id.nav_slideshow) {

      } else if (id == R.id.nav_manage) {

      } else if (id == R.id.nav_share) {

      } else if (id == R.id.nav_send) {

      }

      mDrawerLayout.closeDrawer(GravityCompat.START);
      return true;

    });

    handlePermission(this);

  }


  public void handlePermission(Context context) {
    AndPermission.with(context)
        .permission(Permission.Group.STORAGE)
        .rationale((context1, permissions, executor) -> {
          new AlertDialog.Builder(context1)
              .setMessage("请提供储存权限，用于保存图片")
              .setPositiveButton("允许", (dialog, which) -> {
                executor.execute();
              })
              .setNegativeButton("拒绝", ((dialog, which) -> {
                executor.cancel();
              }))
              .create()
              .show();
        })
        .onGranted(permissions -> {
          App.mBiliPicDatabase = BiliPicDatabase.getInstance(context);
          FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
          ft.replace(R.id.main_container, PicInfoFragment.newInstance()).commit();
        })
        .start();

  }

}
