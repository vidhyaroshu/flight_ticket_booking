package AirlineBooking;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.UUID;
import Airline.*;
import Userdetails.*;
import DbOperations.Utilities;

public class Ticket{
byte noOfTravellers;
public int flightId;
public Airport from;
public Airport to;
public String departuredate;
public Date bookeddate;
public String pnr;
//static long pnr=875325799764325l;
public double cost;
public FlightRoutes flight;
public Flightclass classtype;
public Quota faretype;
public HashMap<Integer,Passenger> passengers;
public User bookedBy;
private long transactionId;
public String status;
public String[] bookedseats;
public Ticket(User bookedBy,FlightRoutes flight,int flightId,byte noOfTravellers,Airport from,Airport to,String departuredate,double cost,Flightclass classtype,Quota faretype,HashMap<Integer,Passenger> passengers,long transactionId,String[] bookedseats,String pnr,String status) {
	this.noOfTravellers=noOfTravellers;
	this.flight=flight;
	this.to=to;
	this.from=from;
	this.bookedseats=bookedseats;
	this.classtype=classtype;
	this.faretype=faretype;
	this.passengers=passengers;
	this.cost=cost;
	this.bookedBy=bookedBy;
	this.transactionId=transactionId;
	this.pnr=pnr;
	LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date1 = dateObj.format(formatter);
    try {
   bookeddate=new SimpleDateFormat("dd/MM/yyyy").parse(date1);
    }
    catch(Exception e) {
    }
//	this.bookeddate=LocalDate.now();
	this.departuredate=departuredate;
	this.flightId=flightId;
	this.status=status;
//	pnr++;

}
public long getTransactionId(){
	return transactionId;
} 
public Ticket(){}
public Ticket getMyTicket(HashMap<String,Object> sessionmap){
	Ticket currentpnr=null;
	String pnr = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
		Utilities db=new Utilities();
		String mailId=(String)sessionmap.get("userId");
		int flightId=(int)sessionmap.get("flightId");
		String sDate=(String)sessionmap.get("depDate");
		byte travellersCount=(byte)((int)sessionmap.get("adults")+(int)sessionmap.get("children")+(int)sessionmap.get("infants"));
		Flightclass classtype=Flightclass.valueOf((String)sessionmap.get("classtype"));
		Quota faretype=Quota.valueOf((String)sessionmap.get("quota"));
		HashMap<Integer,Passenger> passengerlist=(HashMap<Integer,Passenger>)sessionmap.get("passengerlist");
		Long transactionId=(Long)sessionmap.get("transactionId");
		String[] bookedSeats=(String[])sessionmap.get("bookedseat");
	try{
		User currentUser=db.getUser(mailId);
		FlightRoutes currentRoute=db.getRoute(flightId,sDate);
		double price=db.getTotalPrice(flightId);
		double totalprice=currentRoute.fareCalculation(classtype,faretype,price);
		totalprice = (double) (totalprice) * travellersCount;
		currentpnr = new Ticket(currentUser, currentRoute, currentRoute.flightId,
												travellersCount, currentRoute.departureAirport,
												currentRoute.destinationAirport, sDate, totalprice, classtype, faretype,
												passengerlist,transactionId, bookedSeats, pnr,
												"Booked");
										currentpnr.passengers=passengerlist;
										db.addTicket(currentUser,currentpnr,currentRoute);
	}
	catch(Exception e){
	}
	return currentpnr;	
}
public LinkedHashMap<String,Ticket> getMyBookings(String mailId){
			Utilities db=new Utilities();
			LinkedHashMap<String,Ticket> tickets=null;
			try{	

		User currentUser=db.getUser(mailId);
		tickets=db.getmybookings(currentUser);
		}
	catch(Exception e){
	}
	return tickets;

}
}
