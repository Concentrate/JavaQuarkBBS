package common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liudeyu on 2019/6/27.
 */
public class Role implements Serializable {

    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "description")
    private String des;

    @Column(name = "name",unique = true)
    private String name;

    @ManyToMany(mappedBy = "adminUsers")
    private Set<AdminUser> adminUsers=new HashSet<>();


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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
