package com.cz.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cz.annotation.DataScope;
import com.cz.annotation.Query;
import com.github.yulichang.query.MPJQueryWrapper;
import org.apache.logging.log4j.util.Strings;

import java.lang.reflect.Field;
import java.util.*;

/**
 * mybatis-queryWrapper封装工具类
 * 配合Query注解使用
 *
 * @author chaixuhong
 * @date 2021年12月29日
 */
public class QueryUtil {

    public static <R, T> MPJQueryWrapper<R> buildWrapper(T clazz,Class<R> entityClass) {
        MPJQueryWrapper<R> queryWrapper = new MPJQueryWrapper<>();
        if(!Objects.isNull(entityClass)){
            queryWrapper.selectAll(entityClass);
        }
        /**
         * 关联表过滤数据
         */
        DataScope ds = clazz.getClass().getAnnotation(DataScope.class);
        if (!Objects.isNull(ds)) {
            JSONArray dataScope = CommonSecurityUtil.getDataScope();
            //dataScope size == 0 时，不进行过滤
            queryWrapper.innerJoin("sys_dept dept on dept.dept_id = t." + ds.value());
            if (dataScope == null) {
                queryWrapper.isNull("dept.dept_id");
                return queryWrapper;
            } else if (dataScope.size() > 0) {
                queryWrapper.in("dept.dept_id", dataScope);
            }
        }

        List<Field> fields = getAllFields(clazz.getClass(), new ArrayList<>());
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            // 设置对象的访问权限，保证对private的属性的访
            field.setAccessible(true);
            Query q = field.getAnnotation(Query.class);
            if (!Objects.isNull(q)) {
                String fieldName = Strings.isNotBlank(q.fieldName()) ? q.fieldName() : field.getName();
                fieldName = StringUtils.camelToUnderline(fieldName);
                if (!fieldName.contains(".")) {
                    fieldName = "t." + fieldName;
                }
                String blurry = q.blurry();
                try {
                    Object val = field.get(clazz);
                    if (Strings.isNotBlank(blurry)) {
                        //blurry优先级高于fieldName
                        setCondition(q.type(), queryWrapper, blurry, val);
                    } else {
                        //fieldName
                        setCondition(q.type(), queryWrapper, fieldName, val);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            field.setAccessible(accessible);
        }
        return queryWrapper;
    }


    public static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }

    public static <R> void setCondition(Query.Type type, MPJQueryWrapper<R> wrapper, String fieldName, final Object value) {
        //不需要value
        switch (type) {
            case NOT_NULL:
                wrapper.isNotNull(fieldName);
                break;
            case IS_NULL:
                wrapper.isNull(fieldName);
                break;
            default:
                break;
        }
        if (Objects.isNull(value) || "".equals(value)) {
            return;
        }
        switch (type) {
            case EQUAL:
                wrapper.eq(fieldName, value);
                break;
            case GREATER_THAN:
                wrapper.ge(fieldName, value);
                break;
            case LESS_THAN:
                wrapper.le(fieldName, value);
                break;
            case LESS_THAN_NQ:
                wrapper.lt(fieldName, value);
                break;
            case INNER_LIKE:
                wrapper.like(fieldName, value);
                break;
            case LEFT_LIKE:
                wrapper.likeLeft(fieldName, value);
                break;
            case RIGHT_LIKE:
                wrapper.likeRight(fieldName, value);
                break;
            case IN:
                if (value instanceof Collection) {
                    Collection c = (Collection) value;
                    if (CollUtil.isNotEmpty(c))
                        wrapper.in(fieldName, c);
                }
                break;
            case NOT_IN:
                wrapper.notIn(fieldName, value);
                break;
            case NOT_EQUAL:
                wrapper.ne(fieldName, value);
                break;
            case BETWEEN:
                if (value.toString().contains(",")) {
                    String[] split = value.toString().split(",");
                    wrapper.between(fieldName, split[0], split[1]);
                }
                break;
            case BLURRY: {
                final String[] split = fieldName.split(",");
                wrapper.and(w -> {
                    for (int i = 0; i < split.length; i++) {
                        w.like(split[i], value);
                        if (i < split.length - 1) {
                            w.or();
                        }
                    }
                });
                break;
            }
            default:
                break;
        }
    }
}
