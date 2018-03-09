package com.wanmi.license.project.controller;

import com.foo.base.controller.CController;
import com.foo.base.response.AResponse;
import com.wanmi.license.project.request.LicenseParameterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("parameter")
public class LicenseParameterController extends CController<LicenseParameterRequest, AResponse> {

    public AResponse doStatis(LicenseParameterRequest licenseParameterRequest) {
        return null;
    }

    public AResponse doBatchImport(LicenseParameterRequest licenseParameterRequest) {
        return null;
    }

    public AResponse doSave(LicenseParameterRequest t) {
        return null;
    }

    public AResponse doDelete(LicenseParameterRequest ids) {
        return null;
    }

    public AResponse doUpdate(LicenseParameterRequest list) {
        return null;
    }

    public AResponse doList(LicenseParameterRequest licenseParameterRequest) {
        return null;
    }

    public AResponse doPage(LicenseParameterRequest licenseParameterRequest) {
        return null;
    }

    @RequestMapping("list-view")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("parameter/list");
        return mv;
    }
}
