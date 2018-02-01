package com.example.edz.java;

/**
 * authr : edz on 2018/1/15  下午4:00
 * describe ：
 */
public class FushuException extends Exception {
    private String msg;

    public FushuException(String msg) {
        this.msg = msg;
        //super(msg);
    }





    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return msg;
    }
}
