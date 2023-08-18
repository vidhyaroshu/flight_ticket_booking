<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,DbOperations.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
			margin: 0px;
		}
		#header{
			width: 100%;
			height: 80px;
			background-color: #162e44;
			color: white;
			font-size: 18px;
			padding-top: 25px;
			box-sizing: border-box;
			position: fixed;
			top: 0px;
			z-index: 1000;
			
		}
		.lefthead{
			float: left;
			padding-left: 110px;
			font-size: 28px;
			font-family: Lucida Handwriting;

		}
		.righthead{
			float: right;
			width: 50%;
			padding-right: 80px;
			display: flex;
			justify-content: space-around;
			font-style: italic;

		}
		.name{
		color: whitesmoke;
	}
	.mail{
		display: none;
		background-color: #f5f5f536;
	    font-size: 15px;
	    display: none;
	    border-radius: 15px;
	    padding: 5px;
	}
	.container:hover>.mail{
		display: block;
	}
		.righthead span:not(.name):hover{
			border-top: 3px solid #01b3a7;

		}
		#login,#logout,#signup,#trips,#book,#home{
		border:none;
		background-color: transparent;
		color:white;
		font-size: 20px;
		font-family: Lucida Handwriting;
		font-style: italic;


	}
	.anchor{
		color: white;
		text-decoration:none;
	}

</style>
</head>
<body>
	<header>
	<div id="header">
		<div class="lefthead">
			<span>
				<a href="welcome.jsp" class="anchor">
				Travel Partner
			</a>
			</span>
		</div>
		<div class="righthead">
			<%  session=request.getSession(false);
if(session==null||session.getAttribute("sessionId")==null){%>
	<span><form action="login.jsp" method="get">
		<input type="submit" value="login" id="login">
	</form>
</span>
	<span><form action="signup.jsp" method="get">
		<input type="submit" value="signup" id="signup">
	</form></span>
	<span><form action="Searchservlet" method="get">
		<input type="submit" value="Book Tickets" id="book">
	</form></span>
	<span><form action="MyBookingsServlet" method="post">
			<input type="hidden" name="action" value="upcoming">
		<input type="submit" value="My Trips" id="trips">
	</form></span>
	<span><form action="welcome.jsp" method="get">
		<input type="submit" value="Home" id="home">
	</form></span>

<%
}%>

			
			

<% if(session.getAttribute("sessionId")!=null){
	String mailId=(String)session.getAttribute("userId");
	String name="";
	Utilities db=new Utilities();
		try{	
System.out.println(mailId+"maillllllll");
		 name=db.getUserName(mailId);
		}
	catch(Exception e){
	System.out.println("bye");	
	e.printStackTrace();
	}
	%>
	<div class="container">
		<span class="name">Hi...<%=name%></span>
		<div class="mail"><%=mailId%></div>
	</div>
	<span><form action="Searchservlet" method="get">
		<input type="submit" value="Book Tickets" id="book">
	</form></span>
	<span>
		<form action="MyBookingsServlet" method="post">
			<input type="hidden" name="action" value="upcoming">
		<input type="submit" value="My Trips" id="trips">
	</form></span>
	<span>
	<form action="user" method="post">
		<input type="hidden" name="action" value="logout">
		<input type="submit" value="logout" id="logout">
	</form>
</span>
<span><form action="welcome.jsp" method="get">
		<input type="submit" value="Home" id="home">
	</form></span>

<%
}
%>

		</div>
		
	</div>
</header>
</body>
</html>	