package common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liudeyu on 2019/6/27.
 */
@Entity
@Table(name = "quark_posts")
public class Post implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name =
            "content")
    private String content;

    @Column(name = "good")
    private boolean isGood;

    @Column(name = "init_time")
    private Timestamp initTime;

    @Column(name = "label_id")
    @ManyToOne
    private Label label;

    @Column(name = "reply_count")
    private int replyCount;

    @Column(name = "title")
    private String title;

    @Column(name = "top")
    private boolean isTop;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public Timestamp getInitTime() {
        return initTime;
    }

    public void setInitTime(Timestamp initTime) {
        this.initTime = initTime;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
