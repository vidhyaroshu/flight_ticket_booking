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

public class PaymentServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 HttpSession session = request.getSession(false);
			String[] selectedSeats=(String[])session.getAttribute("bookedseat");
			long cardNumber=Long.parseLong(request.getParameter("cardnum"));
			String name=request.getParameter("holdername");
			String expiryDate=request.getParameter("expdate");
			String cvv=request.getParameter("cvv");
			int pin=Integer.parseInt(request.getParameter("pin"));
			Account account = new Account();
			boolean isvalidaccount=account.isValidCardDetails(cardNumber,name,expiryDate,cvv,pin);
			if(isvalidaccount){
			  int flightId=(int)session.getAttribute("flightId");
			  Flightclass classtype=Flightclass.valueOf((String)session.getAttribute("classtype"));
			  Quota quota=Quota.valueOf((String)session.getAttribute("quota"));
				Transaction transaction=account.getTransactionDetails(flightId,classtype,quota,cardNumber);
				request.setAttribute("transaction",transaction.getTransactionId());
				RequestDispatcher dispatcher = request.getRequestDispatcher("TicketsServlet");
   		    	dispatcher.forward(request, response);
   		    	return;
			}
			else{
				System.out.println("invalid account");
			}
		

		}
}