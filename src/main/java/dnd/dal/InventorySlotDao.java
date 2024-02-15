package dnd.dal;

import dnd.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventorySlotDao {

    private InventorySlotDao() {}

    /**
     * Inserts a new InventorySlot record into the database.
     */
    public static InventorySlot create(Connection cxn, GameCharacter character, int slotNumber, ItemPrototype prototype, int stackSize) throws SQLException {
        String sql = "INSERT INTO InventorySlot (characterID, slotNumber, prototypeID, stackSize) VALUES (?, ?, ?, ?);";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, character.getCharacterID());
            stmt.setInt(2, slotNumber);
            stmt.setInt(3, prototype.getPrototypeID());  // prototypeID (same as itemID)
            stmt.setInt(4, stackSize);

            stmt.executeUpdate();

            return new InventorySlot(character, slotNumber, prototype, stackSize);
        }
    }

    /**
     * Retrieves an InventorySlot by character and slot number.
     */
    public static InventorySlot getInventorySlotByCharacterSlotNumber(Connection cxn, GameCharacter character, int slotNumber) throws SQLException {
        String sql = "SELECT prototypeID, stackSize FROM InventorySlot WHERE characterID = ? AND slotNumber = ?;";

        try (PreparedStatement stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, character.getCharacterID());
            stmt.setInt(2, slotNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int prototypeID = rs.getInt("prototypeID");
                    int stackSize = rs.getInt("stackSize");

                    ItemPrototype item = ItemPrototypeDao.getItemFromPrototypeID(cxn, prototypeID);
                    return new InventorySlot(character, slotNumber, item, stackSize);
                } else {
                    return null;
                }
            }
        }
    }
}
