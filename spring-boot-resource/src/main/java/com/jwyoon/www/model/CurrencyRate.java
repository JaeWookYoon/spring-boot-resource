package com.jwyoon.www.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {
    private long rowNum;
    private String idx;
    private Timestamp date;
    private double currencyPrice;

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
    @Column(name = "currency_price")
    public double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRate that = (CurrencyRate) o;
        return rowNum == that.rowNum &&
                Double.compare(that.currencyPrice, currencyPrice) == 0 &&
                Objects.equals(idx, that.idx) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rowNum, idx, date, currencyPrice);
    }
}
