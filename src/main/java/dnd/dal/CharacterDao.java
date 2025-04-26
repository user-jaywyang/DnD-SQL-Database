/**
 * @author Jay Yang
 */

package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CharacterDao{
	private CharacterDao() {}
	
	public static GameCharacter create(Connection cxn,
			Player player,
			String firstName,
			String lastName,
			Clan clan,
			Job currentJob,
			Weapon currWeapon) throws SQLException {
		final String insertGameCharacter = """
			      INSERT INTO `Character` (playerID, firstName, lastName, clanID, currentJob, equippedWeapon )
		        VALUES (?, ?, ?, ?, ?, ?);""";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertGameCharacter, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setInt(1, player.getPlayerID());
			insertStmt.setString(2, firstName);
			insertStmt.setString(3, lastName);
			insertStmt.setInt(4, clan.getClanID());
			insertStmt.setInt(5, currentJob.getJobID());
			insertStmt.setInt(6, currWeapon.getPrototypeID());
			insertStmt.executeUpdate();
			
			return new GameCharacter(Utils.getAutoIncrementKey(insertStmt), player, firstName, lastName, clan, currentJob, currWeapon);
		}
	}
	
	public static GameCharacter getCharFromCharID(Connection cxn, int charID) throws SQLException {
		String selectChar = """
				SELECT characterID, playerID, firstName, lastName, clanID, currentJob, equippedWeapon
					FROM `Character`
					WHERE characterID = ?;""";
					
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectChar)) {
			selectStmt.setInt(1, charID);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				
				if (results.next()) {
					Player player = PlayerDao.getPlayerFromPlayerID(cxn, results.getInt("playerID"));
					Clan clan = ClanDao.getClanFromClanID(cxn, results.getInt("clanID"));
					Job currentJob = JobDao.getJobFromJobID(cxn, results.getInt("currentJob"));
					Weapon equippedWeapon = WeaponDao.getWeaponFromWeaponID(cxn, results.getInt("equippedWeapon"));
					
					return new GameCharacter(charID,
							player, results.getString("firstName"), 
							results.getString("lastName"),
							clan, currentJob, equippedWeapon);
					
				} else return null;
			}
 		}
		
	}

	public static List<GameCharacter> getCharsByFirstName(Connection cxn, String firstName) throws SQLException {
		List<GameCharacter> chars = new ArrayList<>();
		
		String selectChars = """
				SELECT characterID, playerID, firstName, lastName, clanID, currentJob, equippedWeapon
					FROM `Character`
					WHERE firstName LIKE ?
					ORDER BY firstName ASC;
				""";		// sort in alphabetical order
		
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectChars)) {
		      selectStmt.setString(1, "%" + firstName + "%");
		      try (ResultSet results = selectStmt.executeQuery()) {
		        while (results.next()) {
					Player player = PlayerDao.getPlayerFromPlayerID(cxn, results.getInt("playerID"));
					Clan clan = ClanDao.getClanFromClanID(cxn, results.getInt("clanID"));
					Job currentJob = JobDao.getJobFromJobID(cxn, results.getInt("currentJob"));
					Weapon equippedWeapon = WeaponDao.getWeaponFromWeaponID(cxn, results.getInt("equippedWeapon"));
					
					chars.add(new GameCharacter(results.getInt("characterID"), player, results.getString("firstName"), 
							results.getString("lastName"), clan, currentJob, equippedWeapon));
		        }
		      }
		    }
		
		return chars;
	}
	
	public static GameCharacter updateCharFirstName(Connection cxn, GameCharacter c, String newFirstName) throws SQLException{
		String updateChar = """
				UPDATE `Character` SET firstName = ?
				WHERE characterID = ?;
				""";
		
		try (PreparedStatement updateStmt = cxn.prepareStatement(updateChar)) {
			updateStmt.setString(1, newFirstName);
			updateStmt.setInt(2, c.getCharacterID());
			updateStmt.executeUpdate();
			
			c.setFirstName(newFirstName);
			return c;
		}
	}
}

