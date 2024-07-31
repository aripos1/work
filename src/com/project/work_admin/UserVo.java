package com.project.work_admin;

public class UserVo {

	private int employeeId;
	private String empName;
	private String loginID;
	private String loginPW;
	private String eMail;
	private int departmentId;
	private String departmentName;
	private String phoneNumber;
	private String addRess;
	private String hireDate;

	public UserVo() {

	}

	public UserVo(String loginID, String loginPW) {
		super();
		this.loginID = loginID;
		this.loginPW = loginPW;
	}


	public UserVo(int employeeId, String empName, String loginID, String loginPW, String eMail, int departmentId,
			String departmentName, String phoneNumber, String addRess, String hireDate) {
	
		this.employeeId = employeeId;
		this.empName = empName;
		this.loginID = loginID;
		this.loginPW = loginPW;
		this.eMail = eMail;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.phoneNumber = phoneNumber;
		this.addRess = addRess;
		this.hireDate = hireDate;
	}

	public UserVo(int employeeId, String empName, String loginID, String loginPW, String eMail, int departmentId,
			String phoneNumber, String addRess, String hireDate) {

		this.employeeId = employeeId;
		this.empName = empName;
		this.loginID = loginID;
		this.loginPW = loginPW;
		this.eMail = eMail;
		this.departmentId = departmentId;
		this.phoneNumber = phoneNumber;
		this.addRess = addRess;
		this.hireDate = hireDate;
	}

	public final String getDepartmentName() {
		return departmentName;
	}

	public final void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public final int getEmployeeId() {
		return employeeId;
	}

	public final void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public final String getEmpName() {
		return empName;
	}

	public final void setEmpName(String empName) {
		this.empName = empName;
	}

	public final String getLoginID() {
		return loginID;
	}

	public final void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public final String getLoginPW() {
		return loginPW;
	}

	public final void setLoginPW(String loginPW) {
		this.loginPW = loginPW;
	}

	public final String geteMail() {
		return eMail;
	}

	public final void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public final int getDepartmentId() {
		return departmentId;
	}

	public final void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public final String getPhoneNumber() {
		return phoneNumber;
	}

	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public final String getAddRess() {
		return addRess;
	}

	public final void setAddRess(String addRess) {
		this.addRess = addRess;
	}

	public final String getHireDate() {
		return hireDate;
	}

	public final void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "UserVo [employeeId=" + employeeId + ", empName=" + empName + ", loginID=" + loginID + ", loginPW="
				+ loginPW + ", eMail=" + eMail + ", departmentId=" + departmentId + ", phoneNumber=" + phoneNumber
				+ ", addRess=" + addRess + ", hireDate=" + hireDate + "]";
	}

}
