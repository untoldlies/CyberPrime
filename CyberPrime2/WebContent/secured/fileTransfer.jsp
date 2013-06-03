 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
div.main{
		 width:775px;
	 	height:580px;
	 	background-color:grey;
		}
</style>
<title>File Upload</title>
</head>
<body>
	<div class="main">
		<center class="center">
		<h3>Please upload your files that you want to transfer to:</h3>
		<!-- action should change back to servletUpload -->
			<form action="fileTransferFinished.jsp" method="post"
				enctype="multipart/form-data">
				<input type="file" name="file" size="100" /> <br /> 
				<strong>Now please type in the person's ID that you want to transfer your file to:</strong>
				<input type="text" name="Id" class="calc-input" maxlength="15" onChange="valueChange('0');">
				</br><input type="submit" value="Upload File" />
			</form>
			
				
		</center>
	</div>
</body>
</html>
