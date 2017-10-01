package com.example.edz.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * authr : edz on 2017/9/5  下午10:42
 * describe
 */


public class DoughnutView extends View {

    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH = 400;
    //圆环颜色
    private int[] doughnutColors = new int[]{0xFFffc746, 0xFF6095ff, 0xFFf74d3c};
    private int[] strPercent = new int[]{33, 34, 34};

    private int width;
    private int height;
    private float currentValue = 0f;
    private Paint paint = new Paint();

    public DoughnutView(Context context) {
        this(context, null);
    }

    public DoughnutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoughnutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void resetParams() {
        width = getWidth();
        height = getHeight();
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    public void setValue(float value) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(currentValue, value);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return 1 - (1 - v) * (1 - v) * (1 - v);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();
        //画背景白色圆环
        initPaint();
        float doughnutWidth = Math.min(width, height) / 2 * 0.15f;
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        RectF rectF = new RectF((width > height ? Math.abs(width - height) / 2 : 0) + doughnutWidth / 2,
                (height > width ? Math.abs(height - width) / 2 : 0) + doughnutWidth / 2,
                width - (width > height ? Math.abs(width - height) / 2 : 0) - doughnutWidth / 2,
                height - (height > width ? Math.abs(height - width) / 2 : 0) - doughnutWidth / 2);
        canvas.drawArc(rectF, 0, 360, false, paint);

        //画彩色圆环



        /*float startPercent = 0;
        float sweepPercent = 0;
        for (int i = 0; i < strPercent.length; i++) {
            paint.setColor(doughnutColors[i]);
            startPercent = sweepPercent + startPercent;
            //这里采用比例占100的百分比乘于360的来计算出占用的角度，使用先乘再除可以算出值
            sweepPercent = strPercent[i] * 360 / 100;
            //canvas.drawArc(rectF, startPercent, sweepPercent, false, paint);

        }*/
        float v = currentValue / 360f;
        Log.e("tag", "v=" + v);
        float sweepPercent1 = strPercent[0] * 360 / 100;
        float sweepPercent2 = strPercent[1] * 360 / 100;
        float sweepPercent3 = strPercent[2] * 360 / 100;


        if (v <= 0.33 && v > 0) {
            initPaint();
            canvas.rotate(-135, width / 2, height / 2);

            paint.setStrokeWidth(doughnutWidth);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(doughnutColors[0]);
            canvas.drawArc(rectF, 0, sweepPercent1, false, paint);


        } else if (v <= 0.67 && v > 0.33) {



            initPaint();
            canvas.rotate(-135+sweepPercent1, width / 2, height / 2);
            paint.setStrokeWidth(doughnutWidth);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(doughnutColors[1]);
            canvas.drawArc(rectF, 0, sweepPercent2, false, paint);

        } else if (v > 0.67 && v < 1) {



            initPaint();
            canvas.rotate(-135+sweepPercent1+sweepPercent2, width / 2, height / 2);
            paint.setStrokeWidth(doughnutWidth);
            paint.setStyle(Paint.Style.STROKE);

            paint.setColor(doughnutColors[2]);
            canvas.drawArc(rectF, 0, sweepPercent3, false, paint);

        }


      /*  if (doughnutColors.length > 1) {
            paint.setShader(new SweepGradient(width / 2, height / 2, doughnutColors, null));
        } else {
            paint.setColor(doughnutColors[0]);
        }*/
        /* paint.setColor(getCurrentColor(v));
         canvas.drawArc(rectF, 0, currentValue, false, paint);
*/
        //画中间数值的背景
       /* int fontSize = 50;
        initPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(width / 2, height / 2, fontSize * 2, paint);

        //画中间数值
        canvas.rotate(90, width / 2, height / 2);
        initPaint();
        paint.setColor(ColorUtils.getCurrentColor(currentValue / 360f, doughnutColors));
        paint.setTextSize(fontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        float baseLine = height / 2 - (paint.getFontMetrics().descent + paint.getFontMetrics().ascent) / 2;
        canvas.drawText((int) (currentValue / 360f * 100) + "%", width / 2, baseLine, paint);*/
    }

   /* public int getCurrentColor(float v) {
        if (v <= 0.33 && v > 0) {
            return doughnutColors[0];
        } else if (v <= 0.67 && v > 0.33) {
            return doughnutColors[1];

        } else if (v > 0.67 && v <= 1) {

            return doughnutColors[2];
        }
        return -1;
    }*/

    /**
     * 当布局为wrap_content时设置默认长宽
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
