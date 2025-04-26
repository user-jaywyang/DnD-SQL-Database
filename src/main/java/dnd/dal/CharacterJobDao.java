/**
 * @author Jay Yang
 */

package dnd.dal;

import dnd.model.*;

import java.sql.*;

public class CharacterJobDao {
    private CharacterJobDao() {}

    public static CharacterJob create(Connection cxn,
                                      GameCharacter character,
                                      Job job,
                                      Integer level,
                                      Integer experiencePoints,
                                      Boolean unlocked) throws SQLException {
        String sql = "INSERT INTO CharacterJob (characterID, jobID, level, experiencePoints, unlocked) " +
                     "VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, character.getCharacterID());
            stmt.setInt(2, job.getJobID());
            
            // for allowing default vals in sql
            stmt.setInt(3, level != null ? level : CharacterJob.getDefaultLevel());
            stmt.setInt(4, experiencePoints != null ? experiencePoints : CharacterJob.getDefaultExp());
            stmt.setBoolean(5, unlocked != null ? unlocked : CharacterJob.isDefaultUnlocked());

            stmt.executeUpdate();

            return new CharacterJob(job, character, level, experiencePoints, unlocked);
        }
    }

    public static CharacterJob getCharJobFromCharIDJobID(Connection cxn,
                                                         GameCharacter character,
                                                         Job job) throws SQLException {
        String sql = "SELECT level, experiencePoints, unlocked FROM CharacterJob " +
                     "WHERE characterID = ? AND jobID = ?;";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, character.getCharacterID());
            stmt.setInt(2, job.getJobID());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int level = rs.getInt("level");
                    int exp = rs.getInt("experiencePoints");
                    boolean unlocked = rs.getBoolean("unlocked");

                    return new CharacterJob(job, character, level, exp, unlocked);
                } else {
                    return null;
                }
            }
        }
    }
}
