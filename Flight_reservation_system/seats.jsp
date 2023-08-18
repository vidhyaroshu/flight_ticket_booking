<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
    .ticket{
     background-image: url(seats.jpg);
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
        .row{
               display: flex;
    justify-content: space-between;
    height: 60%;
    width: 69%;
        }
        .seats{
            position: absolute;
        }
        .booking{
           position: absolute;
    height: 108px;
    width: 100%;
    margin: auto;
    margin-left: 43px;
    background-color: #30111100;
    padding-top: 40px;
    padding-left: 30px;
    bottom: 415px;
    padding-right: 30px;
    box-sizing: border-box;
        }
        .button{
            height: 50px;
    width: 150px;
    border-radius: 10px;
    border: none;
    margin-top: 40px;
    margin-bottom: 30px;
    margin-left: 350px;
    background-color: #f5deb3bf;
    color: teal;
    font-size: 20px

        }
</style>
<script>
function validateCheckboxes(numSelected) {
  const checkboxes = document.querySelectorAll('input[type="checkbox"]');
  let numChecked = 0;

  for (let i = 0; i < checkboxes.length; i++) {
    if (checkboxes[i].checked) {
      numChecked++;
    }
  }

  if (numChecked > numSelected) {
    alert('only' + numSelected + ' seats should be selected.');
  }
  else if(numChecked<numSelected){
    alert('Invalid: Please select at least ' + numSelected + ' seats.');
  } 
  else{
    alert("proceed to pay");
  }
}
</script>
</head>
<body>
    <%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");%>
      <form action="payment.jsp" method="post">
        <div class="ticket"> 
<% String[][] seatsview = (String[][]) request.getAttribute("seats");
   int numSelectedSeats=0;%>
   <div class="booking">
<%for (int i = 0; i < 9; i++) {
    String print="";%>
    <div class="row">
        <%
    for (int j = 0; j < 5; j++) {
         print=seatsview[i][j];
         boolean isBooked=false;
         if(seatsview[i][j].endsWith("B")){
            isBooked=true;
            print=print.substring(0,print.length()-1);
         }
          boolean isSelected = request.getParameterValues("bookId") != null && Arrays.asList(request.getParameterValues("bookId")).contains(print);
   %>
   
    <label><input type="checkbox" name="bookId" value="<%= print %>" <% if (isBooked || (numSelectedSeats >= 3 && !isSelected)) { %>disabled<% } %><% if (isSelected) { %>checked<% numSelectedSeats++;} %>> <%= print %></label><br>
   

    <%}%>
</div>
<%}%>
<% if (numSelectedSeats >= 3) { %>
            <p>You have already selected the maximum of 3 seats.</p>
        <% }int adult=(int) session.getAttribute("adults");
    int child=(int) session.getAttribute("children");
int infants=(int) session.getAttribute("infants");
int total=adult+child+infants;%>
        <input type="submit" value="Book Seats" class="button" onclick="validateCheckboxes(${total})">
        <input type="hidden" name="seatsSelected" value="<%=total%>">

    </div>
    </div>
    </form>


 

</body>
</html>