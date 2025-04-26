/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class Player {
	
	private int playerID;
	private String fullName;
	private String email;
	
	// constructor
	public Player(int playerID, String fullName, String email) {
		this.playerID = playerID;
		this.fullName = fullName;
		this.email = email;
	}
	
	// getters/setters

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// equals/hash/toString

	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, playerID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& playerID == other.playerID;
	}

	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", fullName=" + fullName + ", email=" + email + "]";
	}

}
