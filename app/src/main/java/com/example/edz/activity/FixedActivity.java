package com.example.edz.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.edz.R;


/*
 * 热修复
 */
public class FixedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed);
    }

}
