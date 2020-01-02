package com.oncoit.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oncoit.config.CsvUtil;
import com.oncoit.enums.ExceptionEnum;
import com.oncoit.exception.LyException;
import com.oncoit.mapper.AssociationMapper;
import com.oncoit.pojo.Association;
import com.oncoit.vo.PageResult;
import com.oncoit.vo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Service
@Transactional
public class AssociationService {
    @Autowired
    private AssociationMapper associationMapper;


    public List<Association> queryByDiseaseName(String diseaseName) {
        Association record = new Association();
        record.setDisease_name(diseaseName);
        List<Association> list = associationMapper.select(record);

        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.DISEASE_NOT_FOUND);
        }
        return list;
    }

    public List<Association> queryByBodysiteName(String bodysiteName) {
        Association record=new Association();
        record.setBodysite(bodysiteName);
        List<Association> list = associationMapper.select(record);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BODYSITE_NOT_FOUND);
        }
        return list;
    }

    public PageResult<Association> queryAssociationByPage
            (Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        PageHelper.startPage(page,rows);
        // 过滤
        /**
         * select * from oncoit_core_table
         * where Biomarker_parent LIKE "%Lung%" or
         * Disease_name LIKE "%Lung%" ORDER BY id DESC;
         */
        Example example = new Example(Association.class);
        if(StringUtils.isNotBlank(key)){
            example.createCriteria().orLike("Biomarker_parent","%"+key+"%")
                    .orLike("Disease_name","%"+key+"%");
        }
        // 排序
        if(StringUtils.isNotBlank(sortBy)){
            String orderClause=sortBy+(desc ? " DESC":" ASC");
            example.setOrderByClause(orderClause);
        }
        // 查询
        List<Association> associationList = associationMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(associationList)){
            throw new LyException(ExceptionEnum.ASSOCIATION_NOT_FOUND);
        }
        // 解析分页结果
        PageInfo<Association> info = new PageInfo<>(associationList);
        PageResult<Association> result = new PageResult<>(info.getTotal(), associationList);
        return result;
    }

    public PageResult<Association> queryAssociationByBodysite(
            String position, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Association.class);
        //过滤,排序
        if (StringUtils.isNotBlank(position)){
            example.createCriteria().orLike("Bodysite","%"+position+"%");
            example.setOrderByClause("Disease_name");
        }
        // 查询
        List<Association> associationList = associationMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(associationList)){
            throw new LyException(ExceptionEnum.ASSOCIATION_NOT_FOUND);
        }
        PageInfo<Association> info = new PageInfo<>(associationList);
        PageResult<Association> result = new PageResult<>(info.getTotal(), info.getPages(),associationList);
        return result;
    }

    public List<Association> queryByTargetName(String targetName) {
        Example example=new Example(Association.class);
        if (StringUtils.isNotBlank(targetName)) {
            example.createCriteria().orLike("Target", "%" + targetName.toUpperCase() + "%");
        }
        List<Association> list=associationMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.TARGET_NOT_FOUND);
        }
        return list;
    }

    public List<Association> queryByDrugName(String drugName) {
        Example example=new Example(Association.class);
        if (StringUtils.isNotBlank(drugName)) {
            example.createCriteria().orLike("Drugs", "%" + drugName + "%");
        }
        List<Association> list=associationMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.DRUG_NOT_FOUND);
        }
        return list;
    }

    public List<Association> queryByBiomarkerName(String biomarkerName) {
        Example example=new Example(Association.class);
        if (StringUtils.isNotBlank(biomarkerName)) {
            example.createCriteria().orLike("Biomarker_child", "%" + biomarkerName + "%");
        }
        List<Association> list=associationMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BIOMARKER_NOT_FOUND);
        }
        return list;
    }

    public List<Record> listGroupByPosition() {
        List<Record> records=associationMapper.selectListGroupByPosition();
        if (CollectionUtils.isEmpty(records)){
            throw new LyException(ExceptionEnum.LISTGROUPBYPOSITION_IS_NULL);
        }
        List<Record> recordList=records.stream().peek(record ->
                record.put("id",record.getString("Bodysite")
                .replaceAll(" ","_"))).collect(Collectors.toList());
        return recordList;
    }

    /**
     * 获取结果的byte对象
      * @param bodysite
     * @param diseaseNme
     * @param drugName
     * @param biomarkerName
     * @param targetName
     * @return
     */
    public byte[] getFile(
            String bodysite, String diseaseNme, String drugName,
            String biomarkerName, String targetName) {
        List<Association> list=listByName(bodysite,diseaseNme,drugName,biomarkerName,targetName);
        for (Association ls:list){
            System.out.println("list= "+ls);
        }
        return getFileBytes(list);
    }
    public List<Association> listByName(
            String bodysite, String diseaseNme, String drugName,
            String biomarkerName, String targetName){
        List<Association> selectList=associationMapper.selectByqueryName(
                bodysite,diseaseNme,drugName,biomarkerName,targetName);
//        List<Association> collect = selectList.stream().peek(association -> {
//            if (StringUtils.isNotBlank(association.getId())) {
//            }
//        }).collect(Collectors.toList());
//        return collect;
        return selectList;
    }
    private byte[] getFileBytes(List<Association> associations){
        String[] headers=new String[]{"Association ID","Disease","Treatment","Control",
                "Biomarker","Target","Type","Body Site","PMID"};
        List<Map<String,String>> datas= IntStream.range(
                0,associations.size()).mapToObj(i -> {
            Association association=associations.get(i);
            Map<String,String> map=new HashMap<>();
            map.put("Association ID",association.getId());
            map.put("Disease",association.getDisease_name());
            map.put("Treatment",association.getTreatment());
            map.put("Control",association.getControl());
            map.put("Biomarker",association.getBiomarker_child());
            map.put("Target",association.getTarget());
            map.put("Type",association.getResearch_type());
            map.put("Body Site",association.getBodysite());
            map.put("PMID",association.getPmid());
            System.out.println("map= "+map);
            return map;
        }).collect(Collectors.toList());
        System.out.println("datas= "+datas);
        return CsvUtil.getBytesByDateMap(headers,datas);
    }
}
