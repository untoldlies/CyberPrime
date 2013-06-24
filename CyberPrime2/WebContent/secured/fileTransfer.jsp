
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
<%
	File file;
	int maxFileSize = 5000 * 1024;
	int maxMemSize = 5000 * 1024;
	ServletContext context = pageContext.getServletContext();
	String filePath = context.getInitParameter("file-upload");

	//Verify the content Type
	String contentType = request.getContentType();
	if ((contentType.indexOf("multipart/form-data") >= 0)) {
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//Maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);

		//Location to save data that is larger than MaxMemSize.
		factory.setRepository(new File(
				"C:\\Users\\Tan Wai Kit\\Desktop\\FileUploadApps"));

		//Create a new file Upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		//maximum file size to be uploaded
		upload.setSizeMax(maxFileSize);
		try {

			//Parse the request to get file items
			List fileItems = upload.parseRequest(request);

			//Process the uploaded file items
			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>JSP File upload</title>");
			out.println("</head>");
			out.println("<body>");

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {

					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath
								+ fileName.substring(fileName
										.lastIndexOf("\\")));
					} else {
						file = new File(filePath
								+ fileName.substring(fileName
										.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					out.println("Uploaded Filename: " + filePath
							+ fileName + "<br>");
				}
			}
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	} else {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet upload</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>No file uploaded</p>");
		out.println("</body>");
		out.println("</html>");
	}
%>
	<div class="main">
		<center class="center">
			<h3>Please upload your files that you want to transfer to:</h3>
			<!-- action should change back to servletUpload -->
			<form action="UploadServlet" method="post"
				enctype="multipart/form-data">
				<input type="file" name="file" size="50" /> <br /> 
				</br> 
				</br> 
				</br> 
				</br> 
				</br> 
				</br> 
				<strong>Now please type in the person's ID that you want to transfer your file to:</strong> 
				<input type="text" name="Id" class="calc-input" maxlength="15"
					onChange="valueChange('0');"> </br> <input type="submit"
					value="Upload File" />
			</form>
		</center>
	</div>
</body>
</html>
