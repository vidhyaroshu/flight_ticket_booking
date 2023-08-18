package Userdetails;

import java.util.Date;

public class Passportdetails{
String passportno;
String passportIssuingCountry;
Date expiryDate;
public Passportdetails(String passportno,String passportIssuingCountry,Date expiryDate) {
	this.passportno=passportno;
	this.passportIssuingCountry=passportIssuingCountry;
	this.expiryDate=expiryDate;
}

}