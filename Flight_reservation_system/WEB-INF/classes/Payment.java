package AirlineBooking;

import java.time.LocalDate;
import java.util.Date;

public class Payment{
private long cardNumber;
String cardHolderName;
String expiryDate;
private String cvv;
double totalPrice;
static int count=1;
public Payment(long cardNumber,String cardHolderName,String expiryDate,String cvv,double totalPrice) {
	this.cardNumber=cardNumber;
	this.cardHolderName=cardHolderName;
	this.expiryDate=expiryDate;
	this.cvv=cvv;
	this.totalPrice=totalPrice;
}
public long getCardNumber() {
	return cardNumber;
}
public String getCvv() {
	return cvv;
}
public Transaction pay(Account account) {
	count++;
	Transaction transaction=null;
	if(totalPrice<=account.getBalance()) {
		transaction=new Transaction(cardNumber,"transacted",LocalDate.now(),java.time.LocalTime.now(),totalPrice,count);
		double balance=(double)(account.getBalance()-totalPrice);
		account.setBalance(balance);
		
	}
	return transaction;
}
}