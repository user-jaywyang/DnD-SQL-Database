/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class Clan {
	private int clanID;
	private Races race;
	private String clanName;
	
	// Constructor
	public Clan(int clanID, Races race, String clanName) {
		this.clanID = clanID;
		this.race = race;
		this.clanName = clanName;
	}
	
	// getters/setters

	public int getClanID() {
		return clanID;
	}

	public void setClanID(int clanID) {
		this.clanID = clanID;
	}

	public Races getRace() {
		return race;
	}

	public void setRace(Races race) {
		this.race = race;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}
	
	
	// equals/hash/toString

	@Override
	public int hashCode() {
		return Objects.hash(clanID, clanName, race);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clan other = (Clan) obj;
		return clanID == other.clanID && Objects.equals(clanName, other.clanName) && Objects.equals(race, other.race);
	}

	@Override
	public String toString() {
		return "Clan [clanID=" + clanID + ", race=" + race + ", clanName=" + clanName + "]";
	}
	
	
	
	
	
	

}
