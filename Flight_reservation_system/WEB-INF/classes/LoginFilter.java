// import jakarta.servlet.Filter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.FilterConfig;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.annotation.WebFilter;
// import jakarta.servlet.http.HttpFilter;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.io.PrintWriter;

// public class LoginFilter extends HttpFilter implements Filter {
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {
//                 HttpSession session = request.getSession(false);
//                 if(session==null||session.getAttribute("sessionId")==null){
//     //                 String path=request.getServletPath();
//     // request.setAttribute("bookingrequest",path);
//     RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//                 dispatcher.forward(request, response);


//                 }
//         System.out.println("LoggingFilter: Before servlet processing");

//         // Pass the request and response to the next filter or servlet in the chain
//         chain.doFilter(request, response);

//         // Postprocessing code
//         System.out.println("LoggingFilter: After servlet processing");
//     }

// }
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter extends HttpFilter implements Filter {
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     HttpServletRequest req=(HttpServletRequest)request;
HttpServletResponse res=(HttpServletResponse)response;
     HttpSession session = req.getSession(false);
                if(session==null||session.getAttribute("sessionId")==null){
    //                 String path=request.getServletPath();
    // request.setAttribute("bookingrequest",path);
    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, res);


                }
        System.out.println("LoggingFilter: Before servlet processing");

        // Pass the request and response to the next filter or servlet in the chain
        chain.doFilter(request, response);

        // Postprocessing code
        System.out.println("LoggingFilter: After servlet processing");
    }
    

}
