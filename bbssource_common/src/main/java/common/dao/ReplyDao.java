package common.dao;

import common.entity.Posts;
import common.entity.Reply;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
@CacheConfig(cacheNames = "reply")
public interface ReplyDao extends JpaRepository<Reply,Integer>,JpaSpecificationExecutor<Reply> {

    @Cacheable
    @Override
    List<Reply> findAll();


    @Cacheable
    Page<Reply> findByPostsOrderByInitTimeDesc(Posts posts, Pageable pageRequest);




}
