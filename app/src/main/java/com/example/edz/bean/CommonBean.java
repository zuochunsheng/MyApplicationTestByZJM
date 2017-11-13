package com.example.edz.bean;

/**
 * authr : edz on 2017/11/13  上午11:59
 * describe ：
 */


public class CommonBean {
    public String token;
    public String invitecode;

    public CommonBean(String token, String invitecode) {
        this.token = token;
        this.invitecode = invitecode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

   /* @Override
    public String toString() {
        return super.toString();
    }*/
}
