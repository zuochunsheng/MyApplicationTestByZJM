package com.example.edz.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edz.R;
import com.example.edz.pulltorefresh.RefreshLayout;
import com.example.edz.pulltorefresh.ShopView;

/**
 * @author edz
 */
public class RefreshActivity extends Activity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        listView = (ListView) findViewById(R.id.listView);
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        if (refreshLayout != null) {
            // 刷新状态的回调
            refreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // 延迟3秒后刷新成功
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.refreshComplete();
                            if (listView != null) {
                                listView.setAdapter(new MainAdapter());
                            }
                        }
                    }, 3000);
                }
            });
            ShopView shopView = new ShopView(this);
            refreshLayout.setRefreshHeader(shopView);
           // refreshLayout.autoRefresh();
        }

    }

    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                TextView textView =  new TextView(RefreshActivity.this);
                textView.setText(String.valueOf(position));
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(0x55ff0000);
                textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
                textView.setGravity(Gravity.CENTER);
                convertView = textView;
            } else {
                ((TextView) convertView).setText(String.valueOf(position));
            }

            return convertView;
        }
    }


}
