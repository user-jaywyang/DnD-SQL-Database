/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class InventorySlot {
	private GameCharacter character;
	private int slotNumber;
	private ItemPrototype prototypeID;
	private int stackSize;
	
	private static final int DEFAULT_STACK_SIZE = 1;

	
	public InventorySlot(GameCharacter character, int slotNumber, ItemPrototype prototypeID,
			Integer stackSize) {
		this.character = character;
		this.slotNumber = slotNumber;
		this.prototypeID = prototypeID;
		this.stackSize = stackSize != null ? stackSize : DEFAULT_STACK_SIZE;
	}


	public GameCharacter getCharacter() {
		return character;
	}


	public void setCharacter(GameCharacter character) {
		this.character = character;
	}


	public int getSlotNumber() {
		return slotNumber;
	}


	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}


	public ItemPrototype getPrototypeID() {
		return prototypeID;
	}


	public void setPrototypeID(ItemPrototype prototypeID) {
		this.prototypeID = prototypeID;
	}


	public int getStackSize() {
		return stackSize;
	}


	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}


	@Override
	public int hashCode() {
		return Objects.hash(character, prototypeID, slotNumber, stackSize);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventorySlot other = (InventorySlot) obj;
		return Objects.equals(character, other.character)
				&& Objects.equals(prototypeID, other.prototypeID) && slotNumber == other.slotNumber
				&& stackSize == other.stackSize;
	}


	@Override
	public String toString() {
		return "InventorySlot [character=" + character + ", slotNumber=" + slotNumber
				+ ", prototypeID=" + prototypeID + ", stackSize=" + stackSize + "]";
	}

}
