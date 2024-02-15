package dnd.model;

import java.util.Objects;

public class GameCharacter {
	private int characterID;
	private Player player;
	private String firstName;
	private String lastName;
	private Clan clan;
	private Job currentJob;
	private Weapon equippedWeapon;
	
	// constructor
	public GameCharacter(int characterID,
			Player player,
			String firstName,
			String lastName,
			Clan clan,
			Job currentJob,
			Weapon equippedWeapon) {
		this.characterID = characterID;
		this.player = player;
		this.firstName = firstName;
		this.lastName = lastName;
		this.clan = clan;
		this.currentJob = currentJob;
		this.equippedWeapon = equippedWeapon;
	}
	
	// getters/setters

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}
	
	
	public Weapon getCurrWeapon() {
		return equippedWeapon;
	}

	public void setCurrWeapon(Weapon currWeapon) {
		this.equippedWeapon = currWeapon;
	}
	
	// equals/hash/toString

	@Override
	public int hashCode() {
		return Objects.hash(characterID, clan, equippedWeapon, currentJob, firstName, lastName, player);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameCharacter other = (GameCharacter) obj;
		return characterID == other.characterID && Objects.equals(clan, other.clan)
				&& Objects.equals(equippedWeapon, other.equippedWeapon) && Objects.equals(currentJob, other.currentJob)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(player, other.player);
	}

	@Override
	public String toString() {
		return "GameCharacter [characterID=" + characterID + ", player=" + player + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", clan=" + clan + ", currentJob=" + currentJob + ", currWeapon="
				+ equippedWeapon + "]";
	}
}
