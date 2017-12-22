package com.example.edz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edz.R;
import com.example.edz.sp.SharedPreferencesUtil;
import com.example.edz.test.HumanBean;
import com.example.edz.test.SchoolBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpActivity extends Activity {

    @BindView(R.id.boolean_check)
    CheckBox booleanCheck;
    @BindView(R.id.int_input)
    EditText intInput;
    @BindView(R.id.text_input)
    EditText textInput;
    @BindView(R.id.float_input)
    EditText floatInput;
    @BindView(R.id.long_input)
    EditText longInput;
    @BindView(R.id.showResult)
    TextView showResult;

    private String intKey = "intKey";
    private String stringKey = "stringKey";
    private String floatKey = "floatKey";
    private String longKey = "longKey";
    private String booleanKey = "booleanKey";
    private String beanKey = "beanKey";
    private String hunmansKey = "hunmansKey";
    private String intsKey = "intsKey";
    private String strsKey = "strsKey";
    private String mapKey = "mapKey";

    private HumanBean bean;
    //List
    private List<Integer> integers = new ArrayList<>();
    private List<HumanBean> humanBeens = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    //Map
    private Map<String, HumanBean> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        ButterKnife.bind(this);
        //初始化HumanBean
        bean = new HumanBean(true, 20, "Black_Hao", 24f, "男", System.currentTimeMillis()
                , new SchoolBean("AnHui", 20, System.currentTimeMillis(), false));
    }



    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_input:
                //保存从EditText输入的常规数据到sp
                boolean bool = booleanCheck.isChecked();
                String str = textInput.getText().toString().trim();
                int in = Integer.parseInt(intInput.getText().toString());
                float f = Float.parseFloat(floatInput.getText().toString().trim());
                long lo = Long.parseLong(longInput.getText().toString().trim());

                boolean resultBol = SharedPreferencesUtil.putData(booleanKey, bool);
                boolean resultInt = SharedPreferencesUtil.putData(intKey, in);
                boolean resultStr = SharedPreferencesUtil.putData(stringKey, str);
                boolean resultFlo = SharedPreferencesUtil.putData(floatKey, f);
                boolean resultLon = SharedPreferencesUtil.putData(longKey, lo);
                boolean resultBea = SharedPreferencesUtil.putData(beanKey, bean);
                showResult.setText("resultBol:---" + resultBol + " \nresultInt:--- " + resultInt
                        + " \nresultStr:--- " + resultStr + " \nresultFlo:--- " +
                        resultFlo + "  \nresultLon:--- " + resultLon
                        + " \nresultBea --- " + resultBea);
                break;
            case R.id.get_input:
                //读取常规数据并显示到showResult
                boolean Bol = (boolean) SharedPreferencesUtil.getData(booleanKey, false);
                int Int = (int) SharedPreferencesUtil.getData(intKey, -1);
                String Str = (String) SharedPreferencesUtil.getData(stringKey, "");
                float Flo = (float) SharedPreferencesUtil.getData(floatKey, 0f);
                long Lon = (long) SharedPreferencesUtil.getData(longKey, 1L);
                HumanBean Bea = (HumanBean) SharedPreferencesUtil.getData(beanKey, bean);

                showResult.setText(Bol + " \n " + Int + " \n " + Str
                        + " \n " + Flo + " \n " + Lon + " \n " + Bea.toString());
                break;

            case R.id.save_list:
                //保存List数据到SharedPreferences
                getDefaultList();
                boolean resultInts = SharedPreferencesUtil.putListData(intsKey, integers);
                boolean resultBeans = SharedPreferencesUtil.putListData(hunmansKey, humanBeens);
                boolean resultStrs = SharedPreferencesUtil.putListData(strsKey, strings);
                showResult.setText("resultInts:---" + resultInts
                        + "\nresultBeans:---" + resultBeans + "\nresultStrs:---" + resultStrs);
                break;
            case R.id.get_list:
                ////读取Map数据并显示到showResult
                integers.clear();
                humanBeens.clear();
                strings.clear();
                integers.addAll(SharedPreferencesUtil.getListData(intsKey, Integer.class));
                humanBeens.addAll(SharedPreferencesUtil.getListData(hunmansKey, HumanBean.class));
                strings.addAll(SharedPreferencesUtil.getListData(strsKey, String.class));
                //遍历integers并将结果打印
                String intsStr = "{";
                for (int i = 0; i < integers.size(); i++) {
                    intsStr += integers.get(i) + " --- ";
                }
                intsStr += "}";
                //遍历humanBeens并将结果打印
                String humsStr = "";
                for (int i = 0; i < humanBeens.size(); i++) {
                    humsStr += humanBeens.get(i).toString() + "\n";
                }
                //遍历strings并将结果打印
                String strs = "{";
                for (int i = 0; i < strings.size(); i++) {
                    strs += strings.get(i) + " ---- ";
                }
                strs += "}";
                showResult.setText(intsStr + "\n" + humsStr + "\n" + strs);
                break;
            case R.id.save_map:
                //保存Map数据到SharedPreferences
                getDefaultList();
                boolean resultMap = SharedPreferencesUtil.putHashMapData(mapKey, map);
                showResult.setText("resultMap:---" + resultMap);
                break;
            case R.id.get_map:
                //读取Map数据并显示到showResult
                map.clear();
                map.putAll(SharedPreferencesUtil.getHashMapData(mapKey, HumanBean.class));
                //遍历map并将结果打印
                String mapStr = "";
                for (String key : map.keySet()) {
                    mapStr += key + " : " + map.get(key).toString() + "\n";
                }
                showResult.setText(mapStr);
                break;
        }
    }

    /**
     * 生成默认数据以保存到SharedPreferences
     */
    private void getDefaultList() {
        integers.clear();
        humanBeens.clear();
        strings.clear();
        String[] names = new String[]{"Black_Hao", "White_Hao", "Gray_Hao", "Blue_Hao"};
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int s = random.nextInt(100);
            integers.add(s);
            humanBeens.add(new HumanBean(s % 2 == 0, s, names[s % 4], 24f, "男", System.currentTimeMillis()
                    , new SchoolBean("AnHui", 20, System.currentTimeMillis(), s % 3 == 0)));
            strings.add(names[s % 4]);
            map.put(strings.get(i) + i, humanBeens.get(i));
        }
    }
}
