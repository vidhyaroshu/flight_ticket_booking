<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
    body{
        background-color: rgb(225, 231, 238);
    }
    .flightsbox{
        margin-top: 80px;
        height: 200px;
        width: 80%;
        background-color: white;
        box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: auto;
        margin-left: 115px;
        margin-top: 115px;
        padding-bottom: 25px;
    }
    .button{
    width: 120px;
    height: 50px;
    background-color: #e94916;
    border-radius: 10px;
        border: none;
    color: white;
    font-size: 21px;
    font-family: cursive;
    }
    .first{
        margin-left: 50px;
    font-size: 20px;
    margin-top: 24px;
    }
    .second{
            display: flex;
    justify-content: space-around;
    font-size: 25px;
    margin-left: 33px;
    color: grey;
    }
    .third{
        display: flex;
    justify-content: space-evenly;
    font-size: 28px;
    margin-right: 90px;
    }
    .firstspan{
            font-size: 25px;
    margin-left: 33px
    }
    .secondspan{
      font-size: 25px;
    margin-right: 130px;
    }
    .thirdspan{
        margin-right: 70px;
    }

</style>
</head>
<body>
<% ArrayList<FlightRoutes> flights = (ArrayList<FlightRoutes>) request.getAttribute("flights");
// String ismybooking=(String)request.getAttribute("bookingrequest");
//     out.println(ismybooking+"poiiiiiiiiiiii");
int i=0;
   for (FlightRoutes flight : flights) {%>
   <form action="passengers.jsp" method="post">
    <input type="hidden" name="bookId" value="<%= flight.flightId %>">
    <%System.out.println(flight.flightId+"flightId");%>
   <div class="flightsbox">
       <div class="first"><span class="firstspan">JetOz <%=flight.flightId%></span></div>
       <div class="second"><span class="secondspan"><%=flight.departureAirport.city+" "+flight.departureAirport.country%></span><span><%=flight.destinationAirport.city+" "+flight.destinationAirport.country%></span>
          <button type="submit" class="button">BOOK</button>
       </div>
       <div class="third"><span class="thirdspan"><%=flight.departureTime%></span><span><%=flight.duration%></span><span><%=flight.arrivalTime%></span><span><%=flight.price%></span>
       </div>
   </div>
</form>
 
<% } %>

</body>
</html>