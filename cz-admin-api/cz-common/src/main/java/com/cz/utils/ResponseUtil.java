package com.cz.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cz.enums.ResultEnum;
import com.cz.model.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @Auther: chaixuhong
 */
public class ResponseUtil {

    /**
     * 错误返回
     *
     * @param resultEnum
     * @return
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setErrMsg(resultEnum.getErrMsg());
        return resultVO;
    }

    /**
     * 自定义返回
     *
     * @param code
     * @param errMsg
     * @return
     */
    public static ResultVO error(int code, String errMsg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setErrMsg(errMsg);
        return resultVO;
    }

    /**
     * 有参成功返回
     *
     * @param data
     * @return
     */
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setErrMsg(ResultEnum.SUCCESS.getErrMsg());
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 无参成功返回
     *
     * @return
     */
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setErrMsg(ResultEnum.SUCCESS.getErrMsg());
        return resultVO;
    }

    /**
     * 标准枚举输出
     *
     * @param response
     * @param httpStatus
     * @param resultEnum
     * @throws IOException
     */
    public static void printOut(HttpServletResponse response, int httpStatus, ResultEnum resultEnum) throws IOException {
        printOutData(response, httpStatus, resultEnum, null);
    }

    /**
     * 标准枚举输出
     *
     * @param response
     * @param httpStatus
     * @param resultEnum
     * @throws IOException
     */
    public static void printOutData(HttpServletResponse response, int httpStatus, ResultEnum resultEnum, Object data) throws IOException {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setErrMsg(resultEnum.getErrMsg());
        if (!Objects.isNull(data)) {
            resultVO.setData(data);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(httpStatus);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(resultVO));
        out.flush();
        out.close();
    }
}
