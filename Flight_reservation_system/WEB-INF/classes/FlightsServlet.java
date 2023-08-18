package Airline;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import jakarta.servlet.RequestDispatcher;


public class FlightsServlet extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 // String airporttype = request.getParameter("airporttype");
			int adultscount = Integer.parseInt(request.getParameter("adults"));
			 int chidrencount = Integer.parseInt(request.getParameter("children"));
			 int infantscount = Integer.parseInt(request.getParameter("infants"));
			 int sum=adultscount+chidrencount+infantscount;
			 if(sum>9){
			 	RequestDispatcher dispatcher = request.getRequestDispatcher("Searchservlet");
   		    dispatcher.forward(request, response);
   		    return;

			 	// response.sendRedirect("Searchservlet");
			 }
			 String from = request.getParameter("from");
			 String to = request.getParameter("to");
			 FlightRoutes routeobj=new FlightRoutes(from,to);
			String airporttype= routeobj.getAirportType();
			 
			 String date = (String)request.getParameter("departureDate");
			 String classtype = request.getParameter("classtype");
			 String quota = request.getParameter("quota");
			 
			 HttpSession session = request.getSession();
			session.setAttribute("classtype", classtype);
			session.setAttribute("quota", quota);
			session.setAttribute("adults", adultscount);
			session.setAttribute("children", chidrencount);
			session.setAttribute("infants", infantscount);
			session.setAttribute("depDate", date);
			session.setAttribute("airporttype",airporttype);
			FlightRoutes flightobj=new FlightRoutes();
			ArrayList<FlightRoutes> routes= flightobj.getFlightRoutes(from,to,date,airporttype,quota);
			String path=request.getServletPath();
			// request.setAttribute("bookingrequest",path);
			request.setAttribute("flights",routes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
   		    dispatcher.forward(request, response);
   		    return;
		}
}