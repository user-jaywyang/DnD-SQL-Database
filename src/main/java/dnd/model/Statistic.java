/**
 * @author Jay Yang
 */

package dnd.model;

import java.util.Objects;

public class Statistic {
	private int statisticID;
	private String statisticName;
	
	public Statistic(int statisticID, String statisticName) {
		this.statisticID = statisticID;
		this.statisticName = statisticName;
	}
	
	

	public int getStatisticID() {
		return statisticID;
	}



	public void setStatisticID(int statisticID) {
		this.statisticID = statisticID;
	}



	public String getStatisticName() {
		return statisticName;
	}



	public void setStatisticName(String statisticName) {
		this.statisticName = statisticName;
	}



	@Override
	public int hashCode() {
		return Objects.hash(statisticID, statisticName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statistic other = (Statistic) obj;
		return statisticID == other.statisticID && Objects.equals(statisticName, other.statisticName);
	}

	@Override
	public String toString() {
		return "Statistic [statisticID=" + statisticID + ", statisticName=" + statisticName + "]";
	}

}
