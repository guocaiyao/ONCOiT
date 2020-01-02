package com.oncoit.mapper;

import com.oncoit.pojo.Association;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Author: gyao
 * Date: 12/20/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Repository
public interface DiseaseMapper extends Mapper<Association> {
    List<String> selectListMatchNames(String name);
}
