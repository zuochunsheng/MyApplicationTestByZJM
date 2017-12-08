package com.example.edz.kotlin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edz.R;
import com.example.edz.annotation.OnClick;
import com.example.edz.annotation.ViewBinder;
import com.example.edz.annotation.ViewBinderParser;
import com.example.edz.util.LogUtil;
import com.example.edz.util.OnClickCostomListener;
import com.example.edz.util.OnClickFastListener;

/**
 * @author edz
 *         <p>
 *         自定义ButterKnife
 */
public class KotlinActivity extends Activity {

    @ViewBinder(id = R.id.text)
    public TextView textView;

    @ViewBinder(id = R.id.button1)
    public Button button1;
    @ViewBinder(id = R.id.button2)
    public Button button2;

    /**
     * 执行button的点击事件
     */
    @OnClick(id = R.id.button)
    public void button1OnClick() {
        Log.e("tag", "这是一个onClick测试的例子");
    }

    private int index = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin);

        ViewBinderParser.inject(KotlinActivity.this);


        //快速点击
        button1.setOnClickListener(new OnClickFastListener() {
            @Override
            public void onFastClick(View v) {

                index ++ ;
                LogUtil.e("您点击了快速点击按钮 index =" + index);
            }
        });
        // 自定义 事件
        button2.setOnClickListener(new OnClickCostomListener() {
            @Override
            public boolean isCorrect() {
                return false;
            }

            @Override
            public void onCorrentClick(View v) {
                Toast.makeText(KotlinActivity.this, "判断通过,执行后续操作...",
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNoCorrentClick(View v) {
                Toast.makeText(KotlinActivity.this, "判断未通过,无法执行后续操作...",
                        Toast.LENGTH_LONG).show();

            }
        });

    }
}
