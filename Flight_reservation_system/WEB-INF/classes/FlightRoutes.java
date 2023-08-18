package Airline;	
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import DbOperations.Utilities;
import java.util.*;

public class FlightRoutes{
	public int flightId;
	public String flightRouteId;
	public  Airport departureAirport;
	public int departureTerminalNo;
	public Airport destinationAirport;
	public int destinationTerminalNo;
	public Time departureTime;
	public Time arrivalTime;
	public String duration;
	public double price;
	public String airporttype;
	public int ecoavailableseats;
	public int preavailableseats;
	public int busiavailableseats;
	public int firavailableseats;
	public String from;
	public String to;
	public  FlightRoutes(int flightId,String flightRouteId,Airport departureAirport,int departureTerminalNo,Airport destinationAirport,int destinationTerminalNo,Time departureTime,Time arrivalTime,String duration,double price,int ecoavailableseats,int preavailableseats,int busiavailableseats,int firavailableseats){
		 this.flightId=flightId;
		 this.flightRouteId=flightRouteId;
		 this.departureAirport=departureAirport;
		 this.departureTerminalNo=departureTerminalNo;
		 this.destinationAirport=destinationAirport;
		 this.destinationTerminalNo=destinationTerminalNo;
		 this.departureTime=departureTime;
		 this.arrivalTime=arrivalTime;
		 this.duration=duration;
		 this.price=price;
		 this.ecoavailableseats=ecoavailableseats;
		 this.preavailableseats=preavailableseats;
		 this.busiavailableseats=busiavailableseats;
		 this.firavailableseats=firavailableseats;
	 }
	public  FlightRoutes(int flightId,String flightRouteId,Airport departureAirport,int departureTerminalNo,Airport destinationAirport,int destinationTerminalNo,Time departureTime,Time arrivalTime,String duration,double price,String airporttype){
		 this.flightId=flightId;
		 this.flightRouteId=flightRouteId;
		 this.departureAirport=departureAirport;
		 this.departureTerminalNo=departureTerminalNo;
		 this.destinationAirport=destinationAirport;
		 this.destinationTerminalNo=destinationTerminalNo;
		 this.departureTime=departureTime;
		 this.arrivalTime=arrivalTime;
		 this.duration=duration;
		 this.price=price;
		 this.airporttype=airporttype;
	 }
	 public FlightRoutes(){

	 }
	 public FlightRoutes(String from,String to){
	 	this.from=from;
	 	this.to=to;
	 }
	 public String getAirportType(){
	 		 	Utilities db=new Utilities();
	 		 	String airporttype=null;
	 		 	try{
	 		 	boolean flag=db.isDomestic(from,to);
	 		 	if(flag==true){
	 		 		airporttype="DOMESTIC";
	 		 	}
	 		 	else{
	 		 		airporttype="INTERNATIONAL";
	 		 	}
	 		 }
	 		 catch(Exception e){
	 		 	e.printStackTrace();
	 		 }
	 		 return airporttype;
	 }
	 private boolean isTodaysDate(String date) {
	 	System.out.println(date+"given date");
		boolean flag = false;
		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date1 = dateObj.format(formatter);
		System.out.println(date1+"current date");
		if (date1.equals(date)) {
			System.out.println("equal");
			flag = true;
		}

		return flag;
	}
	public double fareCalculation(Flightclass classtype, Quota faretype, double price) {
		double totalprice = price;
		if (classtype == Flightclass.PREMIUM) {
			totalprice = (double) price + ((price * 10) / 100);
		} else if (classtype == Flightclass.BUSINESS) {
			totalprice = (double) price + ((price * 30) / 100);
		} else if (classtype == Flightclass.FIRSTCLASS) {
			totalprice = (double) price + ((price * 40) / 100);
		} else {
			totalprice = (double) price;
		}

		if (faretype == Quota.ARMEDFORCES) {
			totalprice = (double) totalprice - ((price * 5) / 100);
		} else if (faretype == Quota.STUDENT) {
			totalprice = (double) totalprice - ((price * 2) / 100);
		} else if (faretype == Quota.SENIORCITIZEN) {
			totalprice = (double) totalprice - ((price * 4) / 100);
		} else if (faretype == Quota.DOCTORS_NURSES) {
			totalprice = (double) totalprice - ((price * 3) / 100);
		} else {
			totalprice = (double) totalprice;
		}

		return totalprice;
	}
	 public ArrayList<FlightRoutes> getFlightRoutes(String from, String to, String date, String airporttype,String quota){
	 	Utilities db=new Utilities();
		ArrayList<FlightRoutes> flights=null;
		Airporttype airporttyp=Airporttype.valueOf(airporttype.toUpperCase());
		Quota quot=Quota.valueOf(quota.toUpperCase());
		try{
		 flights=db.getFlightList(from,to,date,airporttyp);
		 for(FlightRoutes flight:flights){
		 	double totalprice=fareCalculation(Flightclass.ECONOMY,quot, flight.price);
		 	flight.price=totalprice;
		 	boolean istodayavailable = isTodaysDate(date);
				if (istodayavailable) {
					System.out.println("todays date");
					Date currenttime = null;
					LocalDateTime myDateObj = LocalDateTime.now();
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

					String formattedDate = myDateObj.format(myFormatObj);
					try {
						currenttime = new SimpleDateFormat("dd/MM/yyyy").parse(formattedDate);
					} catch (Exception e) {
					}
					System.out.println(currenttime+"currenttime");
					System.out.println(formattedDate+"formattedDate");
					String departtime = flight.departureTime.toString();
					String[] split = formattedDate.split(":");
					int hours = Integer.valueOf(split[0]);
					int min = Integer.valueOf(split[1]);
					String[] split1 = departtime.split(":");
					int hours1 = Integer.valueOf(split1[0]);
					hours1 -= 2;
					int min1 = Integer.valueOf(split[1]);
					System.out.println(hours+"hours");
					System.out.println(hours1+"hours1");
					if (hours>hours1) {
						System.out.println("true");
						flights.remove(flight);
					}
				}
		 }
		}
		catch(Exception e){
		e.printStackTrace();
		}
		return flights;
	 }
}
