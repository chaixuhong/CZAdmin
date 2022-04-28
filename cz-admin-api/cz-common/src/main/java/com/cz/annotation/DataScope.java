package com.cz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {
    //与部门的关联字段, 默认dept_id
    String value() default "dept_id";

    enum Type{
        ALL,
        CURRENT,
        CUSTOM,
        ;
    }
}
