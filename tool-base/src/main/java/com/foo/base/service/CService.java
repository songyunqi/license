package com.foo.base.service;

import com.foo.base.request.ARequest;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public abstract class CService<T, ID, TRequest extends ARequest> implements DDService<T, ID, TRequest> {

    public abstract List<T> list(TRequest tRequest);

    public PageInfo<T> page(final TRequest request) {
        PageInfo<T> pageInfo = PageHelper.startPage(request.getPageNum(), request.getPageSize()).doSelectPageInfo(() -> list(request));
        return pageInfo;
    }

    public PageInfo<T> page(TRequest request, ISelect iSelect) {
        //Long totalCount = PageHelper.count(iSelect);
        //iSelect.
        Page<?> page = PageHelper.startPage(request.getPageNum(), request.getPageSize(), false);
        List<T> list = list(request);
        PageInfo<T> pageInfo = new PageInfo(page, page.getPageSize());
        pageInfo.setList(list);
        //pageInfo.setTotal(totalCount);


        pageInfo = PageHelper.startPage(request.getPageNum(), request.getPageSize()).doSelectPageInfo(iSelect);

        return pageInfo;
    }

}
