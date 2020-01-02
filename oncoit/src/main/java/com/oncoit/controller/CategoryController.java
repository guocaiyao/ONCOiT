package com.oncoit.controller;

import com.oncoit.pojo.Category;
import com.oncoit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Controller
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询商品分类
     * @param pid
     * @return
     */
    @RequestMapping("list")
    public ResponseEntity<List<Category>>queryCategoryListByParentId(
        @RequestParam(value = "pid",defaultValue = "0") Long pid
    ){
        List<Category> categoryList=categoryService.queryCategoryListByParentId(pid);
        return ResponseEntity.ok(categoryList);
    }
}
