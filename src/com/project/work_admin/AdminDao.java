package com.project.work_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.0.75:3306/work_db";
	private String id = "workadmin";
	private String pw = "workadmin123";

	public AdminDao() {

	}

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public boolean loginProcess(String id, String pw) {

		// 0. import java.sql.*;

		this.getConnection();

		try {
			String qeury = "";
			qeury += " select login_ID ";
			qeury += "		 ,login_PW ";
			qeury += " from employees ";
			qeury += " where login_ID like ? ";
			qeury += " and login_PW like ? ";

			pstmt = conn.prepareStatement(qeury);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();
			// 4.결과처리
			if (rs.next()) {
				return true; // 로그인 성공
			} else {
				return false; // 로그인 실패
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return false;
	}

	public void workAllList() {
		List<WorkVo> workAllList = new ArrayList<WorkVo>();

		// 0. import java.sql.*;

		this.getConnection();
		try {

			String query = "";
			query += " select  e.employee_id ";
			query += " 		   ,w.start_dateAndtime ";
			query += "         ,w.end_dateAndtime ";
			query += "         ,w.work_start_status";
			query += "         ,w.work_end_status ";
			query += "         ,d.department_name ";
			query += "         ,e.emp_name ";
			query += " from employees e, department d, workdate w ";
			query += " where e.department_id = d.department_id ";
			query += " and w.employee_id = e.employee_id ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int employeeId = rs.getInt("e.employee_id");
				String startDateAndtime = rs.getString("w.start_dateAndtime");
				String endDateAndtime = rs.getString("w.end_dateAndtime");
				String workStartStauts = rs.getString("w.work_start_status");
				String workEndStauts = rs.getString("w.work_end_status");
				String departmentName = rs.getString("d.department_name");
				String empName = rs.getString("e.emp_name");
				WorkVo workVo = new WorkVo(employeeId, startDateAndtime, endDateAndtime, workStartStauts, workEndStauts,
						departmentName, empName);

				workAllList.add(workVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		for (WorkVo Vo : workAllList) {
			System.out.print(" 사번 :" + Vo.getEmployeeId() + "\t");
			System.out.print(" 출근시간 :" + Vo.getStartDateAndtime() + " " + Vo.getWorkStartStauts() + "\t");
			System.out.print(" 퇴근시간:" + Vo.getEndDateAndtime() + " " + Vo.getWorkEndStauts() + "\t");
			System.out.print(" 부서 :" + Vo.getDepartmentName() + "\t");
			System.out.println(" 이름 :" + Vo.getEmpName());

		}
		this.close();

	}

	public void workSelectList(int num) {
		List<WorkVo> workSelectList = new ArrayList<WorkVo>();
		this.getConnection();

		try {

			String query = "";
			query += " select  e.employee_id ";
			query += " 		   ,w.start_dateAndtime ";
			query += "         ,w.end_dateAndtime ";
			query += "         ,w.work_start_status";
			query += "         ,w.work_end_status ";
			query += "         ,d.department_name ";
			query += "         ,e.emp_name ";
			query += " from employees e, department d, workdate w ";
			query += " where e.department_id = d.department_id ";
			query += " and w.employee_id = e.employee_id ";
			query += " and e.employee_id like ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int employeeId = rs.getInt("e.employee_id");
				String startDateAndtime = rs.getString("w.start_dateAndtime");
				String endDateAndtime = rs.getString("w.end_dateAndtime");
				String workStartStauts = rs.getString("w.work_start_status");
				String workEndStauts = rs.getString("w.work_end_status");
				String departmentName = rs.getString("d.department_name");
				String empName = rs.getString("e.emp_name");
				WorkVo workVo = new WorkVo(employeeId, startDateAndtime, endDateAndtime, workStartStauts, workEndStauts,
						departmentName, empName);

				workSelectList.add(workVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		for (WorkVo Vo : workSelectList) {
			System.out.println("  이름 : " + Vo.getEmpName());
			System.out.println("  출근시간 : " + Vo.getStartDateAndtime() + "\t" + Vo.getWorkStartStauts());
			System.out.println("  퇴근시간 : " + Vo.getEndDateAndtime() + "\t" + Vo.getWorkEndStauts());

		}
		this.close();
	}

	public int updateWorkStart(int num) {
		int count = -1;

		this.getConnection();
		try {

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '출근' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(" [" + count + "] 건이 수정 되었습니다.");

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	}

	public int updateWorkLate(int num) {
		int count = -1;
		this.getConnection();
		try {

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '지각' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(" [" + count + "] 건이 수정 되었습니다.");

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	}

	public int updateWorkEarly(int num) {
		int count = -1;
		this.getConnection();

		try {

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '조퇴' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(" [" + count + "] 건이 수정 되었습니다.");

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	}

	public int updateWorkLeave(int num) {
		int count = -1;
		this.getConnection();

		try {

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '퇴근' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(" [" + count + "] 건이 수정 되었습니다.");

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	}

	public int updateWorkVacation(int num) {
		int count = -1;
		this.getConnection();
		try {

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '휴가' ";
			query += " 	   ,work_end_status = '휴가' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(" [" + count + "] 건이 수정 되었습니다.");

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	}

	public List<UserVo> selectEmployeesAll() {
		List<UserVo> UserList = new ArrayList<UserVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " select 	e.employee_id, ";
			query += "		    e.emp_name, ";
			query += "          e.login_ID, ";
			query += "          e.login_PW, ";
			query += "          e.email, ";
			query += "          d.department_id, ";
			query += "          d.department_name, ";
			query += "          e.phone_number, ";
			query += "          e.address, ";
			query += "          e.hire_date, ";
			query += "          d.manager_id ";
			query += " from employees e, department d ";
			query += " where e.department_id = d.department_id ";
			query += " order by e.employee_id ";

			// *바인딩
			pstmt = conn.prepareStatement(query);

			// *실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기
			while (rs.next()) {
				int eId = rs.getInt("e.employee_id");
				String name = rs.getString("e.emp_name");
				String ID = rs.getString("e.login_ID");
				String PW = rs.getString("e.login_PW");
				String EM = rs.getString("e.email");
				int deID = rs.getInt("d.department_id");
				String dName = rs.getString("d.department_name");
				String num = rs.getString("e.phone_number");
				String add = rs.getString("e.address");
				String HD = rs.getString("e.hire_date");
				int mgId = rs.getInt("d.manager_id");

				UserVo userVo = new UserVo(eId, name, ID, PW, EM, deID, dName, num, add, HD, mgId);
				UserList.add(userVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return UserList;
	}

	// 부서 수정

	public int updateDepartment(String oldDepartname, int oldmanagerId, String departmentName, int managerId) {
		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " update department ";
			query += " set    department_name = ?, ";
			query += "        manager_id = ? ";
			query += " where  department_name = ? ";
			query += " and manager_id = ? ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, departmentName);
			pstmt.setInt(2, managerId);
			pstmt.setString(3, oldDepartname);
			pstmt.setInt(4, oldmanagerId);

			// *실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(" [" + count + "] 건이 수정 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}

	// 부서 추가

	public int insertDepartment(String departmentname, int managerId) {
		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " insert into department ";
			query += " values(null, ?, ?) ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, departmentname);
			pstmt.setInt(2, managerId);

			// *실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(" [" + count + "] 건이 등록 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}
	// 부서 삭제

	public int deleteDepartment(int departmentId) {
		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " delete from department ";
			query += " where department_id = ? ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, departmentId);

			// *실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(" [" + count + "] 건이 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}

	public List<UserVo> selectdepartment(int departmentId) {
		List<UserVo> UserList = new ArrayList<UserVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비

			String query = "";
			query += " select 	e.employee_id, ";
			query += "		    e.emp_name, ";
			query += "          e.login_ID, ";
			query += "          e.login_PW, ";
			query += "          e.email, ";
			query += "          d.department_id, ";
			query += "          d.department_name, ";
			query += "          e.phone_number, ";
			query += "          e.address, ";
			query += "          e.hire_date, ";
			query += "          d.manager_id ";
			query += " from department d ";
			query += " Left JOIN employees e ON e.department_id = d.department_id ";
			query += " where (d.department_id = ? OR ? IS NULL)  ";
			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, departmentId);
			pstmt.setInt(2, departmentId);
			// *실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기
			while (rs.next()) {
				int eId = rs.getInt("employee_id");
				String name = rs.getString("emp_name");
				String ID = rs.getString("login_ID");
				String PW = rs.getString("login_PW");
				String EM = rs.getString("email");
				int deID = rs.getInt("department_id");
				String dName = rs.getString("department_name");
				String num = rs.getString("phone_number");
				String add = rs.getString("address");
				String HD = rs.getString("hire_date");
				int mgId = rs.getInt("manager_id");

				UserVo userVo = new UserVo(eId, name, ID, PW, EM, deID, dName, num, add, HD, mgId);
				UserList.add(userVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return UserList;
	}

	public List<UserVo> selectUser(String userName) {
		List<UserVo> userVo = new ArrayList<>();
		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비
			String query = "";
			query += " select 	e.employee_id, ";
			query += "		    e.emp_name, ";
			query += "          e.login_ID, ";
			query += "          e.login_PW, ";
			query += "          e.email, ";
			query += "          d.department_id, ";
			query += "          d.department_name, ";
			query += "          e.phone_number, ";
			query += "          e.address, ";
			query += "          e.hire_date, ";
			query += "          d.manager_id ";
			query += " from employees e, department d ";
			query += " where e.department_id = d.department_id ";
			query += " and emp_name like ?  ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			String select = "%" + userName + "%";
			pstmt.setString(1, select);

			// *실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String empName = rs.getString("emp_name");
				String loginID = rs.getString("login_ID");
				String loginPW = rs.getString("login_PW");
				String eMail = rs.getString("email");
				int departmentId = rs.getInt("department_id");
				String departmentName = rs.getString("department_name");
				String phoneNumber = rs.getString("phone_number");
				String addRess = rs.getString("address");
				String hireDate = rs.getString("hire_date");
				int mgId = rs.getInt("manager_id");

				UserVo vo = new UserVo(employeeId, empName, loginID, loginPW, eMail, departmentId, departmentName,
						phoneNumber, addRess, hireDate, mgId);
				userVo.add(vo);

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return userVo;
	}

	public List<DepartmentVo> departmentList() {
		List<DepartmentVo> dList = new ArrayList<DepartmentVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비

			String query = "";
			query += " select department_id, ";
			query += "  	  department_name, ";
			query += "        manager_id ";
			query += " from department  ";

			// *바인딩
			pstmt = conn.prepareStatement(query);

			// *실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기
			while (rs.next()) {

				int deID = rs.getInt("department_id");
				String dName = rs.getString("department_name");

				int mgId = rs.getInt("manager_id");

				DepartmentVo dVo = new DepartmentVo(deID, dName, mgId);
				dList.add(dVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return dList;
	}

}