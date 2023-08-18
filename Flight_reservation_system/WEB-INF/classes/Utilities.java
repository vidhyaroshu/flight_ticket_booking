package DbOperations;
import Userdetails.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.*;
import Airline.*;
import AirlineBooking.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities{

	public User getUser(String mailid, String password) throws SQLException {
		User currentUser=null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		try {
			String query = "select * from passengers left join users on users.passenger_id=passengers.id left join gender on passengers.gender=gender.gender_id where mail_id=? and password=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, mailid);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next()) {
				currentUser = new User(rs.getString("name"), rs.getString("mail_id"), rs.getString("password"),
						rs.getString("phone_number"), rs.getString("gender"),rs.getInt("id"));
			}
			rs.close();
		} catch (SQLException e) {
		e.printStackTrace();
			throw new SQLException();
		}
		return currentUser;
	}
	public User getUser(String mailid) throws SQLException {
		User currentUser=null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		try {
			String query = "select * from passengers left join users on users.passenger_id=passengers.id left join gender on passengers.gender=gender.gender_id where mail_id=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, mailid);
			rs = statement.executeQuery();
			if (rs.next()) {
				currentUser = new User(rs.getString("name"), rs.getString("mail_id"), rs.getString("password"),
						rs.getString("phone_number"), rs.getString("gender"),rs.getInt("id"));
			}
			rs.close();
		} catch (SQLException e) {
		e.printStackTrace();
			throw new SQLException();
		}
		return currentUser;
	}
	public String getUserName(String mailid) throws SQLException {
		String name=null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		try {
			String query = "select name from passengers where mail_id=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, mailid);
			rs = statement.executeQuery();
			if (rs.next()) {
				name=rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
		e.printStackTrace();
			throw new SQLException();
		}
		return name;
	}
	public User addUser(String name, String mailId, String phoneNumber, String gender, String password) throws SQLException {
		User currentUser=null;
		if (gender.equalsIgnoreCase("f")) {
			gender = "female";
		} else if (gender.equalsIgnoreCase("m")) {
			gender = "male";
		}
		User newUser = null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		PreparedStatement statement;
		ResultSet rs = null;
		boolean userexist=false;
		try {
			String query = "select id from passengers where mail_id=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, mailId);
			rs = statement.executeQuery();
			if (rs.next()) {
				userexist = true;
			}
			rs.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		if (!userexist) {
			int pass_id = 0;
			try {
				int gender_id = 0;
				
				String read = "select gender_id from gender where gender=?";
				statement = connection.prepareStatement(read);
				statement.setString(1, gender);
				rs = statement.executeQuery();
				if (rs.next()) {
					gender_id = rs.getInt("gender_id");
				String query = "insert into passengers(name,mail_Id,gender,age_category) values(?,?," + gender_id + ","
						+ 1 + ")";
				statement = connection.prepareStatement(query);
				statement.setString(1, name);
				statement.setString(2, mailId);
				statement.executeUpdate();
				}
				rs.close();
				read = "select id from passengers where mail_id=?";
				statement = connection.prepareStatement(read);
				statement.setString(1, mailId);
				rs = statement.executeQuery();
				if (rs.next()) {
					pass_id = rs.getInt("id");
				String query = "insert into users(passenger_id,phone_number,password) values(" + pass_id + ",?,?)";
				statement = connection.prepareStatement(query);
				statement.setString(1, phoneNumber);
				statement.setString(2, password);
				statement.executeUpdate();
				}
				rs.close();
//				connection.close();
			} catch (SQLException e1) {
				throw new SQLException();
			}
			newUser = new User(name, mailId, password, phoneNumber, gender, pass_id);
		}
		return newUser;
	}
	public ArrayList<String> getPlacesList() throws SQLException{
		ArrayList<String> placesList=new ArrayList<String>();
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement = null;
		try {
			String read="select city from airports where airport_type=2";
			statement = connection.prepareStatement(read);
			rs = statement.executeQuery();
			while (rs.next()) {
				placesList.add(rs.getString("city"));
			}
			rs.close();
			
		} catch (SQLException e1) {
			throw new SQLException();

		}
		return placesList;
	}
	public boolean isDomestic(String from,String to) throws SQLException{
		boolean flag=false;
		String fromCountry="";
		String toCountry="";
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement = null;
		try {
			String read="select country from airports where city=?";
			statement = connection.prepareStatement(read);
			statement.setString(1, from);
			rs = statement.executeQuery();
			while (rs.next()) {
				fromCountry=rs.getString("country");
			}
			rs.close();
			 read="select country from airports where city=?";
			statement = connection.prepareStatement(read);
			statement.setString(1, to);
			rs = statement.executeQuery();
			while (rs.next()) {
				toCountry=rs.getString("country");
			}
			rs.close();
			if(fromCountry.equals(toCountry)){
				flag=true;
			}

			
		} catch (SQLException e1) {
			throw new SQLException();

		}
		return flag;
	}
	private Airport getFromAirport(String from, Airporttype airporttype) throws SQLException {
		Airport airportfrom = null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		PreparedStatement statement;
		int type_id = 0;
		try {
			String query = "select airport_id,city,country,airport_type.airport_type from airports left join airport_type on airport_type.id=airports.airport_type where LOWER(city)= '" + from.toLowerCase() + "' and airport_type.airport_type='" + airporttype.toString() + "'";
			statement = connection.prepareStatement(query);
			rs1 = statement.executeQuery();
			if (rs1.next()) {
				airportfrom = new Airport(rs1.getString("airport_id"), rs1.getString("city"), rs1.getString("country"),
						airporttype);
			}

			rs1.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return airportfrom;
	}

	private Airport getToAirport(String to, Airporttype airporttype) throws SQLException {
		Airport airportto = null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		PreparedStatement statement;
		int type_id = 0;
		try {
			String query = "select airport_id,city,country,airport_type.airport_type from airports left join airport_type on airport_type.id=airports.airport_type where LOWER(city)= '" + to.toLowerCase() + "' and airport_type.airport_type='" + airporttype.toString() + "'";
			statement = connection.prepareStatement(query);
			rs1 = statement.executeQuery();
			if (rs1.next()) {
				airportto = new Airport(rs1.getString("airport_id"), rs1.getString("city"), rs1.getString("country"),
						airporttype);
			}
			rs1.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return airportto;
	}
	public FlightRoutes getRoute(int flightId,String depDate) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement statement;
		FlightRoutes route = null;
		int from_id = 0;
		int to_id = 0;
		int flight_id = 0;
		int ecoseatsavail = 0;
		int preseatsavail = 0;
		int busiseatsavail = 0;
		int firseatsavail = 0;
		String from="";
		Airporttype type=null;
		String to="";
		try{
			String read="select city,airport_type from flight_routes left join airports on flight_routes.departureairport=airports.id where flight_routes.id="+flightId+";";
			statement = connection.prepareStatement(read);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					from = rs1.getString("city");
					int typeId=rs1.getInt("airport_type");
					if(typeId==1){
						type=Airporttype.valueOf("DOMESTIC");
					}
					if(typeId==2){
						type=Airporttype.valueOf("INTERNATIONAL");
					}
					
				}
				rs1.close();
				read="select city from flight_routes left join airports on flight_routes.destinationairport =airports.id where flight_routes.id="+flightId+";";
			statement = connection.prepareStatement(read);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					to = rs1.getString("city");
				}
				rs1.close();
				read = "select * from flight_routes where id="
					+ flightId + "";
			statement = connection.prepareStatement(read);
			rs = statement.executeQuery();
			while (rs.next()) {
				flight_id = rs.getInt("id");
				//get economic seats count...
				String query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=1 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					ecoseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				//get premium seats count...
				
				query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=2 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					preseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				//get business seats count...
				
				query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=3 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					busiseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				//get first class seats count...
				
				query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=4 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					firseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				route = new FlightRoutes(rs.getInt("id"), rs.getString("flight_route_id"),
						getFromAirport(from, type), rs.getInt("departureterminalno"),
						getToAirport(to, type), rs.getInt("destinationterminalno"), rs.getTime("departuretime"),
						rs.getTime("arrivaltime"), rs.getString("duration"), rs.getDouble("price"), ecoseatsavail,
						preseatsavail, busiseatsavail, firseatsavail);
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return route;

		
	}


	public ArrayList<FlightRoutes> getFlightList(String from,String to,String depDate,Airporttype airporttype) throws SQLException{
		ArrayList<FlightRoutes> routes=new ArrayList<FlightRoutes>();	
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement statement;
		FlightRoutes route = null;
		int from_id = 0;
		int to_id = 0;
		int flight_id = 0;
		int ecoseatsavail = 0;
		int preseatsavail = 0;
		int busiseatsavail = 0;
		int firseatsavail = 0;
		try {
			String read = "select id from airports where airport_id='" + getFromAirport(from, airporttype).airportId
					+ "'";
			statement = connection.prepareStatement(read);
			rs = statement.executeQuery();
			if (rs.next()) {
				from_id = rs.getInt("id");
			}
			rs.close();
			read = "select id from airports where airport_id='" + getToAirport(to, airporttype).airportId + "'";
			statement = connection.prepareStatement(read);
			rs = statement.executeQuery();
			if (rs.next()) {
				to_id = rs.getInt("id");
			}
			rs.close();
			read = "select * from flight_routes where departureairport =" + from_id + " and destinationairport ="
					+ to_id + "";
			statement = connection.prepareStatement(read);
			rs = statement.executeQuery();
			while (rs.next()) {
				flight_id = rs.getInt("id");
				//get economic seats count...
				String query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=1 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					ecoseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				//get premium seats count...
				
				query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=2 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					preseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				//get business seats count...
				
				query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=3 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					busiseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				//get first class seats count...
				
				query = "select count(*) from tickets left join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="
						+ flight_id + " and departuredate='" + depDate
						+ "' and class_type=4 and passenger_status.status=1";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					firseatsavail = 45 - (rs1.getInt("count"));
				}
				rs1.close();
				route = new FlightRoutes(rs.getInt("id"), rs.getString("flight_route_id"),
						getFromAirport(from, airporttype), rs.getInt("departureterminalno"),
						getToAirport(to, airporttype), rs.getInt("destinationterminalno"), rs.getTime("departuretime"),
						rs.getTime("arrivaltime"), rs.getString("duration"), rs.getDouble("price"), ecoseatsavail,
						preseatsavail, busiseatsavail, firseatsavail);
				routes.add(route);
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return routes;

	}
	public int addPassenger(String name, String gender, String age_category,String mailId) throws SQLException {
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		ResultSet rs1 = null;
		int gender_id = 0;
		int age_category_id = 0;
		int login_id = 0;
		int pass_id = 0;
		PreparedStatement statement = null;
		try {
			String read = "select gender_id from gender where gender=?";
			statement = connection.prepareStatement(read);
			statement.setString(1, gender);
			rs = statement.executeQuery();
			if (rs.next()) {
				gender_id = rs.getInt("gender_id");
			}
			rs.close();
			read = "select id from age_category where LOWER(age_category)='" + age_category.toLowerCase() + "'";
			statement = connection.prepareStatement(read);
			rs = statement.executeQuery();
			if (rs.next()) {
				age_category_id = rs.getInt("id");
			}
			rs.close();
			read = "select login_id from users left join passengers on users.passenger_id=passengers.id where mail_id=?";
			statement = connection.prepareStatement(read);
			statement.setString(1,mailId);
			rs = statement.executeQuery();
			if (rs.next()) {
				login_id = rs.getInt("login_id");
			}
			rs.close();
			read = "select passengers.id from passengers left join wish_list on passengers.id=wish_list.passenger_id where login_id="
					+ login_id + " and LOWER(name)=? and gender=" + gender_id + " and age_category=" + age_category_id
					+ "";
			statement = connection.prepareStatement(read);
			statement.setString(1, name.toLowerCase());
			rs = statement.executeQuery();
			if(rs.next()) {
				pass_id=rs.getInt("id");	
				System.out.println("*****");			
			}
			else{
				String query = "insert into passengers(name,gender,age_category) values(?," + gender_id + ","
						+ age_category_id + ") returning id";
				statement = connection.prepareStatement(query);
				statement.setString(1, name);
				rs1 = statement.executeQuery();
				if (rs1.next()) {
					pass_id = rs1.getInt("id");
				}
				rs1.close();
				query = "insert into wish_list(login_id,passenger_id) values(" + login_id + "," + pass_id + ")";
				statement = connection.prepareStatement(query);
				statement.executeUpdate();
			} 
			rs.close();
//			connection.close();
		} catch (SQLException e1) {
		throw new SQLException();
		}
		return pass_id;
	}
	public HashMap<String,Integer> getClassSeats(String date,int flight_id,int seatsperclass) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		HashMap<String,Integer> classseats=new HashMap<String,Integer>();
		PreparedStatement statement;
			try {
				String query="select count(*) from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=1 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					classseats.put("ECONOMY", seatsperclass-(rs1.getInt("count")));
				}
				rs1.close();
				 query="select count(*) from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=2 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					classseats.put("PREMIUM", seatsperclass-(rs1.getInt("count")));
				}
				rs1.close();
				 query="select count(*) from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=3 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					classseats.put("BUSINESS", seatsperclass-(rs1.getInt("count")));
				}
				rs1.close();
				 query="select count(*) from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=4 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					classseats.put("FIRSTCLASS", seatsperclass-(rs1.getInt("count")));
				}
				rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return classseats;
			
	} 
	public HashMap<String,HashMap<String,Integer>> getClassQuotaSeats(String date,int flight_id,int seatsperclass) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		HashMap<String,HashMap<String,Integer>> classquotaseats=new HashMap<String,HashMap<String,Integer>>();
		 HashMap<String,Integer>classseats=null;
		 HashMap <String,Integer>quotaseats=null;
		PreparedStatement statement;
			try {
				int classscount=0;
				int quotacounts=0;
				String query ="select count(*) from class_type;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					classscount=rs1.getInt("count");
				}
				rs1.close();
				query ="select count(*) from quota;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					quotacounts=rs1.getInt("count");
				}
				rs1.close();
				String classty="";
				String quotaty="";
				for(int i=1;i<=classscount;i++) {
					query="select class_type from class_type where id="+i+";";
					statement = connection.prepareStatement(query);
					rs1 = statement.executeQuery();
					while (rs1.next()) {
						classty=rs1.getString("class_type");
					}
					rs1.close();
					quotaseats=new HashMap<String,Integer>();
					for(int j=1;j<=quotacounts;j++) {
						
						query="select quota from quota where id="+j+";";
						statement = connection.prepareStatement(query);
						rs1 = statement.executeQuery();
						while (rs1.next()) {
							quotaty=rs1.getString("quota");
						}
						rs1.close();
						int seatsqcount=0;
						if(quotaty.equals("REGULAR")) {
							seatsqcount=seatsperclass;
						}
						else {
							seatsqcount=seatsperclass/5;
						}
						query="select count(*) from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type="+i+" and quota="+j+" and passenger_status.status=1;";
						statement = connection.prepareStatement(query);
						rs1 = statement.executeQuery();
						while (rs1.next()) {
							int count=rs1.getInt("count");
							quotaseats.put(quotaty,seatsqcount-(rs1.getInt("count")));
						}
						rs1.close();
					}
					classquotaseats.put(classty, quotaseats);
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return classquotaseats;

	}
	public boolean[] getEconomicSeats(String date,int flight_id,boolean[] ecoSeatno) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		PreparedStatement statement;
			try {
				
				String query="select booked_seat from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=1 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					String str1=rs1.getString("booked_seat");
					String str=str1.substring(1, str1.length());
					int seat=Integer.parseInt(str);
						 ecoSeatno[seat-1]=false;
				}
				rs1.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ecoSeatno;
	}
	public boolean[] getPremiumSeats(String date,int flight_id,boolean[] preSeatno) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		PreparedStatement statement;
			try {
				
				String query="select booked_seat from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=2 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					String str1=rs1.getString("booked_seat");
					String str=str1.substring(1, str1.length());
					int seat=Integer.parseInt(str);
						 preSeatno[seat-1]=false;
				}rs1.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return preSeatno;
	}
	public boolean[] getBusinessSeats(String date,int flight_id,boolean[] busiSeatno) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		PreparedStatement statement;
			try {
				
				String  query="select booked_seat from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=3 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					String str1=rs1.getString("booked_seat");
					String str=str1.substring(1, str1.length());
					int seat=Integer.parseInt(str);
						 busiSeatno[seat-1]=false;
				}
				rs1.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return busiSeatno;
	}
	public boolean[] getFirstclassSeats(String date,int flight_id,boolean[] firSeatno) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs1 = null;
		PreparedStatement statement;
			try {
				
				String query="select booked_seat from tickets inner join passenger_status on tickets.ticket_id=passenger_status.ticket_id where flight_id="+flight_id+" and departuredate='"+date+"' and class_type=4 and passenger_status.status=1;";
				statement = connection.prepareStatement(query);
				rs1 = statement.executeQuery();
				while (rs1.next()) {
					String str1=rs1.getString("booked_seat");
					String str=str1.substring(1, str1.length());
					int seat=Integer.parseInt(str);
						 firSeatno[seat-1]=false;
				}
				rs1.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return firSeatno;
	}
	public boolean isCardexist(long cardNumber) throws SQLException {
		boolean flag = false;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		long cardnum = 0l;
		String read = "select cardnumber from accounts where cardnumber=?";
		try {
			statement = connection.prepareStatement(read);
			statement.setLong(1, cardNumber);
			rs = statement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public boolean isValidholderName(String holdername, long cardno) throws SQLException {
		boolean flag = false;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		String holder = "";
		String read = "select accountholdername  from accounts where cardnumber=?";
		try {
			statement = connection.prepareStatement(read);
			statement.setLong(1, cardno);
			rs = statement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return flag;
	}
	public boolean isValidCardExpiryDate(String date, long cardno) throws SQLException {
		boolean flag = false;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		String holder = "";
		String read = "select expirydate from accounts where cardnumber=?";
		try {
			statement = connection.prepareStatement(read);
			statement.setLong(1, cardno);
			rs = statement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return flag;
	}
	public boolean isValidcvv(String cvv, long cardno) throws SQLException {
		String regex = "^[0-9]{3,4}$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(cvv);
		boolean flag = false;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		String cvvcheck = "";
		String read = "select cvv  from accounts where cardnumber=?";
		try {
			statement = connection.prepareStatement(read);
			statement.setLong(1, cardno);
			rs = statement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return match.matches() && flag;
	}
	public boolean isvalidPin(int pin, long cardno) throws SQLException {
		Account currentaccount=null;
		boolean flag = false;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		int pincheck = 0;
		String read = "select pin from accounts where cardnumber=?";
		
		try {
			statement = connection.prepareStatement(read);
			statement.setLong(1, cardno);
			rs = statement.executeQuery();
			if (rs.next()) {
					flag = true;
			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		
		return flag;
	}
	public Account getAccountDetails(long cardno) throws SQLException{
		Account currentaccount=null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		PreparedStatement statement;
		String cvvcheck = "";
		String read = "select * from accounts where cardnumber=?";
		try {
			statement = connection.prepareStatement(read);
			statement.setLong(1, cardno);
			rs = statement.executeQuery();
			if (rs.next()) {
		currentaccount = new Account(rs.getString("bankname"), rs.getString("branchname"),
							rs.getLong("accountnumber"), rs.getString("accountholdername"), rs.getString("cvv"),
							rs.getString("expirydate"), rs.getInt("pin"), rs.getDouble("balance"));			}
			rs.close();
//			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
		return currentaccount;

	}
	public double getTotalPrice(int flightId) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		double price=0;
		PreparedStatement statement;
		try {
			String read ="select price from flight_routes where id=?";
			statement = connection.prepareStatement(read);
			statement.setInt(1, flightId);
			rs = statement.executeQuery();
			if (rs.next()) {
				price = rs.getDouble("price");
			}
			rs.close();
			} catch (SQLException e) {
			throw new SQLException();
		}
		return price;
	}
	public void addTicket(User currentUser,Ticket currentpnr,FlightRoutes currentRoute){
		try {
				DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
												// add passengers to db...

												ResultSet rs = null;
												PreparedStatement statement;
												int classt = 0;
												int quotat = 0;
												String query = "select id from class_type where class_type='" + currentpnr.classtype
														+ "'";
												statement = connection.prepareStatement(query);
												rs = statement.executeQuery();
												if (rs.next()) {
													classt = rs.getInt("id");
												}
												rs.close();
												query = "select id from quota where quota='" + currentpnr.faretype + "'";
												statement = connection.prepareStatement(query);
												rs = statement.executeQuery();
												if (rs.next()) {
													quotat = rs.getInt("id");
												}
												rs.close();
												int login_id = 0;
												query = "select login_id from users left join passengers on users.passenger_id=passengers.id where mail_id=?";
												statement = connection.prepareStatement(query);
												statement.setString(1, currentUser.mailId);
												rs = statement.executeQuery();
												if (rs.next()) {
													login_id = rs.getInt("login_id");
												}
												rs.close();
												int i = 0;
												for (Map.Entry<Integer,Passenger> pass : currentpnr.passengers
														.entrySet()) {
													int passenger_id = 0;
													query = "select passenger_id from passengers left join wish_list on passengers.id=wish_list.passenger_id where login_id="
															+ login_id + " and name ilike ?";
													statement = connection.prepareStatement(query);
													statement.setString(1, pass.getValue().name);
													rs = statement.executeQuery();
													if (rs.next()) {
														passenger_id = rs.getInt("passenger_id");
													}
													rs.close();
													int ticket_id = 0;
													LocalDate dateObj = LocalDate.now();
													DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
													String date1 = dateObj.format(formatter);
													query = "insert into tickets(pnr,passenger_id,login_id,flight_id,departuredate,class_type,quota,transaction_id,bookeddate,status) values('"
															+ currentpnr.pnr + "'," + passenger_id + "," + login_id + ","
															+ currentRoute.flightId + ",'" + currentpnr.departuredate + "'," + classt + ","
															+ quotat + "," + currentpnr.getTransactionId() + ",'" + date1
															+ "'," + 1 + ") returning ticket_id";
													statement = connection.prepareStatement(query);
													rs = statement.executeQuery();
													if (rs.next()) {
														ticket_id = rs.getInt("ticket_id");
													}
													rs.close();
													query = "insert into passenger_status(passenger_id,ticket_id,booked_seat,status) values("
															+ passenger_id + "," + ticket_id + ",'" + currentpnr.bookedseats[i]
															+ "',1)";
													statement = connection.prepareStatement(query);
													statement.executeUpdate();
													i++;
												}
											} catch (SQLException e1) {
												e1.printStackTrace();
												System.out.println("sorry something went wrong...");
											}
	}
	public LinkedHashMap<String, Ticket> getmybookings(User currentUser) throws SQLException {
		LinkedHashMap<String, Ticket> tickets = new LinkedHashMap<String, Ticket>();
		FlightRoutes route = null;
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String gender = "";
		String age_category = "";
		int login_id = 0;
		int pass_id = 0;
		int passcount = 0;
		int flight_idd = 0;
		FlightRoutes routee = null;
		String[] bookseats = null;
		String passstatus = "";
		PreparedStatement statement = null;
		try {

			// get passengers count in a ticket...
// System.out.println(currentUser.mailId);
			String read = "select distinct pnr,count(*) from tickets left join users on tickets.login_id=users.login_id left join passengers on users.passenger_id=passengers.id where mail_id=? group by pnr";
			statement = connection.prepareStatement(read);
			statement.setString(1, currentUser.mailId);
			rs1 = statement.executeQuery();
			while (rs1.next()) {
				// System.out.println("1");
				int i = 0;
				bookseats = new String[rs1.getInt("count")];
				String classtyp = "";
				String faretyp = "";
				double price = 0;
				String depdate = "";
				Long tran_id = 0l;
				String stat = "";
				HashMap<Integer, Passenger> passengerslis = new HashMap<Integer, Passenger>();

				// get ticket details...

				read = "select name,tickets.flight_id,passenger_status.passenger_id,tickets.status as ticket_status,class_type.class_type,quota.quota,tickets.departuredate ,tickets.transaction_id,gender.gender,age_category.age_category,passenger_status.status,passenger_status.booked_seat from tickets left join passengers on tickets.passenger_id=passengers.id left join passenger_status on tickets.ticket_id=passenger_status.ticket_id left join class_type on tickets.class_type=class_type.id left join quota on tickets.quota=quota.id left join gender on passengers.gender=gender.gender_id left join age_category on passengers.age_category=age_category.id where pnr='"
						+ rs1.getString("pnr") + "' ORDER BY tickets.departuredate DESC";
				statement = connection.prepareStatement(read);
				rs = statement.executeQuery();
				while (rs.next()) {
					// System.out.println("2");
					flight_idd = rs.getInt("flight_id");
					bookseats[i] = rs.getString("booked_seat");
					i++;
					depdate = rs.getString("departuredate");
					tran_id = rs.getLong("transaction_id");
					classtyp = rs.getString("class_type");
					faretyp = rs.getString("quota");
					stat = rs.getString("ticket_status");
					String passenger_status = "";

					if (rs.getInt("status") == 1) {
						passenger_status = "BOOKED";
					} else if (rs.getInt("status") == 2) {
						passenger_status = "CANCELLED";
					}
					if (rs.getInt("ticket_status") == 1) {
						stat = "BOOKED";
					} else if (rs.getInt("ticket_status") == 2) {
						stat = "CANCELLED";
					}

					passengerslis.put(rs.getInt("passenger_id"),
							new Passenger(rs.getString("name"), rs.getString("gender"), rs.getString("age_category"),
									rs.getInt("passenger_id"), passenger_status));
				}
				rs.close();
				Airport fromair = null;
				Airport toair = null;

				// get flight details...

				read = "select * from flight_routes left join airports on airports.id=flight_routes.departureairport left join airport_type on airports.airport_type=airport_type.id where flight_routes.id="
						+ flight_idd + "";
				read = "select * from flight_routes where id=" + flight_idd + "";
				statement = connection.prepareStatement(read);
				rs = statement.executeQuery();
				if (rs.next()) {
					// System.out.println("3");
					price = rs.getDouble("price");
					String query = "select * from airports left join airport_type on airports.airport_type=airport_type.id where airports.id="
							+ rs.getInt("departureairport") + "";
					statement = connection.prepareStatement(query);
					rs2 = statement.executeQuery();
					if (rs2.next()) {
						// System.out.println("4");
						String airtype = "";
						Airporttype type = null;
						airtype = rs2.getString("airport_type");
						if (airtype.equals("DOMESTIC")) {
							type = Airporttype.DOMESTIC;
						} else {
							type = Airporttype.INTERNATIONAL;
						}
						fromair = new Airport(rs2.getString("airport_id"), rs2.getString("city"),
								rs2.getString("country"), type);
					}
					rs2.close();
					query = "select * from airports left join airport_type on airports.airport_type=airport_type.id where airports.id="
							+ rs.getInt("destinationairport") + "";
					statement = connection.prepareStatement(query);
					rs2 = statement.executeQuery();
					String airtype = "";
					if (rs2.next()) {
						// System.out.println("5");
						airtype = "";
						Airporttype type = null;
						airtype = rs2.getString("airport_type");
						if (airtype.equals("DOMESTIC")) {
							type = Airporttype.DOMESTIC;
						} else {
							type = Airporttype.INTERNATIONAL;
						}

						toair = new Airport(rs2.getString("airport_id"), rs2.getString("city"),
								rs2.getString("country"), type);
					}
					rs2.close();
					routee = new FlightRoutes(flight_idd, rs.getString("flight_route_id"), fromair,
							rs.getInt("departureterminalno"), toair, rs.getInt("destinationterminalno"),
							rs.getTime("departuretime"), rs.getTime("arrivaltime"), rs.getString("duration"),
							rs.getDouble("price"), airtype);
					// System.out.println("route : "+routee);
				}
				rs.close();
				Flightclass classt = null;
				Quota faret = null;
				if (classtyp.equalsIgnoreCase("ECONOMY")) {
					classt = Flightclass.ECONOMY;
				} else if (classtyp.equalsIgnoreCase("PREMIUM")) {
					classt = Flightclass.PREMIUM;
				} else if (classtyp.equalsIgnoreCase("BUSINESS")) {
					classt = Flightclass.BUSINESS;
				} else if (classtyp.equalsIgnoreCase("FIRSTCLASS")) {
					classt = Flightclass.FIRSTCLASS;
				}
				if (faretyp.equalsIgnoreCase("REGULAR")) {
					faret = Quota.REGULAR;
				} else if (faretyp.equalsIgnoreCase("ARMEDFORCES")) {
					faret = Quota.ARMEDFORCES;
				} else if (faretyp.equalsIgnoreCase("DOCTORS_NURSES")) {
					faret = Quota.DOCTORS_NURSES;
				} else if (faretyp.equalsIgnoreCase("SENIORCITIZEN")) {
					faret = Quota.SENIORCITIZEN;
				} else if (faretyp.equalsIgnoreCase("STUDENT")) {
					faret = Quota.STUDENT;
				}

				double total = routee.fareCalculation(classt, faret, price);
				Ticket ticketobj = new Ticket(currentUser, routee, routee.flightId,
						Byte.parseByte("" + rs1.getInt("count")), fromair, toair, depdate, total, classt, faret,
						passengerslis, tran_id, bookseats, rs1.getString("pnr"), stat);
				// System.out.println("ticket : "+ticketobj);
				tickets.put(rs1.getString("pnr"), ticketobj);
			}
			rs1.close();
//			rs.close();			
			currentUser.ticketlist = tickets;
//			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new SQLException();

		}
		return tickets;
	}
	public void cancelFullTicket(String pnr) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		PreparedStatement statement;
											ResultSet rs = null;
											try {
												String query = "update tickets set status=2 where pnr='" + pnr
														+ "' returning ticket_id";
												statement = connection.prepareStatement(query);
												rs = statement.executeQuery();
												while (rs.next()) {
													int tickid = rs.getInt("ticket_id");
													query = "update passenger_status set status=2 where ticket_id="
															+ tickid + "";
													statement = connection.prepareStatement(query);
													statement.executeUpdate();
												}
												rs.close();
											} catch (SQLException e1) {
												System.out.println("sorry something went wrong...");
// 												System.exit(0);
// //												viewoptions=true;
// //												continue choose;
											}

	}
	public HashMap<String,Integer> getPassengers(String pnr) throws SQLException{
		HashMap<String,Integer> namesStatus=new HashMap<String,Integer>();
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		PreparedStatement statement;
		ResultSet rs = null;
											try {
												int count=0;
												String query="select count(*) from tickets where pnr=?";
												System.out.println(pnr+"pnnnrrr");
												statement = connection.prepareStatement(query);
															statement.setString(1,pnr);

												rs = statement.executeQuery();
												while (rs.next()) {
													 count  = rs.getInt("count");
												}
												rs.close();
														 // names=new String[count];
														 // status=new String[count];
														int i=0;
														System.out.println(count+"count");
												 query = "select name,passenger_status.status from passengers left join tickets on passengers.id=tickets.passenger_id left join passenger_status on passenger_status.ticket_id=tickets.ticket_id where pnr=?";
												statement = connection.prepareStatement(query);
												statement.setString(1,pnr);
												rs = statement.executeQuery();
												while (rs.next()) {
													String name  = rs.getString("name");
													int stat=rs.getInt("status");
													namesStatus.put(name,stat);
													// names[i]=name;
													// status[i]=stat;
													// i++;
											
												}
												rs.close();
											} catch (SQLException e1) {
												System.out.println("sorry something went wrong...");
												e1.printStackTrace();
// 												System.exit(0);
// //												viewoptions=true;
// //												continue choose;
											}
											return namesStatus;

	}
	public void cancelPartialTicket(String[] cancelPassengers,String pnr) throws SQLException{
		DbConnection db = DbConnection.getInstance();
		Connection connection = db.getConnection();
		PreparedStatement statement;
		ResultSet rs = null;
		try {
			int count=0;
												String query=" select count(*) from tickets left join passenger_status on passenger_status.ticket_id=tickets.ticket_id where pnr=? and passenger_status.status=1";
												statement = connection.prepareStatement(query);
															statement.setString(1,pnr);

												rs = statement.executeQuery();
												while (rs.next()) {
													 count  = rs.getInt("count");
												}
												rs.close(); 
												if(count==cancelPassengers.length){
													String query1 = "update tickets set status=2 where pnr=?";
												statement = connection.prepareStatement(query1);
												statement.setString(1,pnr);
												statement.executeUpdate();
												}
			for(int i=0;i<cancelPassengers.length;i++){
				String read= "select passengers.id from passengers left join tickets on passengers.id=tickets.passenger_id where pnr=? and name=?";
				statement = connection.prepareStatement(read);
				statement.setString(1,pnr);
				statement.setString(2,cancelPassengers[i]);
				ResultSet result = statement.executeQuery();
													if (result.next()) {

														int passId=result.getInt("id");
														  query = "update passenger_status set status=2 where passenger_id='"
															+ passId+"'";
													statement = connection.prepareStatement(query);
													statement.executeUpdate();

													}
result.close();
				
												}
												
											}catch (Exception e1) {
													System.out.println("sorry something went wrong...");
													e1.printStackTrace();
												}
													
	}

}