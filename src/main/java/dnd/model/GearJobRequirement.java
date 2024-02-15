package dnd.model;

import java.util.Objects;

public class GearJobRequirement {
	private Gear gear;
	private Job job;
	
	public GearJobRequirement(Gear gear, Job job) {
		this.gear = gear;
		this.job = job;
	}
	

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}


	@Override
	public int hashCode() {
		return Objects.hash(gear, job);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GearJobRequirement other = (GearJobRequirement) obj;
		return Objects.equals(gear, other.gear) && Objects.equals(job, other.job);
	}


	@Override
	public String toString() {
		return "GearJobRequirement [gear=" + gear + ", job=" + job + "]";
	}
}
