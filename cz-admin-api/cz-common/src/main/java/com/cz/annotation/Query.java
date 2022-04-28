package com.cz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {
    //字段名称
    String fieldName() default "";

    //查询方式
    Type type() default Type.EQUAL;

    //模糊字段
    String blurry() default "";

    enum Type {
        // 相等
        EQUAL
        //  大于等于
        , GREATER_THAN
        //小于等于
        , LESS_THAN
        //中模糊查询
        , INNER_LIKE
        // 左模糊查询
        , LEFT_LIKE
        // 右模糊查询
        , RIGHT_LIKE
        // 小于
        , LESS_THAN_NQ
        //  包含
        , IN
        // 不包含
        , NOT_IN
        // 不等于
        , NOT_EQUAL
        // between
        , BETWEEN
        // 不为空
        , NOT_NULL
        // 为空
        , IS_NULL
        //模糊
        , BLURRY
    }
}
