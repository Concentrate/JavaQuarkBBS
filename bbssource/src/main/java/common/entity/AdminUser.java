package common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liudeyu on 2019/6/27.
 */
@Entity
@Table(name = "quark_adminuser")
public class AdminUser implements Serializable {
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "enable")
    private int enable;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "username")
    private String userName;

    @JsonIgnore
    @JoinTable(name = "quark_adminuser_role", joinColumns = {@JoinColumn
            (name = "adminuser_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    })
    @ManyToOne(cascade = CascadeType.ALL)
    private Set<AdminUser> adminUsers = new HashSet<>();


    public Set<AdminUser> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(Set<AdminUser> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
