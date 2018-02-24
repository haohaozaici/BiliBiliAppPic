package io.github.haohaozaici.bilibiliapppic.util.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SnackbarUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.haohaozaici.bilibiliapppic.R;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by haoyuan on 2018/1/17.
 */
public class PermissionItemViewBinder extends
    ItemViewBinder<PermissionItem, PermissionItemViewBinder.ViewHolder> {

  PermissionDialog mDialog;

  public PermissionItemViewBinder(PermissionDialog permissionDialog) {
    this.mDialog = permissionDialog;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.permission_dialog_item, parent, false);
    return new ViewHolder(root);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder,
      @NonNull PermissionItem permissionItem) {

    holder.mImageView.setImageResource(permissionItem.icon);
    holder.mTitle.setText(permissionItem.title);
    holder.mDescription.setText(permissionItem.description);
    if (permissionItem.allowed) {
      holder.mButton.setText("");
      holder.mCheckImg.setImageResource(R.drawable.permission_check);
      holder.mButton.setBackgroundResource(R.drawable.permission_button_allowed_background);
      holder.mButton.setOnClickListener(v -> {
        SnackbarUtils.with(v)
            .setMessage("已成功获得" + permissionItem.title)
            .setDuration(SnackbarUtils.LENGTH_SHORT)
            .show();
      });
    } else {
      holder.mButton.setText("允许");
      holder.mButton.setBackgroundResource(R.drawable.permission_button_denied_background);
      holder.mButton.setOnClickListener(v -> {
        // TODO: 2018/1/17 get permission
        AndPermission.with(v.getContext())
            .requestCode(404)
            .permission(Permission.STORAGE)
            .callback(new PermissionListener() {
              @Override
              public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                mDialog.checkPermission();
              }

              @Override
              public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                SnackbarUtils.with(holder.mButton)
                    .setMessage(permissionItem.title + "被拒绝，相关功能将无法正常使用")
                    .setDuration(SnackbarUtils.LENGTH_SHORT)
                    .show();
                mDialog.checkPermission();
              }
            })
            .start();
      });
    }

    holder.mImageView.setImageResource(permissionItem.icon);

  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.permission_img) ImageView mImageView;
    @BindView(R.id.permission_title) TextView mTitle;
    @BindView(R.id.permission_line1_description) TextView mDescription;
    @BindView(R.id.permission_button) Button mButton;
    @BindView(R.id.permission_check) ImageView mCheckImg;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
