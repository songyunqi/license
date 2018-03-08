package com.wanmi.license.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mv.setViewName("index");
        mv.addObject("principal", principal);
        return mv;
    }
}
