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

::-webkit-scrollbar {
      width: 15px;
} /* this targets the default scrollbar (compulsory) */

::-webkit-scrollbar-track {
      background-color: #b46868;
} /* the new scrollbar will have a flat appearance with the set background color */
::-webkit-scrollbar-thumb {
      background-color: rgba(0, 0, 0, 0.2);
} /* this will style the thumb, ignoring the track */
::-webkit-scrollbar-corner {
      background-color: #b46868;
} /* if both the vertical and the horizontal bars appear, then perhaps the right bottom corner also needs to be styled */
</style>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}\WebBrowsing">
		<input type="button" class="buttonBack">
		<input type="button" class="buttonForward">
		<input type="text" name="url" autocomplete="off"placeholder="Enter url of the page" value="${url}">
		<input type="button" class="buttonRefresh">
	</form>
	<br>

	<iframe id="iframe"width="775" height="520" src="http://${url}"></iframe>

	</body>
</html>
