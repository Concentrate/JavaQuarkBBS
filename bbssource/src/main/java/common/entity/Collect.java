package common.entity;

import javafx.geometry.Pos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liudeyu on 2019/6/27.
 */
public class Collect implements Serializable{

    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "init_time")
    private Timestamp initTime;

    @ManyToOne
    @Column(name = "posts_id")
    private Post post;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getInitTime() {
        return initTime;
    }

    public void setInitTime(Timestamp initTime) {
        this.initTime = initTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
