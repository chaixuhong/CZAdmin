package com.cz.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class CommonSecurityUtil {

    public static JSONArray getDataScope(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(Objects.isNull(principal)){
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(principal));
        JSONArray dataScope = jsonObject.getJSONArray("dataScope");
        return dataScope;
    }
}
