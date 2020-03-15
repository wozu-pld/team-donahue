package com.donahue.cashtransaction;

public class Transaction {
	
	private double transTotal;
	private double moneyReceived;
	private int itemsSold;
	
	public Transaction() {
		transTotal=0;
	}
	
	public void itemTotalInventory(int itemInventory) {
		itemInventory = itemInventory - itemsSold;
	}
		
	public void AddItem(double itemTotalRetail) {
		transTotal = transTotal + itemTotalRetail;
	}
	
	public void AddTax(double transTotal) {
		transTotal = transTotal * 1.05;  //total plus sales tax
	}
	
	public double returnChange() {
		double changeReturned = moneyReceived - transTotal;
		return changeReturned;
	}
	
}