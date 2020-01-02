package com.oncoit.pojo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: gyao
 * Date: 11/27/19
 * E-mail: yaoguocai_cool@163.com
 **/
@Data
@Table(name = "oncoit_core_table")
public class Association implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String pmid;
    private String Research_type;
    private String Sample;
    private String Disease_name;
    private String Target;
    private String Treatment;
    private String Control;
    private String Drugs;
    private String Size_of_treatment_group;
    private String Size_of_control_group;
    private String Design;
    private String Prognosis;
    private String Efficacy;
    private String Adverse_event;
    private String Cell_based_evidence;
    private String Animal_model_based_evidence;
    private String Biomarker_parent;
    private String Biomarker_child;
    private String Pub_date;
    private String Pub_title;
    private String Joural_name;
    private String Abstract;
    private String Bodysite;
}
