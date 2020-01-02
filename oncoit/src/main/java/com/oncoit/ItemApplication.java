package com.oncoit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@SpringBootApplication
@MapperScan("com.oncoit.mapper")

public class ItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}
