package dnd.dal;

import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class ItemPrototypeDao {
	private ItemPrototypeDao() {}
	
	
	public static int create(Connection cxn, String itemName, int itemLevel, Float itemPrice, int itemMaxStackSize) throws SQLException {
		String insertItemPrototype = "INSERT INTO ItemPrototype (itemName, itemLevel, itemPrice, itemMaxStackSize) VALUES (?, ?, ?, ?);";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertItemPrototype, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, itemName);
			insertStmt.setInt(2, itemLevel);
			
			// for default val in sql
			insertStmt.setFloat(3, itemPrice != null ? itemPrice : ItemPrototype.getDefaultItemPrice());
			
			insertStmt.setInt(4, itemMaxStackSize);
			
			insertStmt.executeUpdate();
			
			return Utils.getAutoIncrementKey(insertStmt);
		}
	}
	
	
	// call consumableDao/EquippableItemDao methods
	public static ItemPrototype getItemFromPrototypeID(Connection cxn, int prototypeID) throws SQLException {
		Consumable consumable = ConsumableDao.getConsumableByPrototypeID(cxn, prototypeID);
		if (consumable != null) {
			return consumable;
		}
		
		return EquippableItemDao.getEquippableItemFromProtoypeID(cxn, prototypeID);
	}
}
