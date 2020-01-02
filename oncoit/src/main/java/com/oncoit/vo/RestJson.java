package com.oncoit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: gyao
 * Date: 12/22/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 定义返回的结果类
 */
public class RestJson<T> {
    private Integer status;
    private String message;
    private T data;
    public void setSuccess() {
        setSuccess(null);
    }
    public void setSuccess(T data){
        this.status=0;
        this.message="Success";
        this.data=data;
    }
    public void setFailure(String errorMsg){
        setFailure(-1,errorMsg);
    }
    public void setFailure(int errorCode){
        setFailure(errorCode,"");
    }
    public void setFailure(int errorCode,String errorMsg){
        this.status=errorCode;
        this.message=errorMsg;
    }
    public void setData(T errorMsg){

    }
}
