package com.project.work_admin;

public class DepartmentVo {

	private int departmentId;
	private String departmentName;
	private int managerId;
	
	
	public DepartmentVo() {
		super();
	}


	public DepartmentVo(int departmentId, String departmentName, int managerId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
	}


	public final int getDepartmentId() {
		return departmentId;
	}


	public final void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public final String getDepartmentName() {
		return departmentName;
	}


	public final void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public final int getManagerId() {
		return managerId;
	}


	public final void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	@Override
	public String toString() {
		return "DepartmentVo [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId="
				+ managerId + "]";
	}

	
}
