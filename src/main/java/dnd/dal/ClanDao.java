package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClanDao {
	
	private ClanDao() {}
	
	public static Clan create(Connection cxn, Races race, String clanName) throws SQLException {
		String insertRace = "INSERT INTO Clan (raceID, clanName) VALUES (?, ?);";
				
				try(PreparedStatement insertStmt = cxn.prepareStatement(insertRace, Statement.RETURN_GENERATED_KEYS)) {
					insertStmt.setInt(1, race.getRaceID());
					insertStmt.setString(2, clanName);
					
					insertStmt.executeUpdate();
					
					return new Clan(Utils.getAutoIncrementKey(insertStmt), race, clanName);
				}		
	}
	
	public static Clan getClanFromClanID(Connection cxn, int clanID) throws SQLException {
		String selectClan = """
				SELECT clanID, raceID, clanName
					FROM Clan
					WHERE clanID = ?;"""; 
					
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectClan)) {
			selectStmt.setInt(1, clanID);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				
				if (results.next()) {
					Races race = RacesDao.getRaceFromRaceID(cxn, results.getInt("raceID"));
					
					return new Clan(clanID, race, results.getString("clanName"));
					
				} else return null;
			}
 		}
	}

}
