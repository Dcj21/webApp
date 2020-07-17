//Note: also uses ServletUtilities.java, 
package crud.admin;

import java.io.*;

//Note: ArrayList provides *dynamic* resizable-array (i.e., items can be added and removed from list), unlike simple Array 
import java.util.*; //example: ArrayList<SomeCollection>
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*; //need for servlet mapping below

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have no values or
 *  multiple values.
 *  <P>
 *  Part of tutorial on servlets and JSP that appears at
 *  http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/
 *  1999 Marty Hall; may be freely used or adapted.
 */

//servlet mapping for Servlet 3.0
@WebServlet("/testInput")
public class ShowParameters extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Reading All Request Parameters";
    out.println(ServletUtilities.headWithTitle(title) +
                "<body bgcolor=\"#fdf5e6\">\n" +
                "<h1 align='center'>" + title + "</h1>\n" +
                "<table border='1' align='center'>\n" +
                "<tr bgcolor=\"#ffad00\">\n" +
                "<th>Parameter Name<th>Parameter Value(s)");
    Enumeration paramNames = request.getParameterNames();
    while(paramNames.hasMoreElements())
			{
				String paramName = (String)paramNames.nextElement();
				out.println("<tr><td>" + paramName + "\n<td>");
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1)
					{
						String paramValue = paramValues[0];
						if (paramValue.length() == 0)
							out.print("<i>No Value</i>");
						else
							out.print(paramValue);
					}
				else
					{
						out.println("<ul>");
						for(int i=0; i<paramValues.length; i++) {
							out.println("<li>" + paramValues[i]);
						}
						out.println("</ul>");
					}
			}
    out.println("</table>\n</body></html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    doGet(request, response);
  }
}
