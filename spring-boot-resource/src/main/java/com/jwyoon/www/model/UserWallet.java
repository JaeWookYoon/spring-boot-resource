package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_wallet")
public class UserWallet {
    private long rowNum;
    private String idx;
    private Timestamp date;
    private String userIdx;
    private String walletIdx;
    private String walletAddress;
    private double amount;
    private Double averagePriceDollar;
    private String tokenInfo;
    private String tokenPath;

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
    @Column(name = "date", insertable = false, updatable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
    @Column(name = "wallet_idx")
    public String getWalletIdx() {
        return walletIdx;
    }

    public void setWalletIdx(String walletIdx) {
        this.walletIdx = walletIdx;
    }

    @Basic
    @Column(name = "wallet_address")
    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "average_price_dollar")
    public Double getAveragePriceDollar() {
        return averagePriceDollar;
    }

    public void setAveragePriceDollar(Double averagePriceDollar) {
        this.averagePriceDollar = averagePriceDollar;
    }

    @Basic
    @Column(name = "token_info")
    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @Basic
    @Column(name = "token_path")
    public String getTokenPath() {
        return tokenPath;
    }

    public void setTokenPath(String tokenPath) {
        this.tokenPath = tokenPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWallet that = (UserWallet) o;
        return rowNum == that.rowNum &&
                Double.compare(that.amount, amount) == 0 &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(date, that.date) &&
                Objects.equals(userIdx, that.userIdx) &&
                Objects.equals(walletIdx, that.walletIdx) &&
                Objects.equals(walletAddress, that.walletAddress) &&
                Objects.equals(averagePriceDollar, that.averagePriceDollar) &&
                Objects.equals(tokenInfo, that.tokenInfo) &&
                Objects.equals(tokenPath, that.tokenPath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, date, userIdx, walletIdx, walletAddress, amount, averagePriceDollar, tokenInfo, tokenPath);
    }
}
