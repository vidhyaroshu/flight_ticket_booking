package Userdetails;
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
            request.setAttribute("places", placesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
   		    dispatcher.forward(request, response);
   		    return;
		}
}