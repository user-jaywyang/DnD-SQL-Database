package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquippableItemDao {
	private EquippableItemDao() {}
	
	
	public static int create(Connection cxn, String itemName,
			int itemLevel, Float itemPrice, int maxStackSize, Integer requiredLevel) throws SQLException {
		
		String insertEquippable = """
				INSERT INTO EquippableItem (equippableItemID, requiredLevel) VALUES  (?, ?);
				""";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertEquippable)) {
			int itemID = ItemPrototypeDao.create(cxn, itemName, itemLevel, itemPrice, maxStackSize);	// create itemPrototype record in sql
			
			insertStmt.setInt(1, itemID);
			
			insertStmt.setInt(2, requiredLevel != null ? requiredLevel : EquippableItem.getDefaultRequiredLevel());
			insertStmt.executeUpdate();
			
			return itemID;
		}
		
	}
	
	
	// check if needed, implicit up-cast to equippableItem
	
	public static EquippableItem getEquippableItemFromProtoypeID(Connection cxn, int prototypeID) throws SQLException {
		Weapon weapon = WeaponDao.getWeaponFromWeaponID(cxn, prototypeID);
		if (weapon != null) {
			return weapon;
		}
		
		Gear gear = GearDao.getGearFromGearID(cxn, prototypeID);
		return gear;		// returns gear or null
	}
}
