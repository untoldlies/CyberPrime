package cyberprime.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import cyberprime.entities.Clients;
import cyberprime.entities.dao.ClientsDAO;
import cyberprime.util.ImageEncryption;
import cyberprime.util.ImageValidator;
import cyberprime.util.RandomString;



/**
 * Servlet implementation class Registration
 */
//@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String repos = "C:\\Users\\Tan Wai Kit\\Desktop\\CyberPrime\\CyberPrime2\\WebContent\\loginImages\\";
		Clients client = new Clients();
		ImageEncryption en = null;
		File repo = new File(repos);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String saveFileName = "";
			try{
			factory.setSizeThreshold(2*1024*1024);
			factory.setRepository(repo);
	//		if(repo != null)
	//		System.out.println("Repository :"+repo.getAbsolutePath());
			ServletFileUpload upload = new ServletFileUpload(factory);
			RequestContext req = new ServletRequestContext(request);
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> iterator =  items.iterator();
			
				while(iterator.hasNext()){
				FileItem item = iterator.next();
					if(item.isFormField()){
						String fieldName = item.getFieldName();
						if(fieldName.equalsIgnoreCase("email"))
						client.setEmail(item.getString());
					}
					
					else{
						String fileName = item.getName();
						saveFileName = repos + fileName;
						File uploadedFile = new File(saveFileName);
						
						if(!fileName.isEmpty()){
							
							ImageValidator iv = new ImageValidator();
							
							if(iv.validate(fileName)){
								try{
									item.write(uploadedFile);
								}catch(Exception e){
									Object obj = new Object();
									obj = "<p style='color:red'>*Access denied</p>";
									request.setAttribute("regResult", obj);
									request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
									return;
								}

								en = new ImageEncryption(uploadedFile.getAbsolutePath());

								client.setImageHash(en.getHash());
								client.setImageSize(en.getSize());
								client.setImageExtension(en.getExtension());
								
								session.setAttribute("image",fileName);
							}
							
							else{
								Object obj = new Object();
								obj = "<p style='color:red'>*Unauthorized file type or file name</p>";
								request.setAttribute("regResult", obj);
								request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
								return;
							}

						}

						else{
							Object obj = new Object();
							obj = "<p style='color:red'>*Please choose a file</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
							return;
						}


					}
				}
				response.reset();
				response.setHeader("Content-Disposition", "inline");
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Expires", "0");
				response.setContentType("image/jpg");
				
			}catch(FileUploadException e){
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				System.gc();
			}
				try{
					Clients c = null;
					c = ClientsDAO.retrieveClient(client);
					if(c.getImageHash()==null){
						String userId = RandomString.generateRandomString();
						client.setUserId(userId);
						boolean check = ClientsDAO.checkUser(client);
						boolean checkEmail = ClientsDAO.checkEmail(client);
						if(!check && !checkEmail){

								session.setAttribute("client", client);
								request.getRequestDispatcher("pattern.jsp").forward(request, response);	
	
						}
						
						else if(check && !checkEmail){
							Object obj = new Object();
							obj = "<p style='color:red'>*Registration failed as there is a similar userId.. Please register again</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						}
						
						else if(!check && checkEmail){
							Object obj = new Object();
							obj = "<p style='color:red'>*The email entered has registered with us</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						}
						
						else if(check && checkEmail){
							Object obj = new Object();
							obj = "<p style='color:red'>*The email entered and userId has registered with us</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						}
		

					}
					
					else{
						Object obj = new Object();
						obj = "<p style='color:red'>*Registration Failed as there is a similar image in our database</p>";
						request.setAttribute("regResult", obj);
						request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
					}	
					
					}catch(Exception e){
					e.printStackTrace();
				}
	}

}
