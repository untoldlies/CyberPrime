
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
@import "dojo-release-1.0.2/dijit/themes/tundra/tundra.css";

@import "dojo-release-1.0.2/dojo/resources/dojo.css" body
	{
	width:775px;
	height:570px;
	background-color:grey;
}
.center {
	padding: 5% 5% 5% 5%;
	border-color: black;
}
</style>
<script type="text/javascript" src="dojo-release-1.0.2/dojo/dojo.js"
	djConfig="parseOnLoad: true">
</script>

<script type="text/javascript">
	dojo.require("dijit.ProgressBar");

	function doProgress() {
		var button = new window.document.getElementById("submitButton");
		button.disabled = true;
		var max = 100;
		var prog = 0;
		var counter = 0;
		getProgress();
		doProgressLoop(prog, max, counter);
	}
	
	function doProgressLoop(prog, max, counter) {
		var x = dojo.byId('progress-content').innerHTML;
		var y = parseInt(x);
		if (!isNaN(y)) {
			prog = y;
		}
		jsProgress.update({
			maximum : max,
			progress : prog
		});
		counter = counter + 1;
		dojo.byId('counter').innerHTML = counter;
		if (prog < 100) {
			setTimeout("getProgress()", 500);
			setTimeout("doProgressLoop(" + prog + "," + max + "," + counter
					+ ")", 1000);
		}
	}

	function getProcess() {
		dojo
				.xhrGet({
					url : 'progress',
					load : function(data) {
						dojo.byId('progress-content').innerHTML = data;
					},
					error : function(data) {
						dojo.byId('progress-content').innerHTML = "Error retrieving progress";
					}
				});
	}
</script>

<title>File Upload</title>
</head>
<body>
	<%@ page import="cyberprime.entities.Clients"%>
	<%
		session = request.getSession();
		Clients client = (Clients) session.getAttribute("c");
	%>
	<div class="main">
		<center class="center">
			<h3>Please upload your files that you want to transfer to:</h3>
			<!-- action should change back to servletUpload -->
			<form action="${pageContext.request.contextPath}/FileTransfer"
				method="post" enctype="multipart/form-data">
				<strong>Now please type in the person's ID that you want to
					transfer your file to:</strong> <input type="text" name="Id"
					class="calc-input" maxlength="25" onChange="valueChange('0');">
				${ftResult} </br> </br> </br> </br> </br> </br> <input type="file" name="file" /> <br /> </br> <input
					type="submit" value="Upload File" />
			</form>
		</center>
	</div>
	<div class="tundra">
		Progress:
		<div dojoType="dijit.ProgressBar" style="width: 300px"
			jsId="jsProgress" id="downloadProgress"></div>
	</div>
	<br><br><br>
	<div style="visibility: visible">
	Content from Progress Servlet: <span id="progress-content">---</span><br/>
	Counter: <span id="counter">---</span><
	</div>
</body>
</html>
