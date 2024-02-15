package dnd.model;

import java.util.Objects;

public class ConsumableBonus {
	private Statistic statistic;
	private Consumable consumable;
	private float bonusPercentage;
	private float bonusCap;
	
	public ConsumableBonus(Statistic statistic, Consumable consumable, float bonusPercentage, float bonusCap) {
		this.statistic = statistic;
		this.consumable = consumable;
		this.bonusPercentage = bonusPercentage;
		this.bonusCap = bonusCap;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public Consumable getConsumable() {
		return consumable;
	}

	public void setConsumable(Consumable consumable) {
		this.consumable = consumable;
	}

	public float getBonusPercentage() {
		return bonusPercentage;
	}

	public void setBonusPercentage(float bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}

	public float getBonusCap() {
		return bonusCap;
	}

	public void setBonusCap(float bonusCap) {
		this.bonusCap = bonusCap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bonusCap, bonusPercentage, consumable, statistic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumableBonus other = (ConsumableBonus) obj;
		return Float.floatToIntBits(bonusCap) == Float.floatToIntBits(other.bonusCap)
				&& Float.floatToIntBits(bonusPercentage) == Float.floatToIntBits(other.bonusPercentage)
				&& Objects.equals(consumable, other.consumable) && Objects.equals(statistic, other.statistic);
	}

	@Override
	public String toString() {
		return "ConsumableBonus [statistic=" + statistic + ", consumable=" + consumable + ", bonusPercentage="
				+ bonusPercentage + ", bonusCap=" + bonusCap + "]";
	}
}
