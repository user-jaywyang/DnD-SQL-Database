-- Drop schema if it exists and recreate
DROP SCHEMA IF EXISTS CS5200Project;
CREATE SCHEMA CS5200Project;
USE CS5200Project;

-- ===========================
-- Currency Table
-- ===========================
CREATE TABLE Currency (
    currencyID INT AUTO_INCREMENT PRIMARY KEY,
    currencyName VARCHAR(50) UNIQUE NOT NULL,
    cap INT DEFAULT NULL,
    weeklyCap INT DEFAULT NULL
);

-- ===========================
-- Player Table
-- ===========================
CREATE TABLE Player (
    playerID INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(50) NOT NULL,
    emailAddress VARCHAR(100) UNIQUE NOT NULL
);

-- ===========================
-- Race & Clan Tables
-- ===========================
CREATE TABLE Races (
    raceID INT AUTO_INCREMENT PRIMARY KEY,
    raceName VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Clan (
    clanID INT AUTO_INCREMENT PRIMARY KEY,
    raceID INT NOT NULL,
    clanName VARCHAR(50) UNIQUE NOT NULL,
    CONSTRAINT fk_clan FOREIGN KEY (raceID) REFERENCES Races(raceID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ===========================
-- Statistic Table
-- ===========================
CREATE TABLE Statistic (
    statisticID INT AUTO_INCREMENT PRIMARY KEY,
    statisticsName VARCHAR(50) UNIQUE NOT NULL
);

-- ===========================
-- gearSlot Table 
-- ===========================
CREATE TABLE gearSlot (
    slotID INT AUTO_INCREMENT PRIMARY KEY,
    slotName VARCHAR(50) UNIQUE NOT NULL
);

-- ===========================
-- Job Table
-- ===========================
CREATE TABLE Job (
    jobID INT AUTO_INCREMENT PRIMARY KEY,
    jobName VARCHAR(50) UNIQUE NOT NULL
);

-- ===========================
-- Inventory & Item Hierarchy
-- ===========================
CREATE TABLE ItemPrototype (
    prototypeID INT AUTO_INCREMENT PRIMARY KEY,
    itemName VARCHAR(100) NOT NULL,
    itemLevel INT NOT NULL,
    itemPrice DECIMAL(10,2) DEFAULT 0,
    itemMaxStackSize INT NOT NULL
);


CREATE TABLE Consumable (
    consumableID INT PRIMARY KEY,
    CONSTRAINT fk_consumable_consumableID FOREIGN KEY (consumableID) REFERENCES ItemPrototype(prototypeID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE EquippableItem (
    equippableItemID INT PRIMARY KEY,
    requiredLevel INT DEFAULT 1,
    CONSTRAINT fk_Equippable_equippableID FOREIGN KEY (equippableItemID) REFERENCES ItemPrototype(prototypeID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Gear (
    gearID INT PRIMARY KEY,
    slotID INT NOT NULL,
    CONSTRAINT fk_Gear_gearID FOREIGN KEY (gearID) REFERENCES EquippableItem(equippableItemID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_Gear_slotID FOREIGN KEY (slotID) REFERENCES gearSlot(slotID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Weapon (
    weaponID INT PRIMARY KEY,
    jobID INT NOT NULL,
    attackDamage INT NOT NULL,
    CONSTRAINT fk_weapon_weaponID FOREIGN KEY (weaponID) REFERENCES EquippableItem(equippableItemID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_weapon_jobID FOREIGN KEY (jobID) REFERENCES Job(jobID) ON DELETE CASCADE ON UPDATE CASCADE
);


-- ===========================
-- Character Table 
-- ===========================
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
    CONSTRAINT fk_Character_currJob FOREIGN KEY (currentJob) REFERENCES Job(jobID) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_Character_currWeapon FOREIGN KEY (equippedWeapon) REFERENCES Weapon(weaponID) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ===========================
-- Equipped Gear Table
-- ===========================
CREATE TABLE EquippedGear (
    characterID INT NOT NULL,
    slotID INT NOT NULL,
    gearID INT NULL,
    CONSTRAINT pk_equippedGear PRIMARY KEY (characterID, slotID),
    CONSTRAINT fk_EquippedGear_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_EquippedGear_slotID FOREIGN KEY (slotID) REFERENCES gearSlot(slotID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_EquippedGear_gearID FOREIGN KEY (gearID) REFERENCES Gear(gearID) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT unique_charID_slotID UNIQUE (characterID, slotID)
);

-- ===========================
-- INVENTORY SLOT
-- ===========================

CREATE TABLE InventorySlot (
    characterID INT NOT NULL,
    slotNumber INT NOT NULL,
    prototypeID INT NOT NULL,
    stackSize INT NOT NULL,
    PRIMARY KEY (characterID, slotNumber),
    CONSTRAINT fk_Inventory_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_Inventory_prototypeID FOREIGN KEY (prototypeID) REFERENCES ItemPrototype(prototypeID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT unique_inventory_charID_slotNum UNIQUE (slotNumber, characterID)
);

-- ===========================
-- Character Currency Table
-- ===========================
CREATE TABLE CharacterCurrency (
    characterID INT NOT NULL,
    currencyID INT NOT NULL,
    amountHeld INT DEFAULT 0,
    amountEarnedThisWeek INT DEFAULT 0,
    CONSTRAINT pk_CharCurr PRIMARY KEY (characterID, currencyID),
    CONSTRAINT fk_CharCurr_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_CharCurr_currID FOREIGN KEY (currencyID) REFERENCES Currency(currencyID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ===========================
-- Character Job Table 
-- ===========================

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

-- ===========================
-- Character Stats Table 
-- ===========================

CREATE TABLE CharacterStats (
    characterID INT NOT NULL,
    statisticID INT NOT NULL,
    value INT NOT NULL,
    CONSTRAINT pk_CharStats PRIMARY KEY (characterID, statisticID),
    CONSTRAINT fk_CharStats_charID FOREIGN KEY (characterID) REFERENCES `Character`(characterID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_CharStats_statID FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID) ON DELETE CASCADE ON UPDATE CASCADE
);


-- ===========================
-- Gear Requirements
-- ===========================
CREATE TABLE GearJobRequirement (
    gearID INT NOT NULL,
    jobID INT NOT NULL,
    CONSTRAINT pk_GearJobRequirement PRIMARY KEY (gearID, jobID),
    CONSTRAINT fk_GearJobRequirement_gearID FOREIGN KEY (gearID) REFERENCES Gear(gearID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_GearJobRequirement_jobID FOREIGN KEY (jobID) REFERENCES Job(jobID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ===========================
-- Bonuses
-- ===========================
CREATE TABLE EquippableBonus (
    statisticID INT NOT NULL,
    equippableItemID INT NOT NULL,
    bonusValue INT NOT NULL,
    CONSTRAINT pk_equippableBonus PRIMARY KEY (statisticID, equippableItemID),
    CONSTRAINT fk_EquippableBonus_statID FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_EquippableBonus_equippableID FOREIGN KEY (equippableItemID) REFERENCES EquippableItem(equippableItemID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ConsumableBonus (
    statisticID INT NOT NULL,
    consumableID INT NOT NULL,
    bonusPercentage FLOAT NOT NULL,
    bonusCap FLOAT DEFAULT NULL,
    CONSTRAINT pk_consumableBonus PRIMARY KEY (statisticID, consumableID),
    CONSTRAINT fk_consumableBonus_statID FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_consumableBonus_consumableID FOREIGN KEY (consumableID) REFERENCES Consumable(consumableID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ===========================
-- Sample Data for Currency
-- ===========================
INSERT INTO Currency (currencyName, cap, weeklyCap) VALUES 
('Gold', NULL, NULL),
('Gems', 1000, 500),
('Tokens', 500, 250),
('Platinum', 10000, 1000),
('Silver', NULL, NULL);


-- ===========================
-- Sample Data for Player
-- ===========================
INSERT INTO Player (fullName, emailAddress) VALUES 
('John Smith', 'john@example.com'),
('Sally Park', 'sally@example.com'),
('Alice Johnson', 'alice@example.com'),
('Bob Brown', 'bob@example.com'),
('Charlie White', 'charlie@example.com');


-- ===========================
-- Sample Data for Races
-- ===========================
INSERT INTO Races (raceName) VALUES 
('Human'),
('Wood Elf'),
('Orc'),
('Dwarf'),
('Dark Elf');

-- ===========================
-- Sample Data for Clan
-- ===========================
INSERT INTO Clan (raceID, clanName) VALUES 
(1, 'Eagle'),
(2, 'Wolf'),
(3, 'Bear'),
(4, 'Badger'),
(5, 'Stag');

-- ===========================
-- Sample Data for Job
-- ===========================
INSERT INTO Job (jobName) VALUES 
('Warrior'),
('Mage'),
('Thief'),
('Priest'),
('Archer');

-- ===========================
-- Sample Data for Character
-- ===========================
INSERT INTO `Character` (playerID, firstName, lastName, clanID, currentJob) VALUES 
(1, 'Hey', 'Arthur', 1, 1),
(2, 'Its', 'Caillou', 2, 2),
(3, 'Dragon', 'Tails', 3, 3),
(4, 'Reading', 'Rainbow', 4, 4),
(5, 'Clifford', 'Dog', 5, 5);


-- ===========================
-- Sample Data for CharacterJob
-- ===========================
INSERT INTO CharacterJob (characterID, jobID, level, experiencePoints, unlocked) VALUES 
(1, 1, 10, 5000, TRUE),
(2, 2, 8, 3200, TRUE),
(3, 3, 5, 2100, TRUE),
(4, 4, 12, 7000, TRUE),
(5, 5, 15, 10000, TRUE);


-- ===========================
-- Sample Data for CharacterCurrency
-- ===========================
INSERT INTO CharacterCurrency (characterID, currencyID, amountHeld, amountEarnedThisWeek) VALUES 
(1, 1, 1000, 100),
(2, 2, 500, 50),
(3, 3, 200, 25),
(4, 4, 5000, 500),
(5, 5, 1200, 150);

-- ===========================
-- Sample Data for ItemPrototype
-- ===========================
INSERT INTO ItemPrototype (itemName, itemLevel, itemPrice, itemMaxStackSize) VALUES 
('Steel Sword', 1, 100, 1),
('Fire Staff', 2, 300, 1),
('Glass Dagger', 1, 150, 1),
('Bronze Shield', 3, 500, 1),
('Holy Relic', 5, 700, 1);

-- ===========================
-- Sample Data for InventorySlot
-- ===========================
INSERT INTO InventorySlot (characterID, slotNumber, prototypeID, stackSize)
VALUES
  (1, 1, 1, 1),
  (2, 1, 2, 1),
  (3, 1, 3, 1),
  (4, 1, 4, 1),
  (5, 1, 5, 1);

-- ===========================
-- Sample Data for gearSlot
-- ===========================
INSERT INTO gearSlot (slotName) VALUES 
('Helmet'),
('Chestplate'),
('Gauntlet'),
('Greaves'),
('Boots');

-- ===========================
-- Sample Data for EquippableItem
-- ===========================
INSERT INTO EquippableItem (equippableItemID, requiredLevel) VALUES 
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 5);

-- ===========================
-- Sample Data for Gear
-- ===========================
INSERT INTO Gear (gearID, slotID) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- ===========================
-- Sample Data for Weapon
-- ===========================
INSERT INTO Weapon (weaponID, jobID, attackDamage) VALUES 
(1, 1, 25),
(2, 2, 40),
(3, 3, 30),
(4, 4, 50),
(5, 5, 60);


-- ===========================
-- Sample Data for Consumable
-- ===========================
INSERT INTO Consumable (consumableID) VALUES 
(1),
(2),
(3),
(4),
(5);

-- ===========================
-- Sample Data for Statistic
-- ===========================
INSERT INTO Statistic (statisticsName) VALUES 
('Strength'),
('Intelligence'),
('Agility'),
('Defense'),
('Mana');

-- ===========================
-- Sample Data for EquippableBonus
-- ===========================
INSERT INTO EquippableBonus (statisticID, equippableItemID, bonusValue) VALUES 
(1, 1, 5),
(2, 2, 10),
(3, 3, 8),
(4, 4, 15),
(5, 5, 20);

-- ===========================
-- Sample Data for ConsumableBonus
-- ===========================
INSERT INTO ConsumableBonus (statisticID, consumableID, bonusPercentage, bonusCap) VALUES 
(1, 1, 0.05, NULL),
(2, 2, 0.10, NULL),
(3, 3, 0.08, NULL),
(4, 4, 0.12, NULL),
(5, 5, 0.15, NULL);

-- ===========================
-- Sample Data for GearJobRequirement
-- ===========================
INSERT INTO GearJobRequirement (gearID, jobID) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);


-- ===========================
-- Sample Data for CharacterStats
-- ===========================
INSERT INTO CharacterStats (characterID, statisticID, value) VALUES 
(1, 1, 20),
(2, 2, 35),
(3, 3, 25),
(4, 4, 40),
(5, 5, 50);

