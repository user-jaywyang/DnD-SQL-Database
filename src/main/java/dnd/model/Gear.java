package dnd.model;

import java.util.Objects;

public class Gear extends EquippableItem{
	private GearSlot gearSlot;
	
	public Gear(int prototypeID, String itemName, Integer itemLevel, Float itemPrice, Integer itemMaxStackSize,
			Integer requiredLevel, GearSlot gearSlot) {
		super(prototypeID, itemName, itemLevel, itemPrice, itemMaxStackSize, requiredLevel);
		this.gearSlot = gearSlot;
	}

	public GearSlot getSlotID() {
		return this.gearSlot;
	}

	public void setSlotID(GearSlot gearSlot) {
		this.gearSlot = gearSlot;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(gearSlot);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gear other = (Gear) obj;
		return Objects.equals(gearSlot, other.gearSlot);
	}

	@Override
	  public String toString() {
	    return String.format(
	      "Gear(%s, %s)",
	      super.fieldsToString(),
	      this.gearSlot.toString()
	    );
	  }
		
}
