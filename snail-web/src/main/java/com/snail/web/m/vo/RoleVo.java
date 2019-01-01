package com.snail.web.m.vo;

/**
 * Created by liutao on 2018/12/29.
 */
public class RoleVo {

    private Long id;

    private String roleName;

    private Long start;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", start=" + start +
                '}';
    }
}