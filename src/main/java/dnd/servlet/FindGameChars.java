package dnd.servlet;
import dnd.dal.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findgamechars")

public class FindGameChars extends HttpServlet{
	  private static final long serialVersionUID = 1L;
	  private static final String RESPONSE_MESSAGE = "response";

	  @Override
	  public void doGet(		// request & response
	    HttpServletRequest req,
	    HttpServletResponse resp
	  ) throws ServletException, IOException {
	    handleRequest(req, resp);
	  }
	  
	  @Override
	  public void doPost(
	    HttpServletRequest req,
	    HttpServletResponse resp
	  ) throws ServletException, IOException {
	    handleRequest(req, resp);
	  }
	  
	  private void handleRequest(
			    HttpServletRequest req,
			    HttpServletResponse resp
			  ) throws ServletException, IOException {

			    Map<String, String> messages = new HashMap<>();
			    req.setAttribute("messages", messages);

			    
			    String firstName = req.getParameter("firstName");
			    if (firstName == null || firstName.trim().isEmpty()) {
			      messages.put(RESPONSE_MESSAGE, "Please enter a valid first name.");
			    } else {
			      
			      try (Connection cxn = ConnectionManager.getConnection()) {
			        req.setAttribute(		
			          "gameChar",		
			          CharacterDao.getCharsByFirstName(cxn, firstName)		// query
			        );
			        messages.put(RESPONSE_MESSAGE, "Displaying results for " + firstName);
			      } catch (SQLException e) {	
			        e.printStackTrace();
			        throw new IOException(e);
			      }
			    }
			    
			    req.getRequestDispatcher("/FindGameChars.jsp").forward(req, resp);	
			  }
	
}