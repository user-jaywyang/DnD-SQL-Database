package dnd.model;

import java.util.Objects;

public class Races{
	private int raceID;
	private String raceName;
	
	// constructor
	public Races(int raceID, String raceName) {
		this.raceID = raceID;
		this.raceName = raceName;

	}
	
	
	// getters/setters

	public int getRaceID() {
		return raceID;
	}

	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	// hash/equals/toString

	@Override
	public int hashCode() {
		return Objects.hash(raceID, raceName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Races other = (Races) obj;
		return raceID == other.raceID && Objects.equals(raceName, other.raceName);
	}


	@Override
	public String toString() {
		return "Races [raceID=" + raceID + ", raceName=" + raceName + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}