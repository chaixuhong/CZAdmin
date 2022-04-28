package com.cz.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;


/**
 * @author chaizi
 * @date 2019-09-01
 */
public class CodeGeneratedUtil {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String genUUID() {
        return IdUtil.simpleUUID();
    }

    public static String genMD5(String content) {
        return SecureUtil.md5(content);
    }


}
