package com.example.edz.ui.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;

import com.example.edz.R;


/**
 * Created by gbkj on 2017/9/12.
 * 数字 翻滚
 */
public class FlipView extends FrameLayout {

    private int layoutWidth;
    private int layoutHeight;
    //private int[] size={0,1,2,3,4,5,6,7,8,9};
    private int[] images={R.drawable.count_0,R.drawable.count_1,R.drawable.count_2,R.drawable.count_3,
            R.drawable.count_4,R.drawable.count_5,R.drawable.count_6,R.drawable.count_7,R.drawable.count_8,
            R.drawable.count_9};

    //private Camera mCamera = new Camera();
    //private Matrix mMatrix = new Matrix();
    private Rect mTopRect = new Rect();
    private Rect mBottomRect = new Rect();
    //private boolean isUp2Down = true;
    //private Paint mShinePaint = new Paint();
    //private Paint mShadePaint = new Paint();
    //private boolean isFlipping = false;

    public FlipView(Context context) {
        this(context, null);
    }

    public FlipView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public FlipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context) {
        Scroller mScroller = new Scroller(context, new DecelerateInterpolator());
       // mInvisibleImageView.
        ImageView mInvisibleImageView = new ImageView(context);
        mInvisibleImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),images[0]));
        addView(mInvisibleImageView);


        ImageView mVisibleImageView = new ImageView(context);
        addView(mVisibleImageView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        layoutHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = getChildCount();
        //将两个textView放置进去
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.layout(0, 0, layoutWidth, layoutHeight);
        }

        mTopRect.top = 0;
        mTopRect.left = 0;
        mTopRect.right = getWidth();
        mTopRect.bottom = getHeight() / 2;

        mBottomRect.top = getHeight() / 2;
        mBottomRect.left = 0;
        mBottomRect.right = getWidth();
        mBottomRect.bottom = getHeight();
    }


}