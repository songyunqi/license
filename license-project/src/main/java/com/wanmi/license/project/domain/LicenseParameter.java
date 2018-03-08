package com.wanmi.license.project.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LicenseParameter implements Serializable {

    //private Map<String, Object> extra;

    private int consumerAmount;
    private String consumerType;
    private String holder;
    private String issuer;
    private String subject;
    private Date notBefore;
    private Date notAfter;
    private String info;
    private String extra;
}
