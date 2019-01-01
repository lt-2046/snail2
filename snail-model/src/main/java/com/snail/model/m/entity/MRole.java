package com.snail.model.m.entity;

import javax.persistence.*;

@Table(name = "M_ROLE")
public class MRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "start")
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
        return "MRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", start=" + start +
                '}';
    }
}
