package com.example.edz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.edz.R;
import com.example.edz.broadcast.NetWorkReceiver;
import com.example.edz.util.NetUtil;

public class NetErrorActivity extends Activity implements NetWorkReceiver.EventHandler,
        View.OnClickListener {

    private LinearLayout mNetErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_status_bar);

        mNetErrorView = findViewById(R.id.net_status_bar_top);
        mNetErrorView.setOnClickListener(this);
    }

    @Override
    public void onNetChange(boolean isNetConnected) {
        if (!isNetConnected) {
            Toast.makeText(getApplicationContext(), R.string.net_error_tip, Toast.LENGTH_SHORT).show();
            mNetErrorView.setVisibility(View.VISIBLE);
        } else {
            mNetErrorView.setVisibility(View.GONE);
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.net_status_bar_top:
                // 跳转到网络设置
                startActivity(new Intent(
                        android.provider.Settings.ACTION_WIFI_SETTINGS));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetUtil.isNetConnected(this)) {
            mNetErrorView.setVisibility(View.VISIBLE);
        }
        else {
            mNetErrorView.setVisibility(View.GONE);
        }
    }
}
