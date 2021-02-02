package com.hainiu.cat.util;

/**
 * create by biji.zhao on 2020/12/10
 */
@FunctionalInterface
public interface BooleanFunctionalInterface<T> {

    boolean test(T t);
}
