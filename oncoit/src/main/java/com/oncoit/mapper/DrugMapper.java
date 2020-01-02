package com.oncoit.mapper;

import com.oncoit.pojo.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * Author: gyao
 * Date: 12/20/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Repository
public interface DrugMapper extends Mapper<Drug> {

}
