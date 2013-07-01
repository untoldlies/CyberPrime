
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.io.output.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body {
	width: 775px;
	height: 570px;
	background-color: grey;
}
</style>
<title>File Upload</title>
</head>
<body>
<%@ page import ="cyberprime.entities.Clients" %>
<%
session = request.getSession();
Clients client = (Clients) session.getAttribute("c");
%>
	<div class="main">
		<center class="center">
			<h3>Please upload your files that you want to transfer to:</h3>
			<!-- action should change back to servletUpload -->
			<form action="${pageContext.request.contextPath}/FileTransfer" method="post"
				enctype="multipart/form-data">
					<strong>Now please type in the person's ID that you want to transfer your file to:</strong> 
				<input type="text" name="Id" class="calc-input" maxlength="15"
					onChange="valueChange('0');">

				</br> 
				</br> 
				</br> 
				</br> 
				</br> 
				</br> 
								<input type="file" name="file" size="10000000" /> <br /> 
 </br> <input type="submit"
					value="Upload File" />
			</form>
		</center>
	</div>
</body>
</html>
