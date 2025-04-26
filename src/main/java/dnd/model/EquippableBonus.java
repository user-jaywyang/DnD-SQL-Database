/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class EquippableBonus {
	private Statistic statistic;
	private EquippableItem equippableItem;
	private int bonusValue;
	
	
	public EquippableBonus(Statistic statistic, EquippableItem equippableItem, int bonusValue) {
		this.statistic = statistic;
		this.equippableItem = equippableItem;
		this.bonusValue = bonusValue;
	}


	public Statistic getStatistic() {
		return statistic;
	}


	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}


	public EquippableItem getEquippableItem() {
		return equippableItem;
	}


	public void setEquippableItem(EquippableItem equippableItem) {
		this.equippableItem = equippableItem;
	}


	public int getBonusValue() {
		return bonusValue;
	}


	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}


	@Override
	public int hashCode() {
		return Objects.hash(bonusValue, equippableItem, statistic);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquippableBonus other = (EquippableBonus) obj;
		return bonusValue == other.bonusValue && Objects.equals(equippableItem, other.equippableItem)
				&& Objects.equals(statistic, other.statistic);
	}


	@Override
	public String toString() {
		return "EquippableBonus [statistic=" + statistic + ", equippableItem=" + equippableItem + ", bonusValue="
				+ bonusValue + "]";
	}
	
	
	
	

}
