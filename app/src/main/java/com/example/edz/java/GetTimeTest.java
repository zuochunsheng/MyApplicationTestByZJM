package com.example.edz.java;

/**
 * authr : edz on 2018/1/10  下午4:11
 * describe ：
 */
public class GetTimeTest extends GetTime {
    @Override
    public void runCode() {
        // 耗时
        for (int i = 0; i < 50000; i++) {
            System.out.print("" + i);
        }
    }
}
