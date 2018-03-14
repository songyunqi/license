package com.foo.base.controller;

import com.foo.base.request.ARequest;
import com.foo.base.response.AResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public abstract class CController<TRequest extends ARequest, TResponse extends AResponse>
        implements DDController<TRequest, TResponse>, AController<TRequest, TResponse> {

    @RequestMapping("save")
    @ResponseBody
    public TResponse save(TRequest t) {
        TResponse response = doSave(t);
        return response;
    }

    @RequestMapping("delete")
    @ResponseBody
    public TResponse delete(TRequest request) {
        TResponse response = doDelete(request);
        return response;
    }

    @RequestMapping("update")
    @ResponseBody
    public TResponse update(TRequest request) {
        TResponse response = doUpdate(request);
        return response;
    }

    @RequestMapping("list")
    @ResponseBody
    public TResponse list(TRequest request) {
        TResponse response = doList(request);
        return response;
    }

    @RequestMapping("page")
    @ResponseBody
    public TResponse page(TRequest request) {
        TResponse response = doPage(request);
        return response;
    }

    @RequestMapping("statis")
    @ResponseBody
    public TResponse statis(TRequest request) {
        TResponse response = doStatis(request);
        return response;
    }

    @RequestMapping("batchImport")
    @ResponseBody
    public TResponse batchImport(TRequest request) {
        TResponse response = doBatchImport(request);
        return response;
    }

    @RequestMapping("batchExport")
    public void doBatchExport(TRequest request, HttpServletRequest httpServletRequest) {

    }

}
