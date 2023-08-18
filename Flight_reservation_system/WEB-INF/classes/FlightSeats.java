package Airline;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import DbOperations.Utilities;

public class FlightSeats{
boolean[][] seatno;
 boolean[] ecoSeatno;
 boolean[] preSeatno;
 boolean[] busiSeatno;
 boolean[] firSeatno;
 String date;
 int flight_id;
public Flightclass classtype;
public Quota faretype;
public static int seatsperclass=45;
public HashMap<String,HashMap<String,Integer>>classquotaseats;
public HashMap<String,Integer>classseats;
public HashMap <String,Integer>quotaseats;
 static int quotacount=5;
String[] bookedSeats;
 File filename;
 String readfilename;
public static int row=seatsperclass/5;
String[][] seatView=new String[row][5];
public FlightSeats(){

}
public FlightSeats(Flightclass classtype,Quota faretype,String date,int flight_id) {	
	this.classtype=classtype;
	this.faretype=faretype;
	this.date=date;
	this.flight_id=flight_id;
	ecoSeatno=new boolean[seatsperclass];
	 preSeatno=new boolean[seatsperclass];
	 busiSeatno=new boolean[seatsperclass];
	 firSeatno=new boolean[seatsperclass];
	 for(int i=0;i<seatsperclass;i++) {
		 ecoSeatno[i]=true;
		 preSeatno[i]=true;
		 busiSeatno[i]=true;
		 firSeatno[i]=true;

	 }
//	 quotaseatcount();
	 classseatcount();
	 updateSeats();
}
	private void classseatcount() {
		classseats=new HashMap<String,Integer>();
		classseats.put("BUSINESS", seatsperclass);
		classseats.put("ECONOMY", seatsperclass);
		classseats.put("PREMIUM", seatsperclass);
		classseats.put("FIRSTCLASS", seatsperclass);

	}
	private void updateSeats() {
		Utilities db=new Utilities();
	try{
		classseats=db. getClassSeats( date, flight_id, seatsperclass);
		classquotaseats=db. getClassQuotaSeats( date, flight_id, seatsperclass);
		ecoSeatno=db. getEconomicSeats( date, flight_id, ecoSeatno);
		preSeatno=db. getPremiumSeats( date, flight_id, preSeatno);
		busiSeatno=db. getBusinessSeats( date, flight_id, busiSeatno);
		firSeatno=db. getFirstclassSeats( date, flight_id, firSeatno);
	}
	catch(Exception e){
	}
			
	}
	public FlightSeats getFlightSeats(int flightId, String sdate,
			Flightclass classtype, Quota faretype) throws IOException {
		FlightSeats currentSeatobj = null;
		currentSeatobj = new FlightSeats(classtype, faretype, sdate, flightId);
		return currentSeatobj;
	}
			public void canceltickets(String[] canceltickets) {
//				System.out.println("dfghjkl");
//				readseats();
				for(int j=0;j<canceltickets.length;j++) {
					String str1=canceltickets[j];
					String str=str1.substring(1, str1.length());
					int seat=Integer.parseInt(str);
//					System.out.println(classtype);

					if(classtype==Flightclass.ECONOMY) {
//						System.out.println("true");
						 ecoSeatno[seat-1]=true;
						}
						else if(classtype==Flightclass.PREMIUM) {
							 preSeatno[seat-1]=true;
							 }
						else if(classtype==Flightclass.BUSINESS) {
							 busiSeatno[seat-1]=true;
						}
						else if(classtype==Flightclass.FIRSTCLASS) {
							 firSeatno[seat-1]=true;
						}
					 int classseatscount=classseats.get(classtype.toString())+1;
					 classseats.replace(classtype.toString(),classseatscount);
					 int quotaseatcount=quotaseats.get(faretype.toString())+1;
					 quotaseats.replace(faretype.toString(), quotaseatcount);
					 classquotaseats.replace(classtype.toString(), quotaseats);
					 if(faretype!=Quota.REGULAR) {
						 quotaseatcount=quotaseats.get(Quota.REGULAR.toString())+1;
						 quotaseats.replace(Quota.REGULAR.toString(), quotaseatcount);
						 classquotaseats.replace(classtype.toString(), quotaseats);

					 }
					
				}
//				 seats();
				
			}
			public void canceltickets(String cancelseat) {
					String str1=cancelseat;
					String str=str1.substring(1, str1.length());
					int seat=Integer.parseInt(str);
					if(classtype==Flightclass.ECONOMY) {
						 ecoSeatno[seat-1]=true;
						}
					
						else if(classtype==Flightclass.PREMIUM) {
							 preSeatno[seat-1]=true;
							 }
						else if(classtype==Flightclass.BUSINESS) {
							 busiSeatno[seat-1]=true;
						}
						else if(classtype==Flightclass.FIRSTCLASS) {
							 firSeatno[seat-1]=true;
						}
					 int classseatscount=classseats.get(classtype.toString())+1;
					 classseats.replace(classtype.toString(),classseatscount);
					 int quotaseatcount=quotaseats.get(faretype.toString())+1;
					 quotaseats.replace(faretype.toString(), quotaseatcount);
					 classquotaseats.replace(classtype.toString(), quotaseats);
					 if(faretype!=Quota.REGULAR) {
						 quotaseatcount=quotaseats.get(Quota.REGULAR.toString())+1;
						 quotaseats.replace(Quota.REGULAR.toString(), quotaseatcount);
						 classquotaseats.replace(classtype.toString(), quotaseats);

					 }
//					 seats();

			}
			public int seatscount() {
				int count=0;
				count=classseats.get(classtype.toString());
				return count;
			}
			public String[][] viewSeats(){
				if(classtype==Flightclass.ECONOMY) {
					byte k=1;
					for (int i=0; i<row; i++)
	                {
	                    for (int j=0; j<5; j++)
	                    {
	                    	if(ecoSeatno[k-1]==false) {
	                    		seatView[i][j]="E"+k+"B";
	                    	}
	                    	else {
	                        seatView[i][j]="E"+k;
	                    	}
	                    	k++;
	                    }
	                }
				}
				if(classtype==Flightclass.PREMIUM) {
					byte k=1;
					for (int i=0; i<row; i++)
	                {
	                    for (int j=0; j<5; j++)
	                    {
	                    	if(preSeatno[k-1]==false) {
	                    		seatView[i][j]="P"+k+"B";
	                    	}
	                    	else {
	                        seatView[i][j]="P"+k;
	                    	}
	                    	k++;
	                    }
	                }
				}
				if(classtype==Flightclass.BUSINESS) {
					byte k=1;
					for (int i=0; i<row; i++)
	                {
	                    for (int j=0; j<5; j++)
	                    {
	                    	if(busiSeatno[k-1]==false) {
	                    		seatView[i][j]="B"+k+"B";
	                    	}
	                    	else {
	                        seatView[i][j]="B"+k;
	                    	}
	                    	k++;
	                    }
	                }
				}
				if(classtype==Flightclass.FIRSTCLASS) {
					byte k=1;
					for (int i=0; i<row; i++)
	                {
	                    for (int j=0; j<5; j++)
	                    {
	                    	if(firSeatno[k-1]==false) {
	                    		seatView[i][j]="F"+k+"B";
	                    	}
	                    	else {
	                        seatView[i][j]="F"+k;
	                    	}
	                    	k++;
	                    }
	                }
				}
				return seatView;
			}		
	}