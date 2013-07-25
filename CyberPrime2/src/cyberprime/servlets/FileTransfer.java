package cyberprime.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

import sun.net.www.http.HttpClient;

import cyberprime.entities.Clients;
import cyberprime.entities.Notifications;
import cyberprime.entities.Sessions;
import cyberprime.entities.dao.NotificationsDAO;

@WebServlet("/FileTransfer")
public class FileTransfer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1024 * 1024 * 955; // 1gb size
	private File file;
	private String Id = null;
	private static final int BUFSIZE = 4096;
	private int length = 0;
	private HttpClient mHttpClient;
	private HTTPrequestListener mHTTPrequestListener = null;
	
	public interface HTTPrequestListener
    {
        public void downloadProgress(int iPercent);
    }
	
	public void setHTTPrequestListener(HTTPrequestListener httpRequestListener)
    {
        mHTTPrequestListener = httpRequestListener;
    }

	public void init() {
		// Get the file location where it would be stored.
		/*
		 * DOWNLOAD FROM C:\Users\Tan Wai Kit\Desktop\MAIN
		 * DESKTOP\workspace\.metadata\.plugins\org.eclipse.wst.server.core\
		 * tmp1\wtpwebapps\CyberPrime2
		 */
		filePath = getServletContext().getInitParameter("file-upload");
		// filePath4 = getServletContext().getRealPath("") + File.separator +
		// "";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		HttpSession session = request.getSession();
		Clients client = (Clients) session.getAttribute("c");

		java.io.PrintWriter out = response.getWriter();
		File repo = new File(filePath);
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

							if (Id.equalsIgnoreCase(sess.getClientId())) {
								// Get the uploaded file parameters

								Notifications n = new Notifications(
										client.getUserId(), sess.getClientId(),
										"FileTransfer");

								try {
									NotificationsDAO.createNotification(n);
									String fileName = item.getName();

									// Write the file
									if (fileName.lastIndexOf("\\") >= 0) {

										file = new File(filePath
												+ fileName.substring(fileName
														.lastIndexOf("\\")));
										length = request.getContentLength();
										System.out.println(length);
									} else {
										file = new File(filePath
												+ fileName.substring(fileName
														.lastIndexOf("\\") + 1));
										out.println("<p><strong>Thank You For Waiting</strong></p>");
										item.write(file);
										out.println("Uploaded Filename: "
												+ fileName + "<br>");
									}
									out.println("</body>");
									out.println("</html>");

								} catch (Exception ex) {
									out.print("<p><strong>No file found, please try again</strong></p>");
								}
							}

							else {

								if (Id.isEmpty()) {
									out.println("<p><strong>Please enter a username</strong></p>");
									out.println("</body>");
									out.println("</html>");
									return;
								}

								else if (length > maxFileSize) {

								}

								else {
									out.println("<p><strong>Please put a a valid ID</strong></p>");
									out.println("</body>");
									out.println("</html>");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// Check the content length to prevent denial of service attacks
			//length = 
			if (length > maxFileSize) {
				out.print("<p><strong>Posted content length of " + length
						+ " exceeds limit of " + maxFileSize + "by "
						+ (length - maxFileSize) + "</strong></p>");
				System.out.println("length is " + (length - maxFileSize)
						+ "bigger than " + maxFileSize);
			}
		}
	}

	public Object notifications() {
		Object postNotifications = null;

		return postNotifications;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		File file = new File(filePath);
		int length = 0;
		ServletOutputStream outStream = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		// change here
		String mimetype = context.getMimeType(filePath);

		// sets response content type
		if (mimetype == null) {
			mimetype = "application/octet-stream";
		}
		response.setContentType(mimetype);
		response.setContentLength((int) file.length());
		// remember to edit filepath
		String fileName = (new File(filePath)).getName();

		// sets HTTP header
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");

		byte[] byteBuffer = new byte[BUFSIZE];
		DataInputStream in = new DataInputStream(new FileInputStream(file));

		// reads the file's bytes and writes them to the response stream
		while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
			outStream.write(byteBuffer, 0, length);
		}

		in.close();
		file.delete();
		outStream.close();
	}
}