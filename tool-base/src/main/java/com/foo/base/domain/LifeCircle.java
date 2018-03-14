package com.foo.base.domain;

public interface LifeCircle<T> {

    void created(T t);

    void willDelete(T t);

    void deleted(T t);

    boolean isDependent(T t);

}
