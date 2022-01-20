package com.jwyoon.www.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the exchange_history database table.
 * 
 */
@Entity
@Table(name="exchange_history")
public class ExchangeHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idx;

	private Timestamp date;

	@Column(name="exchange_idx")
	private String exchangeIdx;

	@Column(name="exchange_type")
	private String exchangeType;

	@Column(name="price_dollar")
	private double priceDollar;

	@Column(name="row_num")
	private Long rowNum;

	@Column(name="status")
	private Boolean status;

	@Column(name="amount")
	private double amount;
	
	@Column(name="user_idx")
	private String userIdx;

	@Column(name="user_wallet_idx")
	private String userWallet;

	

	public String getIdx() {
		return idx;
	}



	public void setIdx(String idx) {
		this.idx = idx;
	}



	public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}



	public String getExchangeIdx() {
		return exchangeIdx;
	}



	public void setExchangeIdx(String exchangeIdx) {
		this.exchangeIdx = exchangeIdx;
	}



	public String getExchangeType() {
		return exchangeType;
	}



	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}



	public double getPriceDollar() {
		return priceDollar;
	}



	public void setPriceDollar(double priceDollar) {
		this.priceDollar = priceDollar;
	}



	public Long getRowNum() {
		return rowNum;
	}



	public void setRowNum(Long rowNum) {
		this.rowNum = rowNum;
	}



	public Boolean getStatus() {
		return status;
	}



	public void setStatus(Boolean status) {
		this.status = status;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getUserIdx() {
		return userIdx;
	}



	public void setUserIdx(String userIdx) {
		this.userIdx = userIdx;
	}



	public String getUserWallet() {
		return userWallet;
	}



	public void setUserWallet(String userWallet) {
		this.userWallet = userWallet;
	}

}