<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*"%>

<!DOCTYPE html>
<%  session=request.getSession(false);
if(session==null||session.getAttribute("sessionId")==null){
    response.sendRedirect("login.jsp");
}%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
     .ticket{
     background-image: url(istockphoto-1131788406-612x612.jpg);
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
    height: 85%;
    width: 40%;
    margin: auto;
    left: 325px;
    top: 54px;
    background-color: #9e9e9ea6;
        }
        h1{
                text-align: center;
    color: antiquewhite;
        }
        input{
          height: 42px;
    width: 265px;
    border-radius: 10px;
    border: none;
    margin: 8px;
    margin-left: 130px;
    background-color: #ffffff94;
        }
        button{
                height: 50px;
    width: 150px;
    border-radius: 10px;
    border: none;
    margin-top: 40px;
    margin-bottom: 30px;
    margin-left: 185px;
    background-color: #f5deb3bf;
    color: teal;
    font-size: 20px
        }
</style>
</head>
<body>

    <div class="ticket">
    <%
       String[] selectedSeats = request.getParameterValues("bookId");
int travellersCount=Integer.parseInt(request.getParameter("seatsSelected"));
if(selectedSeats!=null){
if(selectedSeats.length!=travellersCount){
response.sendRedirect("SeatsServlet");
}
else{
        session.setAttribute("bookedseat",selectedSeats);  
        }}
        else{response.sendRedirect("SeatsServlet");}
        %>           <div class="booking">
            <h1>
            PAYMENT</h1>
    <form action="PaymentServlet" method="post">
        
        
         <input type="text" name="cardnum" placeholder="Enter your Card number" required><br>
       <input type="text" name="holdername" placeholder="Enter card holder name" required pattern="[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" oninvalid="this.setCustomValidity('Please enter a valid name')" oninput="this.setCustomValidity('')"><br>
        <input type="date" name="expdate"required><br>
     <input type="text" name="cvv" placeholder="Enter your CVV" required pattern="^[0-9]{3,4}$" oninvalid="this.setCustomValidity('Please enter valid cvv')" oninput="this.setCustomValidity('')"><br>
        <input type="text" name="pin" placeholder="Enter your PIN" required><br>
    <button type="submit">PAY</button>
</div>
</div>
</form>
</body>
</html>