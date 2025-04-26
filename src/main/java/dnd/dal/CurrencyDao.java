/**
 * @author Jay Yang
 */

package dnd.dal;

import dnd.model.Currency;

import java.sql.*;


public class CurrencyDao {
    private CurrencyDao() {}

    public static Currency create(Connection cxn, String currencyName, Integer cap, Integer weeklyCap) throws SQLException {
        String sql = "INSERT INTO Currency (currencyName, cap, weeklyCap) VALUES (?, ?, ?);";

        try (PreparedStatement stmt = cxn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, currencyName);
            if (cap != null) {
                stmt.setInt(2, cap);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            if (weeklyCap != null) {
                stmt.setInt(3, weeklyCap);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int currencyID = keys.getInt(1);
                    return new Currency(currencyID, currencyName, cap, weeklyCap);
                } else {
                    throw new SQLException("Currency creation failed: no ID returned.");
                }
            }
        }
    }

    public static Currency getCurrencyFromCurrencyID(Connection cxn, int id) throws SQLException {
        String sql = "SELECT currencyName, cap, weeklyCap FROM Currency WHERE currencyID = ?;";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("currencyName");
                    Integer cap = rs.getObject("cap", Integer.class);
                    Integer weeklyCap = rs.getObject("weeklyCap", Integer.class);
                    return new Currency(id, name, cap, weeklyCap);
                } else {
                    return null;
                }
            }
        }
    }

}

