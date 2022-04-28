package com.cz.exception.handler;

import com.cz.enums.ResultEnum;
import com.cz.exception.BusinessException;
import com.cz.exception.GlobalException;
import com.cz.model.vo.ResultVO;
import com.cz.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@ResponseBody
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 业务自定义异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResultVO handler(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
        log.error("【业务自定义异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 全局自定义异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public ResultVO handler(HttpServletRequest request, HttpServletResponse response, GlobalException e) {
        log.error("【全局自定义异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO parameterMissingExceptionHandler(HttpServletRequest request, HttpServletResponse response, MissingServletRequestParameterException e) {
        log.error("【忽略参数异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseUtil.error(ResultEnum.PARAM_ERROR.getCode(), "请求参数 " + e.getParameterName() + " 不能为空");
    }


    /**
     * 无权访问异常处理器
     *
     * @param e security无权访问处理器
     * @return ResponseResult
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResultVO parameterMissingExceptionHandler(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        log.error("【无权访问异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return ResponseUtil.error(ResultEnum.NO_AUTH_ACCESS);
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO parameterBodyMissingExceptionHandler(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException e) {
        log.error("【缺少请求体异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseUtil.error(ResultEnum.PARAM_ERROR.getCode(), "参数体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ExceptionHandler(BindException.class)
    public ResultVO parameterBindExceptionHandler(HttpServletRequest request, HttpServletResponse response, BindException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                log.error("【参数验证异常】: err = {} url = {} ", fieldError.getDefaultMessage(), request.getRequestURL());
                return ResponseUtil.error(ResultEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
        return ResponseUtil.error(ResultEnum.PARAM_ERROR);
    }


    /**
     * 参数效验异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO constraintViolationExceptionHandler(HttpServletRequest request, HttpServletResponse response, ConstraintViolationException ex) {
        log.error("【参数效验异常】: err = {} url = {} ", ex.getMessage(), request.getRequestURL());
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        if (!msgList.isEmpty()) {
            return ResponseUtil.error(ResultEnum.PARAM_ERROR.getCode(), msgList.get(0));
        }
        return ResponseUtil.error(ResultEnum.PARAM_ERROR);
    }


    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO parameterExceptionHandler(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                log.error("【参数验证异常】: err = {} url = {} ", fieldError.getDefaultMessage(), request.getRequestURL());
                return ResponseUtil.error(ResultEnum.PARAM_ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
        return ResponseUtil.error(ResultEnum.PARAM_ERROR);
    }


    /**
     * 不支持当前媒体类型 参数类型不对
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultVO handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpServletResponse response, HttpMediaTypeNotSupportedException e) {
        log.error("【请求异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseUtil.error(ResultEnum.PARAM_ERROR.getCode(), e.getMessage());
    }


    /**
     * 上传文件过大异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResultVO handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpServletResponse response, MaxUploadSizeExceededException e) {
        log.error("【请求异常】: err = {} url = {} ", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseUtil.error(ResultEnum.FILE_PARAM_ERROR);
    }


    /**
     * 未捕获异常处理器
     *
     * @param e 未捕获异常
     * @return ResponseInfo
     */
    @ExceptionHandler(Exception.class)
    public ResultVO unCatchExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        log.error("【未捕获异常】: err = {} url = {}", e.getMessage(), request.getRequestURL());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ResponseUtil.error(ResultEnum.SERVER_ERROR);
    }


}
