<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*,AirlineBooking.*,Userdetails.*"%>

<!DOCTYPE html>
<%  session=request.getSession(false);
if(session==null||session.getAttribute("sessionId")==null){
    String path=request.getServletPath();
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
     body{
        margin-top: 125px;
        background-color: rgb(225, 231, 238);
    }
    .flightsbox{
/*        margin-top: 80px;*/
/*        height: 200px;*/
        width: 80%;
        background-color: white;
        box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: auto;
        margin-left: 115px;
/*        margin-top: 115px;*/
        padding-bottom: 25px;
        margin-bottom: 50px;
    }
  /*  .ticket{
            background-image: url("background-07-1920x900.jpg");
            width: 100%;
            height:400px;
              background-position: center;
             background-repeat: no-repeat;
             background-size: cover;
             margin-top: 50px;
             padding-top: 90px;
             box-sizing: border-box;
             padding-left: 60px
        }*/
    .nav{
               display: flex;
    margin-left: 120px;
    margin-right: 36px;
    margin-top: 60px;

        }
        .navbutton{
            height: 53px;
            width: 150px;
            border: 1px solid lightgrey;
            background-color: #01b3a7;
            border-radius: 10px 10px 0px 0px }
            .dropdown{
                    height: 30px;
    width: 67px;
            }
            .head{
                     font-size: 19px;
    padding: 20px;
    font-family: cursive;
    padding-left: 24px;
            }
            .date{
                    padding-left: 71px;
            }
            .passenger{
                   font-size: 33px;
                    padding-left: 65px;
                    font-family: cursive;
                    color: yellowgreen;
            }
            .foot{
                font-size: 20px;
    padding-left: 65px;
        font-family: cursive;

            }
            button{
                float: right;
    height: 50px;
    width: 180px;
    font-size: 18px;
    margin-right: 101px;
    border-radius: 10px;
    background-color: #01b3a7;
        border: none;
    color: white;
            }
            .button{
                    border: none;
    background-color: transparent;
    margin-left: 19px;
    margin-top: 18px;
    color: white;
    font-size: 20px;
    font-weight: 700;
            }
            .label{
                    font-size: 24px;
    font-weight: bold;
            }
</style>
</head>
<body>
    <div class="ticket">
    <div class="nav">
            <form action="MyBookingsServlet" method="post" class="navbutton">
            <input type="hidden" name="bookingss" value="upcoming">
            <input type="hidden" name="action" value="null">
        <input type="submit" value="Upcoming" class="button">
    </form>
    <form action="MyBookingsServlet" method="post" class="navbutton">
            <input type="hidden" name="bookingss" value="cancelled">
            <input type="hidden" name="action" value="null">
        <input type="submit" value="Cancelled" class="button">
    </form>
    <form action="MyBookingsServlet" method="post" class="navbutton">
            <input type="hidden" name="bookingss" value="completed">
            <input type="hidden" name="action" value="null">
        <input type="submit" value="Completed" class="button">
    </form> 
        </div>
    </div>
   
<% HashMap<String,Ticket> mybookingslist = (HashMap<String,Ticket>) request.getAttribute("book");
    String trip=(String)request.getAttribute("tripOption");
    if(trip==null){
        trip="upcoming";
    }
    System.out.println(trip+"trip");

int i = 0;
                                    for (Map.Entry<String, Ticket> ticks : mybookingslist.entrySet()) {
                                        i++;
                                        // out.println("Your bookings!!!");
                                        // out.println("*****************");

                                        Ticket mybooking = ticks.getValue();%>
                                          <div class="flightsbox">
                                        <div class="head">
                                        <span><span class="label">Departure</span> : <%=mybooking.from.city+" "+mybooking.from.country%></span>
                                        <img src="arrow-right-solid.svg" class="dropdown">
                                         <span><span class="label">Destination</span> : <%=mybooking.to.city+" "+mybooking.to.country%></span>
                                     
                                         <span class="date"><span class="label">Departure Date</span> : <%=mybooking.departuredate%></span>
                                     </div>
                                        <u> <span class="passenger">Passengers</span></u><br>
                                                                              <%for (Map.Entry<Integer, Passenger> pass : ticks.getValue().passengers.entrySet()) { %>
                                               <span class="foot"><%=pass.getValue().name%></span><br>

                                
                                      <%  }%>
                                      <span class="foot"><span class="label">Booked by</span> : <span> <%=mybooking.bookedBy.name%></span></span><br>
                                      <span class="foot"><span class="label">Status </span>: <%String bookingStatus=mybooking.status.toLowerCase(); %><span><%=bookingStatus%></span></span>

                                        <br><br>
                     <form action="MyBookingsServlet" method="post">
                    <input type="hidden" name="pnr" value="<%=ticks.getKey() %>">
                    <input type="hidden" name="bookingss" value="view">
                     <input type="hidden" name="action" value="null">
                     <input type="hidden" name="trip" value="<%=trip%>">
                     <%
                     if("cancelled".equals(trip)||"completed".equals(trip)){%>
                    <button type="submit">View Tickets</button>

                    <% }
                     else{%>
                    <button type="submit">View and Manage Tickets</button>

                     <%} %>
                     </div>
                    </form><%
                                    }

   %>

</body>
</html>