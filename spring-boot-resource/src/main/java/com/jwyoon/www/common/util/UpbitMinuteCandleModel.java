package com.jwyoon.www.common.util;

public class UpbitMinuteCandleModel {
	
	private String market;
	private double openingPrice;
	private double highPrice;
	private double tradePrice;
	private long timestamp;
	private double candleAccTradePrice;
	private double candleAccTradeVolume;
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public double getOpeningPrice() {
		return openingPrice;
	}
	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	public double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getCandleAccTradePrice() {
		return candleAccTradePrice;
	}
	public void setCandleAccTradePrice(double candleAccTradePrice) {
		this.candleAccTradePrice = candleAccTradePrice;
	}
	public double getCandleAccTradeVolume() {
		return candleAccTradeVolume;
	}
	public void setCandleAccTradeVolume(double candleAccTradeVolume) {
		this.candleAccTradeVolume = candleAccTradeVolume;
	}
	@Override
	public String toString() {
		return "UpbitMinuteCandleModel [market=" + market + ", openingPrice=" + openingPrice + ", highPrice="
				+ highPrice + ", tradePrice=" + tradePrice + ", timestamp=" + timestamp + ", candleAccTradePrice="
				+ candleAccTradePrice + ", candleAccTradeVolume=" + candleAccTradeVolume + "]";
	}
	
	
	
	
	
	

}
