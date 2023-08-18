<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
		body{
			background-color:#ffb6c145;
		}
		*{
			color: white;
		}
		.main{
			height: 525px;
			width: 500px;
			margin: auto;
			margin-top: 120px;
			display: flex;
			font-family: sans-serif;
			box-shadow: -7px 5px 25px 0px grey;
			border-radius: 10px;
			color: white;

		}
		.left{
			width: 100%;
			height: 100%;
			background: linear-gradient(to right,#ed4242,#f35587);
			padding-top: 30px;
			box-sizing: border-box;
			border-radius: 10px 10px 10px 10px;

		}
		.right{
			width: 50%;
			height: 100%;
			background: linear-gradient(to right,#ed4242,#f35587);
			padding-top: 120px;
			box-sizing: border-box;
			border-radius: 0px 10px 10px 0px;
		}
		.login{
			font-size: 30px;
			font-weight: bolder;

		}
		.icon{
			display: flex;
			margin-left: 128px;
		}
		.circle{
			border-radius: 50%;
			border: 1px solid #80808063;
			height: 30px;
			width: 30px;
			margin-right: 10px;
		}
		.img{
			height: 23px;
			width: 23px;
			padding: 3px;
		}
		.use{
			color: grey;
			font-size: 14px;
		}
		.input{
			width: 75%;
			height: 35px;
			border: none;
			background-color: #d3d3d35e;
			margin-top: 15px;
			margin-left: 39px;
			border-radius: 50px;
		}
		::placeholder{
			padding-left: 10px;
			color: white;
		}
		.forget{
			color: white;
			font-size: 17px;
			margin-top: 15px;
			padding: 110px;
			padding-top: 30px;
		}
		.button{
			height: 36px;
			width: 135px;
			border-radius: 30px;
			background: linear-gradient(to right,#9E9E9E,#c54b72);
			margin-top: 35px;
			color: white;
			margin-left: 150px;
			border: none;
		}
		.head{
			color: white;
			font-size: 30px;
			font-weight: bolder;
		}
		.txt{
			color: white;
			font-size: 15px;
		}

	</style>
	<script type="text/javascript">

</script>
</head>
<body>
<div class="main">
		<div class="left">
			<b><center class="login">
				Sign Up
			</center></b><br><br>
			
			<center class="use">or use your account</center>
			<form action="user" method="post">
	<input type="hidden" name="action" value="signup">
		 <input type="text" name="name" class="input" id="name" placeholder="Name" required pattern="[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" oninvalid="this.setCustomValidity('Please enter a valid name')" oninput="this.setCustomValidity('')"><br>
		 <input type="text" name="mailid" class="input" id="email-input" placeholder="Email" required pattern="[^\s@]+@[^\s@]+\.[^\s@]+"><br>
		 <input type="password" name="password" class="input" placeholder="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,}$" oninvalid="this.setCustomValidity('Please enter a valid password')" oninput="this.setCustomValidity('')"><br>
		   <select name="gender" class="input" required>
            <option value="female">Female</option>
            <option value="male">Male</option>
        </select>
		 <br>
		 <input type="text" name="phone" class="input" placeholder="Phone" required pattern="^(\+\d{1,3}\s?)?\d{9,10}$"><br>
		 <br><br>
		 <a href="login.jsp" class="forget">Already have an account</a>
		<input type="submit" value="sign up" class="button">
	</form>
		
			<!-- <input type="email" name="email" class="input" placeholder="Email"><br>
			<input type="password" name="pass" class="input" placeholder="Password">
			<center class="forget">Forgot your password?</center><br>
			<button class="button">LOG IN</button> -->
		</div>
		<!-- <div class="right">
			<center>
				<span class="head">
					HTML CSS Login<br>Form
				</span><br><br>
				<span class="txt">
					This login form is created using pure<br> HTML and CSS.For social icons,<br>FontAwesome is used.
				</span>
			</center>
		</div> -->
	</div>



<!-- <form action="user" method="post">
	<input type="hidden" name="action" value="signup">
		Enter your Name: <input type="text" name="name"><br>
		Enter your Mail Id: <input type="text" name="mailid"><br>
		Enter your Password: <input type="password" name="password"><br>
		Enter your Gender: <input type="text" name="gender"><br>
		Enter your Phone number: <input type="text" name="phone"><br>
		<input type="submit" value="sign up">
	</form>
		<a href="login.jsp">Already have an account</a> -->
</body>
</html>