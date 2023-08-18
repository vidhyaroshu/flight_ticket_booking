package AirlineBooking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.RequestDispatcher;


public class MyBookingsServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			String action = request.getParameter("action");
			String requestparam=request.getParameter("bookingss");
				String mailId=(String)session.getAttribute("userId");
			 	Ticket myTicket=new Ticket();
   		    	HashMap<String,Ticket> myBooking=myTicket.getMyBookings(mailId);
			 		if(action.equals("upcoming")){
						 session=request.getSession(false);
						if(session==null||session.getAttribute("sessionId")==null){
						    String path=request.getServletPath();
						    request.setAttribute("bookingrequest",path);
						    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			                dispatcher.forward(request, response);
			                return;
			            }
			                else{
								 LocalDate currentDate = LocalDate.now();
					   		     HashMap<String,Ticket> upcoming=new HashMap<String,Ticket>();
					        for (Map.Entry<String, Ticket> entry : myBooking.entrySet()) {
					            String date = entry.getValue().departuredate;
					            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					                LocalDate value = LocalDate.parse(date, formatter);
					                String status=entry.getValue().status;
					                
					            if (status.equalsIgnoreCase("BOOKED")&&(value.isAfter(currentDate)||value.isEqual(currentDate))) {
					            	upcoming.put(entry.getKey(),entry.getValue());
					       		 }
					       	}
					         request.setAttribute("book",upcoming);
					        RequestDispatcher dispatcher = request.getRequestDispatcher("mybooking.jsp");
						   	dispatcher.forward(request, response);
						   	return;

					   		    }
		   				}


   		   String requestpara=(String)request.getAttribute("bookings");
   		   if(requestpara!=null&&requestpara.equals("up")){
		   		   LocalDate currentDate = LocalDate.now();
		   		     HashMap<String,Ticket> upcoming=new HashMap<String,Ticket>();
		        for (Map.Entry<String, Ticket> entry : myBooking.entrySet()) {
		            String date = entry.getValue().departuredate;
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		                LocalDate value = LocalDate.parse(date, formatter);
		                String status=entry.getValue().status;
		                
		            if (status.equalsIgnoreCase("BOOKED")&&(value.isAfter(currentDate)||value.isEqual(currentDate))) {
		            	upcoming.put(entry.getKey(),entry.getValue());
		       		 }
		       	}
		       	 request.setAttribute("tripOption","upcoming");
		         request.setAttribute("book",upcoming);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("mybooking.jsp");
			   	dispatcher.forward(request, response);
			   	return;
    		}
    		if(requestparam.equals("upcoming")){
		   		   LocalDate currentDate = LocalDate.now();
		   		     HashMap<String,Ticket> upcoming=new HashMap<String,Ticket>();
		        for (Map.Entry<String, Ticket> entry : myBooking.entrySet()) {
		            String date = entry.getValue().departuredate;
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		                LocalDate value = LocalDate.parse(date, formatter);
		                String status=entry.getValue().status;
		            if (status.equalsIgnoreCase("BOOKED")&&(value.isAfter(currentDate)||value.isEqual(currentDate))) {
		            	upcoming.put(entry.getKey(),entry.getValue());
		            } 
		        }
		        request.setAttribute("tripOption","upcoming");
		         request.setAttribute("book",upcoming);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("mybooking.jsp");
			   	dispatcher.forward(request, response);
			   	return;
    		}
    		if(requestparam.equals("completed")){
		   		   LocalDate currentDate = LocalDate.now();
		   		    HashMap<String,Ticket> completed=new HashMap<String,Ticket>();
		        for (Map.Entry<String, Ticket> entry : myBooking.entrySet()) {
		            String date = entry.getValue().departuredate;
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		                LocalDate value = LocalDate.parse(date, formatter);
		                String status=entry.getValue().status;
		             if (status.equalsIgnoreCase("BOOKED")&&value.isBefore(currentDate)) {
		            	completed.put(entry.getKey(),entry.getValue());
		            } 
		        }
		         request.setAttribute("tripOption","completed");
		         request.setAttribute("book",completed);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("mybooking.jsp");
			   	dispatcher.forward(request, response);
			   	return;
    		}
    		if(requestparam.equals("cancelled")){
		   		    HashMap<String,Ticket> cancelled=new HashMap<String,Ticket>();
		        for (Map.Entry<String, Ticket> entry : myBooking.entrySet()) {
		                String status=entry.getValue().status;
		             if (status.equalsIgnoreCase("CANCELLED")) {
		            	cancelled.put(entry.getKey(),entry.getValue());
		            } 
		        } 
		        request.setAttribute("tripOption","cancelled");
		         request.setAttribute("book",cancelled);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("mybooking.jsp");
			   	dispatcher.forward(request, response);
			   	return;
    		}
    		if(requestparam.equals("view")){
    						String pnr=request.getParameter("pnr");
		   		    HashMap<String,Ticket> view=new HashMap<String,Ticket>();
		   		    if(myBooking.containsKey(pnr)){
		   		    	view.put(pnr,myBooking.get(pnr));
		   		    }
		        String tripoption=request.getParameter("trip");
		        request.setAttribute("trip",tripoption);
		         request.setAttribute("ticket",view);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("viewTicket.jsp");
			   	dispatcher.forward(request, response);
			   	return;
    		}
    	
   		}
   	}