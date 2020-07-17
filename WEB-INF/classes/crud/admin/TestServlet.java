// save as "<TOMCAT_HOME>\webapps\lis4368\WEB-INF\classes\crud\admin\TestServlet.java"
/*
1. Compile:
Windows:
  cd to C:\tomcat\webapps\lis4368\WEB-INF\classes
  javac -cp .;c:\tomcat\lib\servlet-api.jar crud/admin/TestServlet.java

Mac: 	
  cd to /Applications/tomcat/webapps/lis4368/WEB-INF/classes
  javac -cp .:/Applications/tomcat/lib/servlet-api.jar crud/admin/TestServlet.java

2. Run: http://localhost:9999/lis4368/test
*/
package crud.admin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name="HelloTestServlet",urlPatterns={"/test"})
public class TestServlet extends HttpServlet
{
	//perform different request data processing depending on transfer method (here, Post or Get)	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html");        
		PrintWriter out = response.getWriter();        
		try
			{
				out.println("<h1>Servlet compiled, and outputs HTML...Awesome Job! :)</h1>");
			} 
		finally
			{
				out.close();
			}
	}
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		doPost(request, response);
	}    
}
