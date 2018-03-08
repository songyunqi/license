package com.wanmi.license.project.request;

import com.foo.base.request.ARequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest extends ARequest {

    private Long id;
    private String groupId;
    private String artifactId;
    private String companyName;
    private String defaultPassword;
    private String licensingSubject;

}
