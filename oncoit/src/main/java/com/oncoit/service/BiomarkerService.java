package com.oncoit.service;

import com.oncoit.mapper.BiomarkerMapper;
import com.oncoit.pojo.Association;
import com.oncoit.pojo.Biomarker;
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
public class BiomarkerService {
    @Autowired
    private BiomarkerMapper biomarkerMapper;

    public List<String> listMatchNames(String name) {
        Example example=new Example(Biomarker.class);
        if (StringUtils.isNotBlank(name)) {
            example.createCriteria().orLike("biomarker", "%" + name + "%");
            example.setOrderByClause("biomarker asc");
        }

        List<Biomarker> list=biomarkerMapper.selectByExample(example);
        List<String> nameList=new ArrayList<>();
        for (Biomarker ls:list){
            String biomarkerName=ls.getBiomarker();
            if(!nameList.contains(biomarkerName)){
                nameList.add(biomarkerName);
            }
        }
        return nameList;
    }
}
