package com.atguigu.gulimall.product.exception;
import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MrGuo
 * @description
 * @date 2021/1/12
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.atguigu.gulimall")
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handValidException(MethodArgumentNotValidException e){
        log.error("参数校验异常{}，异常类型{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> errorMap = new HashMap();
        bindingResult.getFieldErrors().forEach(item->{
            String message = item.getDefaultMessage();
            String field = item.getField();
            errorMap.put(field,message);
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION).putData(errorMap);
    }

    @ExceptionHandler(value = Exception.class)
    public R handException(Exception e){
        log.error("发生异常{}，异常类型{}",e.getMessage(),e.getClass());
        e.printStackTrace();
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION);
    }
}
