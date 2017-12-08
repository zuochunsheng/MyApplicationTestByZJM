package com.example.edz.kotlin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.edz.R;
import com.example.edz.annotation.OnClick;
import com.example.edz.annotation.ViewBinder;
import com.example.edz.annotation.ViewBinderParser;

/**
 * @author edz
 *
 * 自定义ButterKnife
 *
 *
 */
public class KotlinActivity extends Activity {

    @ViewBinder(id = R.id.text)
    public TextView textView ;

    /**
     * 执行button的点击事件
     */
    @OnClick(id = R.id.button)
    public void button1OnClick() {
        Log.e("tag", "这是一个onClick测试的例子");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin);

        ViewBinderParser.inject(KotlinActivity.this);

    }
}
