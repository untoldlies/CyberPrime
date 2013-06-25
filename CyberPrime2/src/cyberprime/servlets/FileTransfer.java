package cyberprime.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.output.*;

@WebServlet("/FileTransfer")
public class FileTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1000000 * 1000;
	private int maxMemSize = 1000000 * 1000;
	private File file;
	private String Id;
	private String ID = "waikit";

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		File repo = new File("D:\\Temp\\");
		DiskFileItemFactory factory = new DiskFileItemFactory();

		try {
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxFileSize);

			// Location to save data that is larger than maxMemSize.
			factory.setRepository(repo);

			if (repo != null) {

				System.out.println("Repository: " + repo.getAbsolutePath());

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				// maximum file size to be uploaded.
				upload.setSizeMax(maxFileSize);

				RequestContext req = new ServletRequestContext(request);
				List<FileItem> items = upload.parseRequest(req);
				Iterator<FileItem> iterator = items.iterator();

				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						if (fieldName.equalsIgnoreCase("Id"))
							Id = item.getString();
						System.out.println(Id);
					}

					else {

						if (!Id.equalsIgnoreCase(ID)) {
							out.println("<p><strong>Sorry There Is No Such User, Please Try Again</strong></p>");
						}

						else {

							// Check that we have a file upload request
							isMultipart = ServletFileUpload
									.isMultipartContent(request);
							response.setContentType("text/html");
							java.io.PrintWriter out = response.getWriter();
							if (!isMultipart) {
								out.println("<html>");
								out.println("<head>");
								out.println("<title>Servlet upload</title>");
								out.println("</head>");
								out.println("<body>");
								out.println("<p><strong>Thank you for waiting</strong></p>");
								out.println("<p>No file uploaded</p>");
								out.println("</body>");
								out.println("</html>");
								return;
							}

							// Parse the request to get file items.
							List fileItems = upload.parseRequest(request);

							// Process the uploaded file items
							Iterator i = fileItems.iterator();

							out.println("<html>");
							out.println("<head>");
							out.println("<title>Servlet upload</title>");
							out.println("<style>");
							out.println("body {width:775px; height:570px; background-color:grey; color:white}");
							out.println("</style>");
							out.println("</head>");
							out.println("<body>");
							try {
								while (i.hasNext()) {
									FileItem fi = (FileItem) i.next();
									if (!fi.isFormField()) {
										// Get the uploaded file parameters
										String fieldName = fi.getFieldName();
										String fileName = fi.getName();
										String contentType = fi
												.getContentType();
										boolean isInMemory = fi.isInMemory();
										long sizeInBytes = fi.getSize();
										// Write the file
										if (fileName.lastIndexOf("\\") >= 0) {
											file = new File(
													filePath
															+ fileName
																	.substring(fileName
																			.lastIndexOf("\\")));
										} else {
											file = new File(
													filePath
															+ fileName
																	.substring(fileName
																			.lastIndexOf("\\") + 1));
										}
										out.println("<p><strong>Thank You For Waiting</strong></p>");
										fi.write(file);
										out.println("Uploaded Filename: "
												+ fileName + "<br>");
									}
								}
								out.println("</body>");
								out.println("</html>");
							} catch (Exception ex) {
								out.println("<p><strong>No File Found, Please Try Again</strong></p>");
							}
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
}