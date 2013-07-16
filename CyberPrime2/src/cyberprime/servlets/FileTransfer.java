package cyberprime.servlets;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.sun.security.ntlm.Client;

import cyberprime.entities.Clients;
import cyberprime.entities.Sessions;

@WebServlet("/FileTransfer")
public class FileTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1000000 * 1000;
	private File file;
	private String Id = null;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		HttpSession session = request.getSession();
		Clients client = (Clients) session.getAttribute("c");
		
		java.io.PrintWriter out = response.getWriter();
		File repo = new File("D:\\Temp\\files\\");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxFileSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(repo);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");

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

		else {

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("<style>");
			out.println("body {width:775px; height:570px; background-color:grey; color:white}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
		}

		try {

			if (repo != null) {

				RequestContext req = new ServletRequestContext(request);
				// Parse the request to get file items.
				List<FileItem> items = upload.parseRequest(req);
				// Process the uploaded file items
				Iterator<FileItem> iterator = items.iterator();

				Set sessions = (Set) getServletContext().getAttribute(
						"cyberprime.sessions");
				Iterator sessionIt = sessions.iterator();

				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						if (fieldName.equalsIgnoreCase("Id"))
							Id = item.getString();
					}

					else {

						while (sessionIt.hasNext()) {
							Sessions sess = (Sessions) sessionIt.next();
							if (!Id.equalsIgnoreCase(sess.getClientId()) || !sess.getClientId().equalsIgnoreCase(client.getUserId())) {
								out.println("<p>Sorry There, there is an error in the process. Please Try Again.</p>");
								break;
							}

							else {
								// Get the uploaded file parameters

								try{
									
								String fileName = item.getName();
								// Write the file
								if (fileName.lastIndexOf("\\") >= 0) {
									file = new File(filePath
											+ fileName.substring(fileName
													.lastIndexOf("\\")));
								} else {
									file = new File(filePath
											+ fileName.substring(fileName
													.lastIndexOf("\\") + 1));
								out.println("<p><strong>Thank You For Waiting</strong></p>");
								item.write(file);
								out.println("Uploaded Filename: " + fileName
										+ "<br>");
								}
								out.println("</body>");
								out.println("</html>");
								
								}catch(Exception ex){
									out.println("<p><strong>No file found, please try again</strong></p>");
								}

							}
						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object notifications() {
		Object postNotifications = null;

		return postNotifications;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
}