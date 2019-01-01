package com.snail.web.m.vo;

public class RolePermissionVo {

    private Long id;
    private Long rId;
    private Long pId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "MRolePermission{" +
                "id=" + id +
                ", rId=" + rId +
                ", pId=" + pId +
                '}';
    }
}
