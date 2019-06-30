package common.baseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
public class BaseServiceImp<E extends JpaRepository, T> implements BaseService<T> {

    @Autowired
    protected E reposity;


    @Override
    public T save(T entity) {
        return (T) reposity.save(entity);
    }

    @Override
    public void delete(Object key) {
        reposity.delete(key);
    }

    @Override
    public T findOne(int key) {
        return (T) reposity.findOne(key);
    }

    @Override
    public List<T> findAll() {
        return reposity.findAll();
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        reposity.deleteInBatch(entities);
    }

    @Override
    public List<T> findInBatch(Iterable<Integer> keys) {
        return reposity.findAll(keys);
    }

    @Override
    public List<T> saveInBatch(Iterable<Integer> keys) {
        return reposity.save(keys);
    }
}
