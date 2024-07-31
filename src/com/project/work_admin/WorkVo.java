package com.project.work_admin;

public class WorkVo {

	private int workNumber;
	private String startDateAndtime;
	private String endDateAndtime;
	private String workStartStauts;
	private String workEndStauts;
	private int departmentId;
	private String departmentName;
	private int employeeId;
	private String empName;
	
	public WorkVo() {

	}

	public WorkVo(int workNumber, String startDateAndtime, String endDateAndtime, String workStartStauts, String workEndStauts) {

		this.workNumber = workNumber;
		this.startDateAndtime = startDateAndtime;
		this.endDateAndtime = endDateAndtime;
		this.workStartStauts = workStartStauts;
		this.workEndStauts = workEndStauts;
	}


	public WorkVo(int employeeId, String startDateAndtime, String endDateAndtime , String workStartStauts, String workEndStauts,String departmentName, String empName) {
	
		this.employeeId = employeeId;
		this.startDateAndtime = startDateAndtime;
		this.endDateAndtime = endDateAndtime;
		this.workStartStauts = workStartStauts;
		this.workEndStauts = workEndStauts;
		this.departmentName = departmentName;
		this.empName = empName;
	}

	public final int getWorkNumber() {
		return workNumber;
	}

	public final void setWorkNumber(int workNumber) {
		this.workNumber = workNumber;
	}

	public final String getStartDateAndtime() {
		return startDateAndtime;
	}

	public final void setStartDateAndtime(String startDateAndtime) {
		this.startDateAndtime = startDateAndtime;
	}

	public final String getEndDateAndtime() {
		return endDateAndtime;
	}

	public final void setEndDateAndtime(String endDateAndtime) {
		this.endDateAndtime = endDateAndtime;
	}

	public final String getWorkStartStauts() {
		return workStartStauts;
	}

	public final void setWorkStartStauts(String workStartStauts) {
		this.workStartStauts = workStartStauts;
	}

	public final String getWorkEndStauts() {
		return workEndStauts;
	}

	public final void setWorkEndStauts(String workEndStauts) {
		this.workEndStauts = workEndStauts;
	}

	public final String getDepartmentName() {
		return departmentName;
	}

	public final void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public final String getEmpName() {
		return empName;
	}

	public final void setEmpName(String empName) {
		this.empName = empName;
	}

	public final int getDepartmentId() {
		return departmentId;
	}

	public final void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public final int getEmployeeId() {
		return employeeId;
	}

	public final void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "WorkVo [workNumber=" + workNumber + ", startDateAndtime=" + startDateAndtime + ", endDateAndtime="
				+ endDateAndtime + ", workStartStauts=" + workStartStauts + ", workEndStauts=" + workEndStauts
				+ ", departmentName=" + departmentName + ", empName=" + empName + "]";
	}

	
}
