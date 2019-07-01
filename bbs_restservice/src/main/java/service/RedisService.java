package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    public void putDataInCache(String key, T value, int time) {
        ValueOperations<String, T> valueOperations = template.opsForValue();
        valueOperations.set(key, value, time, TimeUnit.HOURS);
    }

    public T getDataFromCache(String key) {
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

}
