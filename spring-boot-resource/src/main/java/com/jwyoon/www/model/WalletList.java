package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "wallet_list")
public class WalletList {
    private long rowNum;
    private String idx;
    private Timestamp date;
    private String cryptoIdx;
    private String name;
    private String description;

    @Basic
    @Column(name = "row_num",insertable = false, updatable = false, nullable = false)
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
    @Column(name = "date", insertable = false, updatable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "crypto_idx")
    public String getCryptoIdx() {
        return cryptoIdx;
    }

    public void setCryptoIdx(String cryptoIdx) {
        this.cryptoIdx = cryptoIdx;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletList that = (WalletList) o;
        return rowNum == that.rowNum &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(date, that.date) &&
                Objects.equals(cryptoIdx, that.cryptoIdx) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, date, cryptoIdx, name, description);
    }
}
