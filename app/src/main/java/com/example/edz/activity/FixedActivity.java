package com.example.edz.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edz.R;
import com.example.edz.application.MainApplication;
import com.taobao.sophix.SophixManager;


/*
 * 热修复
 */
public class FixedActivity extends Activity {

    private static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 0;

    private TextView mStatusTv;
    private String mStatusStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed);


        mStatusTv = (TextView) findViewById(R.id.tv_status);
        updateConsole(MainApplication.cacheMsg.toString());

        if (Build.VERSION.SDK_INT >= 23) {
            requestExternalStoragePermission();
        }
        MainApplication.msgDisplayListener = new MainApplication.MsgDisplayListener() {
            @Override
            public void handle(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateConsole(msg);
                    }
                });
            }
        };

    }


    /**
     * 如果本地补丁放在了外部存储卡中, 6.0以上需要申请读外部存储卡权限才能够使用. 应用内部存储则不受影响
     */

    private void requestExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    updateConsole("local external storage patch is invalid as not read external storage permission");
                }
                break;
            default:
        }
    }

    /**
     * 更新监控台的输出信息
     *
     * @param content 更新内容
     */
    private void updateConsole(String content) {
        mStatusStr += content + "\n";
        if (mStatusTv != null) {
            mStatusTv.setText(mStatusStr);
        }
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_restest:
                int text = 3 ;
                mStatusTv.setText(text +"");
                Toast.makeText(this,"bug修复",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_download:
                SophixManager.getInstance().queryAndLoadNewPatch();
                break;
            case R.id.btn_test:
               /* DexFixDemo.test_normal(MainActivity.this);
                DexFixDemo.test_annotation();
                DexFixDemo.test_addField();
                DexFixDemo.test_addMethod();
                updateConsole("old apk from java...");*/
                break;
            case R.id.btn_sotest:
              /*  SOFixDemo.test(MainActivity.this);
                updateConsole("apk from native...");*/
                break;
            case R.id.btn_kill:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.btn_clean_patch:
                SophixManager.getInstance().cleanPatches();
                updateConsole("clean patches");
                break;
            case R.id.btn_clean_console:
                mStatusStr = "";
                updateConsole("");
                break;
            default:
                break;
        }


    }
   /* public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_restest:
                startActivity(new Intent(this, ResTestActivity.class));
                break;
            case R.id.btn_download:
                SophixManager.getInstance().queryAndLoadNewPatch();
                break;
            case R.id.btn_test:
                DexFixDemo.test_normal(MainActivity.this);
                DexFixDemo.test_annotation();
                DexFixDemo.test_addField();
                DexFixDemo.test_addMethod();
                updateConsole("old apk from java...");
                break;
            case R.id.btn_sotest:
                SOFixDemo.test(MainActivity.this);
                updateConsole("apk from native...");
                break;
            case R.id.btn_kill:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.btn_clean_patch:
                SophixManager.getInstance().cleanPatches();
                updateConsole("clean patches");
                break;
            case R.id.btn_clean_console:
                mStatusStr = "";
                updateConsole("");
                break;
            default:
                break;
        }
    }*/


}
