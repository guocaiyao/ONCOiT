package com.oncoit.exception;

import com.oncoit.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 * 自定义通用异常
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LyException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
