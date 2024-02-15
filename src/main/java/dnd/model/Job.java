package dnd.model;

import java.util.Objects;

public class Job {
	private int jobID;
	private String jobName;
	
	// constructor
	public Job(int jobID, String jobName) {
		this.jobID = jobID;
		this.jobName = jobName;
	}
	
	// getters/setters

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	// equals/hash/toString

	@Override
	public int hashCode() {
		return Objects.hash(jobID, jobName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		return jobID == other.jobID && Objects.equals(jobName, other.jobName);
	}

	@Override
	public String toString() {
		return "Job [jobID=" + jobID + ", jobName=" + jobName + "]";
	}
	
	
	
	

}
