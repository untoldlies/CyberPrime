<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>

<script src="../js/newHome.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script> 
$(document).ready(function(){
  $("#notificationsTab").click(function(){
    $("#notifications").slideToggle("slow");
  });
  
  $('#userid').click(function(){
	 $('#users').slideToggle("slow"); 
  });
  
	$('.tabs').click(function(){
		$('.moving_bg').animate($(this).position(),'medium');
	});


});


function changePage(src) {
	
	var iframe = document.getElementById('iframe');
	iframe.src = src;
}
</script>
<link rel="stylesheet" type="text/css" href="../css/newHome.css"/>

</head>
<body>

<div id="tabMenu">

			<div class="moving_bg">
			&nbsp;
			</div>	
			
<ul class="tabsContent">
<li class="tabs" onclick="changePage('Home.jsp');">home</li>
<li class="tabs" onclick="changePage('video.jsp');">web browsing</li>
<li class="tabs" onclick="changePage('fileTransfer.jsp');">file transfer</li>
<li class="tabs" onclick="changePage('chat.jsp');">chat</li>
<li class="tabs" onclick="changePage('conference.jsp')">video conference</li>
</ul>

</div>

 	
 	<div id="userMenu">
<ul class="menu">
<li class="middlemenu" id="userid">UserId</li>
<li class="middlemenu anon">Anonymous Mode</li>
<li id="anon"class="anon"><div class="anonSwitch">
    <input type="checkbox" name="anonSwitch" class="anonSwitch-checkbox" id="anonswitch">
    <label class="anonSwitch-label" for="anonswitch">
        <div class="anonSwitch-inner"></div>
        <div class="anonSwitch-switch"></div>
    </label>
</div></li>
<li class="rightmenu">logout</li>
<li class="rightmenu" id="notificationsTab">Notifications</li>
</ul>
	<div id="users">
	<input type="text" id="username"><input type="button" id="add" value="add" onclick="addUser();"/>
	<div id="usernames">
	</div>
	</div>

	<div id = "notifications">
	<p><br><br>All notifications here</p>

	</div>

</div>

	<div id="content">
	<iframe src="" id ="iframe" scrolling="no" frameborder="0">Content here</iframe>
	
	</div>

</body>
</html>