package common.dao;

import common.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by liudeyu on 2019/6/30.
 */
@Repository
public interface LabelDao extends JpaRepository<Label,Integer>,JpaSpecificationExecutor{

}
