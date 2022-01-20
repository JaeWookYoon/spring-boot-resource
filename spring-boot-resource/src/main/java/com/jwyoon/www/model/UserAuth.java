package com.jwyoon.www.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_auth")
public class UserAuth {
    private long rowNum;
    private String idx;
    private String id;
    private String auth;

    @Basic
    @Column(name = "row_num",insertable = false, updatable = false)
    public long getRowNum() {
        return rowNum;
    }

    public void setRowNum(long rowNum) {
        this.rowNum = rowNum;
    }

    @Id
    @Column(name = "idx")
    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "auth")
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return rowNum == userAuth.rowNum &&
                Objects.equals(idx, userAuth.idx) &&
                Objects.equals(id, userAuth.id) &&
                Objects.equals(auth, userAuth.auth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, id, auth);
    }
}
