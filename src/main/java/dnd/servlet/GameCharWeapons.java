/**
 * @author Jay Yang
 */

package dnd.servlet;
import dnd.model.*;
import dnd.dal.*;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gamecharweapons")
public class GameCharWeapons extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String TITLE_MESSAGE = "title";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Weapon> weapons = new ArrayList<>();

        // retrieve/validate charID
        String charID = req.getParameter("characterID");
        if (charID == null || charID.trim().isEmpty()) {
            messages.put(TITLE_MESSAGE, "Missing character ID.");
        } else {
            try (Connection cxn = ConnectionManager.getConnection()) {
                int id = Integer.parseInt(charID);
                GameCharacter character = CharacterDao.getCharFromCharID(cxn, id);
                
                if (character == null) {
                    messages.put(TITLE_MESSAGE, "Character not found.");
                } else {
                    weapons = WeaponDao.getWeaponsByCharID(cxn, id);
                    req.setAttribute("character", character); 
                    messages.put(TITLE_MESSAGE, "Weapons for " + character.getFirstName() + " " + character.getLastName());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.setAttribute("weapons", weapons);
        req.getRequestDispatcher("/GameCharWeapons.jsp").forward(req, resp);
    }
}
