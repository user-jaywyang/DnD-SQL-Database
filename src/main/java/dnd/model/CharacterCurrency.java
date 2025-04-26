/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class CharacterCurrency {
	private GameCharacter character;
	private Currency currency;
	private int amountHeld;
	private int amountEarnedThisWeek;
	
	private static final int DEFAULT_AMOUNT_HELD = 0;
	private static final int DEFAULT_AMOUNT_EARNED = 0;
	
	
	public CharacterCurrency(GameCharacter character, Currency currency, Integer amountHeld, Integer amountEarnedThisWeek) {
		this.character = character;
		this.currency = currency;
		this.amountHeld = amountHeld != null ? amountHeld : DEFAULT_AMOUNT_HELD;
		this.amountEarnedThisWeek = amountEarnedThisWeek != null ? amountEarnedThisWeek : DEFAULT_AMOUNT_EARNED;
	}


	public GameCharacter getCharacter() {
		return character;
	}


	public void setCharacter(GameCharacter character) {
		this.character = character;
	}


	public Currency getCurrency() {
		return currency;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public int getAmountHeld() {
		return amountHeld;
	}


	public void setAmountHeld(int amountHeld) {
		this.amountHeld = amountHeld;
	}


	public int getAmountEarnedThisWeek() {
		return amountEarnedThisWeek;
	}


	public void setAmountEarnedThisWeek(int amountEarnedThisWeek) {
		this.amountEarnedThisWeek = amountEarnedThisWeek;
	}

	

	public static int getDefaultAmountHeld() {
		return DEFAULT_AMOUNT_HELD;
	}


	public static int getDefaultAmountEarned() {
		return DEFAULT_AMOUNT_EARNED;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amountEarnedThisWeek, amountHeld, character, currency);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharacterCurrency other = (CharacterCurrency) obj;
		return amountEarnedThisWeek == other.amountEarnedThisWeek && amountHeld == other.amountHeld
				&& Objects.equals(character, other.character) && Objects.equals(currency, other.currency);
	}


	@Override
	public String toString() {
		return "CharacterCurrency [character=" + character + ", currency=" + currency + ", amountHeld=" + amountHeld
				+ ", amountEarnedThisWeek=" + amountEarnedThisWeek + "]";
	}
}
