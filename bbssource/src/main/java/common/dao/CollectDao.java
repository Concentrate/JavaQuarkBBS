package common.dao;

import common.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liudeyu on 2019/6/30.
 */
public interface CollectDao extends JpaRepository<Collect,Integer> {
}
