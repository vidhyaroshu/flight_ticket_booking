<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*,AirlineBooking.*,Userdetails.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
    .mainbox{
       height: 650px;
    width: 100%;
    background-image: url(parallax-1-1920x850.jpg);
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    margin-top: 160px;
    position: relative;
    }
    .dropdown{
                    height: 16px;
    width: 16px;
            }
    .head{
            width: 100%;
            height: 80px;
            background-color: #162e56;
    color: white;
    font-size: 18px;
    text-align: center;
    font-family: cursive;
    box-sizing: border-box;
    position: fixed;
    top: 0px;
    z-index: 1000;
    margin-top: 80px;
    padding-top: 25px;
    box-sizing: border-box;
            
           
    }
    .flight{
        
     position: absolute;
    height: 274px;
    width: 80%;
    background-color: #ffffff8c;
    box-sizing: border-box;
    left: 120px;
    top: 66px;
    padding-left: 55px;
    padding-right: 55px;
    font-size: 20px;
    padding-top: 30px;
    box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
   
    }
    .box{
        display: flex;
    justify-content: space-between;
    }
    .div{
/*        height: 80%;*/
        width: 80%;
        background-color: white;
    }
    table {
  border-collapse: collapse;
    width: 80%;
    text-align: center;
    position: absolute;
    top: 340px;
    left: 118px;
    box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
}

th, td {
  padding: 8px;
/*  border: 1px solid #ddd;*/
}

th {
  background-color: #f2f2f2;
  font-weight: bold;
}
tbody{
    background-color: #f5deb3ad;
}
/*tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}*/
</style>
</head>
<body>
    <% FlightRoutes currentRoute = (FlightRoutes) request.getAttribute("route");
Ticket currentpnr = (Ticket) request.getAttribute("ticket");%>

    <div class="head">
        <span>YOUR BOOKING CONFIRMED!!!</span>
    </div>
    <div class="mainbox">
    <div class="flight">
        <div class="box">
        <div>
            <span><b>JetOz <%=currentRoute.flightId%></b></span>
        </div>
        <div>
            <span>
            <%=currentRoute.departureTime+","+currentpnr.departuredate%>
            </span><br>
            <span><%=currentRoute.departureAirport.city%></span><br>
            <span><%=currentRoute.departureAirport.country%></span><br>
            <span><b>Terminal no : </b><%=currentRoute.departureTerminalNo%></span>
        </div>
        <div>
            <span><%=currentRoute.duration%></span>
        </div>
        <div><span>
            <%=currentRoute.arrivalTime+","+currentpnr.departuredate%>
            </span><br>
            <span><%=currentRoute.destinationAirport.city%></span><br>
            <span><%=currentRoute.destinationAirport.country%></span><br>
            <span><b>Terminal no : </b><%=currentRoute.destinationTerminalNo%></span>
        </div>
    </div>
          <span><img src="ticket-solid.svg" class="dropdown"><%="  "+currentpnr.classtype%>  </span><br>
        <span><img src="tags-solid.svg" class="dropdown"><%="  "+currentpnr.faretype%></span><br>
        <span><img src="hand-holding-dollar-solid.svg" class="dropdown"><%="  "+currentpnr.cost  %></span><br>
        <span><b>Pnr : <%=currentpnr.pnr  %></span></b><br>
        <span><b> Booked by : <%=currentpnr.bookedBy.name%></b></span><br>
        <span><b>Transaction Id : <%=currentpnr.getTransactionId()%></b></span>
    </div>


<div class="table">
<table>
  <thead>
    <tr>
      <th>Traveller</th>
      <th>Gender</th>
      <th>     </th>
      <th>Seat</th>

    </tr>
  </thead>
  <tbody>
     <%int i = 0;
     for (Map.Entry<Integer, Passenger> pass : currentpnr.passengers.entrySet()) {%>
    <tr>
      <td><%=pass.getValue().name%></td>
      <td><%=pass.getValue().gender%></td>
      <td><%=pass.getValue().age%></td>
      <td><%=currentpnr.bookedseats[i]%></td>
    </tr>
    <%i++;}%>
  </tbody>
</table>
</div>
</div>
</body>
</html>