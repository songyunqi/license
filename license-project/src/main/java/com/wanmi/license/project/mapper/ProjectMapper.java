package com.wanmi.license.project.mapper;

import com.foo.base.mapper.DDMapper;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.request.ProjectRequest;


public interface ProjectMapper extends DDMapper<Project, Long, ProjectRequest> {

    Long selectProjectCount(ProjectRequest request);
    int updateById(Project project);

}
