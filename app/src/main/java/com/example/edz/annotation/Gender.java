package com.example.edz.annotation;

/**
 * authr : edz on 2017/12/8  下午2:27
 * describe ：枚举，模拟注解中添加枚举属性
 */


public enum Gender {
    MAN{
        @Override
        public String getName(){return "男";}
    },
    WOMEN{
        @Override
        public String getName(){return "女";}
    }; //记得有“;”
    public abstract String getName();
}
