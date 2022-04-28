package com.cz.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * sql字段转java
 *
 * @author Zheng Jie
 * @date 2019-01-03
 */
@Slf4j
public class ColUtil {

    private static volatile Properties properties;

    /**
     * 转换mysql数据类型为java数据类型
     *
     * @param type 数据库字段类型
     * @return String
     */
    public static String cloToJava(String type) {
        if (Objects.isNull(properties)) {
            initProperties();
        }
        return properties.getProperty(type, "unknowType");
    }

    /**
     * 初始化配置信息
     */
    public static Properties initProperties() {
        try {
            synchronized (ColUtil.class) {
                if (Objects.isNull(properties)) {
                    properties = new Properties();
                    properties.load(ColUtil.class.getClassLoader().getResourceAsStream("generator.properties"));
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return properties;
    }
}
