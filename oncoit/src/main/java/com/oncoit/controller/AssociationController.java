package com.oncoit.controller;

import com.oncoit.interceptor.LoginInterceptor;
import com.oncoit.pojo.Association;
import com.oncoit.service.AssociationService;
import com.oncoit.vo.PageResult;
import com.oncoit.vo.Record;
import com.oncoit.vo.RestJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Console;
import java.util.List;
import java.util.logging.Logger;
import com.oncoit.config.BaseController;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Controller
@RequestMapping("api/association")
public class AssociationController extends BaseController{
    @Autowired
    private AssociationService associationService;

    @RequestMapping("disease")
    public ResponseEntity<List<Association>> queryByDiseaseName(
            @RequestParam(value = "diseaseName",required = true,defaultValue = "Breast cancer")
                    String diseaseName){
      List<Association> associationList=associationService.queryByDiseaseName(diseaseName);
      return ResponseEntity.ok(associationList);
    }

    @RequestMapping("target")
    public ResponseEntity<List<Association>> queryByTargetName(
            @RequestParam(value = "targetName",required = true,defaultValue = "TLR3 (O15455);")
            String targetName){
        List<Association> associationList=associationService.queryByTargetName(targetName);
        return ResponseEntity.ok(associationList);
    }

    @RequestMapping("drug")
    public ResponseEntity<List<Association>> queryByDrugName(
            @RequestParam(value = "drugName",required = true,defaultValue = "MVA-MUC1 vaccine (NCIT:C2241);")
            String drugName){
        List<Association> associationList=associationService.queryByDrugName(drugName);
        return ResponseEntity.ok(associationList);
    }

    @RequestMapping("biomarker")
    public ResponseEntity<List<Association>> queryByBiomarkerName(
            @RequestParam(value = "biomarkerName",required = true,defaultValue = "CD8 (P01732); CD4 (P01730); T-Lymphocyte (NCIT:C12476); ")
            String biomarkerName){
        List<Association> associationList=associationService.queryByBiomarkerName(biomarkerName);
        return ResponseEntity.ok(associationList);
    }

    /**
     * 分页查询整个数据库结果
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @RequestMapping("page")
    public ResponseEntity<PageResult<Association>> queryAssociationByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false) String key
    ){
        PageResult<Association> result=associationService.queryAssociationByPage(page,rows,sortBy,desc,key);
        return ResponseEntity.ok(result);
    }

    /**
     * 基于position分页查询association
     * @param position
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("bodysite")
    public ResponseEntity<PageResult<Association>> queryAssociationtionByBodysite(
            @RequestParam(value = "position",defaultValue = "Gastrointestinal tract") String position,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "20") Integer rows
    ){
        System.out.println(position+", "+page);
        PageResult<Association> result=associationService.queryAssociationByBodysite(position,page,rows);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("summary")
    /**
     * 基于bodysite的分组统计
     */
    public ResponseEntity<List<Record>> summary(){
        List<Record> records=associationService.listGroupByPosition();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/download/bodysite")
    public void downloadByBodysite(@NotBlank @RequestParam String bodysite){
        if (StringUtils.isBlank(bodysite)){
            return;
        }
        byte[] csvBytes=associationService.getFile(
                bodysite,null,null,null,null);
        System.out.println(csvBytes);
        downloadFile(bodysite.replace(" ","_")+".csv",
                csvBytes,"text/csv");
    }

    @GetMapping("/download/disease")
    public void downloadByDisease(
            @NotBlank @RequestParam String diseaseName){
        byte[] csvBytes=associationService.getFile(
          null,diseaseName,null,null,null);
        System.out.println("csvBytes: "+csvBytes);
        downloadFile("association.csv",csvBytes,"text/csv");
    }

    @GetMapping("/download/drug")
    public void downloadBydrug(
            @NotBlank @RequestParam String drugName){
        byte[] csvBytes=associationService.getFile(
                null,null,drugName,null,null
        );
        downloadFile("association.csv",csvBytes,"text/csv");
    }
    @GetMapping("/download/biomarker")
    public void downloadBybiomarker(
            @NotBlank @RequestParam String biomarkerName){
        byte[] csvBytes=associationService.getFile(
                null,null,null,biomarkerName,null
        );
        downloadFile("association.csv",csvBytes,"text/csv");
    }
    @GetMapping("/download/target")
    public void downloadBytarget(
            @NotBlank @RequestParam String targetName){
        byte[] csvBytes=associationService.getFile(
                null,null,null,null,targetName
        );
        downloadFile("association.csv",csvBytes,"text/csv");
    }

}
