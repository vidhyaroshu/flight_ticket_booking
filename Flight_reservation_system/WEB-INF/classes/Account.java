package AirlineBooking;
import DbOperations.Utilities;
import Airline.*;

public class Account{
String bankName;
String branchName;
private long accountNumber;
String accountHolderName;
String cvv;
String expirydate;
int pin;
private double balance;
public Account(String bankName,String branchName,long accountNumber,String accountHolderName,String cvv,String expirydate,int pin,double balance) {
	this.bankName=bankName;
	this.branchName=branchName;
	this.accountNumber=accountNumber;
	this.accountHolderName=accountHolderName;
	this.cvv=cvv;
	this.balance=balance;
	this.expirydate=expirydate;
	this.pin=pin;
}
public Account(){

}
public long getAccountNumber() {
	return accountNumber;
}
public double getBalance() {
	return balance;
}
public void setBalance(double price) {
this.balance=price;
}
public boolean isValidCardDetails(long cardNumber,String name,String expiryDate,String cvv,int pin){
		Utilities db=new Utilities();
		boolean flag=false;
		try{
		boolean isValidCard=db.isCardexist(cardNumber);
		boolean isValidName=db.isValidholderName(name,cardNumber);
		boolean isValidDate=db.isValidCardExpiryDate(expiryDate,cardNumber);
		boolean isValidCvv=db.isValidcvv(cvv,cardNumber);
		boolean isValidPin=db.isvalidPin(pin,cardNumber);
		if(isValidCard&&isValidCvv&&isValidDate&&isValidName&&isValidPin){
			flag=true;
		}
	}
	catch(Exception e){
	e.printStackTrace();
	}
	return flag;
}
public Transaction getTransactionDetails(int flightId,Flightclass classtype,Quota quota,long cardNumber){
	Utilities db=new Utilities();
	Transaction transaction=null;
		try{
			Account account=db.getAccountDetails(cardNumber);
			  double price=db.getTotalPrice(flightId);
			  FlightRoutes flight=new FlightRoutes();
			double totalprice=flight.fareCalculation(classtype,quota,price);
			Payment paymentobj = new Payment(cardNumber, account.accountHolderName, account.expirydate, account.cvv, totalprice);
			 transaction = paymentobj.pay(account);
			}
	catch(Exception e){
	e.printStackTrace();
	}
	return transaction;
}
}
