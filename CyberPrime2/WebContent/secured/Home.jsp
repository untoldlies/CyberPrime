<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
  <link rel="stylesheet" type="text/css" href="../css/home.css" />
  <script>
function changePage(source){
	document.getElementById('iframe').src = source;
}

</script>
</head>
<body>
<div id="menubar">
<a href="../template.jsp"><img src="../images/cplogo.png" alt="Cyber Prime"/ width="200" height="200"></a>
<ul id="menu">
<li><a class="tooltip"href="#"onclick="changePage('chat.jsp')"><img src="../images/ispjiconchat.png" alt="Chat" ><span></br>Chat</span></a></li>
<li><a class="tooltip"href="#"onclick="changePage('video.jsp')"><img src="../images/ispjiconvidstream.png" alt="Video" ><span></br>Video Streaming</span></a></li>
<li><a class="tooltip"href="#"onclick="changePage('fileTransfer.jsp')"><img src="../images/ispjiconfiletransfer.png" alt="File Transfer" ><span></br>File Transfer</span></a></li>
<li><a class="tooltip"href="#"onclick="changePage('conference.jsp')"><img src="../images/ispjiconvidconf.png" alt="Conference" ><span></br>Video Conference</span></a></li>
</ul> 
</div>
<div id="content">
<iframe scrollable=false id="iframe"src="" width="800" height="600" frameborder="0"></iframe>
</div>


<div id="rightMenu">
<a href="../template.jsp" title="Logout"><img id="logout" src="../images/cpicon.png" alt="logout" width="40" height="40"/></a>
<div id ="accInfo">
<p>ID:</p>
<div class="checkbox">
</br>
  		<input type="checkbox" value="1" id="checkboxInput" />
	  	<label for="checkboxInput"></label>
  	</div>


</div>
<div id="notifications">
Notifications here
</div>
</div>

</body>
</html>