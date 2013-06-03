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

</style>
<body>
<div id="top">
<form id="form1">
<input type="text" name="url" placeholder="Add users ID here.">
<input type="submit" name="form1" value="Add">
</form>
</div>
<br><br>
<div id="chat">
<textarea rows="20" cols="100" disabled="disabled" style="resize:none" placeholder="Messages will appear here.">
</textarea>
<br/><br/>
<div id="post">
<form id="form2">
<textarea rows="2" cols="100" style="resize:none" placeholder="Enter your message here.">
</textarea>
<input type="submit" name="form2" value="Post">
</form>
</div>
</div>
</body>
</html>