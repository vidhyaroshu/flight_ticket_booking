package Userdetails;
import java.util.UUID;
import DbOperations.Utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;



public class Passenger{
public String name;
public String gender;
public Passportdetails passport;
public String age;
public int passengerId;
public String status;
public String airporttype;
public Passenger(String name,String gender,String age,int passengerId,String status){
	this.name=name;
	this.gender=gender;
	this.age=age;
	this.airporttype="DOMESTIC";
	this.status=status;
	this.passengerId=passengerId;
}
public Passenger(String name,String gender,Passportdetails passport,String age,int passengerId,String status){
	this.name=name;
	this.gender=gender;
	this.passport=passport;
	this.age=age;
	this.airporttype="INTERNATIONAL";

	this.status=status;
	this.passengerId=passengerId;
}
public Passenger(){
}
public Passenger addPassengerDetails(String name, String gender, String age_category,String mailId){
	Utilities db=new Utilities();
	int passId=0;
	try{
	 passId=db.addPassenger(name,gender,age_category,mailId);
	}
	catch(Exception e){
	}

	Passenger currentPassenger=new Passenger(name,gender,age_category,passId,"BOOKED");
	return currentPassenger;
}
public Passenger addPassengerDetails(String name, String gender, String age_category,String mailId,String passportno,String country,String date){
	Utilities db=new Utilities();
	int passId=0;
	try{
	 passId=db.addPassenger(name,gender,age_category,mailId);
	}
	catch(Exception e){
	}
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date dateObject=null;

        try {
             dateObject = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
	
	Passenger currentPassenger=new Passenger(name,gender,new Passportdetails(passportno, country, dateObject),age_category,passId,"BOOKED");
	return currentPassenger;
}
public boolean isValidPassportno(String passportno) {
		String regex = "^[A-PR-WYa-pr-wy][1-9]\\d\\s?\\d{4}[1-9]$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(passportno);
		return match.matches();
	}

	public boolean isValidcountry(String passportissuingcountry) {
		String regex = "^[A-za-z]{3,}$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(passportissuingcountry);
		return match.matches();
	}



}