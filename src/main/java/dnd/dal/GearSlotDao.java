package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GearSlotDao {
	private GearSlotDao() {}
	
	public static GearSlot create(Connection cxn, String slotName) throws SQLException {
		String insertGearSlot = "INSERT INTO gearSlot (slotName) VALUES (?);";
		
		try(PreparedStatement insertStmt  = cxn.prepareStatement(insertGearSlot, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, slotName);
			insertStmt.executeUpdate();
			
			return new GearSlot(Utils.getAutoIncrementKey(insertStmt), slotName);
		}
	}
	
	
	public static GearSlot getGearSlotFromSlotID(Connection cxn, int slotID) throws SQLException {
		final String selectGearSlot = "Select slotID, slotName FROM gearSlot WHERE slotID = ?;";
		
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectGearSlot)) {
		      selectStmt.setInt(1, slotID);

		      try (ResultSet results = selectStmt.executeQuery()) {
		        
		        if (results.next()) {
		          return new GearSlot(slotID, results.getString("slotName"));
		        } else return null;
		      }
		}
	}
	
	

}
