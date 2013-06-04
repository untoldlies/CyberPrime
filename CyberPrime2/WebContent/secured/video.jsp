<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Browsing</title>
<style type="text/css">
body{
width:775px;
height:580px;
background-color:white;
}

input.buttonBack{
  	background-image: url(../images/back.png),url(images/back.png);
    background-color: transparent;
    background-repeat: no-repeat;
    background-position: 0px 0px;
    border: none;           
    cursor: pointer;        
    height: 35px;           
    padding-left: 35px;     
    vertical-align: middle; 
}

input.buttonForward{
  background-image: url(../images/forward.png),url(images/forward.png); 
    background-color: transparent; 
    background-repeat: no-repeat; 
    background-position: 0px 0px;
    border: none;          
    cursor: pointer;       
    height: 35px;     
    padding-left: 35px;     
    vertical-align: middle;
}

input.buttonRefresh{
  background-image: url(../images/refresh.jpg),url(images/refresh.jpg); 
    background-color: transparent; 
    background-repeat: no-repeat;  
    background-position: 0px 0px; 
    border: none;          
    cursor: pointer;       
    height: 35px;           
    padding-left: 35px;    
    vertical-align: middle;
}
</style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}\WebBrowsing">
		<input type="button" class="buttonBack">
		<input type="button" class="buttonForward">
		<input type="text" name="url" placeholder="Enter url of the page" value="${url}">
		<input type="button" class="buttonRefresh">
	</form>
	<br>

	<iframe id="iframe"width="700" height="500" src="http://${url}"></iframe>

	</body>
</html>
