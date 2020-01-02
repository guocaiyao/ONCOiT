package com.oncoit.mapper;

import com.oncoit.pojo.Association;
import com.oncoit.pojo.Target;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Author: gyao
 * Date: 12/20/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Repository
public interface TargetMapper extends Mapper<Target> {
    List<String> selectListMatchNames(String name);
}
