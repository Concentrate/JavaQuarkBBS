package common.dao;

import common.entity.Reply;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
public interface ReplyDao extends JpaRepository<Reply,Integer>,JpaSpecificationExecutor<Reply> {

    @Cacheable
    @Override
    List<Reply> findAll();
}
