package dnd.model;

import java.util.Objects;

public abstract class EquippableItem extends ItemPrototype {
	protected int requiredLevel;
	
	private static final int DEFAULT_REQUIRED_LEVEL = 1;

	// constructor
	// always input all 6 args, to use default vals, set as null
	public EquippableItem(int prototypeID, String itemName, Integer itemLevel, Float itemPrice,
			Integer itemMaxStackSize, Integer requiredLevel) {
		
		super(prototypeID, itemName, itemLevel, itemPrice, itemMaxStackSize);
		this.requiredLevel = (requiredLevel != null) ? requiredLevel : DEFAULT_REQUIRED_LEVEL;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int requiredLevel) {
		this.requiredLevel = requiredLevel;
	}
	
	

	public static int getDefaultRequiredLevel() {
		return DEFAULT_REQUIRED_LEVEL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(requiredLevel);
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
		EquippableItem other = (EquippableItem) obj;
		return requiredLevel == other.requiredLevel;
	}
	
	  @Override
	  public String toString() {
	    return String.format(
	      "Equippable(%s, %s)",
	      super.fieldsToString(),
	      this.requiredLevel
	    );
	  }
	  
	  protected String fieldsToString() {
		    return String.format(
		      "\"%d\", \"%s\", \"%d\", \"%.2f\", \"%d\", \"%d\"",
		      super.getItemName(),
		      super.getItemLevel(),
		      super.getItemPrice(),
		      super.getItemMaxStackSize(),
		      this.requiredLevel);
		}
}
