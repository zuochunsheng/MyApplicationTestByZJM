package com.example.edz.test;

import java.util.ArrayList;
import java.util.List;

/**
 * authr : edz on 2017/12/22  下午3:53
 * describe ：用来测试SharedPreferencesUtil
 */


public class HumanBean {
    private boolean isTest;
    private int num;
    private String name;
    private String sex;
    private float age;
    private long time;
    private SchoolBean bean;
    private List<String> family;

    public HumanBean(boolean isTest, int num, String name, float age,
                     String sex, long time, SchoolBean bean) {
        this.isTest = isTest;
        this.num = num;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.time = time;
        this.bean = bean;
        family = new ArrayList<>();
        family.add("Father");
        family.add("Mother");
        family.add("Sister");
        family.add("Brother");
        family.add("Self");
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public SchoolBean getBean() {
        return bean;
    }

    public void setBean(SchoolBean bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "HumanBean{" +
                "isTest=" + isTest +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", time=" + time +
                ", bean=" + bean +
                ", family=" + family +
                '}';
    }

}
