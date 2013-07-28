package cyberprime.servlets;

import org.apache.commons.fileupload.*;

import cyberprime.util.TestProgressListener;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost (request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
		if(session == null) {
			out.println("Sorry, session is null");
			return;
		}
		
		TestProgressListener testProgressListener = (TestProgressListener) session.getAttribute("testProgressListener");
		if (testProgressListener == null){
			out.println("Progress listener is null");
			return;
		}
		
//		out.println(testProgressListener.getMessage());
		out.println(testProgressListener.getPercentDone());
	}

}
 