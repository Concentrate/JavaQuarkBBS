package common.baseservice;

import java.util.Iterator;
import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
public interface BaseService<T> {

    T save(T entity);

    void delete(Object key);

    T findOne(int key);

    List<T> findAll();


    void deleteInBatch(Iterable<T> entities);

    List<T> findInBatch(Iterable<Integer> keys);

    List<T> saveInBatch(Iterable<Integer> keys);


}
