package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "crypto_list")
public class CryptoList {
    private long rowNum;
    private String idx;
    private Timestamp date;
    private String code;
    private String name;

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
    @Column(name="date",insertable=false,updatable=false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoList that = (CryptoList) o;
        return rowNum == that.rowNum &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(date, that.date) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, date, code, name);
    }
}
