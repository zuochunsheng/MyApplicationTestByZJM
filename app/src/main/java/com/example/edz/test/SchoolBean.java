package com.example.edz.test;

import java.util.ArrayList;
import java.util.List;

/**
 * authr : edz on 2017/12/22  下午3:53
 * describe ：用来测试SharedPreferencesUtil
 */


public class SchoolBean {
    private String homeAddr;
    private int grade;
    private long schoolTime;
    private boolean isBoarding;
    private List<Integer> students;

    public SchoolBean(String homeAddr, int grade, long schoolTime, boolean isBoarding) {
        this.homeAddr = homeAddr;
        this.grade = grade;
        this.schoolTime = schoolTime;
        this.isBoarding = isBoarding;
        students = new ArrayList<>();
        students.add(15);
        students.add(105);
        students.add(150);
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public long getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(long schoolTime) {
        this.schoolTime = schoolTime;
    }

    public boolean isBoarding() {
        return isBoarding;
    }

    public void setBoarding(boolean boarding) {
        isBoarding = boarding;
    }

    @Override
    public String toString() {
        return "SchoolBean{" +
                "homeAddr='" + homeAddr + '\'' +
                ", grade=" + grade +
                ", schoolTime=" + schoolTime +
                ", isBoarding=" + isBoarding +
                ", students=" + students +
                '}';
    }
}
