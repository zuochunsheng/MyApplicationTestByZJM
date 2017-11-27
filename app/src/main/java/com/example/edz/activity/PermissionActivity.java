package com.example.edz.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edz.R;
import com.example.edz.base.BaseActivity;
import com.example.edz.permission.IPermission;
import com.example.edz.permission.PermissionUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
 * 权限 封装
 */
public class PermissionActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        //用法  BaseActivity
       /* requestRunTimePermission(new String[]{Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                 new IPermission() {
                  @Override
                    public void onGranted() {
                      Toast.makeText(PermissionActivity.this,"申请同意",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        Toast.makeText(PermissionActivity.this,"申请拒绝",Toast.LENGTH_SHORT).show();
                    }
                });*/

       //工具类 方式
        PermissionUtil.getInstance(this).requestRunTimePermission(new String[]{Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                new IPermission() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(PermissionActivity.this,"所有权限都同意了",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        for (String permission :deniedPermission) {
                            Toast.makeText(PermissionActivity.this,"被拒绝权限："  + permission,Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }




}
