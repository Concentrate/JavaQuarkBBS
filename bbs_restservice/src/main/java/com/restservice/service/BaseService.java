package com.restservice.service;

import java.util.List;

/**
 * Created by liudeyu on 2019/7/10.
 */
public interface BaseService<T, KEY> {

    void save(T t);

    List<T> find(KEY key);

    T findOne(KEY key);

    void delete(KEY key);

    void saveInBatch(Iterable<T> items);

    List<T> findInBatch(Iterable<KEY> keys);


}
