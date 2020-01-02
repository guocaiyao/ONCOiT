package com.oncoit.mapper;

import com.oncoit.pojo.Association;
import com.oncoit.vo.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


import java.util.BitSet;
import java.util.List;


/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Repository
@org.apache.ibatis.annotations.Mapper
public interface AssociationMapper extends Mapper<Association> {

    List<Record> selectListGroupByPosition();
    int count();

    List<Association> selectByqueryName(
            @Param("bodysite") String bodysite,
            @Param("diseaseName") String diseaseNme,
            @Param("drugName") String drugName,
            @Param("biomarkerName") String biomarkerName,
            @Param("targetName") String targetName);
}
