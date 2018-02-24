package io.github.haohaozaici.bilibiliapppic;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yanzhenjie.permission.AndPermission;
import io.github.haohaozaici.bilibiliapppic.feature.bilibiliapppic.PicInfoFragment;
import io.github.haohaozaici.bilibiliapppic.util.widget.PermissionDialog;

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

      if (id == R.id.nav_camera) {
        // Handle the camera action
      } else if (id == R.id.nav_gallery) {

      } else if (id == R.id.nav_slideshow) {

      } else if (id == R.id.nav_manage) {

      } else if (id == R.id.nav_share) {

      } else if (id == R.id.nav_send) {

      }

      mDrawerLayout.closeDrawer(GravityCompat.START);
      return true;

    });

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.main_container, PicInfoFragment.newInstance()).commit();

//        handlePermission();

  }

  private void handlePermission() {

    if (!AndPermission.hasPermission(this, Manifest.permission_group.STORAGE)) {
      PermissionDialog dialog = PermissionDialog.newInstance();
      dialog.show(getSupportFragmentManager(), "");
      dialog.setCancelable(false);
    }

  }


}
