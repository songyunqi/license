package com.foo.base.user;

import com.foo.base.controller.CController;
import com.foo.base.response.AResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController extends CController<UserRequest, AResponse> {

    public AResponse doStatics(UserRequest userRequest) {
        return null;
    }

    public AResponse doBatchImport(UserRequest userRequest) {
        return null;
    }

    public AResponse doSave(UserRequest t) {
        return null;
    }

    public AResponse doDelete(UserRequest ids) {
        return null;
    }

    public AResponse doUpdate(UserRequest list) {
        return null;
    }

    public AResponse doList(UserRequest userRequest) {
        return null;
    }

    public AResponse doPage(UserRequest userRequest) {
        return null;
    }
}
