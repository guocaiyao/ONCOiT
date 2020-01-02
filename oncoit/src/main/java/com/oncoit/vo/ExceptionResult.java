package com.oncoit.vo;

import com.oncoit.enums.ExceptionEnum;
import lombok.Data;

import java.text.SimpleDateFormat;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private String timestamp;

    public ExceptionResult(ExceptionEnum em){
        this.status=em.getCode();
        this.message=em.getMsg();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp=df.format(System.currentTimeMillis());
    }
}
