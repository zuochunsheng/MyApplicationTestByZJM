package com.example.edz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.edz.R;
import com.example.edz.sp.SharedPreferencesUtil;
import com.example.edz.util.ListCompare;
import com.example.edz.util.LogUtil;
import com.example.edz.gesture.GestureLockViewGroup;

import java.util.ArrayList;
import java.util.List;

public class GestureActivity extends Activity {

    private GestureLockViewGroup mGestureLockViewGroup;
    private int count_max = 5;//最大绘制 次数

    private TextView tvTip;
    private boolean isSettedGesture = false;

    private boolean isFirstSetting = false;//设置手势时 是否第一次 ， false ： 第一次
    private List<Integer> choose1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        tvTip = (TextView) findViewById(R.id.tv_tip);
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        //判断 是否设置 手势锁 ，从服务器获取
        //无  去设置手势  并上传服务器
        //有  解开 手势


        isSettedGesture = (boolean) SharedPreferencesUtil.getData("isSettedGesture", false);
        if (isSettedGesture) {
            List<Integer> answerGesture = SharedPreferencesUtil.getListData("answerGesture", Integer.class);
            //mGestureLockViewGroup.setAnswer(new int[] { 1, 2, 3, 4,5 });
            //int[] ints = {1, 2, 3, 4, 5};
            Integer[] arr = (Integer[]) answerGesture.toArray(new Integer[answerGesture.size()]);//使用了第二种接口，返回值和参数均为结果

            mGestureLockViewGroup.setAnswer(arr);

            tvTip.setText("请绘制手势密码");
        } else {
            tvTip.setText("请设置手势密码");
        }


        mGestureLockViewGroup.setUnMatchExceedBoundary(count_max);
        mGestureLockViewGroup.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {

            // 超过尝试次数
            @Override
            public void onUnmatchedExceedBoundary() {

                LogUtil.e("tag", "onUnmatchedExceedBoundary 错误5次");
            }

            //是否匹配
            @Override
            public void onGestureEvent(boolean matched) {

                if (isSettedGesture) {
                    if (matched) {
                        tvTip.setText("手势验证成功");
                    } else {

                        tvTip.setText("手势验证失败 ，剩余可尝试次数 getTryTime = " + mGestureLockViewGroup.getTryTime());
                        LogUtil.e("tag", "手势验证失败 ，剩余可尝试次数 getTryTime = " + mGestureLockViewGroup.getTryTime());
                    }


                } else {// 设置

                    if (isFirstSetting) {
                        List<Integer> choose2 = mGestureLockViewGroup.getChoose();
                        Log.e("tag", "mChoose1 = " + choose1);
                        Log.e("tag", "mChoose2 = " + choose2);
                        if (ListCompare.equalList(choose2, choose1)) {
                            //存储  或 上传服务器


                            SharedPreferencesUtil.putListData("answerGesture", choose1);
                            SharedPreferencesUtil.putData("isSettedGesture", true);
                            tvTip.setText("手势密码设置成功");

                        } else {


                            tvTip.setText("两次手势密码不一致，请重新绘制");
                        }

                    } else {//false ： 第一次
                        List<Integer> choose = mGestureLockViewGroup.getChoose();
                        /*
                         *   重点  ，数组 指针
                         */
                        choose1 = new ArrayList<Integer>(choose);//保存状态的值

                        Log.e("tag", "mChoose1 = " + choose1);
                        isFirstSetting = true;
                        tvTip.setText("请再绘制一次手势密码");


                    }

                }
                //  绘制手势后 重置一下
                mGestureLockViewGroup.reset();

                //LogUtil.e("tag","onGestureEvent matched = "  + matched);


            }

            //单独选中元素的Id
            @Override
            public void onBlockSelected(int cId) {
                //LogUtil.e("tag","onBlockSelected cId = "  + cId);
            }
        });


    }
}
