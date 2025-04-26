/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class EquippedGear {
	private GameCharacter character;
	private GearSlot gearSlot;
	private Gear gear;
	
	public EquippedGear(GameCharacter character, GearSlot gearSlot, Gear gear) {
		this.character = character;
		this.gearSlot = gearSlot;
		this.gear = gear;
	}
	
	public EquippedGear(GameCharacter character, GearSlot gearSlot) {
		this.character = character;
		this.gearSlot = gearSlot;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public void setCharacter(GameCharacter character) {
		this.character = character;
	}

	public GearSlot getGearSlot() {
		return gearSlot;
	}

	public void setGearSlot(GearSlot gearSlot) {
		this.gearSlot = gearSlot;
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(character, gear, gearSlot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquippedGear other = (EquippedGear) obj;
		return Objects.equals(character, other.character) && Objects.equals(gear, other.gear)
				&& Objects.equals(gearSlot, other.gearSlot);
	}

	@Override
	public String toString() {
		return "EquippedGear [character=" + character + ", gearSlot=" + gearSlot + ", gear=" + gear + "]";
	}
	
	
	
	
	

}
