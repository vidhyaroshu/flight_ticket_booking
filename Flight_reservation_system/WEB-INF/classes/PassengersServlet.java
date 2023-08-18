package Userdetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;	

public class PassengersServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 PrintWriter out = response.getWriter();
			HashMap<Integer,Passenger> passengerlist = new HashMap<Integer,Passenger>();
			HttpSession session = request.getSession(false);
			  int adults=(int)session.getAttribute("adults");
	          int children=(int)session.getAttribute("children");
	          int infants=(int)session.getAttribute("infants");
	          String mailId=(String)session.getAttribute("userId");
	          String airportttype=(String)session.getAttribute("airporttype");

	          int travellers=adults+children+infants;
	          for(int i=1;i<=travellers;i++){
	          	String name=request.getParameter("name"+i);
	          	String gender=request.getParameter("gender"+i);
	          	String ageCategory=request.getParameter("age"+i);

	          	System.out.println(name+"age");

	          	String passportno=request.getParameter("passportno"+i);
	          	String country=request.getParameter("passportcountry"+i);
	          	String date=(String)request.getParameter("expirydate"+i);
	          	Passenger passenger=new Passenger();
	          	Passenger currentPass=null;
	          	if(airportttype.equalsIgnoreCase("INTERNATIONAL")){
					 currentPass=passenger.addPassengerDetails(name,gender,ageCategory,mailId,passportno,country,date);
	 
	          	}
	          	else{
	          	 currentPass=passenger.addPassengerDetails(name,gender,ageCategory,mailId);
	          	}
	          	passengerlist.put(currentPass.passengerId,currentPass);
	          }
	          System.out.println(passengerlist.size()+"size");
	          session.setAttribute("passengerlist",passengerlist);
	          response.sendRedirect("SeatsServlet");
	          return;
		}
}