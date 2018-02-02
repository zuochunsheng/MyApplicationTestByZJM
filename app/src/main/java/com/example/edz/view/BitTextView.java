package com.example.edz.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.edz.R;

/**
 * authr : edz on 2018/2/2  上午11:46
 * describe ：
 */
public class BitTextView extends TextView {


    private Bitmap bitmap;

    public BitTextView(Context context) {
        super(context);
        initView(context);
    }

    public BitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){

        //Drawable drawable = context.getResources().getDrawable(R.mipmap.refresh_item5);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.count_1);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap,0,0 ,null);
    }
}
