<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Airline.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<style type="text/css">
    .box1{
/*        height: 80%;*/
        width: 80%;
        background-color: #ffffff73;
        box-shadow: -7px 5px 25px 0px grey;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: auto;
        margin-left: 115px;
        margin-top: 160px;
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

  if (numChecked < numSelected) {
    alert('Invalid: Please select at least ' + numSelected + ' checkboxes.');
  } else {
    alert('confirm');
  }
}
</script>
</head>
<body>
      <form action="CancelServlet" method="post">
                    <input type="hidden" name="cancel" value="pass">
                    <div class="box1">
                        <span>Select passengers to CANCEL</span><br>
                    
<% 
// out.println("Select passengers to cancel");
HashMap<String,Integer> names = (HashMap<String,Integer>) request.getAttribute("names");
String pnr=(String)request.getAttribute("pnr");
%>
<input type="hidden" name="selected" value="<%=names.size()%>"><%
for(Map.Entry<String,Integer> namesStatus:names.entrySet()){
System.out.println(namesStatus.getKey()+"hi"+namesStatus.getValue());

if(namesStatus.getValue()==2){
System.out.println("true");%>
<span><label><input type="checkbox" name="passengers" value="<%= namesStatus.getKey()%>" disabled><%= namesStatus.getKey()%></label></span>
    <%}else{%>        <span><label><input type="checkbox" name="passengers" value="<%= namesStatus.getKey()%>"><%= namesStatus.getKey()%></label></span>
    <%}  }%>
            <input type="submit" value="Cancel" class="button" onclick="validateCheckboxes(1)">
           

</div>

    </form>
