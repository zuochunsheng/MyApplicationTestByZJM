package com.example.edz.java;

/**
 * authr : edz on 2018/1/10  下午4:09
 * describe ：模板方法设计模式
 * 在定义功能的时候，功能的一部分是不确定的，而确定的部分在使用不确定的部分的时候，那么这时就将不确定的 部分暴露出去让子类去完成
 */
abstract class GetTime {
    // 获取时间
    public void getTime() {
        long start = System.currentTimeMillis();

        runCode();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    /**
     * 耗时方法
     */
    public abstract void runCode();
}
