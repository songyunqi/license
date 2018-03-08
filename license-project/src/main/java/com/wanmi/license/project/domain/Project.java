package com.wanmi.license.project.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Project implements Serializable {

    private Long id;
    private String groupId;
    private String artifactId;
    private String companyName;
    private String defaultPassword;
    private String licensingSubject;
}
