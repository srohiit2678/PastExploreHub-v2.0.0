package com.pastexplorehub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminActivityDTO {
    
    private String guideName;
    private String department;
    private long approved; // Use long because SUM/COUNT returns Long
    private long pending;
    private long rejected;
    private int efficiency;
    // Manual Constructor (Required for JPQL 'SELECT new' if not using Lombok)
    public AdminActivityDTO(String guideName, String department, Long approved, Long pending, Long rejected) {
        this.guideName = guideName;
        this.department = department;
        this.approved = (approved != null) ? approved : 0;
        this.pending = (pending != null) ? pending : 0;
        this.rejected = (rejected != null) ? rejected : 0;
        this.efficiency = this.GetEfficiency();
    }

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getApproved() {
		return approved;
	}

	public void setApproved(long approved) {
		this.approved = approved;
	}

	public long getPending() {
		return pending;
	}

	public void setPending(long pending) {
		this.pending = pending;
	}

	public long getRejected() {
		return rejected;
	}

	public void setRejected(long rejected) {
		this.rejected = rejected;
	}
    
	public void setEfficiency(long efficiency) {
		this.efficiency = this.GetEfficiency();
	}
	public int GetEfficiency(){
		int total = (int)((this.approved*5)+(this.pending*3)+(this.rejected*1));
		int efficiency = (total*100)/12;
		return efficiency;
	}
	    
}