// save as "<TOMCAT_HOME>\webapps\lis4368\WEB-INF\classes\crud\admin\CustomerServlet.java"
/*
	1. Compile:
	NOTE: *YOUR* path will more than likely be *different*--depending upon where tomcat is installed!

	Windows:
  cd to C:\tomcat\webapps\lis4368\WEB-INF\classes
  javac -cp .;c:\tomcat\lib\servlet-api.jar crud/admin/CustomerServlet.java

	Mac: 	
  cd to /Applications/tomcat/webapps/lis4368/WEB-INF/classes
  javac -cp .:/Applications/tomcat/lib/servlet-api.jar crud/admin/CustomerServlet.java

	2. Run: http://localhost:9999/lis4368/customerAdmin
*/
//package statement indicates which directory this file exists, starting from "classes" directory
package crud.admin;

import java.io.*; //input/output
//Note: ArrayList provides *dynamic* resizable-array (i.e., items can be added and removed from list), unlike simple Array (fixed-length)
import java.util.*; //ArrayList<SomeCollection>, Enumeration
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*; 

//import Customer and CustomerDB classes
import crud.business.Customer;
import crud.data.CustomerDB;

//servlet mapping for Servlet 3.0
//servlet Customer is mapped to the URL pattern /customerAdmin
//When accessing this servlet, it will return a message.
@WebServlet("/customerAdmin")
public class CustomerServlet extends HttpServlet
{
	//perform different request data processing depending on transfer method (here, Post or Get)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//initialize global variables:
		//if no current session, returns new session (required!)
		HttpSession session = request.getSession();

		//initialize url variable - used below to direct user to appropriate page	
		String url = "/index.jsp";
		String message = ""; //initialize message variable				
		String action = null; //initialize action
		String cid = null; //initialize customer id, and used to add customer (auto-increment PK)
		action = request.getParameter("action"); // get current action

		//get user action
		//from customers.jsp
		//get id from hidden form field name property: modify_customer (Edit button customers.jsp)
		if (request.getParameter("modify_customer") != null) 
			{
				action = "modify_customer";
				cid = request.getParameter(action); 
			}
		//from customers.jsp
		//get id from hidden form field name property: delete_customer (Delete button customers.jsp)
		else if (request.getParameter("delete_customer") != null) 
			{
				action = "delete_customer";
				cid = request.getParameter(action); 
			}
		//from modify.jsp
		//get id from hidden form field update_customer name property
		else if (request.getParameter("update_customer") != null) 
			{
				action = "update_customer";
				cid = request.getParameter(action);						
			}
		else
			{
				url = "/index.jsp"; //if none above, pass user to main page
			}

		// perform action, based upon request, and set URL to appropriate page
		//first test: if action variable contains no value, or "join" (from thanks.jsp), send to customerform.jsp
		//Note: if you invoke .equals() on null will return NullPointerException
		//Solution: Always advisble to check null before invoking .equals() method
		if (action == null || action.equals("join"))
			{
				url = "/customerform.jsp";   //"join" page						
			}
		//from customers.jsp
		else if (action.equals("modify_customer"))
			{
				Customer user = CustomerDB.selectCustomer(cid);
				session.setAttribute("user", user); //Pass data from servlet to jsp using session and setAttribute("name","value") method
				url = "/modify.jsp"; //pass user to modify.jsp, and display data
			}
		//from customerform.jsp
		else if (action.equals("display_customers"))
			{
				ArrayList<Customer> users = CustomerDB.selectCustomers();            
				request.setAttribute("users", users);
				url = "/customers.jsp"; //pass user to customers.jsp, and display all values
			}
		//from customers.jsp
		else if (action.equals("add_customer"))
			{
				// get parameters from the request (data conversions not required here)
				//zip should be int, phone long, balance and totalSales BigDecimal data types
				//getParameter() method accepts values from form control *name* attribute
				//Note: getParameter() is pulling user-entered values from "name" attribute of submitted form
				String id_v = request.getParameter(null); //null used for auto increment pk field
				String firstName_v = request.getParameter("fname");
				String lastName_v = request.getParameter("lname");
				String street_v = request.getParameter("street");
				String city_v = request.getParameter("city");
				String state_v = request.getParameter("state");
				String zip_v = request.getParameter("zip");
				String phone_v = request.getParameter("phone");
				String email_v = request.getParameter("email");
				String balance_v = request.getParameter("balance");
				String totalSales_v = request.getParameter("total_sales");
				String notes_v = request.getParameter("notes");

				//instantiate new customer object with associated object variable (user)
				Customer user = new Customer(id_v, firstName_v, lastName_v, street_v, city_v, state_v, zip_v, phone_v, email_v, balance_v, totalSales_v, notes_v);

				//call data input validation method: *Must* agree in TON: type, order, number!
				if(!isValidInput(firstName_v, lastName_v, street_v, city_v, state_v, zip_v, phone_v, email_v, balance_v, totalSales_v, notes_v))
					{
						message = "<span style='color: red; background-color: yellow; font-weight: bold; font-variant:small-caps;'>All text boxes required except Notes.</span>";
						url = "/customerform.jsp";
					}
				else
					{
						//add customer
						CustomerDB.insert(user);
						message = ""; //reset message variable to empty string
						url = "/thanks.jsp"; //pass user to thanks.jsp page
					}
				request.setAttribute("user", user);
				request.setAttribute("message", message);
			}
		else if (action.equals("update_customer"))
			{
				// get parameters from the request
				String firstName_v = request.getParameter("fname");
				String lastName_v = request.getParameter("lname");
				String street_v = request.getParameter("street");
				String city_v = request.getParameter("city");
				String state_v = request.getParameter("state");
				String zip_v = request.getParameter("zip");
				String phone_v = request.getParameter("phone");
				String email_v = request.getParameter("email");
				String balance_v = request.getParameter("balance");
				String totalSales_v = request.getParameter("total_sales");
				String notes_v = request.getParameter("notes");
				
				//call data input validation method: *Must* agree in TON: type, order, number!
				if(!isValidInput(firstName_v, lastName_v, street_v, city_v, state_v, zip_v, phone_v, email_v, balance_v, totalSales_v, notes_v))
					{
						url = "/modify.jsp";
					}
				else
					{
						// get and update customer
						Customer user = (Customer) session.getAttribute("user");        

						user.setId(cid); //cid retrieved from request.getParameter(action);
						user.setFname(firstName_v);
						user.setLname(lastName_v);
						user.setStreet(street_v);
						user.setCity(city_v);
						user.setState(state_v);
						user.setZip(zip_v);
						user.setPhone(phone_v);
						user.setEmail(email_v);
						user.setBalance(balance_v);
						user.setTotalSales(totalSales_v);
						user.setNotes(notes_v);

						// update customer				 
						CustomerDB.update(user);
						
						// display updated customers
						ArrayList<Customer> users = CustomerDB.selectCustomers();            
						request.setAttribute("users", users);
						url = "/customers.jsp"; //pass user to customers.jsp page
					}				
			}
		else if (action.equals("delete_customer"))
			{
				// get customer
				Customer user = CustomerDB.selectCustomer(cid);

				// delete customer
				CustomerDB.delete(user);
            
				// display remaining customers
				ArrayList<Customer> users = CustomerDB.selectCustomers();            
				request.setAttribute("users", users);

				url = "/customers.jsp"; //pass user to customers.jsp page
			}
		//forward (not redirect), can provide additional security, as user does not see actual URL
		getServletContext()
			.getRequestDispatcher(url)
			.forward(request, response);
				
		//sometimes, better to redirect than forward (e.g., insert/update/delete or to see correct URL)
		response.sendRedirect(url);
		response.sendRedirect("index.jsp"); //or literal value
	}    

	//for all get submissions	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		doPost(request, response);
	}

	//validate input: return true or false
	//here: check *only* for data entry
	//empty string: string with zero length.
	//null value: is unknown value--not having a string.

	//must check for both:
	//1) null (no object created/no value), and...
	//2) empty string ("" is an actual string object)
	
	//Note: in production environment need rigorous data validation:
	//http://java-source.net/open-source/validation
	
	//create data input validation method: *Must* agree in TON: type, order, number!
	private boolean isValidInput(String firstName_p, String lastName_p, String street_p, String city_p, String state_p, String zip_p, String phone_p, String email_p, String balance_p, String totalSales_p, String notes_p)
	{
		if (
				firstName_p == null ||
				lastName_p == null ||
				street_p == null ||
				city_p == null ||
				state_p == null ||
				zip_p == null ||
				phone_p == null ||
				email_p == null ||
				balance_p == null ||
				totalSales_p == null ||
								
				firstName_p.isEmpty() ||
				lastName_p.isEmpty() ||
				street_p.isEmpty() ||
				city_p.isEmpty() ||
				state_p.isEmpty() ||
				zip_p.isEmpty() ||
				phone_p.isEmpty() ||
				email_p.isEmpty() ||
				balance_p.isEmpty() ||
				totalSales_p.isEmpty()

				)
			{
				return false; //missing form data
			} 
		else
			{
				return true; //data not missing
			}
	}	
}
