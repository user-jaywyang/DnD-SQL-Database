/**
 * @author Jay Yang
 */

package dnd.dal;

import dnd.model.Statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticDao {
    private StatisticDao() {}

    /**
     * Inserts a new statistic into the database and returns the populated Statistic object.
     */
    public static Statistic create(Connection cxn, String statName) throws SQLException {
        String sql = "INSERT INTO Statistic (statisticsName) VALUES (?);";

        try (PreparedStatement stmt = cxn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, statName);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return new Statistic(id, statName);
                } else {
                    throw new SQLException("Failed to retrieve generated statisticID.");
                }
            }
        }
    }

    /**
     * Retrieves a statistic from the database using its primary key.
     */
    public static Statistic getStatisticFromStatID(Connection cxn, int statID) throws SQLException {
        String sql = "SELECT statisticID, statisticsName FROM Statistic WHERE statisticID = ?;";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, statID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("statisticsName");
                    return new Statistic(statID, name);
                } else {
                    return null;
                }
            }
        }
    }
}
