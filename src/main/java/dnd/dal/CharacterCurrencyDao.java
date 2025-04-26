/**
 * @author Jay Yang
 */

package dnd.dal;

import dnd.model.*;


import java.sql.*;

public class CharacterCurrencyDao {
	

    private CharacterCurrencyDao() {}

    public static CharacterCurrency create(Connection cxn,
                                           GameCharacter character,
                                           Currency currency,
                                           Integer amountHeld,
                                           Integer amountEarnedThisWeek) throws SQLException {
        String sql = "INSERT INTO CharacterCurrency (characterID, currencyID, amountHeld, amountEarnedThisWeek) " +
                     "VALUES (?, ?, ?, ?);";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, character.getCharacterID());
            stmt.setInt(2, currency.getCurrencyID());
            
            
            stmt.setInt(3, amountHeld != null ? amountHeld : CharacterCurrency.getDefaultAmountHeld());		// allows default vals in sql
            stmt.setInt(4, amountEarnedThisWeek != null ? amountEarnedThisWeek : CharacterCurrency.getDefaultAmountEarned());

            stmt.executeUpdate();
            return new CharacterCurrency(character, currency, amountHeld, amountEarnedThisWeek);
        }
    }

    public static CharacterCurrency getByCharacterAndCurrency(Connection cxn,
                                                              GameCharacter character,
                                                              Currency currency) throws SQLException {
        String sql = "SELECT amountHeld, amountEarnedThisWeek FROM CharacterCurrency " +
                     "WHERE characterID = ? AND currencyID = ?;";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, character.getCharacterID());
            stmt.setInt(2, currency.getCurrencyID());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int held = rs.getInt("amountHeld");
                    int earned = rs.getInt("amountEarnedThisWeek");
                    return new CharacterCurrency(character, currency, held, earned);
                } else {
                    return null;
                }
            }
        }
    }
}
