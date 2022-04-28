package com.cz.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流
 *
 * @author chaixuhong
 * @date 2021年12月30日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    //功能描述，不参与逻辑
    String description() default "默认防重复请求";

    // 时间的，单位秒
    String period() default "5";

    // 限制访问次数
    String count() default "1";

    // 限制类型
    LimitType limitType() default LimitType.IP;


    enum LimitType {
        CUSTOMER,
        IP
    }

}
