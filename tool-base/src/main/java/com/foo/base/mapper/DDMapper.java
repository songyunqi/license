package com.foo.base.mapper;

import java.util.List;

public interface DDMapper<T, ID, TRequest> {

    T getOne(ID id);

    int save(T t);

    int delete(List<ID> ids);

    int update(List<T> list);

    int batchImport(List<T> list);

    List<T> list(TRequest t);

}
