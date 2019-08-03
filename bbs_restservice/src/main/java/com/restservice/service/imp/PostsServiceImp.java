package com.restservice.service.imp;


import com.alibaba.druid.sql.ast.SQLOrderingSpecification;
import com.github.wenhao.jpa.Specifications;
import com.restservice.service.PostService;
import com.restservice.service.util.ServiceConstants;
import common.dao.LabelDao;
import common.dao.PostDao;
import common.dao.UserDao;
import common.dto.PageResult;
import common.entity.Label;
import common.entity.Posts;
import common.entity.User;
import common.exceptions.ApiException;
import common.utils.Constants;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.parser.OrderBySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class PostsServiceImp extends BaseIntegerKeyServiceImp<PostDao, Posts> implements PostService {
    @Autowired
    UserDao userDao;
    @Autowired
    LabelDao labelDao;

    @Override
    @Transactional
    public void savePosts(Posts posts, User user, int labelId) {
        repo.save(posts);
        Label label = labelDao.getOne(labelId);
        if (label != null) {
            label.setPostsCount(label.getPostsCount() + 1);
            labelDao.save(label);
        }
    }

    @Override
    public Page<Posts> getPostsByPage(String type, String search, int pageNo, int length) {
        basePageDectect(pageNo, length);
        return repo.findAll(new Specification<Posts>() {
            @Override
            public Predicate toPredicate(Root<Posts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if (!StringUtil.isNullOrEmpty(type)) {
                    Path<Boolean> isGood = root.get("good");
                    Path<Boolean> isTop = root.get("top");
                    switch (type) {
                        case ServiceConstants.GOOD_TYPE:
                            criteriaBuilder.equal(isGood, true);
                            break;
                        case ServiceConstants.TOP_TYPE:
                            criteriaBuilder.equal(isTop, true);
                            break;
                    }
                }
                if (!StringUtil.isNullOrEmpty(search)) {
                    predicate = criteriaBuilder.like(root.get("title"), "%" + search + "%");
                }
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("initTime")));
                return predicate;
            }
        }, new PageRequest(pageNo - 1, length));
    }

    private void basePageDectect(int pageNo, int length) {
        if (pageNo <= 0 || length <= 0) {
            throw new ApiException(Constants.ERROR_REQUEST_PARA_REASON);
        }
    }

    @Override
    public List<Posts> getPostsByUser(User user) {
        Specification<Posts> userPosts = Specifications.<Posts>and()
                .eq("user", user)
                .build();
        return repo.findAll(userPosts, new Sort(Sort.Direction.DESC, "initTime"));
    }

    @Override
    public Page<Posts> getPostsByLabel(Label label, int pageNo, int length) {
        basePageDectect(pageNo, length);
        return repo.findByLabel(label, new PageRequest(pageNo - 1, length));
    }

    @Override
    public List<Posts> getPostByUserLimitNum(User user, int limitCount) {
        Pageable pageable = new PageRequest(1, 10);
        Specification<Posts> userPosts = Specifications.<Posts>and()
                .eq("user", user)
                .build();
        Page<Posts> result = repo.findAll(userPosts, pageable);
        return result.getContent();

    }
}
