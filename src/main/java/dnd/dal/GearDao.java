package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GearDao {
	private GearDao() {}
	
	
	public static Gear create(Connection cxn, String itemName,
			int itemLevel, Float itemPrice, int maxStackSize,
			int requiredLevel,  GearSlot slot) throws SQLException {
		
		String insertGear = """
				INSERT INTO Gear (gearID, slotID)
					VALUES (?, ?);""";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertGear)) {
			int gearID = EquippableItemDao.create(cxn, itemName, itemLevel, itemPrice, maxStackSize, requiredLevel);
			
			insertStmt.setInt(1, gearID);
			insertStmt.setInt(2, slot.getSlotID());
			
			insertStmt.executeUpdate();
			
			return new Gear(gearID, itemName, itemLevel, itemPrice, maxStackSize, requiredLevel, slot);
		}
		
	}
	
	public static Gear getGearFromGearID(Connection cxn, int gearID) throws SQLException {
		String selectGear = """
				SELECT gearID, itemName, itemLevel, itemPrice, itemMaxStackSize, requiredLevel, slotID
					FROM Gear
						INNER JOIN EquippableItem ON EquippableItem.equippableItemID = Gear.gearID
						INNER JOIN ItemPrototype ON ItemPrototype.prototypeID = Gear.gearID
					WHERE gearID = ?;
				""";
		
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectGear)) {
			selectStmt.setInt(1, gearID);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				if (results.next()) {
					GearSlot slot = GearSlotDao.getGearSlotFromSlotID(cxn, results.getInt("slotID"));
					return new Gear(
							gearID,
							results.getString("itemName"),
							results.getInt("itemLevel"),
							results.getFloat("itemPrice"),
							results.getInt("itemMaxStackSize"),
							results.getInt("requiredLevel"),
							slot);
				} else return null;
			}
		}
		
	}
}




