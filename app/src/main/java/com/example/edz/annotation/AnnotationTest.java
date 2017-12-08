package com.example.edz.annotation;

/**
 * authr : edz on 2017/12/8  下午2:20
 * describe ：注解测试类
 */

@MyAnnotation(color = "red",
        array = {22,23} ,
        metaAnnotation = @MetaAnnotation(birthday = "我的出身日期为2000-12-18"))

public class AnnotationTest {

    public static void main(String[] args) {
        //检查类AnnotationTest是否含有@MyAnnotation注解
        if(AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)){
            //若存在就获取注解
            MyAnnotation annotation=(MyAnnotation)AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation);
            //获取注解属性
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            //数组
            int[] arrs = annotation.array();
            for(int arr:arrs){
                System.out.println(arr);
            }
            //枚举
            Gender gender = annotation.gender();
            System.out.println("性别为："+gender);
            //获取注解属性
            MetaAnnotation meta = annotation.metaAnnotation();
            System.out.println(meta.birthday());
        }


    }
}
