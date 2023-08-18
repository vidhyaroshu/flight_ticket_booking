package AirlineBooking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import DbOperations.Utilities;
import java.util.*;
import jakarta.servlet.RequestDispatcher;
import Airline.*;
import java.time.LocalDate;


public class Searchservlet extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Utilities db=new Utilities();
			List<String> placesList=null;
			try{
			 placesList = db.getPlacesList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			Flightclass classarr[] = Flightclass.values();
			Quota quotaarr[] = Quota.values();
			       LocalDate today = LocalDate.now();
        LocalDate oneYearFromToday = today.plusYears(1);
        String date=today.toString();
        String oneYear=oneYearFromToday.toString();
            request.setAttribute("places", placesList);
            request.setAttribute("classtype",classarr);
            request.setAttribute("quota",quotaarr);
        request.setAttribute("min",date);
        request.setAttribute("max",oneYear);
            RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
   		    dispatcher.forward(request, response);
   		    return;
		}
}