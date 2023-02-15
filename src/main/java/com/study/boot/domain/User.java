package com.study.boot.domain;


import com.baomidou.mybatisplus.annotation.TableId;

public class User {

    @TableId("id")
    private Integer id;
    private String name;
    private String pwd;
    private String rid;


    public User() {
    }
    public User(Integer id, String name, String pwd, String rid) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
