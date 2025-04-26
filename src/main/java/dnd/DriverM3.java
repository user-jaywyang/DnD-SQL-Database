/**
* @author Jay Yang
*/

package dnd;

import dnd.dal.*;
import dnd.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DriverM3 {
    public static void main(String[] args) {
        try {
            resetSchema(); // Drops and recreates schema with required structure
            insertRecords(); // Inserts test records and performs DAO method validations
        } catch (SQLException e) {
            System.out.print("SQL Exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void insertRecords() throws SQLException {
        try (Connection cxn = ConnectionManager.getConnection()) {

            // === RECORD CREATION TESTS ===
        	
        	// NOTE: ITEMPROTOTYPE & EQUIPPABLEITEM Creations Tested in Gear/Weapon/Consumable Creations

            // 1. Players
            Player doge = PlayerDao.create(cxn, "Doge Dog", "doge@email.com");
            PlayerDao.create(cxn, "Cat cat", "cat@email.com");

            // 2. Races
            Races elf = RacesDao.create(cxn, "Elf");
            RacesDao.create(cxn, "Dwarf");
            RacesDao.create(cxn, "Human");

            // 3. Clan
            Clan mirk = ClanDao.create(cxn, elf, "Mirkwood");

            // 4. Gear Slots
            GearSlot head = GearSlotDao.create(cxn, "Head");
            GearSlot body = GearSlotDao.create(cxn, "Body");
            GearSlot feet = GearSlotDao.create(cxn, "Feet");

            // 5. Jobs
            Job rogue = JobDao.create(cxn, "Rogue");
            Job paladin = JobDao.create(cxn, "Paladin");
            Job ranger = JobDao.create(cxn, "Ranger");

            // 6. Consumables
            Consumable bob = ConsumableDao.create(cxn, "Bag of Beans", 100, 10000.0f, 10);
            Consumable gem = ConsumableDao.create(cxn, "Elemental Gem", 100, 10000.0f, 10);
            ConsumableDao.create(cxn, "Bag of Beans", 500, 50000.0f, 50); // duplicate name allowed

            // 7. Gear (tests ItemPrototype & EquippableItem)
            Gear mask = GearDao.create(cxn, "Valorous Mask", 15, 10000f, 10, 30, head);
            Gear plate = GearDao.create(cxn, "Valorous Plate", 15, 10000f, 10, 30, body);
            GearDao.create(cxn, "Valorous Greaves", 15, 10000f, 10, 30, feet);

            // 8. Weapons
            Weapon dagger = WeaponDao.create(cxn, "Dagger", 20, 2000f, 20, 20, rogue, 20);
            WeaponDao.create(cxn, "Glaive", 50, 5000f, 50, 50, paladin, 50);
            WeaponDao.create(cxn, "Longbow", 30, 3000f, 30, 30, ranger, 30);

            // 9. Game Characters
            GameCharacter megumi = CharacterDao.create(cxn, doge, "Megumi", "Fushiguro", mirk, rogue, dagger);
            CharacterDao.create(cxn, doge, "Yuji", "Itadori", mirk, paladin, dagger);

            // 10. Statistics
            Statistic str = StatisticDao.create(cxn, "Strength");
            
            // 11. CharacterStatistics
            CharacterStatsDao.create(cxn, megumi, str, 50); // Megumi's base strength

            // 12. Currency
            Currency yen = CurrencyDao.create(cxn, "Yen", null, null);
            Currency usd = CurrencyDao.create(cxn, "Dollar", 1000000000, 200000);
            
            // 13. Character Currency
            CharacterCurrencyDao.create(cxn, megumi, yen, 100, 25); // Starting gil
            CharacterCurrencyDao.create(cxn, megumi, usd, null, null);		// test out default values
            
            // 14. Character Job
            CharacterJobDao.create(cxn, megumi, rogue, 10, 3000, true); // Rogue unlocked

            // 15. Inventory Slots
            InventorySlotDao.create(cxn, megumi, 1, dagger, 5); // 5x itemID: 7 (dagger) in slot 1

            // 16. Equipped Gear
            EquippedGearDao.create(cxn, megumi, head, mask);
            EquippedGearDao.create(cxn, megumi, body, plate);

            // 17. Gear → Job requirements
            GearJobRequirementDao.create(cxn, mask, rogue);
            GearJobRequirementDao.create(cxn, plate, rogue);

            // 18. EquippableItem Bonus
            EquippableBonusDao.create(cxn, str, mask, 5); // +5 STR on mask
            
            // 19. Consumable Bonus
            ConsumableBonusDao.create(cxn, str, bob, 10.0f, 100.0f); // +10% STR on bob, max 100

            // === RECORD RETRIEVAL TESTS ===
            System.out.println("=== RECORD RETRIEVAL TESTS ===");

            // 1. Player
            Player p = PlayerDao.getPlayerFromPlayerID(cxn, 1);
            System.out.format("Reading Player: PlayerID: %d, Full Name: %s, Email: %s\n", p.getPlayerID(), p.getFullName(), p.getEmail());
            
            // 2. Races
            Races race = RacesDao.getRaceFromRaceID(cxn, 1);
            System.out.format("Reading Race: RaceID: %d, Race Name: %s\n", race.getRaceID(), race.getRaceName());
            
            // 3. Clan
            Clan clan = ClanDao.getClanFromClanID(cxn, 1);
            System.out.format("Reading Clan: ClanID: %d, RaceID: %d, Clan Name: %s\n", clan.getClanID(), clan.getRace().getRaceID(), clan.getClanName());
            
            // 4. GearSlot
            GearSlot slot = GearSlotDao.getGearSlotFromSlotID(cxn, 1);
            System.out.format("Reading GearSlot: SlotID: %d, Slot Name: %s\n", slot.getSlotID(), slot.getSlotName());
            
            // 5. Job
            Job job = JobDao.getJobFromJobID(cxn, 1);
            System.out.format("Reading Job: jobID: %d, Job Name: %s\n", job.getJobID(), job.getJobName());
            
            // 6. Consumable
            Consumable consumable = ConsumableDao.getConsumableByPrototypeID(cxn, 1);
            System.out.format("Reading Consumable: Consumable ID: %d, Name: %s, Level: %d, Price: %f, Max Stack Size: %d\n", consumable.getPrototypeID(), consumable.getItemName(),
					consumable.getItemLevel(), consumable.getItemPrice(), consumable.getItemMaxStackSize());
            
            // 7. Gear
            Gear gear = GearDao.getGearFromGearID(cxn, 4);
            System.out.format("Reading Gear: Gear ID: %d, Name: %s, Level: %d, Price: %f, Max Stack Size: %d, RequiredLevel: %d, Slot: %d\n", gear.getPrototypeID(), gear.getItemName(),
					gear.getItemLevel(), gear.getItemPrice(), gear.getItemMaxStackSize(), gear.getRequiredLevel(), gear.getSlotID().getSlotID());
			
            // 8. Weapon
            Weapon weapon = WeaponDao.getWeaponFromWeaponID(cxn, 7);
            System.out.format("Reading Weapon: Weapon ID: %d, Name: %s, Level: %d, Price: %f, Max Stack Size: %d, RequiredLevel: %d, Job: %d, Attack Damage: %d\n",
					weapon.getPrototypeID(), weapon.getItemName(),
					weapon.getItemLevel(), weapon.getItemPrice(), weapon.getItemMaxStackSize(), weapon.getRequiredLevel(), weapon.getJob().getJobID(), weapon.getAttackDamage());
			
            // 9. ItemPrototype
            ItemPrototype ip = ItemPrototypeDao.getItemFromPrototypeID(cxn, 7);
            System.out.format("Reading Item Prototype: Prototype ID: %d, Name: %s, Level: %d, Price: %f, Max Stack Size: %d\n", ip.getPrototypeID(), ip.getItemName(),
					ip.getItemLevel(), ip.getItemPrice(), ip.getItemMaxStackSize());
            
            // 10. EquippableItem 
            EquippableItem ei = EquippableItemDao.getEquippableItemFromProtoypeID(cxn, 7);
            System.out.format("Reading Equippable Item: Equippable ID: %d, Name: %s, Level: %d, Price: %f, Max Stack Size: %d, RequiredLevel: %d\n", ei.getPrototypeID(), ei.getItemName(),
					ei.getItemLevel(), ei.getItemPrice(), ei.getItemMaxStackSize(), ei.getRequiredLevel());
			
            // 11. Game Char
            GameCharacter gc = CharacterDao.getCharFromCharID(cxn, 1);
            System.out.format("Reading Character: CharID: %d, PlayerID: %d, First Name: %s, Last Name: %s, ClanID: %d, CurrJob: %d, EquippedWeapon: %d\n",
					gc.getCharacterID(), gc.getPlayer().getPlayerID(), gc.getFirstName(), gc.getLastName(), gc.getClan().getClanID(),
					gc.getCurrentJob().getJobID(), gc.getCurrWeapon().getPrototypeID());
			

            // 12. EquippedGear
            EquippedGear eg = EquippedGearDao.getEquippedGearFromCharacterGearSlot(cxn, megumi, head);
            System.out.format("Reading EquippedGear: CharID: %d, slotID: %d, gearID: %d\n",
					eg.getCharacter().getCharacterID(), eg.getGearSlot().getSlotID(), eg.getGear().getPrototypeID());
            
            // 13. GearJobRequirement
            GearJobRequirement gjr = GearJobRequirementDao.getGearJobRequirementByGearANDJob(cxn, mask, rogue);
            System.out.format("Reading GearJobRequirement: gearID: %d, jobID: %d\n", gjr.getGear().getPrototypeID(), gjr.getJob().getJobID());
            
            // 14. Currency
            Currency currency = CurrencyDao.getCurrencyFromCurrencyID(cxn, 1);
            System.out.format("Reading Currency: CurrencyID: %d, Currency Name: %s, Cap: %d, Weekly Cap: %d\n", currency.getCurrencyID(),
            		currency.getCurrencyName(), currency.getCap(), currency.getWeeklyCap());
            
            // 15. CharCurrency
            CharacterCurrency currencyRecord = CharacterCurrencyDao.getByCharacterAndCurrency(cxn, megumi, yen);
            System.out.format("Reading CharacterCurrency: CharacterID: %d, CurrencyID: %d, Held: %d, Earned: %d\n",
                currencyRecord.getCharacter().getCharacterID(),
                currencyRecord.getCurrency().getCurrencyID(),
                currencyRecord.getAmountHeld(),
                currencyRecord.getAmountEarnedThisWeek());
            
            // 16. CharJob
            CharacterJob cj = CharacterJobDao.getCharJobFromCharIDJobID(cxn, megumi, rogue);
            System.out.format("Reading CharacterJob: CharID: %d, JobID: %d, Level: %d, XP: %d, Unlocked: %b\n",
                cj.getCharacter().getCharacterID(),
                cj.getJob().getJobID(),
                cj.getLevel(),
                cj.getExperiencePoints(),
                cj.isUnlocked());
            
            // 17. Statistics
            Statistic stat = StatisticDao.getStatisticFromStatID(cxn, 1);		// Strength
            System.out.format("Reading Statistic: StatID: %d, Stat Name: %s \n", stat.getStatisticID(), stat.getStatisticName());
            
            // 18. CharStatistics
            CharacterStats statRecord = CharacterStatsDao.getCharStat(cxn, megumi, str);
            System.out.format("Reading CharacterStats: CharID: %d, StatID: %d, Value: %d\n",
                statRecord.getCharacter().getCharacterID(),
                statRecord.getStatistic().getStatisticID(),
                statRecord.getValue());
            
            // 19. InventorySlot
            InventorySlot inv = InventorySlotDao.getInventorySlotByCharacterSlotNumber(cxn, megumi, 1);
            System.out.format("Reading InventorySlot: CharID: %d, Slot #: %d, PrototypeID: %d, StackSize: %d\n",
                inv.getCharacter().getCharacterID(),
                inv.getSlotNumber(),
                inv.getPrototypeID().getPrototypeID(),
                inv.getStackSize());
            
            // 20. ConsumableBonus
            ConsumableBonus cb = ConsumableBonusDao.getConsumableBonusFromStatANDConsumable(cxn, str, bob);		// id: 1
            System.out.format("Reading CBonus: StatID: %d, consumableID: %d, Bonus Percentage: %f, Bonus Cap: %f\n", 
            		cb.getStatistic().getStatisticID(), cb.getConsumable().getPrototypeID(), cb.getBonusPercentage(), cb.getBonusCap());
            
            
            // 21. EquippableItemBonus
            EquippableBonus eb = EquippableBonusDao.getEquippableBonusFromStatANDPrototypeID(cxn, str, mask);
            System.out.format("Reading Ebonus: StatID: %d, EquippableID: %d, Bonus Value: %d\n", 
            		eb.getStatistic().getStatisticID(), eb.getEquippableItem().getPrototypeID(), eb.getBonusValue());

            // === EXTRA 3 METHODS: CONSUMABLE TESTS ===
            System.out.println();
            System.out.println("=== CONSUMABLE: UPDATE/NAME LIST/DELETE ===");

            // 1. Update Name
            ConsumableDao.updateName(cxn, gem, "Elixir of Life");		// itemID == 2
            System.out.println(ItemPrototypeDao.getItemFromPrototypeID(cxn, 2).getItemName());
            
            // 2. List compilation by Name
            List<Consumable> beans = ConsumableDao.getConsumablesByName(cxn, "Bag of Beans");
            for (Consumable con : beans) {
                System.out.println("Found matching consumable: " + con.getItemName());
            }

            // 3. Deletion
            ConsumableDao.delete(cxn, bob);		// bob's id: 1
            Consumable tryBob = ConsumableDao.getConsumableByPrototypeID(cxn, 1);
            if (tryBob == null) {
                System.out.println("Deletion successful.");
            } else {
                System.out.println("Deletion failed.");
            }
            
        }
    }
    private static void resetSchema() throws SQLException {
        try (Connection cxn = ConnectionManager.getSchemalessConnection()) {
            cxn.createStatement().executeUpdate("DROP SCHEMA IF EXISTS CS5200Project;");
            cxn.createStatement().executeUpdate("CREATE SCHEMA CS5200Project;");
        }

        try (Connection cxn = ConnectionManager.getConnection()) {
        	cxn.createStatement().executeUpdate("""
					CREATE TABLE Currency (
					    currencyID INT AUTO_INCREMENT PRIMARY KEY,
					    currencyName VARCHAR(50) UNIQUE NOT NULL,
					    cap INT DEFAULT NULL,
					    weeklyCap INT DEFAULT NULL
					);
					""");
			
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Player (
					    playerID INT AUTO_INCREMENT PRIMARY KEY,
					    fullName VARCHAR(50) NOT NULL,
					    emailAddress VARCHAR(100) UNIQUE NOT NULL
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Races (
					    raceID INT AUTO_INCREMENT PRIMARY KEY,
					    raceName VARCHAR(50) UNIQUE NOT NULL
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Clan (
					    clanID INT AUTO_INCREMENT PRIMARY KEY,
					    raceID INT NOT NULL,
					    clanName VARCHAR(50) UNIQUE NOT NULL,
					    CONSTRAINT fk_clan FOREIGN KEY (raceID) REFERENCES Races(raceID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Statistic (
					    statisticID INT AUTO_INCREMENT PRIMARY KEY,
					    statisticsName VARCHAR(50) UNIQUE NOT NULL
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE gearSlot (
					    slotID INT AUTO_INCREMENT PRIMARY KEY,
					    slotName VARCHAR(50) UNIQUE NOT NULL
					);
					""");
			
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Job (
					    jobID INT AUTO_INCREMENT PRIMARY KEY,
					    jobName VARCHAR(50) UNIQUE NOT NULL
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE `Character` (
					    characterID INT AUTO_INCREMENT PRIMARY KEY,
					    playerID INT NOT NULL,
					    firstName VARCHAR(50) NOT NULL,
					    lastName VARCHAR(50) NOT NULL,
					    clanID INT NOT NULL,
					    currentJob INT NOT NULL,
					    equippedWeapon INT,
					    CONSTRAINT unique_CharacterName UNIQUE (firstName, lastName),
					    CONSTRAINT fk_Character_playerID FOREIGN KEY (playerID) REFERENCES Player(playerID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_Character_clanID FOREIGN KEY (clanID) REFERENCES Clan(clanID) ON DELETE RESTRICT ON UPDATE CASCADE,
					    CONSTRAINT fk_Character_currJob FOREIGN KEY (currentJob) REFERENCES Job(jobID) ON DELETE RESTRICT ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE CharacterCurrency (
					    characterID INT NOT NULL,
					    currencyID INT NOT NULL,
					    amountHeld INT DEFAULT 0,
					    amountEarnedThisWeek INT DEFAULT 0,
					    CONSTRAINT pk_CharCurr PRIMARY KEY (characterID, currencyID),
					    CONSTRAINT fk_CharCurr_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_CharCurr_currID FOREIGN KEY (currencyID) REFERENCES Currency(currencyID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE CharacterJob (
					    characterID INT NOT NULL,
					    jobID INT NOT NULL,
					    level INT DEFAULT 1,
					    experiencePoints INT DEFAULT 0,
					    unlocked BOOLEAN DEFAULT FALSE,
					    CONSTRAINT pk_CharJob PRIMARY KEY (characterID, jobID),
					    CONSTRAINT fk_CharJob_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_CharJob_jobID FOREIGN KEY (jobID) REFERENCES Job(jobID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE CharacterStats (
					    characterID INT NOT NULL,
					    statisticID INT NOT NULL,
					    value INT NOT NULL,
					    CONSTRAINT pk_CharStats PRIMARY KEY (characterID, statisticID),
					    CONSTRAINT fk_CharStats_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_CharStats_statID FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE ItemPrototype (
					    prototypeID INT AUTO_INCREMENT PRIMARY KEY,
					    itemName VARCHAR(100) NOT NULL,
					    itemLevel INT NOT NULL,
					    itemPrice DECIMAL(10,2) DEFAULT 0,
					    itemMaxStackSize INT NOT NULL
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE InventorySlot (
					    characterID INT NOT NULL,
					    slotNumber INT NOT NULL,
					    prototypeID INT NOT NULL,
					    stackSize INT NOT NULL,
					    PRIMARY KEY (characterID, slotNumber),
					    CONSTRAINT fk_Inventory_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_Inventory_prototypeID FOREIGN KEY (prototypeID) REFERENCES ItemPrototype(prototypeID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Consumable (
					    consumableID INT PRIMARY KEY,
					    CONSTRAINT fk_consumable_consumableID FOREIGN KEY (consumableID) REFERENCES ItemPrototype(prototypeID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE EquippableItem (
					    equippableItemID INT PRIMARY KEY,
					    requiredLevel INT DEFAULT 1,
					    CONSTRAINT fk_Equippable_equippableID FOREIGN KEY (equippableItemID) REFERENCES ItemPrototype(prototypeID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Gear (
					    gearID INT PRIMARY KEY,
					    slotID INT NOT NULL,
					    CONSTRAINT fk_Gear_gearID FOREIGN KEY (gearID) REFERENCES EquippableItem(equippableItemID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_Gear_slotID FOREIGN KEY (slotID) REFERENCES gearSlot(slotID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE Weapon (
					    weaponID INT PRIMARY KEY,
					    jobID INT NOT NULL,
					    attackDamage INT NOT NULL,
					    CONSTRAINT fk_weapon_weaponID FOREIGN KEY (weaponID) REFERENCES EquippableItem(equippableItemID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_weapon_jobID FOREIGN KEY (jobID) REFERENCES Job(jobID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE EquippedGear (
					    characterID INT NOT NULL,
					    slotID INT NOT NULL,
					    gearID INT NULL,
					    CONSTRAINT pk_equippedGear PRIMARY KEY (characterID, slotID),
					    CONSTRAINT fk_EquippedGear_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_EquippedGear_slotID FOREIGN KEY (slotID) REFERENCES gearSlot(slotID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_EquippedGear_gearID FOREIGN KEY (gearID) REFERENCES Gear(gearID) ON DELETE SET NULL ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE GearJobRequirement (
					    gearID INT NOT NULL,
					    jobID INT NOT NULL,
					    CONSTRAINT pk_GearJobRequirement PRIMARY KEY (gearID, jobID),
					    CONSTRAINT fk_GearJobRequirement_gearID FOREIGN KEY (gearID) REFERENCES Gear(gearID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_GearJobRequirement_jobID FOREIGN KEY (jobID) REFERENCES Job(jobID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE EquippableBonus (
					    statisticID INT NOT NULL,
					    equippableItemID INT NOT NULL,
					    bonusValue INT NOT NULL,
					    CONSTRAINT pk_equippableBonus PRIMARY KEY (statisticID, equippableItemID),
					    CONSTRAINT fk_EquippableBonus_statID FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_EquippableBonus_equippableID FOREIGN KEY (equippableItemID) REFERENCES EquippableItem(equippableItemID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
			cxn.createStatement().executeUpdate("""
					CREATE TABLE ConsumableBonus (
					    statisticID INT NOT NULL,
					    consumableID INT NOT NULL,
					    bonusPercentage FLOAT NOT NULL,
					    bonusCap FLOAT DEFAULT NULL,
					    CONSTRAINT pk_consumableBonus PRIMARY KEY (statisticID, consumableID),
					    CONSTRAINT fk_consumableBonus_statID FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID) ON DELETE CASCADE ON UPDATE CASCADE,
					    CONSTRAINT fk_consumableBonus_consumableID FOREIGN KEY (consumableID) REFERENCES Consumable(consumableID) ON DELETE CASCADE ON UPDATE CASCADE
					);
					""");
        }
    }
}
