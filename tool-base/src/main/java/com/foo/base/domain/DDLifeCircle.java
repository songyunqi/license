package com.foo.base.domain;

public abstract class DDLifeCircle<T> implements LifeCircle<T> {


    public void willDelete(T t) {
        boolean isDependent = isDependent(t);
        if (isDependent) {
            return;
        }
        deleted(t);
    }
}
