package com.oncoit.vo;

import lombok.Data;

import java.util.List;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/

/**
 * 定义分页查询的返回结果类
 * @param <T>
 */
@Data
public class PageResult<T> {
    private Long total;
    private Integer totalPage;
    private List<T> items;
    public PageResult(){

    }
    public PageResult(Long total,List<T> items){
        this.total=total;
        this.items=items;
    }
    public PageResult(Long total,Integer totalPage,List<T> items){
        this.total=total;
        this.totalPage=totalPage;
        this.items=items;
    }
}
