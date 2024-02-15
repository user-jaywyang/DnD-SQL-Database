package dnd.model;

import java.util.Objects;

public class Currency {
	private int currencyID;
	private String currencyName;
	private Integer cap;		// nullable
	private Integer weeklyCap;	// nullable
	
	public Currency(int currencyID, String currencyName, Integer cap, Integer weeklyCap) {
		this.currencyID = currencyID;
		this.currencyName = currencyName;
		this.cap = cap;
		this.weeklyCap = weeklyCap;
	}

	public int getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getCap() {
		return cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public Integer getWeeklyCap() {
		return weeklyCap;
	}

	public void setWeeklyCap(Integer weeklyCap) {
		this.weeklyCap = weeklyCap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cap, currencyID, currencyName, weeklyCap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		return Objects.equals(cap, other.cap) && currencyID == other.currencyID
				&& Objects.equals(currencyName, other.currencyName) && Objects.equals(weeklyCap, other.weeklyCap);
	}

	@Override
	public String toString() {
		return "Currency [currencyID=" + currencyID + ", currencyName=" + currencyName + ", cap=" + cap + ", weeklyCap="
				+ weeklyCap + "]";
	}
}
