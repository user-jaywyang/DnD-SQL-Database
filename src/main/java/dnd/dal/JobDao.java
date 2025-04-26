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


public class JobDao {
	private JobDao() {}
	
	public static Job create(Connection cxn, String jobName) throws SQLException {
		String insertJob = "INSERT INTO Job (jobName) VALUES (?);";
				
		try(PreparedStatement insertStmt = cxn.prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, jobName);
			
			insertStmt.executeUpdate();
			
			return new Job(Utils.getAutoIncrementKey(insertStmt), jobName);
		}		
	}
	
	
	public static Job getJobFromJobID(Connection cxn, int id) throws SQLException {
		String selectJob = """
				SELECT jobID, jobName
					FROM Job
					WHERE jobID = ?;"""; 
					
		try (PreparedStatement selectStmt = cxn.prepareStatement(selectJob)) {
			selectStmt.setInt(1, id);
			
			try(ResultSet results = selectStmt.executeQuery()) {
				
				if (results.next()) {
					
					return new Job(id, results.getString("jobName"));
					
				} else return null;
			}
 		}
	}

}
