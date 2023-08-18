package Userdetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import jakarta.servlet.http.Cookie;
import java.util.UUID;
import jakarta.servlet.RequestDispatcher;

public class UsersServlet extends HttpServlet {

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			    String action = request.getParameter("action");

			    String check=request.getParameter("check");

			    PrintWriter out = response.getWriter();

			    if(action.equals("login")){

			    	String mailid=request.getParameter("mailid");

					String password=request.getParameter("password");

					User userobj=new User(mailid,password);

					if(userobj.isValidmailId(mailid)&&userobj.isValidpassword(password)){

						boolean loginUser=userobj.login();

						if(loginUser==true) {

						String sessionId = UUID.randomUUID().toString();

						HttpSession session=request.getSession(false);

						session.setAttribute("sessionId", sessionId);

						session.setAttribute("userId", mailid);

						Cookie cookie = new Cookie("sessionId", sessionId);

						response.addCookie(cookie);
						

						String servletPath = request.getServletPath();

							if(check.equals("null")){

								response.sendRedirect("welcome.jsp");

								return;

							}	
							else if(check.equals("/passengers.jsp")){

								request.setAttribute("pass","yes");

								RequestDispatcher dispatcher = request.getRequestDispatcher("passengers.jsp");

				   		    	dispatcher.forward(request, response);

				   		    	return;

							}				
							else{
								request.setAttribute("bookings","up");

								RequestDispatcher dispatcher = request.getRequestDispatcher("MyBookingsServlet");

				   		    	dispatcher.forward(request, response);

				   		    	return;

							}
						}

						else {

							out.println("invalid mail id or password...");

							response.sendRedirect("login.jsp");

							return;

						}
					}
					else{

						response.sendRedirect("welcome.jsp");

						return;
						
					}
			    }
			    if(action.equals("logout")){

						HttpSession session=request.getSession(false);

						session.removeAttribute("sessionId");

						session.removeAttribute("userId");

						session.invalidate();

						response.sendRedirect("welcome.jsp");

						return;
				}
			    if(action.equals("signup")){

			    	String name=request.getParameter("name");

					String mailid=request.getParameter("mailid");

					String password=request.getParameter("password");

					String gender=request.getParameter("gender");

					String phone=request.getParameter("phone");

					User userobj=new User(name,mailid,password,phone,gender);

					if(userobj.isValidmailId(mailid) && userobj.isValidpassword(password) && userobj.isValidPhoneNumber(phone) && userobj.isValidName(name)){

						boolean loginUser=userobj.signUp();

						if(loginUser==true) {

							String sessionId = UUID.randomUUID().toString();

							HttpSession session = request.getSession();

							session.setAttribute("sessionId", sessionId);
							
							session.setAttribute("userId", mailid);

							Cookie cookie = new Cookie("sessionId", sessionId);

							response.addCookie(cookie);

							response.sendRedirect("welcome.jsp");

							return;
						}
						else {
							out.println("User already exist...");

							response.sendRedirect("signup.jsp");

							return;
						}
					}

					else{

						response.sendRedirect("welcome.jsp");

						return;

					}
			    }
			
			}
}