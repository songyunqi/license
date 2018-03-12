package com.foo.base.service;

import com.foo.base.request.ARequest;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public abstract class CService<T, ID, TRequest extends ARequest> implements DDService<T, ID, TRequest> {

    public abstract List<T> list(TRequest tRequest);

    public PageInfo<T> page(TRequest request) {
        Page<T> page = PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);
        List<T> list = list(request);
        PageInfo<T> pageInfo = new PageInfo(page, page.getPageSize());
        pageInfo.setList(list);
        return pageInfo;
    }

    public PageInfo<T> page(TRequest request, ISelect iSelect) {
        Page<T> page;
        long totalCount = 0;
        page = PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        totalCount = page.doCount(iSelect);
        List<T> list = list(request);
        PageInfo<T> pageInfo = new PageInfo(page, page.getPageSize());
        pageInfo.setList(list);
        pageInfo.setTotal(totalCount);
        return pageInfo;
    }

}
