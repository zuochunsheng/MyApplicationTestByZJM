package com.example.edz.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edz.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 下载
 */
public class DownLoadActivity extends FragmentActivity {

    @BindView(R.id.file_name)
    TextView fileName;
    @BindView(R.id.pb_update)
    ProgressBar pbUpdate;

    @BindView(R.id.progress)
    TextView progress;
    @BindView(R.id.down)
    TextView down;


    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    public static final String downloadUrl = "http://app.zhaojinmao.cn/app-guanfang-release_251_22_guanfang_sign.apk";
    Timer timer;
    long id;
    TimerTask task;

    private static final int CALL_PHONE_REQUEST_CODE = 1;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int pro = bundle.getInt("pro");
            String name  = bundle.getString("name");
            pbUpdate.setProgress(pro);
            progress.setText(String.valueOf(pro)+"%");
            fileName.setText(name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.down)
    public void onViewClicked() {

        checkPhonePermission();
    }

    // 解决华为手机
    private void checkPhonePermission(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
        /* 2. 如果没有CALL_PHONE权限，进行如下判断，
         当第一次申请权限时  shouldShowRequestPermissionRationale 返回 false，
         第一次用户拒绝，再次申请的时候返回 true，在此判断中提示用户为什么要申请这个权限。*/
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                new AlertDialog.Builder(this)
                        .setMessage("申请 下载存储权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                      /* 3. 如果用户点击了允许，则调用 requestPermissions 方法申请权限，注意里面接收的参数是一个 String数组，
                         也就是说可以同时申请多个权限，不过不建议这么做。*/
                                ActivityCompat.requestPermissions(DownLoadActivity.this,
                                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CALL_PHONE_REQUEST_CODE);
                            }
                        }).show();

            }else {
    /* 3. 如果用户点击了允许，则调用 requestPermissions 方法申请权限，注意里面接收的参数是一个 String数组，
     也就是说可以同时申请多个权限，不过不建议这么做。*/
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        CALL_PHONE_REQUEST_CODE);//3 requestcode
            }
        }
        else {
            downStart();

        }


    }

   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CALL_PHONE_REQUEST_CODE){
            //如果用户点击允许的话，此判断为 true，可以在里面处理打开摄像头的操作。
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                downStart();
            }else {
                //用户勾选了 不再 询问，提示用户手动打开权限
                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                    Toast.makeText(this, "存储权限 已被禁止", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void downStart(){


        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        request = new DownloadManager.Request(Uri.parse(downloadUrl));

        request.setTitle("招金猫");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir() ;

        //设置文件存放路径
        request.setDestinationInExternalPublicDir(  Environment.DIRECTORY_DOWNLOADS  , "app-release.apk" ) ;
        pbUpdate.setMax(100);
        final  DownloadManager.Query query = new DownloadManager.Query();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Cursor cursor = downloadManager.query(query.setFilterById(id));
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        pbUpdate.setProgress(100);
                        install(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/app-release.apk" );
                        task.cancel();
                    }
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    // String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    int pro =  (bytes_downloaded * 100) / bytes_total;
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pro",pro);
                    bundle.putString("name",title);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                cursor.close();
            }
        };
        timer.schedule(task, 0,1000);


        id = downloadManager.enqueue(request);
        task.run();

        down.setClickable(false);
        down.setBackgroundResource(R.color.gray2_99);
    }


    // 安装
    private void install(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        //如果没有设置SDCard写权限，或者没有sdcard,apk文件保存在内存中，需要授予权限才能安装
//        String[] command = { "chmod", "777", path };
//        ProcessBuilder builder = new ProcessBuilder(command);
//        try {
//            builder.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        startActivity(intent);
    }
}
