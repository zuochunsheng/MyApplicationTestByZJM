package com.example.edz.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PowerActivity extends Activity {

    @BindView(R.id.tv_showDialog)
    TextView tvShowDialog;

    private Dialog dialog_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        ButterKnife.bind(this);

        Log.e("lift", "onCreate");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("lift", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("lift", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lift", "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lift", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("lift", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lift", "onDestroy");
    }


    //密码框
    public void showPass() {

        String pay_by = "使用余额支付";

        if (dialog_pass != null) {
            dialog_pass = null;
        }
        dialog_pass = new Dialog(this, R.style.ActionSheetDialogStyle);
        // 填充对话框的布局
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this).inflate(
                R.layout.pass_word, null);
        // 初始化控件
        //view.findViewById(R.id.pass_cancel).setOnClickListener(this);//取消
        ((TextView) view.findViewById(R.id.pass_by)).setText(pay_by);
        TextView textView_pass = ((TextView) view.findViewById(R.id.textView));
        TextView tv_alert_title = ((TextView) view.findViewById(R.id.tv_alert_title));

        tv_alert_title.setText("余额支付");

        //view.findViewById(R.id.btn_control_pay_pass).setOnClickListener(this);//确认密码
        EditText editText = ((EditText) view.findViewById(R.id.control_input_pass));


        dialog_pass.setContentView(view);
        // 获取当前Activity所在的窗体
        Window dialogWindow = dialog_pass.getWindow();
        // 设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        // 获得代表当前window属性的对象
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        Point point = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        // 将window的宽高信息保存在point中
        display.getSize(point);
        // 将设置后的大小赋值给window的宽高
        params.width = point.x;
        // 方式一：设置属性
        dialogWindow.setAttributes(params);
        dialog_pass.show();// 显示对话框

    }


    @OnClick(R.id.tv_showDialog)
    public void onViewClicked() {
        showPass();
    }
}
