/**
 * @author Jay Yang
 */

package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EquippedGearDao {
	private EquippedGearDao() {}
	
	public static EquippedGear create(Connection cxn, GameCharacter character, GearSlot slot, Gear gear) throws SQLException {
		String insertEquippedGear = """
				INSERT INTO EquippedGear (characterID, slotID, gearID) VALUES (?, ?, ?);
				""";
		
		try (PreparedStatement insertStmt = cxn.prepareStatement(insertEquippedGear)) {
					
					insertStmt.setInt(1, character.getCharacterID());
					insertStmt.setInt(2, slot.getSlotID());
					insertStmt.setInt(3, gear.getPrototypeID());
					insertStmt.executeUpdate();
					
					return new EquippedGear(character, slot, gear);
				}

		
	}
	
	public static EquippedGear getEquippedGearFromCharacterGearSlot(Connection cxn, GameCharacter character, GearSlot slot) throws SQLException {
		String selectEB = """
				SELECT characterID, slotID, gearID
					FROM EquippedGear
					WHERE characterID = ? AND slotID = ?;
				""";
		
		try(PreparedStatement selectStmt = cxn.prepareStatement(selectEB)) {
			selectStmt.setInt(1, character.getCharacterID());
			selectStmt.setInt(2, slot.getSlotID());
			
			try(ResultSet results = selectStmt.executeQuery()) {
				if (results.next()) {
					Gear gear = GearDao.getGearFromGearID(cxn, results.getInt("gearID"));
					return new EquippedGear(character, slot, gear);
				} else {
					return null;
				}
			}
		}
	}

}
