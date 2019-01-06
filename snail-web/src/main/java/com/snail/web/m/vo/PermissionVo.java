package com.snail.web.m.vo;

import java.util.List;

public class PermissionVo {


    private Long id;
    private String permissionName;
    private String url;
    private Long start;
    private String mark;
    private String version;
    private PermissionVo parentPermission;
    private List<PermissionVo> childPermissionList;

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

    public PermissionVo getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(PermissionVo parentPermission) {
        this.parentPermission = parentPermission;
    }

    public List<PermissionVo> getChildPermissionList() {
        return childPermissionList;
    }

    public void setChildPermissionList(List<PermissionVo> childPermissionList) {
        this.childPermissionList = childPermissionList;
    }

}
