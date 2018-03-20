package com.wanmi.license.project.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LicenseParameter implements Serializable {

    //private Map<String, Object> extra;
    @JSONField(serialize=false)
    private Long id;
    @JSONField(serialize=false)
    private Long projectId;
    private Integer consumerAmount;
    private String consumerType;
    private String holder;
    private String issuer;
    private String subject;
    @JSONField (format="yyyy-MM-dd")
    private String notBefore;
    @JSONField (format="yyyy-MM-dd")
    private String notAfter;
    private String info;
    private String extra;
}
