package com.oncoit.enums;

import com.oncoit.pojo.Association;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {
    DISEASE_NOT_FOUND(404,"Selected disease not found in DB !"),
    ASSOCIATION_NOT_FOUND(404,"Association query not founded !"),
    TARGET_NOT_FOUND(404,"Selected target not found in DB !"),
    DRUG_NOT_FOUND(404,"Selected drug not found in DB !"),
    BIOMARKER_NOT_FOUND(404,"Selected biomarker not found in DB !"),
    BODYSITE_NOT_FOUND(404,"Selected bodysite not found in DB !"),
    LISTGROUPBYPOSITION_IS_NULL(404,"selectListGroupByPosition is null in DB !"),
    CATEGORY_NOT_FOUND(404,"Database category query not founded !");

    private int code;
    private String msg;
}
