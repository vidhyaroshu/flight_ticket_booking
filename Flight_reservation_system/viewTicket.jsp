<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*,AirlineBooking.*,Userdetails.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
	body{
		background-color: rgb(225, 231, 238);
/*		            background-image: url("parallax-1-1920x850.jpg");
background-position: center;
             background-repeat: no-repeat;
             background-size: cover;*/
	}
	.box{
		height: 80%;
		width: 80%;
        background-color: white;
        box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: auto;
        margin-left: 115px;
        margin-top: 160px;
        padding-bottom: 25px;
        border-top: 10px solid #162e44;
        font-family: cursive;
/*        margin-bottom: 50px;*/
	}
	.box1{
		height: 80%;
		width: 80%;
        background-color: #ffffff73;
        box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: auto;
        margin-left: 115px;
/*        margin-top: 160px;*/
        padding-bottom: 25px;
            padding-left: 85px;
    padding-top: 40px;
    font-size: 20px;
    box-sizing: border-box;
/*        margin-bottom: 50px;*/
	}
	
	
	
	 .button{
   width: 210px;
    height: 50px;
    background-color: #162e44;
    border-radius: 10px;
    border: none;
    color: white;
    font-size: 21px;
    font-family: cursive;
    margin: 15px;
    }
    .first{
        margin-left: 50px;
    font-size: 20px;
    margin-top: 24px;
        margin-bottom: 20px;
    }
    .second{
     display: flex;
    justify-content: space-around;
    font-size: 25px;
    /* margin-left: -61px; */
    color: grey;
    margin-right: 221px;
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
    
    .date{
    	margin-left: 85px;
    font-size: 20px;
    margin-top: 20px;
    margin-bottom: 6px
    }
    .pnr{
        margin-left: 85px;
    font-size: 20px;
    margin-bottom: 6px
    }
    .passengerbox{
    	display: flex;
    	justify-content: space-between;
    	flex-direction: column;
        margin-top: 25px;
        margin-bottom: 25px;
    }
    .innerbox{
        display: flex;
        justify-content: space-between;
            padding-right: 400px;
    }
    .label{
                    font-size: 24px;
    font-weight: bold;
            }
            .passenger{
                    font-size: 30px;
    font-family: cursive;
    color: yellowgreen;
            }
            .passengerAlign{            
                display: flex;
    flex-direction: column;
            }
            .cancelbuttons{
                display: flex;
                justify-content: space-around;
                padding-right: 100px;
            }
</style>
<script>
function confirmCancel() {

    alert("Are you sure!you want to cancel");
  
}
</script>
</head>
<body>
	<% HashMap<String,Ticket> mybookingslist = (HashMap<String,Ticket>) request.getAttribute("ticket");
	String trip=(String) request.getAttribute("trip");
int i = 0;
String pnr="";
                                    for (Map.Entry<String, Ticket> ticks : mybookingslist.entrySet()) {
                                        i++;
                                        out.println("Your bookings!!!");
                                        out.println("*****************");

                                        Ticket mybooking = ticks.getValue();
                                        pnr=ticks.getKey();%>
                                        <div class="box">
                                        	<div class="first"><span class="firstspan label">JetOz <%=mybooking.flightId%></span>
                                        		<br>
                                        	</div>
       <div class="second"><span class="secondspan"><%= mybooking.from.city+" "+ mybooking.from.country%></span><span><%=mybooking.to.city+" "+mybooking.to.country%></span>
          <!-- <button type="submit" class="button">BOOK</button> -->
       </div>
       <div class="third"><span class="thirdspan"><%=mybooking.flight.departureTime%></span><span><%=mybooking.flight.duration%></span><span><%=mybooking.flight.arrivalTime%></span><br>
       
       </div>
       	<span class="date"><span class="label">Booked date</span> : <%=mybooking.bookeddate%></span>
        <span class="pnr"><span class="label">Pnr</span> : <%=ticks.getKey()%></span>
                                        </div>
                                        	<div class="box1">
                                        		<u><span class="passenger">Passenger Details</span></u>
                                        		
                                       <% /*out.println("passenger details : \n********************\n");*/
                                        int j = 0;
                                        for (Map.Entry<Integer, Passenger> pass : ticks.getValue().passengers
                                                .entrySet()) {          
                                                %>
                                                <div class="passengerbox">
                                                	<span><span class="label">Id</span> : <%=pass.getValue().passengerId%></span>
                                                    <div class="innerbox">
                                                    <div class="passengerAlign">
                                                	<span><span class="label">Name</span> : <%=pass.getValue().name%></span>
                                                	<span><span class="label">Gender</span> : <%=pass.getValue().gender%></span>
                                                </div>
                                                <div class="passengerAlign">
                                                	<span><span class="label">Seat no</span> : <%=mybooking.bookedseats[j]%></span>
                                                	<span><span class="label">Status</span> : <%String bookingStatus=pass.getValue().status.toLowerCase(); %><%=bookingStatus%></span>
                                                </div>
                                            </div>

                                                </div>                  
                                         <%   j++;
                                        }

                                   
                                            }%>
                                                <br><br>
                                                <%
                                                if(trip.equals("upcoming")){
                                            %>
                                            <div class="cancelbuttons">
	<form action="CancelServlet" method="post">
		<input type="hidden" name="cancel" value="full">
		<input type="hidden" name="pnr" value="<%=pnr%>">
		<input type="submit" value="Cancel all Tickets" class="button" onclick="confirmCancel()">
	</form>
	<form action="CancelServlet" method="post">
		<input type="hidden" name="cancel" value="partial">
		<input type="hidden" name="pnr" value="<%=pnr%>">
		<input type="submit" value="Partial Cancellation" class="button">
	</form>
</div>
	<%}%>
</div>
	<!-- <form action="Searchservlet" method="get">
		<input type="submit" value="Search flights">
	</form>
	<form action="MyBookingsServlet" method="post">
			<input type="hidden" name="action" value="upcoming">
		<input type="submit" value="My Trips">
	</form> -->

	
</body>
</html>