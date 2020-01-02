package com.oncoit.service;

import com.oncoit.mapper.DiseaseMapper;
import com.oncoit.pojo.Association;
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
public class DiseaseService {
    @Autowired
    private DiseaseMapper diseaseMapper;

    public List<String> listMatchNames(String name) {
        Example example=new Example(Association.class);
        if (StringUtils.isNotBlank(name)) {
            example.createCriteria().orLike("Disease_name", "%" + name + "%");
            example.setOrderByClause("Disease_name asc");
        }

        List<Association> list=diseaseMapper.selectByExample(example);
        List<String> nameList=new ArrayList<>();
        for (Association ls:list){
            String diseaseName=ls.getDisease_name();
            if(!nameList.contains(diseaseName)){
                nameList.add(diseaseName);
            }
        }
        return nameList;
    }

}
