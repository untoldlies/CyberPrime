<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body{
background-color:gray;
}
#otherUsers
{
border:2px solid;
height:100px;
}
.navi {
	
	display:inline-block;
	background-color:white;
	border-radius:15px;
	position:relative;
	top:100px;
	/*margin:auto;*/
	font-family:"Lucida Calligraphy" Verdana;
}
}
.naviwords:hover {
	color:white;
}
.navi:hover{
	background-color:rgba(0,0,255,0.6);
	border-radius:15px;
	box-shadow:2px 2px 5px #3366CC;
}
.naviwords{
	text-decoration:none;
	padding: 5px;
	color:black;
/* 	font-size:1.5em; */
}
ul {
position:relative;
left:250px;
top: 300px;
}
</style>
</head>
<body>
<div id="otherUsers">
All the users appear here
</div>
<div id="yourself">
your video here
</div>
<ul>
					<li class="navi"><a class=naviwords href="">End Call</a>
	
					</li>
					<li class="navi">
						<form method="post" action="#">
						<input type="text" name="url" placeholder="Add user's ID here.">
						<input type="submit" class=naviwords value="Add">
						</form>
					</li>
					
</ul>

</body>
</html>