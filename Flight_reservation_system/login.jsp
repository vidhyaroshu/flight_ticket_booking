<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
// 	let emailInput = document.getElementById('email-input');
// let passwordInput = document.getElementById('password-input');

// emailInput.addEventListener('input', function(event) {
//   let email = event.target.value;
//   const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

//   if (emailRegex.test(email)) {
//     console.log('Email is valid');
//   } else {
//     console.log('Email is invalid');
//   }
// });

// passwordInput.addEventListener('input', function(event) {
//   let password = event.target.value;
//   const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

//   if (passwordRegex.test(password)) {
//     console.log('Password is valid');
//   } else {
//     console.log('Password is invalid');
//   }
// });


// const emailInput = document.getElementById('email-input');

// emailInput.addEventListener('input', function() {
//   const email = emailInput.value;
//   const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  
//   if (emailRegex.test(email)) {
//     emailInput.setCustomValidity(''); // Input is valid
//   } else {
//     emailInput.setCustomValidity('Please enter a valid email address'); // Input is invalid
//   }
// });
// const passwordInput = document.getElementById('password-input');

// passwordInput.addEventListener('input', function() {
//   const password = passwordInput.value;
//   const passwordRegix = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
  
//   if (passwordRegix.test(password)) {
//     passwordInput.setCustomValidity(''); // Input is valid
//   } else {
//     passwordInput.setCustomValidity('Please enter a valid password'); // Input is invalid
//   }
// });

</script>
<style type="text/css">
		body{
			background-color:#ffb6c145;
		}
		.main{
			height: 455px;
			width: 460px;
			margin: auto;
			margin-top: 160px;
			display: flex;
			font-family: sans-serif;
			box-shadow: -7px 5px 25px 0px grey;
			border-radius: 10px;

		}
		.left{
			width: 100%;
			height: 100%;
			background-color: white;
			padding-top: 30px;
			box-sizing: border-box;
			border-radius: 10px 10px 10px 10px;

		}
		/*.right{
			width: 50%;
			height: 100%;
			background: linear-gradient(to right,#ed4242,#f35587);
			padding-top: 120px;
			box-sizing: border-box;
			border-radius: 0px 10px 10px 0px;
		}*/
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
		}
		.forget{
			color: grey;
			font-size: 17px;
			margin-top: 15px;
		}
		.button{
			height: 36px;
			width: 135px;
			border-radius: 30px;
			background: linear-gradient(to right,#ed4242,#f35587);

			color: white;
			margin-left: 142px;
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
</head>
<body>
	<div class="main">
		<div class="left">
			<b><center class="login">
				Login
			</center></b><br><br>
			<center class="use">or use your account</center>
			<form action="user" method="post">
	<% 
	String ismybooking=(String)request.getAttribute("bookingrequest");

%>
	<input type="hidden" name="action" value="login">
	<input type="hidden" name="check" value="<%=ismybooking%>">
		 <input type="email" name="mailid" class="input" id="email-input" placeholder="Email" required pattern="[^\s@]+@[^\s@]+\.[^\s@]+"><br>
		 <input type="password" name="password" class="input" id="password-input" placeholder="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,}$" oninvalid="this.setCustomValidity('Please enter a strong password')" oninput="this.setCustomValidity('')">
		 <p id="password-validation-message"></p><br>
		 <center class="forget"><a href="signup.jsp">Create an account</a></center><br>
		<input type="submit" value="login" class="button">
	</form>
	
	<!-- <a href="signup.jsp">Create an account</a> -->
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

	
	<script>
  function openPopup() {
    const popupWindow = window.open("", "PopupWindow", "width=400,height=300");
    popupWindow.document.write("<h1>Hello, " + document.getElementById('email-input').value + "!</h1>");
  }
  </script>
</body>
</html>