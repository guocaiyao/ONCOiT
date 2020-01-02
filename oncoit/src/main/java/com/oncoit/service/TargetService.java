package com.oncoit.service;

import com.oncoit.mapper.DiseaseMapper;
import com.oncoit.mapper.TargetMapper;
import com.oncoit.pojo.Association;
import com.oncoit.pojo.Target;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: gyao
 * Date: 12/20/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Service
@Transactional
public class TargetService {
    @Autowired
    private TargetMapper targetMapper;

    public List<String> listMatchNames(String name) {
        Example example=new Example(Target.class);
        if (StringUtils.isNotBlank(name)) {
            example.createCriteria().orLike("target", "%" + name + "%");
            example.setOrderByClause("target asc");
        }

        List<Target> list=targetMapper.selectByExample(example);
        List<String> nameList=new ArrayList<>();
        for (Target ls:list){
            String targetName=ls.getTarget();
            if(!nameList.contains(targetName)){
                nameList.add(targetName);
            }
        }
        return nameList;
    }

}
