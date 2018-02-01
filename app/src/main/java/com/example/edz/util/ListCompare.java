package com.example.edz.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.List;

/**
 * authr : edz on 2017/10/9  下午10:43
 * describe
 */


public class ListCompare {

    //判断 两个集合相等  -->比较字符串
    public static boolean equalList(List list1, List list2) {
       //return (list1.size() == list2.size()) && list1.containsAll(list2);  //不行

        if (list1==null || list2 ==null || (list1.size() != list2.size())) {
            return false;
        }
        /* for (Object object : list1) {
            if (!list2.contains(object)) {
                return false;
            }
        }
        return true;*/
        Gson gson = new Gson();
        String s1 = gson.toJson(list1);
        String s2 = gson.toJson(list2);
        if(TextUtils.equals(s1,s2)){
            return true;
        }else {
            return false;
        }

    }
}
