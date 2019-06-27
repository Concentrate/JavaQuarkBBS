package common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liudeyu on 2019/6/27.
 */
@Entity
@Table(name = "quark_label")
public class Label implements Serializable {

    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String detail;

    @Column(name = "posts_count")
    private int postCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
