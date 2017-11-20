package com.example.edz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.edz.R;
import com.example.edz.view.DownLoadProgressbar;

public class ProgressActivity extends Activity {


    private TextView mStart;
    private TextView mSize;
    private TextView mSpeed;
    private DownLoadProgressbar mProgress;
    private int max = 100; //总的大小
    private int current = 0; //当前下载大小
    private String speed = "1"; //下载速度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mStart = (TextView) findViewById(R.id.tv_start);
        mProgress = (DownLoadProgressbar) findViewById(R.id.dp_game_progress);
        mSize = (TextView) findViewById(R.id.tv_size);
        mSpeed = (TextView) findViewById(R.id.tv_speed);
        //初始化下载进度
        mSize.setText(current + "MB/" + max + "MB");
        //初始化下载速度
        mSpeed.setText(speed + "MB/s");
        mStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                start();
            }
        });
    }

    //循环模拟下载过程
    public void start() {
        if (current <= max) {
            mSize.setText(current + "MB/" + max + "MB");
            mSpeed.setText(speed + "MB/s");
            mProgress.setMaxValue(max);
            mProgress.setCurrentValue(current);
            handler.postDelayed(runnable, 100);
        } else {
            handler.removeCallbacks(runnable);
        }

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            current = current + 1;
            start();
        }
    };
}
