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
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "start")
    private Long start;
    @Column(name = "mark")
    private String mark;
    @Column(name = "version")
    private String version;

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

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MPermission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", start=" + start +
                ", mark='" + mark + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
