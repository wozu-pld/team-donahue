package model;

public class Price {

 private double amount;
 private String currency;// JPY, USD

 public double getAmount() {
  return amount;
 }
 public void setAmount(double amount) {
  this.amount = amount;
 }
 public String getCurrency() {
  return currency;
 }
 public void setCurrency(String currency) {
  this.currency = currency;
 }

}