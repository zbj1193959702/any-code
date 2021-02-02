package com.hainiu.cat.web.design;

/**
 * create by biji.zhao on 2020/12/10
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
