/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class GearSlot {
	private int slotID;
	private String slotName;
	
	// constructor 
	public GearSlot(int slotID, String slotName) {
		this.slotID = slotID;
		this.slotName = slotName;
	}

	// getters/setters
	
	public int getSlotID() {
		return slotID;
	}

	public void setSlotID(int slotID) {
		this.slotID = slotID;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	
	// equals/hash/toString

	@Override
	public int hashCode() {
		return Objects.hash(slotID, slotName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GearSlot other = (GearSlot) obj;
		return slotID == other.slotID && Objects.equals(slotName, other.slotName);
	}

	@Override
	public String toString() {
		return "GearSlot [slotID=" + slotID + ", slotName=" + slotName + "]";
	}
	
	
	
	
	

}
