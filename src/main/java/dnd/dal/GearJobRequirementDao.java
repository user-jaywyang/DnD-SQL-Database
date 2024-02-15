package dnd.dal;
import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GearJobRequirementDao {
	private GearJobRequirementDao() {}
	
	
	public static GearJobRequirement create(Connection cxn, Gear gear, Job job) throws SQLException{
		String insertGJR = """
				INSERT INTO GearJobRequirement (gearID, jobID) VALUES (?, ?);
				""";
		
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertGJR)) {
			insertStmt.setInt(1, gear.getPrototypeID());
			insertStmt.setInt(2, job.getJobID());
			insertStmt.executeUpdate();
			
			return new GearJobRequirement(gear, job);
		}
	}
	
	public static GearJobRequirement getGearJobRequirementByGearANDJob(Connection cxn, Gear gear, Job job) throws SQLException{
		String selectGJR = """
				SELECT gearID, jobID
				FROM GearJobRequirement
				WHERE gearID = ? AND jobID = ?;
				""";
		
		try(PreparedStatement selectStmt = cxn.prepareStatement(selectGJR)) {
			selectStmt.setInt(1, gear.getPrototypeID());
			selectStmt.setInt(2,  job.getJobID());
			
			try(ResultSet results = selectStmt.executeQuery()) {
				if (results.next()) {
					return new GearJobRequirement(gear, job);
				} else {
					return null;
				}
			}
		}
	}
}
