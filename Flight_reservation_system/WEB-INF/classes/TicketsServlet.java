// then cancel tickets...
// then if the user is logged in the show log out option...
// if the user is logged out then show log in...
// then validations...
// then some patti tingarings...
package AirlineBooking;
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
import Airline.*;
import Userdetails.*;
import DbOperations.Utilities;


public class TicketsServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			PrintWriter out = response.getWriter();
			Utilities db=new Utilities();
			long transactionId=(long)request.getAttribute("transaction");
			Enumeration<String> attributeNames = session.getAttributeNames();
			HashMap<String,Object> sessionmap =new HashMap<String,Object>();
				while (attributeNames.hasMoreElements()) {
				    String name = attributeNames.nextElement();
				    Object value = session.getAttribute(name);
				    sessionmap.put(name,value);
					System.out.println(name + " = " + value);
				}
				sessionmap.put("transactionId",transactionId);
				int flightId=(int)sessionmap.get("flightId");
				String depdate=(String)sessionmap.get("depDate");
				Ticket tickObj=new Ticket();
				Ticket myTicket=tickObj.getMyTicket(sessionmap);
				FlightRoutes currentroute=null;
				try{
				 currentroute=db.getRoute(flightId,depdate);
			}
			catch(Exception e){
			}
			HashMap<Integer,Passenger> pass=(HashMap<Integer,Passenger>)session.getAttribute("passengerlist");
			System.out.println(pass.size()+"qwerty");
				request.setAttribute("ticket",myTicket);
				request.setAttribute("route",currentroute);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ticket.jsp");
   		    dispatcher.forward(request, response);
   		    return;
   		

		}
}
