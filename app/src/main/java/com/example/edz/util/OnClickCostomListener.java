package com.example.edz.util;

import android.view.View;

/**
 * authr : edz on 2017/12/8  下午4:38
 * describe ：通用自定义View处理点击事件
 */


public abstract class OnClickCostomListener implements View.OnClickListener  {

    @Override
    public void onClick(View view) {

        if (isCorrect()) {
            onCorrentClick(view);
        } else {
            onNoCorrentClick(view);
        }
    }

    /**
     * 判断是否逻辑通过
     * @return
     */
    public abstract boolean isCorrect();

    /**
     * 判断正确之后执行的逻辑请求
     * @param v
     */
    public abstract void onCorrentClick(View v);

    /**
     * 判断失败之后执行的逻辑请求
     * @param v
     */
    public abstract void onNoCorrentClick(View v);


}
