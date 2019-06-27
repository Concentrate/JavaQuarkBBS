package common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liudeyu on 2019/6/27.
 */
@Entity
@Table(name = "quark_user")
public class User  implements Serializable{

    @GeneratedValue
    @Column(name = "id")
    private int id;


    @Column(name = "icon")
    private String icon;

    @Column(name = "enable")
    private int enable;
    @Column(name = "init_time")
    private Timestamp initTime;


    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "sex")
    private int sex;

    @Column(name = "signature")
    private String signature;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Timestamp getInitTime() {
        return initTime;
    }

    public void setInitTime(Timestamp initTime) {
        this.initTime = initTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
