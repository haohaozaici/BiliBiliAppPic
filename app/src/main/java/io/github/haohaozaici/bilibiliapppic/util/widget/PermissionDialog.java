package io.github.haohaozaici.bilibiliapppic.util.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import io.github.haohaozaici.bilibiliapppic.R;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by haoyuan on 2018/1/17.
 */

public class PermissionDialog extends DialogFragment {

  @BindView(R.id.permission_title) TextView mTitle;
  @BindView(R.id.permission_recycler_view) RecyclerView mRecyclerView;

  MultiTypeAdapter mAdapter = new MultiTypeAdapter();

  public static PermissionDialog newInstance() {
    Bundle args = new Bundle();
    PermissionDialog fragment = new PermissionDialog();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.permission_dialog_layout, container, false);
    ButterKnife.bind(this, view);

    mAdapter.register(PermissionItem.class, new PermissionItemViewBinder(this));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerView.setAdapter(mAdapter);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    checkPermission();
  }

  void checkPermission() {
    Items items = new Items();

    if (AndPermission.hasPermission(getContext(), Permission.STORAGE)) {
      dismiss();
    } else {
      items.add(new PermissionItem(R.drawable.permission_storage,
          getString(R.string.permission_storage),
          getString(R.string.permission_storage_des), false));

      mAdapter.setItems(items);
      mAdapter.notifyDataSetChanged();
    }


  }


}
