<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
		margin: 0px;

	}
	.bgimg{
			background-image: url("slide-2-1920x776 (1).jpg");
			width: 100%;
			height: 625px;
			background-size: cover;
			background-repeat: no-repeat;
			color: white;
			
		}
		.bgtxt1{
			font-size: 23px;
			padding-top: 270px;
			padding-left: 100px;
			margin-bottom: 10px;

		}
		.bgtxt2{
			font-size: 80px;
			padding-left: 100px;
			margin-top: 0px;
			margin-bottom: 30px;
		}
		.getbutton{
			color: white;
			border: 2px solid white;
			background-color: transparent;
			height: 50px;
			width: 150px;
			margin-left: 100px;
			font-weight: bolder;
			
		}
		.getbutton:hover{
			background-color: #01b3a7;
		}
</style>
<jsp:include page="header.jsp"/>
</head>
<body>
	<div class="bgimg">
		<p class="bgtxt1">Enjoy the Best Destinations with Our Travel Agency</p>
		<p class="bgtxt2">Explore <span><b>The World</b></span></p>
		<form action="Searchservlet" method="get">
		<input type="submit" value="FIND FLIGHTS" class="getbutton">
	</form>
	</div>
</body>
</html>