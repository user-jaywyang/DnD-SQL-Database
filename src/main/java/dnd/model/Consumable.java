/**
 * @author Jay Yang
 */

package dnd.model;

public class Consumable extends ItemPrototype{
		
	
	// constructor
	// always input all 5 args, to use default vals, set as null
	public Consumable(int prototypeID, String itemName, int itemLevel, Float itemPrice, int itemMaxStackSize) {
        super(prototypeID, itemName,
        		itemLevel,
	            itemPrice != null ? itemPrice : DEFAULT_ITEM_PRICE, 
	            itemMaxStackSize);
    }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
	    return String.format("Consumable(%s)", super.fieldsToString());
	}
}
