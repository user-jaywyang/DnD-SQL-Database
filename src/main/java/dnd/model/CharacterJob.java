/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class CharacterJob {
	private Job job;
	private GameCharacter character;
	private int level;
	private int experiencePoints;
	private boolean unlocked;
	
	private static final int DEFAULT_LEVEL = 1;
	private static final int DEFAULT_EXP = 0;
	private static final boolean DEFAULT_UNLOCKED = false;
	
	
	public CharacterJob(Job job, GameCharacter character, Integer level, Integer experiencePoints, Boolean unlocked) {
		this.job = job;
		this.character = character;
		this.level = level != null ? level : DEFAULT_LEVEL;
		this.experiencePoints = experiencePoints != null ? experiencePoints : DEFAULT_EXP;
		this.unlocked = unlocked != null ? unlocked : DEFAULT_UNLOCKED;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	public GameCharacter getCharacter() {
		return character;
	}


	public void setCharacter(GameCharacter character) {
		this.character = character;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getExperiencePoints() {
		return experiencePoints;
	}


	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}


	public boolean isUnlocked() {
		return unlocked;
	}


	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}


	public static int getDefaultLevel() {
		return DEFAULT_LEVEL;
	}


	public static int getDefaultExp() {
		return DEFAULT_EXP;
	}
	

	public static boolean isDefaultUnlocked() {
		return DEFAULT_UNLOCKED;
	}
	
	


	@Override
	public int hashCode() {
		return Objects.hash(character, experiencePoints, job, level, unlocked);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CharacterJob other = (CharacterJob) obj;
		return Objects.equals(character, other.character) && experiencePoints == other.experiencePoints
				&& Objects.equals(job, other.job) && level == other.level && unlocked == other.unlocked;
	}


	@Override
	public String toString() {
		return "CharacterJob [job=" + job + ", character=" + character + ", level=" + level + ", experiencePoints="
				+ experiencePoints + ", unlocked=" + unlocked + "]";
	}
}
