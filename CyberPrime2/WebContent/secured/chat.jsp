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
    height:300px;
    border-style:solid;
    border-width:1px;
    border-color:black;
}

</style>
<script>

function postMessage()  {
	
	var input = document.getElementById('inputMessage').value; // save the object
	var p = document.createElement('p');
	p.textContent = input;
	var displayMessage = document.getElementById('displayMessage');
	displayMessage.appendChild(p);
	document.getElementById('inputMessage').value = "";
}

</script>
<body>
<br><br>
<div id="chat">
<div id="displayMessage">

</div>
<br/><br/>
<div id="post">

<textarea id="inputMessage" rows="2" cols="100" style="resize:none" placeholder="Enter your message here.">
</textarea>
<input id="postMessage" type="button" value="Post" onclick="postMessage();">


</div>
</div>
</body>
</html>