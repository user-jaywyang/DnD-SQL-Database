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


public class RacesDao {
	private RacesDao(){}
	
	public static Races create(Connection cxn, String raceName) throws SQLException {
		String insertRace = "INSERT INTO Races (raceName) VALUES (?);";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertRace, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, raceName);
			
			insertStmt.executeUpdate();
			
			return new Races(Utils.getAutoIncrementKey(insertStmt), raceName);
		}		
	}
	
	
	public static Races getRaceFromRaceID(Connection cxn, int raceID) throws SQLException {
		String selectRace = """
				SELECT raceID, raceName
					FROM Races
					WHERE raceID = ?;"""; 
					
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectRace)) {
			selectStmt.setInt(1, raceID);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				
				if (results.next()) {
					
					return new Races(raceID, results.getString("raceName"));
					
				} else return null;
			}
 		}
	}

}
