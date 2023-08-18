package AirlineBooking;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Transaction{
private static long transactionId=76479654358964l;
private long cardNumber;
String status;
LocalDate date;
LocalTime time;
double amount;
public Transaction(long cardNumber,String status,LocalDate date,LocalTime time,double amount,int count) {
	this.cardNumber=cardNumber;
	this.cardNumber=cardNumber;
	this.status=status;
	this.date=date;
	this.time=time;
	this.amount=amount;
	transactionId=(long)transactionId+count;
}
public long getTransactionId() {
	return transactionId;
}
public long getCardNumber() {
	return cardNumber;
}
}