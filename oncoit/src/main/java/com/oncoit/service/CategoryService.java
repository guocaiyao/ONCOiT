package com.oncoit.service;

import com.oncoit.enums.ExceptionEnum;
import com.oncoit.exception.LyException;
import com.oncoit.mapper.CategoryMapper;
import com.oncoit.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryListByParentId(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        List<Category> list=categoryMapper.select(record);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
