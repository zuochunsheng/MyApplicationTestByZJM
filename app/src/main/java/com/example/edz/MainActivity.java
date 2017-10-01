package com.example.edz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.edz.view.DoughnutView;

import java.util.Random;

public class MainActivity extends Activity {

    private DoughnutView doughnutView;
    private Button actionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        doughnutView = (DoughnutView) findViewById(R.id.doughnutView);
        actionBtn = (Button) findViewById(R.id.actionBtn);

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doughnutView.setValue(new Random().nextInt(360));
            }
        });

        findViewById(R.id.p20Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doughnutView.setValue(360 * 0.2f);
            }
        });
        findViewById(R.id.p50Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doughnutView.setValue(360 * 0.5f);
            }
        });
        findViewById(R.id.p80Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doughnutView.setValue(360 * 0.8f);
            }
        });


        findViewById(R.id.p100Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doughnutView.setValue(360 * 1.0f);
            }
        });
    }

}
