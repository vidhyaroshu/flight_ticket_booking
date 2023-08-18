package Airline;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;	

public class SeatsServlet extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			int flightId=(int)session.getAttribute("flightId");
			String classtype=(String)session.getAttribute("classtype");
			String quota=(String)session.getAttribute("quota");
			String date=(String)session.getAttribute("depDate");
			Flightclass classtyp=Flightclass.valueOf(classtype);
			Quota quot=Quota.valueOf(quota);
			FlightSeats seatobj=new FlightSeats();
			FlightSeats seats=seatobj.getFlightSeats(flightId,date,classtyp,quot);
			String[][] seatsview = seats.viewSeats();
			int adult=(int) session.getAttribute("adults");
    int child=(int) session.getAttribute("children");
int infants=(int) session.getAttribute("infants");
int total=adult+child+infants;
request.setAttribute("total",total);
			request.setAttribute("seats",seatsview);
			RequestDispatcher dispatcher = request.getRequestDispatcher("seats.jsp");
   		    dispatcher.forward(request, response);
   		    return;
		}
}