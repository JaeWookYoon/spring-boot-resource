package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "exchange_list")
public class ExchangeList {
    private long rowNum;
    private String idx;
    private Timestamp date;
    private String userIdx;
    private double cryptoAmount;
    private double conclusionAmount;
    private double remainAmount;
    private String exchangeType;
    private double priceDollar;
    private boolean isConclusion;
    private boolean isCancel;
    private String userWalletIdx;
    private String walletIdx;

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
    @Column(name = "crypto_amount")
    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }

    @Basic
    @Column(name = "conclusion_amount")
    public double getConclusionAmount() {
        return conclusionAmount;
    }

    public void setConclusionAmount(double conclusionAmount) {
        this.conclusionAmount = conclusionAmount;
    }

    @Basic
    @Column(name = "remain_amount")
    public double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(double remainAmount) {
        this.remainAmount = remainAmount;
    }

    @Basic
    @Column(name = "exchange_type")
    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    @Basic
    @Column(name = "price_dollar")
    public double getPriceDollar() {
        return priceDollar;
    }

    public void setPriceDollar(double priceDollar) {
        this.priceDollar = priceDollar;
    }

    @Basic
    @Column(name = "is_conclusion")
    public boolean isConclusion() {
        return isConclusion;
    }

    public void setConclusion(boolean conclusion) {
        isConclusion = conclusion;
    }

    @Basic
    @Column(name = "is_cancel")
    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    @Basic
    @Column(name = "user_wallet_idx")
    public String getUserWalletIdx() {
        return userWalletIdx;
    }

    public void setUserWalletIdx(String userWalletIdx) {
        this.userWalletIdx = userWalletIdx;
    }

    @Basic
    @Column(name = "wallet_idx")
    public String getWalletIdx() {
        return walletIdx;
    }

    public void setWalletIdx(String walletIdx) {
        this.walletIdx = walletIdx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeList that = (ExchangeList) o;
        return rowNum == that.rowNum &&
                Double.compare(that.cryptoAmount, cryptoAmount) == 0 &&
                Double.compare(that.conclusionAmount, conclusionAmount) == 0 &&
                Double.compare(that.remainAmount, remainAmount) == 0 &&
                Double.compare(that.priceDollar, priceDollar) == 0 &&
                isConclusion == that.isConclusion &&
                isCancel == that.isCancel &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(date, that.date) &&
                Objects.equals(userIdx, that.userIdx) &&
                Objects.equals(exchangeType, that.exchangeType) &&
                Objects.equals(userWalletIdx, that.userWalletIdx) &&
                Objects.equals(walletIdx, that.walletIdx);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, date, userIdx, cryptoAmount, conclusionAmount, remainAmount, exchangeType, priceDollar, isConclusion, isCancel, userWalletIdx, walletIdx);
    }
}
