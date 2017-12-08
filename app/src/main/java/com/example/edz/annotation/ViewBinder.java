package com.example.edz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * authr : edz on 2017/12/8  下午3:12
 * describe ：
 */

@Target(ElementType.FIELD) // 标识改注解应用在成员变量上
@Retention(RetentionPolicy.RUNTIME) // 标识生命周期是运行时
public @interface ViewBinder {
    int id() default -1;
}
