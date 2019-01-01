package com.snail.model.m.entity;

import javax.persistence.*;

@Table(name = "M_ROLE_PERMISSION")
public class MRolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "r_id")
    private Long rId;
    @Column(name = "p_id")
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
