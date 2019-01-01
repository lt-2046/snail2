package com.snail.model.m.entity;

import javax.persistence.*;

@Table(name = "M_USER_ROLE")
public class MUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "u_id")
    private Long uId;
    @Column(name = "r_id")
    private Long rId;
    @Column(name = "start")
    private Long start;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "MUserRole{" +
                "id=" + id +
                ", uId=" + uId +
                ", rId=" + rId +
                ", start=" + start +
                '}';
    }
}
