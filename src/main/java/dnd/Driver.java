/**
 * @author Jay Yang
 */

package dnd;

import dnd.dal.*;
import dnd.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Driver {
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
    	try(Connection cxn = ConnectionManager.getConnection()) {
    		// PLAYERS
    		
    		Player aa = PlayerDao.create(cxn, "Apple Aardvark", "aa@email.com");
    		Player bb = PlayerDao.create(cxn, "Banana Bat", "bb@email.com");
    		PlayerDao.create(cxn, "Calamari Cat", "cc@email.com");
    		PlayerDao.create(cxn, "Daikon Dog", "dd@email.com");
    		PlayerDao.create(cxn, "Edamame Echidna", "ee@email.com");
    		PlayerDao.create(cxn, "Fig Frog", "ff@email.com");
    		PlayerDao.create(cxn, "Guacamole Giraffe", "gg@email.com");
    		PlayerDao.create(cxn, "Habanero Hamster", "hh@email.com");
    		PlayerDao.create(cxn, "Icecream Iguana", "ii@email.com");
    		PlayerDao.create(cxn, "Jackfruit Jaguar", "jj@email.com");
    		
    		// Race
    		Races elf = RacesDao.create(cxn, "Elf");
	        Races human = RacesDao.create(cxn, "Human");
	        RacesDao.create(cxn, "Dwarf");
	        RacesDao.create(cxn, "Dragonborn");
	        RacesDao.create(cxn, "Gnome");
	        RacesDao.create(cxn, "Changeling");
	        RacesDao.create(cxn, "Halfling");
	        RacesDao.create(cxn, "Orc");
	        RacesDao.create(cxn, "Goblin");
	        RacesDao.create(cxn, "Dhampir");
	        
	        // Clan
	        Clan mirk = ClanDao.create(cxn, elf, "Mirkwood");
	        Clan sea = ClanDao.create(cxn, human, "Seattle Clan");
	        ClanDao.create(cxn, human, "Oregon Clan");
	        ClanDao.create(cxn, human, "Boston Clan");
	        ClanDao.create(cxn, human, "NYC Clan");
	        ClanDao.create(cxn, human, "LA Clan");
	        ClanDao.create(cxn, human, "San Diego Clan");
	        ClanDao.create(cxn, human, "SF Clan");
	        ClanDao.create(cxn, human, "Cape Cod Clan");
	        ClanDao.create(cxn, human, "Denver Clan");
    		
    		// JOBS
    		Job rogue = JobDao.create(cxn, "Rogue");
    		Job paladin = JobDao.create(cxn, "Paladin");
    		Job ranger = JobDao.create(cxn, "Ranger");
    		Job necromancer = JobDao.create(cxn, "Necromancer");
    		Job sorcerer = JobDao.create(cxn, "Sorcerer");
    		JobDao.create(cxn, "Druid");
    		JobDao.create(cxn, "Fighter");
    		JobDao.create(cxn, "Cleric");
    		JobDao.create(cxn, "Monk");
    		JobDao.create(cxn, "Barbarian");
    		
    		// WEAPONS
    		Weapon dagger = WeaponDao.create(cxn, "Dagger", 20, 2000f, 20, 20, rogue, 20);
    		Weapon glaive = WeaponDao.create(cxn, "Glaive", 50, 5000f, 50, 50, paladin, 50);
    		Weapon bow = WeaponDao.create(cxn, "Longbow", 40, 4000f, 40, 40, ranger, 40);
    		Weapon warbow = WeaponDao.create(cxn, "Warbow", 30, 3000f, 30, 30, ranger, 30);
    		WeaponDao.create(cxn, "Catalyst", 60, 6000f, 60, 60, necromancer, 60);
    		Weapon staff = WeaponDao.create(cxn, "Staff", 70, 7000f, 70, 70, sorcerer, 70);
    		Weapon javelin = WeaponDao.create(cxn, "Javelin", 80, 8000f, 80, 80, rogue, 80);
    		Weapon longsword = WeaponDao.create(cxn, "Longsword", 90, 9000f, 90, 90, paladin, 90);
    		WeaponDao.create(cxn, "Shield", 110, 11000f, 110, 110, paladin, 110);
    		Weapon gauntlets = WeaponDao.create(cxn, "Gauntlets", 120, 12000f, 120, 120, paladin, 120);
    		
    		// CHARACTERS
    		GameCharacter gojo = CharacterDao.create(cxn, aa, "Gojo", "Satoru", sea, sorcerer, staff);
    		CharacterDao.create(cxn, aa, "Yuji", "Itadori", sea, paladin, gauntlets);
    		GameCharacter megumi = CharacterDao.create(cxn, aa, "Megumi", "Fushiguro", mirk, rogue, dagger);
    		CharacterDao.create(cxn, aa, "Nobara", "Kugisaki", sea, sorcerer, staff);
    		CharacterDao.create(cxn, aa, "Kento", "Nanami", mirk, paladin, longsword);
    		GameCharacter toji = CharacterDao.create(cxn, bb, "Toji", "Fushiguro", sea, rogue, javelin);
    		GameCharacter meimei = CharacterDao.create(cxn, bb, "Mei", "Mei", sea, paladin, longsword);
    		GameCharacter nori = CharacterDao.create(cxn, bb, "Noritoshi", "Kamo", mirk, ranger, bow);
    		CharacterDao.create(cxn, bb, "Choso", "Kamo", sea, sorcerer, staff);
    		CharacterDao.create(cxn, bb, "Miwa", "Kasumi", sea, paladin, longsword);
    		
    		
    		// INVENTORYSLOT
    		InventorySlotDao.create(cxn, megumi, 1, dagger, 3);
    		InventorySlotDao.create(cxn, megumi, 2, javelin, 2);
    		InventorySlotDao.create(cxn, toji, 1, dagger, 4);
    		InventorySlotDao.create(cxn, toji, 2, javelin, 5);
    		InventorySlotDao.create(cxn, meimei, 1, longsword, 10);
    		InventorySlotDao.create(cxn, meimei, 2, gauntlets, 10);
    		InventorySlotDao.create(cxn, meimei, 3, glaive, 10);
    		InventorySlotDao.create(cxn, gojo, 1, staff, 10);
    		InventorySlotDao.create(cxn, nori, 1, bow, 3);
    		InventorySlotDao.create(cxn, nori, 2, warbow, 3);
    		
    	

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
