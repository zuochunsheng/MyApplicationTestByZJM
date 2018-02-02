package com.example.edz.activity.baseactivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.example.edz.R;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextViewActivity extends Activity {

    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        ButterKnife.bind(this);

        // 点击链接两种方式

        String text2 = "个人主页：https://www.baidu.com\n";
        text2 += "电子邮箱：zuochunsheng@zhaojinmao.cn\n";
        text2 += "电话：13761654037";

        textView2.setText(text2);


        String text3 = "<font color = 'red'>浏览器 :</font><br>  ";
        text3 += "<a href = 'https://www.baidu.com'> 百度</a>";

        Spanned spanned = Html.fromHtml(text3);

        textView3.setText(spanned);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());


        // 利用html 插入图片
        String text4 = " 图片1：<img src = 'app_log'/><br>";
        text4 += " 图片2：<img src = 'refresh_succeed'/><br>";
        Spanned spanned4 = Html.fromHtml(text4, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String src) {// html 中 src 的值 ,多张图片就遍历
                Drawable d = null;
                try {
                    Field field = R.mipmap.class.getField(src);
                    String s = field.get(null).toString();
                    int resourceId = Integer.parseInt(s);

                    d = getResources().getDrawable(resourceId);
                    d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                return d;
            }
        }, null);
        textView4.setText(spanned4);


        String html = "图片：<img src='" + R.drawable.one_piece + "'/>";
        Html.ImageGetter imgGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {// html 中 src 的值

                int resourceId = Integer.parseInt(source);
                Drawable d = getResources().getDrawable(resourceId);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };
        CharSequence charSequence = Html.fromHtml(html, imgGetter, null);
        textView5.setText(charSequence);


        //Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.count_1);
        //第三个参数  对齐方式
        ImageSpan imgSpan = new ImageSpan(this, R.drawable.count_1, ImageSpan.ALIGN_BOTTOM);

        SpannableString spanString = new SpannableString("abcdefghijklmnopqrstuvwxyz");
        //0 4 图片要替换的文字索引
        spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(spanString);
        // 追加文本
        textView2.append("中国人民很伟大");





    }
}
