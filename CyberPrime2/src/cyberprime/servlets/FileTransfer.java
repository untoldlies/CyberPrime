package cyberprime.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.io.*;

import cyberprime.entities.*;
import cyberprime.entities.dao.*;
import cyberprime.util.TestProgressListener;

@WebServlet("/FileTransfer")
public class FileTransfer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1024 * 1024 * 50; // 50mb size
	private File file;
	private String Id = null;
	private static final int BUFSIZE = 4096;

	public void init() {
		/*
		 * DOWNLOAD FROM C:\Users\Tan Wai Kit\Desktop\MAIN
		 * DESKTOP\workspace\.metadata\.plugins\org.eclipse.wst.server.core\
		 * tmp1\wtpwebapps\CyberPrime2
		 */
		filePath = getServletContext().getInitParameter("file-upload");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		java.io.PrintWriter out = response.getWriter();
		
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

		TestProgressListener testProgressListener = new TestProgressListener();
		upload.setProgressListener(testProgressListener);

		HttpSession session = request.getSession();
		session.setAttribute("testProgressListener", testProgressListener);
		
		Clients client = (Clients) session.getAttribute("c");
		
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
						
						
						if (Id.isEmpty()) {
							out.println("<p><strong>Please enter a username.</strong></p>");
							out.println("</body>");
							out.println("</html>");
							return;
						}
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
									long sizeInBytes = item.getSize();
									String mimeType = item.getContentType();

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
										out.println("Uploaded Filename: "
												+ fileName + "<br>");
										out.println("<p>File Size: "  
												+ (sizeInBytes/(1000*1000)) + "mb " + "</p>");
									}
									out.println("</body>");
									out.println("</html>");
									
									Files files = new Files();
									files.setFileName(fileName);
									files.setFilePath(filePath + fileName);
									files.setLength((int)sizeInBytes);
									files.setMimeType(mimeType);
									
									
								} catch (Exception ex) {
									out.print("<p><strong>No file found, please try again.</strong></p>");
									out.println("</body>");
									out.println("</html>");
								}
							}

							else {

								out.println("<p><strong>Please put a a valid ID.</strong></p>");
								out.println("</body>");
								out.println("</html>");

							}
						}
					}
				}
			}
		} catch (FileUploadException e) {
			
			int length = 0;
			length = request.getContentLength();
			if (length > maxFileSize) {

				out.println("<p><strong>Posted content length of " + (length/(1000 * 1000)) + "mb "
						+ " exceeds limit of " + (maxFileSize/(1000*1000)) + "mb " + " by "
						+ ((length - maxFileSize)/(1000*1000)) + "mb " + "</strong></p>" + "<br/>");
				out.println("<p>Please try again.</p>");
				System.out.println("length is " + ((length - maxFileSize)/(1000*1000)) + "mb "
						+ " bigger than " + (maxFileSize/(1000*1000)) + "mb ");
				
				return;
				
			} else {
				e.printStackTrace();
			}
		}
	}

	public Object notifications() {
		Object postNotifications = null;

		return postNotifications;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
	
		try{
		file = new File(filePath + file.getName());
		int length = 0;
		ServletOutputStream outStream = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(filePath + file.getName());

		// sets response content type
		if (mimetype == null) {
			mimetype = "application/octet-stream";
		}
		response.setContentType(mimetype);
		response.setContentLength((int) file.length());

		String fileName = (new File(filePath + file.getName())).getName();

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
		outStream.flush();
		outStream.close();
		file.delete();
		
		} catch(IOException e){
			e.printStackTrace();
			System.out.println(e);
		}
		}

}