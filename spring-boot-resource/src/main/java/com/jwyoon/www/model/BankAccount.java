package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    private long rowNum;
    private String idx;
    private String userIdx;
    private String account;
    private String bankName;
    private String bankCode;
    private Timestamp regDate;
    private Float asset;

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
    @Column(name = "user_idx")
    public String getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(String userIdx) {
        this.userIdx = userIdx;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    @Column(name = "bank_code")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Basic
    @Column(name = "reg_date")
    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    @Basic
    @Column(name = "asset")
    public Float getAsset() {
        return asset;
    }

    public void setAsset(Float asset) {
        this.asset = asset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return rowNum == that.rowNum &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(userIdx, that.userIdx) &&
                Objects.equals(account, that.account) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(bankCode, that.bankCode) &&
                Objects.equals(regDate, that.regDate) &&
                Objects.equals(asset, that.asset);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, userIdx, account, bankName, bankCode, regDate, asset);
    }
}
