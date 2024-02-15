package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDao {
	private PlayerDao() {}
	
	public static Player create(Connection cxn, String fullName, String email) throws SQLException {
		String insertPlayer = "INSERT INTO Player (fullName, emailAddress) VALUES (?, ?);";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, fullName);
			insertStmt.setString(2, email);
			
			insertStmt.executeUpdate();
			
			return new Player(Utils.getAutoIncrementKey(insertStmt), fullName, email);
		}		
	}
	
	
	public static Player getPlayerFromPlayerID(Connection cxn, int id) throws SQLException {
		String selectPlayer = """
				SELECT playerID, fullName, emailAddress
					FROM Player
					WHERE playerID = ?;"""; 
					
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectPlayer)) {
			selectStmt.setInt(1, id);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				
				if (results.next()) {
					
					return new Player(id, results.getString("fullName"), results.getString("emailAddress"));
					
				} else return null;
			}
 		}
	}

}
