package com.wanmi.license.project.controller;

import com.foo.base.controller.CController;
import com.foo.base.response.AResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.request.ProjectRequest;
import com.wanmi.license.project.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("project")
public class ProjectController extends CController<Project, Long, ProjectRequest, AResponse> {

    @Autowired
    ProjectService projectService;

    public AResponse doStatis(ProjectRequest request) {
        return null;
    }

    public AResponse doBatchImport(ProjectRequest request) {
        return null;
    }

    public AResponse doSave(ProjectRequest request) {
        return null;
    }

    public AResponse doDelete(ProjectRequest request) {
        return null;
    }

    public AResponse doUpdate(ProjectRequest request) {
        return null;
    }

    public AResponse doList(ProjectRequest request) {
        return AResponse.builder().content(projectService.list(request)).build();
    }

    public AResponse doPage(ProjectRequest request) {
        PageInfo<Project> page = projectService.page(request);
        return AResponse.builder().content(page).build();
    }

    @RequestMapping("init-project")
    @ResponseBody
    public AResponse initProject(ProjectRequest request) throws IOException {

        Project project = new Project();
        BeanUtils.copyProperties(request, project);
        if (null == project) {
            return AResponse.builder().content(null).build();
        }
        projectService.initProject(project);
        return null;
    }

    @RequestMapping("gen-license")
    @ResponseBody
    public AResponse genLicense(Project project) throws IOException {
        if (null == project) {
            return AResponse.builder().content(null).build();
        }
        projectService.initProject(project);
        return null;
    }

    @RequestMapping("hello")
    @ResponseBody
    public AResponse hello() {
        return AResponse.builder().content("hello").build();
    }

}
