package dnd.model;

import java.util.Objects;

public abstract class ItemPrototype{
	protected int prototypeID;
	protected String itemName;
	protected int itemLevel;
	protected float itemPrice;
	protected int itemMaxStackSize;
	
	protected static final float DEFAULT_ITEM_PRICE = 0;

	
	// general constructor for different combinations of default values
	// IMPT: When call, pass all 5 args, to use defaults set --> null
	
	public ItemPrototype(int prototypeID, String itemName, int itemLevel, Float itemPrice, int itemMaxStackSize) {
	    this.prototypeID = prototypeID;
	    this.itemName = itemName;
	    this.itemLevel = itemLevel;
	    this.itemPrice = (itemPrice != null) ? itemPrice : DEFAULT_ITEM_PRICE;
	    this.itemMaxStackSize = itemMaxStackSize;
	}

	// getters/setters

	public int getPrototypeID() {
		return prototypeID;
	}


	public void setPrototypeID(int prototypeID) {
		this.prototypeID = prototypeID;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getItemLevel() {
		return itemLevel;
	}


	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}


	public float getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}


	public int getItemMaxStackSize() {
		return itemMaxStackSize;
	}


	public void setItemMaxStackSize(int itemMaxStackSize) {
		this.itemMaxStackSize = itemMaxStackSize;
	}

	
	
	// equals/hash/toString

	public static float getDefaultItemPrice() {
		return DEFAULT_ITEM_PRICE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemLevel, itemMaxStackSize, itemName, itemPrice, prototypeID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPrototype other = (ItemPrototype) obj;
		return itemLevel == other.itemLevel && itemMaxStackSize == other.itemMaxStackSize
				&& Objects.equals(itemName, other.itemName)
				&& Float.floatToIntBits(itemPrice) == Float.floatToIntBits(other.itemPrice)
				&& prototypeID == other.prototypeID;
	}


	@Override
	public String toString() {
		return "ItemPrototype [prototypeID=" + prototypeID + ", itemName=" + itemName + ", itemLevel=" + itemLevel
				+ ", itemPrice=" + itemPrice + ", itemMaxStackSize=" + itemMaxStackSize + "]";
	}
	
	protected String fieldsToString() {
	    return String.format(
	      "\"%d\", \"%s\", \"%d\", \"%.2f\", \"%d\"",
	      this.prototypeID,
	      this.itemName,
	      this.itemLevel,
	      this.itemPrice,
	      this.itemMaxStackSize);
	}

}