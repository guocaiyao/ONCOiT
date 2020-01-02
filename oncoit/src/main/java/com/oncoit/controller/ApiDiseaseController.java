package com.oncoit.controller;

import com.oncoit.pojo.Association;
import com.oncoit.service.DiseaseService;
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
@RequestMapping("api/disease")
public class ApiDiseaseController {
    @Autowired
    private DiseaseService diseaseService;
    @GetMapping("/suggestion")

    public ResponseEntity<List<String>> suggestion(
            @RequestParam(value = "name", required = true,defaultValue = "cancer")
                    String name){
        List<String> matchNames=diseaseService.listMatchNames(name);
        return ResponseEntity.ok(matchNames);
    }
}
