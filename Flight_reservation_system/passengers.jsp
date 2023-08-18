<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*,java.time.LocalDate"%>

<!DOCTYPE html>
<% 
     session=request.getSession(false);
if(session==null||session.getAttribute("sessionId")==null){
        String path=request.getServletPath();
  int flightId = Integer.parseInt(request.getParameter("bookId"));
  session.setAttribute("bookid",flightId);
     request.setAttribute("bookingrequest",path);
    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
    .ticket{
     background-image: url(h_15392413.webp);
    width: 100%;
    height: 100%;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    margin-top: 61px;
    padding-top: 580px;
             box-sizing: border-box;
    padding-left: 60px;
    position: relative;
        }
        .booking{
        position: absolute;
            height: 100%;
            width: 90%;
/*            margin:auto;*/
            margin-left: 43px;
            background-color: #e0e0e08c;
            padding-top: 40px;
            padding-left: 30px;
            bottom: 190px;
            padding-right: 30px;
            box-sizing: border-box;
            top:35px;
        }
        .passengerbox{
            width:230px;
            display: flex;
            font-family: sans-serif;
            box-shadow: -7px 5px 25px 0px grey;
            border-radius: 10px;
            justify-content: space-between;
        }
        .select{
        height: 50px;
    width: 230px;
    margin-left: 95px;
    border-radius: 10px;
    background: #f5f5f5b5;
    border:none;
    margin-top: 20px;
        }
        .button{
            height: 50px;
    width: 150px;
    border-radius: 10px;
    border: none;
    margin-top: 40px;
    margin-bottom: 30px;

        }
</style>
</head>
<body>
     <div class="ticket">
            <div class="booking">
     <form action="PassengersServlet" method="post">
       
               <% System.out.println(request.getAttribute("pass")+"pass");
               if(request.getAttribute("pass")==null){
                int flightId = Integer.parseInt(request.getParameter("bookId"));
                session.setAttribute("bookid",flightId);
               }
int flightId = (int)session.getAttribute("bookid");
               System.out.println(flightId+"ooo");
        session.setAttribute("flightId",flightId);
        int adults=(int)session.getAttribute("adults");
         int children=(int)session.getAttribute("children");
          int infants=(int)session.getAttribute("infants");
          String airporttype=(String)session.getAttribute("airporttype");
          int travellers=adults+children+infants;
          int j=1;
         for (int i = 1; i <=adults; i++,j++) { 
         // request.setAttribute("age"+j,"adult");
         System.out.println(j+"jadult");%>
            <div class="passengerbox">
                <%if(i==1){%>
                <span>ADULT</span>
                <%}%>
                <div>
     <input type="text" name="name<%= j %>" placeholder="Name" class="select" required pattern="[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" oninvalid="this.setCustomValidity('Please enter a valid name')" oninput="this.setCustomValidity('')">
     <input type="hidden" name="age<%= j %>" value="adult">
     </div><br>
     <div>
        <select name="gender<%= j %>" class="select" required>
            <option value="female">Female</option>
            <option value="male">Male</option>
        </select>
    </div>
</div>
    <%
    if (airporttype.equalsIgnoreCase("INTERNATIONAL")) {%>
    <div class="passengerbox">
    <div>
        <input type="text" name="passportno<%=j%>" placeholder="Passport Number" class="select" required>
        <!-- pattern="/^[A-Z]\d{6,8}$/" oninvalid="this.setCustomValidity('Please enter a valid passport number')" oninput="this.setCustomValidity('')" egABC12345,A12345678 -->
    </div><br>
     <div>
        <input type="text" name="passportcountry<%=j%>" placeholder="Passport Issuing Country" class="select" required >
    </div><br>
    <!-- pattern="/^[A-Za-z\s-]+$/" oninvalid="this.setCustomValidity('Please enter a valid country name')" oninput="this.setCustomValidity('')" -->

    <%
     LocalDate today = LocalDate.now();
        LocalDate oneYearFromToday = today.plusYears(6);
        String min=today.toString();
        String max=oneYearFromToday.toString();
        %>
            <div>
            <input type="date" name="expirydate" min="<%=min%>" max="<%=max%>" id="selectt" class="select" required>
        </div><br>
        </div>
    
<%}
%>

      
        <% } %>


        <!-- //children -->
       <% for (int i = 1; i <=children; i++,j++) { 
         // request.setAttribute("age"+j,"children");
         System.out.println(j+"jchild");%>
            <div class="passengerbox">               <%if(i==1){%>
                <span>CHILDREN</span>
                <%}%>
                <div>
         <input type="text" name="name<%= j %>" placeholder="Name" class="select" required pattern="[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" oninvalid="this.setCustomValidity('Please enter a valid name')" oninput="this.setCustomValidity('')">
              <input type="hidden" name="age<%= j %>" value="children">

     </div><br>
     <div>
        <select name="gender<%= j %>" class="select" required>
            <option value="female">Female</option>
            <option value="male">Male</option>
        </select>
    </div>
</div>
    <%
    if (airporttype.equalsIgnoreCase("INTERNATIONAL")) {%>
    <div class="passengerbox">
    <div>
        <input type="text" name="passportno<%=j%>" placeholder="Passport Number" class="select" required>
        <!-- pattern="/^[A-Z]\d{6,8}$/" oninvalid="this.setCustomValidity('Please enter a valid passport number')" oninput="this.setCustomValidity('')" egABC12345,A12345678 -->
    </div><br>
     <div>
        <input type="text" name="passportcountry<%=j%>" placeholder="Passport Issuing Country" class="select" required >
    </div><br>
    <!-- pattern="/^[A-Za-z\s-]+$/" oninvalid="this.setCustomValidity('Please enter a valid country name')" oninput="this.setCustomValidity('')" -->

    <%
     LocalDate today = LocalDate.now();
        LocalDate oneYearFromToday = today.plusYears(6);
        String min=today.toString();
        String max=oneYearFromToday.toString();
        %>
            <div>
            <input type="date" name="expirydate" min="<%=min%>" max="<%=max%>" id="selectt" class="select" required>
        </div><br>
        </div>
    
<%}
%>

      
        <% } %>

        <!-- infant -->

       <% for (int i = 1; i <=infants; i++,j++) { 
        // request.setAttribute("age"+j,"infant");
        System.out.println(j+"jinfant");%>
            <div class="passengerbox">
                 <%if(i==1){%>
                <span>INFANT</span>
                <%}%> 
                <div>
         <input type="text" name="name<%= j %>" placeholder="Name" class="select" required pattern="[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" oninvalid="this.setCustomValidity('Please enter a valid name')" oninput="this.setCustomValidity('')">
              <input type="hidden" name="age<%= j %>" value="infant">

     </div><br>
     <div>
        <select name="gender<%= j %>" class="select" required>
            <option value="female">Female</option>
            <option value="male">Male</option>
        </select>
    </div>
</div>
    <%
    if (airporttype.equalsIgnoreCase("INTERNATIONAL")) {%>
    <div class="passengerbox">
    <div>
        <input type="text" name="passportno<%=j%>" placeholder="Passport Number" class="select" required>
        <!-- pattern="/^[A-Z]\d{6,8}$/" oninvalid="this.setCustomValidity('Please enter a valid passport number')" oninput="this.setCustomValidity('')" egABC12345,A12345678 -->
    </div><br>
     <div>
        <input type="text" name="passportcountry<%=j%>" placeholder="Passport Issuing Country" class="select" required >
    </div><br>
    <!-- pattern="/^[A-Za-z\s-]+$/" oninvalid="this.setCustomValidity('Please enter a valid country name')" oninput="this.setCustomValidity('')" -->

    <%
     LocalDate today = LocalDate.now();
        LocalDate oneYearFromToday = today.plusYears(6);
        String min=today.toString();
        String max=oneYearFromToday.toString();
        %>
            <div>
            <input type="date" name="expirydate" min="<%=min%>" max="<%=max%>" id="selectt" class="select" required>
        </div><br>
        </div>
    
<%}
%>

      
        <% } %>
    <center><button type="submit" class="button">Submit</button></center>

       
</form>
            </div>
        </div>
</body>
</html>
    