package com.oncoit.service;

import com.oncoit.mapper.DrugMapper;
import com.oncoit.pojo.Association;
import com.oncoit.pojo.Drug;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: gyao
 * Date: 12/20/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Service
@Transactional
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;

    public List<String> listMatchNames(String name) {
        Example example=new Example(Drug.class);
        if (StringUtils.isNotBlank(name)) {
            example.createCriteria().orLike("drug", "%" + name + "%");
            example.setOrderByClause("drug asc");
        }

        List<Drug> list=drugMapper.selectByExample(example);
        List<String> nameList=new ArrayList<>();
        for (Drug ls:list){
            String drugsName=ls.getDrug();
            if(!nameList.contains(drugsName)){
                nameList.add(drugsName);
            }
        }
        return nameList;
    }
}
