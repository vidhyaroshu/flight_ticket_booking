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
     background-image: url(background-07-1920x900.jpg);
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
			height: 190px;
			width: 90%;
			margin:auto;
			margin-left: 43px;
			background-color: #e0e0e0;
			padding-top: 40px;
			padding-left: 30px;
			bottom: 190px;
			padding-right: 30px;
			box-sizing: border-box;
		}
		.booking1{
						display: flex;
									justify-content: space-between;


		}
		.search{
			height: 40px;
			width: 125px;
			margin-top: 15px;
			background-color: #01b3a7;
			border: none;
			border-radius: 5px;
		}
		.input{
			height: 30px;
			width: 137px;
			border: none;

		}
</style>
<script type="text/javascript">
	function getSum(){
	 let adult = parseInt(document.getElementById('adult').value);

	 let child = parseInt(document.getElementById('child').value);
	 let infant = parseInt(document.getElementById('infant').value);
	 let sum=adult+child+infant;

	 if(sum>9){
	 	alert('only 9 passengers are allowed to book at a time...');
	 }

}
function fromToCheck(){
	 let fromInput = document.getElementById('from').value;
  let toInput = document.getElementById('to').value;
  if(fromInput==toInput){
  	alert('from and to are same ...change from or to');
  }
}
 </script>
</head>
<body>
	<form action="FlightsServlet" method="get">
		
	<div class="ticket">
<div class="booking">
	<div class="booking1">

			<div><label for="from">Flying From:</label><br>
			<select type="text" name="from" id="from" class="input" required>
				<%
			ArrayList<String> places = (ArrayList<String>) request.getAttribute("places");
			for (String place : places) {
			%>
			    <option value="<%=place %>"><%= place %></option>
			<%
			}
			%>
			</select>
			</div>
			<div><label for="to">To:</label><br>
			<select type="text" name="to" id="to" class="input" onchange="fromToCheck()" required>
				<%
			places = (ArrayList<String>) request.getAttribute("places");
			for (String place : places) {
			%>
			<%if(place.equalsIgnoreCase("Bengaluru")){%>
			    <option value="<%=place %>" selected><%= place %></option>
			<%}
			else{%>
				 <option value="<%=place %>"><%= place %></option>
			<%}
			}
			%>
			</select>
			</div>
			<%
			String min=(String)request.getAttribute("min");
			String max=(String)request.getAttribute("max");
		%>
			<div><label for="depart">Departing:</label><br>
			<input type="date" name="departureDate" min="<%=min%>" max="<%=max%>" id="depart" class="input" required></div>
			<div><label for="adult">Adults:</label><br>
			<select type="text" name="adults" id="adult" class="input">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
				<option>7</option>
				<option>8</option>
				<option>9</option>
			</select>
			</div>
			<div><label for="child">Child:</label><br>
			<select type="number" name="children" id="child" class="input">
				<option>0</option>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
			</select>
			</div>
			<div><label for="child">Infant:</label><br>
			<select type="number" name="infants" id="infant" class="input">
				<option>0</option>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
			</select>
			</div>
		<input type="submit" value="search" class="search" onclick="getSum()">
		</div>
		<br>
		<div>
		<%
			Flightclass[] classtypes = (Flightclass[])request.getAttribute("classtype");
			for (Flightclass classtype : classtypes) {
				if(classtype==Flightclass.ECONOMY){
			%>
		 <input type="radio" name="classtype" value="<%=classtype %>" checked required><%= classtype %>
		 <%}
		 else{%>
		 <input type="radio" name="classtype" value="<%=classtype %>" required><%= classtype %>

		 	<%}
			}
			%><br>
			<br>
			<%
			Quota[] quotas = (Quota[])request.getAttribute("quota");
			for (Quota quota : quotas) {
				if(quota==Quota.REGULAR){
			%>
		 <input type="radio" name="quota" value="<%=quota %>" checked required><%= quota %>
		 <%}
		 else{%>
		 		 <input type="radio" name="quota" value="<%=quota %>" required><%= quota %>

		 <%}
			}
			%>
		</div>
		</div>
	</div>
	


</body>
</html>