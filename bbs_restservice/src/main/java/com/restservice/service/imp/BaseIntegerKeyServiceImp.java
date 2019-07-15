package com.restservice.service.imp;

import com.restservice.service.IntegerKeyBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudeyu on 2019/7/15.
 */
public class BaseIntegerKeyServiceImp<T extends JpaRepository<E, Integer>, E> implements IntegerKeyBaseService<E> {

    @Autowired
    protected T repo;

    @Override
    public void save(E e) {
        repo.save(e);
    }


    @Override
    public E findOne(Integer integer) {
        return repo.findOne(integer);
    }

    @Override
    public void delete(Integer integer) {
        repo.delete(integer);
    }

    @Override
    public void saveInBatch(Iterable<E> items) {
        repo.save(items);
    }

    @Override
    public List<E> findInBatch(Iterable<Integer> integers) {
        return repo.findAll(integers);
    }

    @Override
    public List<E> findAll() {
        return repo.findAll();
    }
}
