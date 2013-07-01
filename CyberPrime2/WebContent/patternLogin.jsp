<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pattern Login</title>
<style>

div#border{
width:800px;
height:600px;
z-index:1;
}


div.height0{
position:relative;
top:0;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height1{
position:relative;
top:-600px;
left:100px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height2{
position:relative;
top:-1200px;
left:200px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height3{
position:relative;
top:-1800px;
left:300px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height4{
position:relative;
top:-2400px;
left:400px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height5{
position:relative;
top:-3000px;
left:500px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height6{
position:relative;
top:-3600px;
left:600px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}

div.height7{
position:relative;
top:-4200px;
left:700px;
border-style:solid;
border-color:black;
border-width: 1px 1px 1px 1px;
width:100px;
height:98px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:70px;
cursor:pointer;
}


div.height0:active{
background-color:lightgray;
}

div.height1:active{
background-color:lightgray;
}

div.height2:active{
background-color:lightgray;
}

div.height3:active{
background-color:lightgray;
}

div.height4:active{
background-color:lightgray;
}

div.height5:active{
background-color:lightgray;
}

div.height6:active{
background-color:lightgray;
}

div.height7:active{
background-color:lightgray;
}

img{
position:absolute;
top:7px;
left:7px;
z-index:-2;
}

div#form{
background-color:lightgray;
cursor:cursor;
position:absolute;
top:50px;
right:300px;
width:200px;
height:200px;
}
</style>

<script>

var pattern = 1;
var finalPattern = "";
function doSomething(obj){
	
	var div = document.getElementById(obj);
	div.innerHTML = pattern;
	pattern += 1;
	var input = document.getElementsByName('pattern')[0];
	finalPattern +=obj;
	input.value = finalPattern;
	div.style.zIndex = -1;

}

function resetPattern(){
	
	for(var i=0; i<8;i++){
		
		for(var j=0; j<6;j++){

			var string = i.toString()+j.toString();
			var div = document.getElementById(string);
			div.innerHTML = "";
			div.style.zIndex = 2;
			
		}
	}
	
	finalPattern = "";
	pattern = 1;
	var input = document.getElementsByName('pattern')[0];
	input.value = "";
}

</script>
</head>
<body>
<%@ page import ="cyberprime.entities.Clients" %>
<%
session = request.getSession();
Clients client = (Clients) session.getAttribute("client");
String image = (String)session.getAttribute("image");
%>

</body>
<div id="border">

<%for(int j=0; j<8; j++){ %>
<%for(int i=0; i<6;i++){
	%>
	<div class="height<%=j %>" id="<%=j%><%=i%>" onclick="doSomething('<%=j%><%=i%>');"></div>
	<%} %>
<% }%>
</div>
<img alt="<%=image %>" src="loginImages/<%=image%>"width="800" height="600">
<div id="form">
<form method ="post" action="${pageContext.request.contextPath}/Login">
Please input your pattern

<br>
<input type="hidden" name="pattern"/>
<input type="reset" value="reset" onclick="resetPattern();"/>
<input type="submit" value="login"/>
</form>
${loginResult}
</div>
</html>