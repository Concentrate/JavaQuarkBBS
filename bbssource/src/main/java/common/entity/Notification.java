package common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liudeyu on 2019/6/27.
 */
public class Notification implements Serializable {

    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "is_read")
    private boolean isRead;


    @JoinColumn(name = "fromuser_id")
    @ManyToOne
    private int fromUserId;

    @JoinColumn(name = "posts_id")
    @ManyToOne
    private int postsId;

    @JoinColumn(name = "touser_id")
    @ManyToOne
    private int toUserId;

    @Column(name = "init_time")
    private Timestamp initTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public Timestamp getInitTime() {
        return initTime;
    }

    public void setInitTime(Timestamp initTime) {
        this.initTime = initTime;
    }
}
