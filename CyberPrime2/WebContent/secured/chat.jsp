<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat</title>
</head>
<style>
textarea
{
    border:1px solid #999999;
    width:98%;
    margin:5px 0;
    padding:1%;
}

body{
    background-color:white;
}
#displayMessage{
    width:100%;
    height:350px;
    border-style:solid;
    border-width:1px;
    border-color:black;
}

</style>
<%@ page import ="cyberprime.entities.Clients" %>
<%
session = request.getSession();
Clients client = (Clients) session.getAttribute("c");
%>
<script>

function postMessage(user)  {
	
	var input = document.getElementById('inputMessage').value; // save the object
	var userName = user+" said:";
	var timestamp = new Date();
	var conMessage = userName + " " + input + " (" + timestamp + ")";
	var p = document.createElement('p');
	p.textContent = conMessage;
	var displayMessage = document.getElementById('displayMessage');
	displayMessage.appendChild(p);
	document.getElementById('inputMessage').value = "";
}

</script>

<body>
<div id="chat">
<h2>Chat</h2>
<div id="displayMessage" style="overflow-y:auto;">

</div>
<br/><br/>
<div id="post">

<textarea id="inputMessage" rows="2" cols="100" style="resize:none" placeholder="Enter your message here.">
</textarea>
<input id="postMessage" type="button" value="Post" onclick="postMessage('<%=client.getUserId() %>');">


</div>
</div>
</body>
</html>