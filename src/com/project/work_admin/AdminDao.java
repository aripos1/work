package com.project.work_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

	public boolean loginProcess(String id, String pw) {

		// 0. import java.sql.*;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행

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

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
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
		return false;

	}

	public void workAllList() {
		List<WorkVo> workAllList = new ArrayList<WorkVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  e.employee_id ";
			query += " 		   ,w.start_dateAndtime ";
			query += "         ,w.end_dateAndtime ";
			query += "         ,w.work_start_status";
			query += "         ,w.work_end_status ";
			query += "         ,d.department_name ";
			query += "         ,e.emp_name ";
			query += " from employees e, department d, workdate w ";
			query += " where e.employee_id = d.department_id ";
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

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

			for (WorkVo Vo : workAllList) {
				System.out.print(Vo.getEmployeeId() + "\t");
				System.out.print(Vo.getStartDateAndtime() + "\t");
				System.out.print(Vo.getEndDateAndtime() + "\t");
				System.out.print(Vo.getWorkStartStauts() + "\t");
				System.out.print(Vo.getWorkEndStauts() + "\t");
				System.out.print(Vo.getDepartmentName() + "\t");
				System.out.println(Vo.getEmpName());

			}
		}
	}

	public void workSelectList(int num) {
		List<WorkVo> workSelectList = new ArrayList<WorkVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  e.employee_id ";
			query += " 		   ,w.start_dateAndtime ";
			query += "         ,w.end_dateAndtime ";
			query += "         ,w.work_start_status";
			query += "         ,w.work_end_status ";
			query += "         ,d.department_name ";
			query += "         ,e.emp_name ";
			query += " from employees e, department d, workdate w ";
			query += " where e.employee_id = d.department_id ";
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

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

			for (WorkVo Vo : workSelectList) {
				System.out.println("이름 : " + Vo.getEmpName());
				System.out.println("출근시간 : " + Vo.getStartDateAndtime() + "\t" + Vo.getWorkStartStauts());
				System.out.println("퇴근시간 : " + Vo.getEndDateAndtime() + "\t" + Vo.getWorkEndStauts());

			}
		}
	}

	public int updateWorkStart(int num) {
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '출근' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(count + "건 수정 되었습니다.");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return count;

	}

	public int updateWorkLate(int num) {
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '지각' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(count + "건 수정 되었습니다.");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return count;

	}

	public int updateWorkEarly(int num) {
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '조퇴' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(count + "건 수정 되었습니다.");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return count;

	}

	public int updateWorkLeave(int num) {
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '퇴근' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(count + "건 수정 되었습니다.");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return count;

	}
	public int updateWorkVacation(int num) {
		int count = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/work_db";
			conn = DriverManager.getConnection(url, "admin", "admin");
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " update workdate ";
			query += " set work_start_status = '휴가' ";
			query += " 	   ,work_end_status = '휴가' ";
			query += " where employee_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			count = pstmt.executeUpdate();
			System.out.println(count + "건 수정 되었습니다.");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		return count;

	}
}