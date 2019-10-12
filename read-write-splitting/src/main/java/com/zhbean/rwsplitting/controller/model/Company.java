package com.zhbean.rwsplitting.controller.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Company implements Serializable {
    private Integer id;

    private String name;

    private String enname;

    private Date created;

    private Date modified;

    private Integer state;

    private String operater;

    private Byte effective;

    private String shortname;

}