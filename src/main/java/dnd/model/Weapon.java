package dnd.model;

import java.util.Objects;

public class Weapon extends EquippableItem {
	private Job job;
	private int attackDamage;
	
	public Weapon(int prototypeID, String itemName, Integer itemLevel, Float itemPrice, Integer itemMaxStackSize,
			Integer requiredLevel, Job job, int attackDamage) {
		super(prototypeID, itemName, itemLevel, itemPrice, itemMaxStackSize, requiredLevel);
		this.job = job;
		this.attackDamage = attackDamage;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(attackDamage, job);
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
		Weapon other = (Weapon) obj;
		return attackDamage == other.attackDamage && Objects.equals(job, other.job);
	}
	
	@Override
	  public String toString() {
	    return String.format(
	      "Weapon(%s, %s, %d)",
	      super.fieldsToString(),
	      this.job.toString(),
	      this.attackDamage
	    );
	  }
	
	
	

}
