package Airline;
import java.util.*;

public class Airport{
	 
	 public String airportId;
	public  String city;
	 public String country;
	public Airporttype airporttype;
	 final byte[] totalTerminals=new byte[3];
	 public Airport(String airportId,String city,String country,Airporttype airporttype) {
		this.airportId= airportId;
		this.city =city;
		 this.country=country;
		 this.airporttype=airporttype;
	 }
}