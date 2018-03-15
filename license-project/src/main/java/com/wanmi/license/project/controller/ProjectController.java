package com.wanmi.license.project.controller;

import com.foo.base.controller.CController;
import com.foo.base.response.AResponse;
import com.github.pagehelper.PageInfo;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.request.ProjectRequest;
import com.wanmi.license.project.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("project")
public class ProjectController extends CController<ProjectRequest, AResponse> {

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

    public AResponse doList(final ProjectRequest request) {
        PageInfo<Project> page = projectService.page(request);
        return AResponse.builder().content(page).build();
    }

    public AResponse doPage(final ProjectRequest request) {
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


    @RequestMapping("list-view")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("project/list");
        return mv;
    }

}
