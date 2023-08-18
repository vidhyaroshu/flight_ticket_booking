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
import DbOperations.Utilities;



public class CancelServlet extends HttpServlet {

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String cancelOption = request.getParameter("cancel");
			HttpSession session=request.getSession(false);
					PrintWriter out=response.getWriter();
			HashMap<String,Integer> names=null;
							Utilities db=new Utilities();
			if(cancelOption.equals("full")){
							String pnr = request.getParameter("pnr");

			try{	
				db.cancelFullTicket(pnr);
			}
		catch(Exception e){
			e.printStackTrace();
		}
		out.println("Suceesfully cancelled...");			
	}
			if(cancelOption.equals("partial")){
							String pnr = request.getParameter("pnr");
							System.out.println(pnr+"pnrrr");

				try{	
				names=db.getPassengers(pnr);
				}
				catch(Exception e){
				}
		request.setAttribute("names",names);
		session.setAttribute("pnr",pnr);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cancel.jsp");
   		    dispatcher.forward(request, response);
   		    return;
			}
			if(cancelOption.equals("pass")){
				String pnr=(String)session.getAttribute("pnr");
				       String[] cancelPassengers = request.getParameterValues("passengers");
				       int travellersCount=Integer.parseInt(request.getParameter("selected"));
if(cancelPassengers!=null){
if(cancelPassengers.length!=travellersCount){
	try{	
				names=db.getPassengers(pnr);
			}
		catch(Exception e){
		}
		request.setAttribute("names",names);
		// session.setAttribute("pnr",pnr);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cancel.jsp");
   		    dispatcher.forward(request, response);
   		    return;
}
}
        else{
        	try{	
				names=db.getPassengers(pnr);
			}
		catch(Exception e){
		}
		request.setAttribute("names",names);
		// session.setAttribute("pnr",pnr);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cancel.jsp");
   		    dispatcher.forward(request, response);
        	response.sendRedirect("cancel.jsp");
        	return;
        }
				       try{	
 db.cancelPartialTicket(cancelPassengers,pnr);			}
		catch(Exception e){
			e.printStackTrace();
		}
		out.println("Successfully Cancelled");
				      
			}
		}
}