package com.snail.model.m.entity;

import javax.persistence.*;

@Table(name = "M_PERMISSION")
public class MPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "permission_name")
    private String permissionName;
    @Column(name = "url")
    private String url;
    @Column(name = "permission_num")
    private Long permissionNum;
    @Column(name = "start")
    private Long start;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPermissionNum() {
        return permissionNum;
    }

    public void setPermissionNum(Long permissionNum) {
        this.permissionNum = permissionNum;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "MPermission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", permissionNum=" + permissionNum +
                ", start=" + start +
                '}';
    }
}
