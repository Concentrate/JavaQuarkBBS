package com.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by liudeyu on 2019/7/1.
 */
@Service
public class RedisService<T> {

    @Autowired
    private RedisTemplate<String, T> template;


    /**
     * time is hour
     */
    public void putDataInMap(String key, T value, int time) {
        ValueOperations<String, T> valueOperations = template.opsForValue();
        valueOperations.set(key, value, time, TimeUnit.HOURS);
    }

    public T getDataFromMap(String key) {
        ValueOperations<String, T> valueOperations = template.opsForValue();
        return valueOperations.get(key);
    }


    public T getDataAndUpdateTime(String key, int time) {
        ValueOperations<String, T> valueOperations = template.opsForValue();
        if (valueOperations.get(key) == null) {
            return valueOperations.get(key);
        }
        T data = valueOperations.get(key);
        valueOperations.set(key, data, time);
        return data;
    }

    public void deleteFromMap(String key) {
        ValueOperations<String, T> valueOperations = template.opsForValue();
        template.delete(key);
    }

    public void putDataInSet(String key, T t) {
        SetOperations<String, T> setOp = template.opsForSet();
        setOp.add(key, t);
    }

    public void removeDataFromSet(String key, T t) {
        SetOperations<String, T> setOp = template.opsForSet();
        setOp.remove(key, t);
    }

    public boolean isDataInSet(String key, T t) {
        SetOperations<String, T> setOp = template.opsForSet();
        return setOp.isMember(key, t);
    }

    public void putSetIfNotExists(String key,T t){
        if(!isDataInSet(key,t)) {
            putDataInSet(key,t);
        }
    }

}
