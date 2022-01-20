package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "crypto_price")
public class CryptoPrice {
    private long rowNum;
    private String idx;
    private Timestamp date;
    private String cryptoIdx;
    private double price;
    private double priceMaximum;
    private double priceMinimum;

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
    @Column(name = "crypto_idx")
    public String getCryptoIdx() {
        return cryptoIdx;
    }

    public void setCryptoIdx(String cryptoIdx) {
        this.cryptoIdx = cryptoIdx;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "price_maximum")
    public double getPriceMaximum() {
        return priceMaximum;
    }

    public void setPriceMaximum(double priceMaximum) {
        this.priceMaximum = priceMaximum;
    }

    @Basic
    @Column(name = "price_minimum")
    public double getPriceMinimum() {
        return priceMinimum;
    }

    public void setPriceMinimum(double priceMinimum) {
        this.priceMinimum = priceMinimum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoPrice that = (CryptoPrice) o;
        return rowNum == that.rowNum &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.priceMaximum, priceMaximum) == 0 &&
                Double.compare(that.priceMinimum, priceMinimum) == 0 &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(date, that.date) &&
                Objects.equals(cryptoIdx, that.cryptoIdx);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, date, cryptoIdx, price, priceMaximum, priceMinimum);
    }
}
