package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EquippableBonusDao {
	private EquippableBonusDao() {}
	
	public static EquippableBonus create(Connection cxn, Statistic stat, EquippableItem equippableItem, int bonusValue) throws SQLException{
		String insertConsumableBonus = "INSERT INTO EquippableBonus (statisticID, equippableItemID, bonusValue) VALUES (?, ?, ?);";
		
		try (PreparedStatement insertStmt = cxn.prepareStatement(insertConsumableBonus)) {
			
			insertStmt.setInt(1, stat.getStatisticID());
			insertStmt.setInt(2, equippableItem.getPrototypeID());
			insertStmt.setInt(3, bonusValue);
			insertStmt.executeUpdate();
			
			return new EquippableBonus( stat, equippableItem, bonusValue);
		}
	}
	
	public static EquippableBonus getEquippableBonusFromStatANDPrototypeID(Connection cxn, Statistic stat,
			EquippableItem equippableItem) throws SQLException{
		
		String selectEB = """
				SELECT statisticID, equippableItemID, bonusValue
					FROM EquippableBonus
					WHERE statisticID = ? AND equippableItemID = ?;
				""";
		
		try(PreparedStatement selectStmt = cxn.prepareStatement(selectEB)) {
			selectStmt.setInt(1, stat.getStatisticID());
			selectStmt.setInt(2, equippableItem.getPrototypeID());
			
			try(ResultSet results = selectStmt.executeQuery()) {
				if (results.next()) {
					return new EquippableBonus(
							stat,
							equippableItem,
							results.getInt("bonusValue"));
				} else {
					return null;
				}
			}
		}
	}

}
