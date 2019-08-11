package common.dao;

import common.entity.Label;
import common.entity.Posts;
import common.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
@CacheConfig(cacheNames = "posts")
public interface PostDao extends JpaRepository<Posts, Integer>, JpaSpecificationExecutor<Posts> {
    @Override
    @Cacheable
    List<Posts> findAll();

    Page<Posts> findByUser(User user, Pageable page);

    Page<Posts> findByLabel(Label label, Pageable pageable);

    @Query(value = "select * from quark_posts p where DATE_SUB(CURDATE(), INTERVAL :recent HOUR) <=DATE(p.init_time) ORDER by p.reply_count desc limit :num"
            , nativeQuery = true)
    List<Posts> getNewLimitPosts(@Param("recent") int recent, @Param("num") int num);


    @Query(value = "select * from quark_posts p where p.user_id=:uid and date_sub(curdate(), interval :recent HOUR) <= DATE(p.init_time) ORDER by p.reply_count desc limit :num",
            nativeQuery = true)
    List<Posts> getUserNewPostsByHourAndLimit(@Param("uid") int uid, @Param("recent") int recent, @Param("num") int num);

}
