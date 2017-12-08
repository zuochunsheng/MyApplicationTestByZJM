package com.example.edz.util;

import android.view.View;

/**
 * authr : edz on 2017/12/8  下午3:56
 * describe ：/**
 * 方法按钮重复点击的监听类源码
 */

public abstract class OnClickFastListener implements View.OnClickListener {


    // 防止快速点击默认等待时长为900ms
    private static final long DELAY_TIME = 1500;
    private static long lastClickTime  = 0L;

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < DELAY_TIME) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    @Override
    public void onClick(View view) {
// 判断当前点击事件与前一次点击事件时间间隔是否小于阙值
        if (isFastDoubleClick()) {
            return;
        }

        onFastClick(view);
    }

    /**
     * 设置默认快速点击事件时间间隔
     * @param delay_time
     * @return
     */
    /*public OnClickFastListener setLastClickTime(long delay_time) {

        this.DELAY_TIME = delay_time;

        return this;
    }
*/



    /**
     * 快速点击事件回调方法
     * @param v
     */
    public abstract void onFastClick(View v);
}
