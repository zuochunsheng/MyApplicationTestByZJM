package com.example.edz.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edz.R;

import java.util.Random;

/**
 * authr : edz on 2017/11/27  上午11:08
 * describe ： 刷新的view 2
 */


public class ViewHeader extends RelativeLayout implements RefreshHeader {

    private Context mContext;
    private int mHeight;
    private int mWidth;
    private LayoutParams lp;
    private Random random = new Random();
    private TextView state_tv;
    private ImageView pull_icon;
    private String[] Strings = new String[]{"黄金协会会员单位","让黄金流动起来","银行系入股的黄金平台"};

    public ViewHeader(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public ViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public ViewHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {

        View view = View.inflate(getContext(), R.layout.refresh_head, this);
        pull_icon = (ImageView) view.findViewById(R.id.pull_icon);
       // ImageView   refreshing_icon =(ImageView) view.findViewById(R.id.refreshing_icon);
        state_tv = (TextView)view.findViewById(R.id.state_tv);

       // ImageView   state_iv =(ImageView) view.findViewById(R.id.state_iv);
        //注意 我这里3张图片的大小都是一样的,所以我只取了一个

       /* //底部 并且 水平居中
        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL, TRUE);//这里的TRUE 要注意 不是true
        lp.addRule(ALIGN_PARENT_BOTTOM, TRUE);
*/

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

    }
    @Override
    public void reset() {
        state_tv.setText("reset");
    }

    @Override
    public void pull() {
        state_tv.setText("pull");
    }

    @Override
    public void refreshing() {
        state_tv.setText("refreshing");
    }

    @Override
    public void onPositionChange(float currentPos, float lastPos, float refreshPos, boolean isTouch, RefreshLayout.State state) {
        state_tv.setText("onPositionChange");
        pull_icon.setImageResource(R.mipmap.refreshing);
    }

    @Override
    public void complete() {
        state_tv.setText("complete");
        pull_icon.setImageResource(R.mipmap.refresh_succeed);
    }
}
