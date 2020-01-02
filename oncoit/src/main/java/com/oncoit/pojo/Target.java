package com.oncoit.pojo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: gyao
 * Date: 12/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Data
@Table(name = "target_oncoit")
public class Target implements Serializable {
    private static final long serialVersionUID = 1L;

    private String target;
    private String id;

}
