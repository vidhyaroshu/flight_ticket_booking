package Userdetails;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.*;


import AirlineBooking.Ticket;
import DbOperations.Utilities;

public class User{
	public String name;
	public String mailId;
	String phoneNumber;
	String gender;
	 private String password;
	int userId;
	public LinkedHashMap<String,Ticket> ticketlist=new LinkedHashMap<String,Ticket>();

	public User(String name,String mailId,String password,String phoneNumber,String gender,int userId){
		this.name=name; 
		this.mailId=mailId;
		this.phoneNumber=phoneNumber;
		this.gender=gender;
		this.userId=userId;
		this.password=password;
	}
	public User(String name,String mailId,String password,String phoneNumber,String gender){
		this.name=name; 
		this.mailId=mailId;
		this.phoneNumber=phoneNumber;
		this.gender=gender;
		this.password=password;
	}
	public User(String mailId,String password){
		this.mailId=mailId;
		this.password=password;

	}
	public String getPassword() {
		return password;
	}
	public boolean login(){
		boolean flag=false;
		Utilities db=new Utilities();
		User user=null;
		try{
		 user=db.getUser(mailId,password);
		 if(user!=null){
		 	flag=true;
		 }
		}
	catch(Exception e){
	System.out.println("bye");	
	e.printStackTrace();
	}
	return flag;
	}
	public boolean signUp(){
		boolean flag=false;
		Utilities db=new Utilities();
		User user=null;
		try{
		 user=db.addUser(name,mailId,phoneNumber,gender,password);
		 if(user!=null){
		 	flag=true;
		 }

	}
	catch(Exception e){
	}
		return flag;
	}
	public boolean isValidName(String name) {
		String regex = "^[A-Za-z]*" + "\\s*" + "[A-Za-z]*$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(name);
		return match.matches();
	}

	public boolean isValidmailId(String mailId) {
		String regex = "^[a-z]+(.+)" + "[@            ]" + "(\\S+)" + "[.]" + "[a-z]{2,6}$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(mailId);
		return match.matches();
	}

	public boolean isValidpassword(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(password);
		return match.matches();
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		String regex = "^(\\+\\d{1,}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(phoneNumber);
		return match.matches();
	}

	public boolean isValidGender(String gender) {
		boolean validgender = false;
		if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("others")
				|| gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m")) {
			validgender = true;
		}
		return validgender;
	}
}
