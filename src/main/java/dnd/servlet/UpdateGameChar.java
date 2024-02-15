package dnd.servlet;
import dnd.model.*;
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

@WebServlet("/updategamechar")
public class UpdateGameChar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String RESPONSE_MESSAGE = "response";

	  @Override
	  public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	    handleRequest(req, resp);
	  }

	  @Override
	  public void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    handleRequest(req, resp);
	  }

	  private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<>();
	    req.setAttribute("messages", messages);
	    String charID = req.getParameter("characterID");
        if (charID == null || charID.trim().isEmpty()) {
            messages.put(RESPONSE_MESSAGE, "Missing character ID.");
        } else {
            try (Connection cxn = ConnectionManager.getConnection()) {
                int id = Integer.parseInt(charID);
                GameCharacter c = CharacterDao.getCharFromCharID(cxn, id);
                
                if (c == null) {
                    messages.put(RESPONSE_MESSAGE, "Character not found. No update to perform.");
                } else {
                	if ("post".equalsIgnoreCase(req.getMethod())) {
                		String newFirstName = req.getParameter("firstName");
                    	if (newFirstName == null || newFirstName.trim().isEmpty()) {
                    		messages.put(RESPONSE_MESSAGE, "Please enter a valid first name.");	
                    	} else {
                    		CharacterDao.updateCharFirstName(cxn, c, newFirstName);
                    		messages.put(RESPONSE_MESSAGE, "Successfully updated: " + newFirstName + " " + c.getLastName());
                    	} 
                	}
                	
                }
                req.setAttribute("character", c);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }  
            req.getRequestDispatcher("/UpdateGameChar.jsp").forward(req, resp);
        }
	  }
}
