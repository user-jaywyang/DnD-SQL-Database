package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConsumableDao {
	private ConsumableDao() {}
	
	
	public static Consumable create(Connection cxn, String itemName, int itemLevel, Float itemPrice, int itemMaxStackSize) throws SQLException{
		
		String insertConsumable = "INSERT INTO Consumable (consumableID) VALUES (?);";
		
		try (PreparedStatement insertStmt = cxn.prepareStatement(insertConsumable)) {
			int itemID = ItemPrototypeDao.create(cxn, itemName, itemLevel, itemPrice, itemMaxStackSize);
			
			insertStmt.setInt(1, itemID);
			insertStmt.executeUpdate();
			
			return new Consumable(itemID, itemName, itemLevel, itemPrice, itemMaxStackSize);
		}
	}
	
	public static Consumable getConsumableByPrototypeID(Connection cxn, int prototypeID) throws SQLException{
		String selectConsumable = """
				SELECT consumableID, itemName, itemLevel, itemPrice, itemMaxStackSize
					FROM Consumable INNER JOIN ItemPrototype ON ItemPrototype.prototypeID = Consumable.consumableID
					WHERE consumableID = ?;
				""";
		
		try(PreparedStatement selectStmt = cxn.prepareStatement(selectConsumable)) {
			selectStmt.setInt(1, prototypeID);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				if (results.next()) {
					return new Consumable(prototypeID,
							results.getString("itemName"),
							results.getInt("itemLevel"),
							results.getFloat("itemPrice"),
							results.getInt("itemMaxStackSize"));
				} else {
					return null;
				}
			}
		}
	}
	
	
	public static void delete(Connection cxn, Consumable consumable) throws SQLException{
		if (consumable == null) {
			return;
		}
		
		int consumableID = consumable.getPrototypeID();
		
		String deletePrototype = "DELETE FROM ItemPrototype WHERE prototypeID = ?;";
		
		try (PreparedStatement deleteStmt = cxn.prepareStatement(deletePrototype)) {	// should cascade deletion up to ItemPrototype table
			deleteStmt.setInt(1, consumableID);
			deleteStmt.executeUpdate();
		}
		
	}
	
	
	public static List<Consumable> getConsumablesByName(Connection cxn, String name) throws SQLException{
		List<Consumable> consumables = new ArrayList<>();
		
		String selectConsumable = """
				SELECT consumableID, itemName, itemLevel, itemPrice, itemMaxStackSize
					FROM Consumable INNER JOIN ItemPrototype ON ItemPrototype.prototypeID = Consumable.consumableID
					WHERE itemName = ?;
				""";
		
		try(PreparedStatement selectStmt = cxn.prepareStatement(selectConsumable)) {
			selectStmt.setString(1, name);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				while (results.next()) {
					consumables.add(new Consumable(results.getInt("consumableID"),
							name,
							results.getInt("itemLevel"),
							results.getFloat("itemPrice"),
							results.getInt("itemMaxStackSize")));
				}
			}
		}
		return consumables;
	}
	
	public static Consumable updateName(Connection cxn, Consumable consumable, String name) throws SQLException {
		
		// consumable name stored in itemPrototype table
		String updateConsumable = """
				UPDATE ItemPrototype SET itemName = ?
				WHERE prototypeID = ?;
				""";
		
		try (PreparedStatement updateStmt = cxn.prepareStatement(updateConsumable)) {
			updateStmt.setString(1, name);
			updateStmt.setInt(2, consumable.getPrototypeID());
			updateStmt.executeUpdate();
			
			consumable.setItemName(name);
			return consumable;
		}
	}

}
