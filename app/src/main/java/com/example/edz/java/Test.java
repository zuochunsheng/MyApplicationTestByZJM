package com.example.edz.java;

/**
 * authr : edz on 2018/1/10  下午4:12
 * describe ：
 */
public class Test {

    public static void main(String[] str){
        /*GetTime t = new GetTimeTest();
        t.getTime();*/

        Demo d = new Demo();
        try {
            d.dev(4, -1);
        } catch (FushuException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            //e.getMessage();
        }

    }

}
class Demo {
    int dev(int a, int b) throws FushuException {
        if (b < 0) {
            // 手动通过throw关键字抛出自定义异常对象
            throw new FushuException("出现了除数是负数的异常");
        }
        return a / b;
    }
}