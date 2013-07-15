<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>

<head>
  <title>template</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
  <style>

div#border{
width:600px;
height:400px;
z-index:1;
}


div.height0{
position:relative;
top:0;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height1{
position:relative;
top:-402px;
left:75px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height2{
position:relative;
top:-804px;
left:150px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height3{
position:relative;
top:-1206px;
left:225px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height4{
position:relative;
top:-1608px;
left:300px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height5{
position:relative;
top:-2010px;
left:375px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height6{
position:relative;
top:-2412px;
left:450px;
border-style:solid;
border-color:black;
border-width: 1px 0 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
cursor:pointer;
}

div.height7{
position:relative;
top:-2814px;
left:525px;
border-style:solid;
border-color:black;
border-width: 1px 1px 1px 1px;
width:75px;
height:65px;
color:#00FF00;
text-align:center;
font-weight:bold;
font-size:60px;
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

#img{
position:absolute;
top:315px;
left:200;
z-index:-2;
}

div#form{
cursor:cursor;
position:relative;
top:50px;
width:400px;
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
  <div id="main">
    <header>
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="template.jsp">cyber<span class="logo_colour">_Prime</span></a></h1>
          <h2>Secure, anonymous collaboration.</h2>
        </div>
      </div>
      <nav>
        <div id="menu_container">
          <ul class="sf-menu" id="nav">
            <li><a href="template.jsp">Home</a></li>
            <li><a href="templateFAQ.jsp">FAQ</a></li>
            <li><a href="templateRegister.jsp">Register</a></li>
			<li><a href="templateLogin.jsp">Login</a></li>
            <li><a href="templateFAQ.jsp#service">Services</a></li>
           <li><a href="templateAbout.jsp">About Us</a></li>
          </ul>
        </div>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
          <h3>Introduction</h3>
          <h4>Welcome!</h4>
          <h5>May 9th, 2013</h5>
          <p>Welcome to Cyber Prime. If it's your first time here, please register with us to use our services.
		  Otherwise, please login.</p>
        </div>
        <div class="sidebar">
          <h3>Logo</h3>
          <p><img src="images/cplogosmall.png"></p>
        </div>
      </div>
      <div class="content">

<div id="border">

<%for(int j=0; j<8; j++){ %>
<%for(int i=0; i<6;i++){
	%>
	<div class="height<%=j %>" id="<%=j%><%=i%>" onclick="doSomething('<%=j%><%=i%>');"></div>
	<%} %>
<% }%>

</div>
<img alt="<%=image%>" id="img" src="loginImages/<%=image%>"width="600" height="400">
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
      </div>
    </div>
    <footer>
      <p>Cyber Prime | Created May 2013 | Secure, anonymous collaboration.</a></p>
    </footer>
  </div>
  <p>&nbsp;</p>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
  <script type="text/javascript" src="js/jquery.sooperfish.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('ul.sf-menu').sooperfish();
    });
  </script>
</body>
</html>

</html>