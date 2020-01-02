package com.oncoit.controller;

import com.oncoit.service.DiseaseService;
import com.oncoit.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: gyao
 * Date: 12/20/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Controller
@RequestMapping("api/target")
public class ApiTargetController {
    @Autowired
    private TargetService targetService;
    @GetMapping("/suggestion")

    public ResponseEntity<List<String>> suggestion(
            @RequestParam(value = "name", required = true,defaultValue = "TLR3")
                    String name){
        List<String> matchNames=targetService.listMatchNames(name);
        return ResponseEntity.ok(matchNames);
    }
}
