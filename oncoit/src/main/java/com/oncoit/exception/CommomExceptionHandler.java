package com.oncoit.exception;

import com.oncoit.enums.ExceptionEnum;
import com.oncoit.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@ControllerAdvice
public class CommomExceptionHandler {
    // 处理异常的方法,代替springMVC自动处理抛出的异常
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e){
        ExceptionEnum anEnum=e.getExceptionEnum();
        return ResponseEntity.status(anEnum.getCode()).
                body(new ExceptionResult(e.getExceptionEnum()));
    }
}
