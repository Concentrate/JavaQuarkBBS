package common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
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


    @Column(name = "fromuser_id")
    @OneToOne
    private int fromUserId;

    @Column(name = "posts_id")
    @OneToOne
    private int postsId;

    @Column(name = "touser_id")
    @OneToOne
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
