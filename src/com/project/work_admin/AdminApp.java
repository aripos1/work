package com.project.work_admin;

import java.util.List;
import java.util.Scanner;

public class AdminApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AdminDao adminDao = new AdminDao();

		while (true) {
			System.out.println("┌──────────────────────────────┐");
			System.out.println("│" + "\t" + "1. 로그인 2. 종료" + "\t" + "       │");
			System.out.println("└──────────────────────────────┘");
			System.out.print("▶입력 : ");

			int input = sc.nextInt();
			sc.nextLine();

			if (input == 1) {
				boolean login = false;
				System.out.println("┌──────────────────────────────┐");
				System.out.println("│     ●로그인 정보를 입력해주세요.     │");
				System.out.println("└──────────────────────────────┘");
				System.out.print("▶ID : ");
				String id = sc.nextLine();
				System.out.print("▶PW : ");
				String pw = sc.nextLine();

				login = adminDao.loginProcess(id, pw);

				if (login) {

					while (true) {
						System.out.println("┌──────────────────────────────┐");
						System.out.println("       ★ 관리자님 어서오세요!      ");
						System.out.println("          					   ");
						System.out.println("   1. 직원근태                    ");
						System.out.println("   2. 부서/직원 정보        	   ");
						System.out.println("   3. 종료                  	   ");
						System.out.println("                  	 		  ");
						System.out.println("└──────────────────────────────┘");
						System.out.print("▶입력 : ");
						int submenu = sc.nextInt();
						sc.nextLine();

						if (submenu == 1) {
							while (true) {
								System.out.println("┌──────────────────────────────┐");
								System.out.println("          ◆ 직원근태현황 ◆    	   ");
								System.out.println("          					   ");
								System.out.println("   1. 일일현황  				   ");
								System.out.println("   2. 근태 상태 수정  			   ");
								System.out.println("   3. 이전으로 				   ");
								System.out.println("          					   ");
								System.out.println("└──────────────────────────────┘");
								System.out.print("▶입력 : ");
								int workmenu = sc.nextInt();
								sc.nextLine();
								if (workmenu == 1) {
									System.out.println(
											" ──────────────────────────────────────────────────────────────────────────────────────────────────────────────");
									System.out.println(
											"                                                ◆ 일일현황 ◆         	   ");
									System.out.println();
									adminDao.workAllList();
									System.out.println(
											" ──────────────────────────────────────────────────────────────────────────────────────────────────────────────");
									System.out.println("※ 출력이 완료 되었습니다.");
								} else if (workmenu == 2) {
									System.out.println("┌──────────────────────────────┐");
									System.out.println(" 수정을 원하는 직원의 사번을 입력해주세요.");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									int employeeID = sc.nextInt();

									System.out.println(" ─────────────────────────────────────── ");
									System.out.println("   ☞ " + employeeID + " 의 정보를 불러옵니다.");
									System.out.println();
									adminDao.workSelectList(employeeID);
									System.out.println(" ─────────────────────────────────────── ");
									System.out.println("┌───────────────────────────────────────┐");
									System.out.println("   수정을 원하는 번호를 입력하세요.");
									System.out.println();
									System.out.println("   1.출근 2.지각 3.조퇴 4.퇴근 5.휴가 6.이전으로 ");
									System.out.println("└───────────────────────────────────────┘");
									System.out.print("▶입력 : ");
									int workNum = sc.nextInt();

									if (workNum == 1) {
										adminDao.updateWorkStart(employeeID);
									} else if (workNum == 2) {
										adminDao.updateWorkLate(employeeID);
									} else if (workNum == 3) {
										adminDao.updateWorkEarly(employeeID);
									} else if (workNum == 4) {
										adminDao.updateWorkLeave(employeeID);
									} else if (workNum == 5) {
										adminDao.updateWorkVacation(employeeID);
									} else if (workNum == 6) {
										break;
									} else {
										System.out.println("(ಥ﹏ಥ) 잘못된 입력입니다. 다시 시도해주세요.");
									}

								} else if (workmenu == 3) {
									break;// 이전으로 만들어야 됨
								}
							}

						} else if (submenu == 2) {

							while (true) {

								System.out.println("┌──────────────────────────────┐");
								System.out.println("         ◆ 부서/직원 정보 ◆ ");
								System.out.println();
								System.out.println("  1. 전직원 리스트 ");
								System.out.println("  2. 부서 리스트 ");
								System.out.println("  3. 부서명 수정 ");
								System.out.println("  4. 부서 추가 ");
								System.out.println("  5. 부서 삭제 ");
								System.out.println("  6. 검색 ");
								System.out.println("  7. 이전으로 ");
								System.out.println();
								System.out.println("└──────────────────────────────┘");
								System.out.print("▶입력 : ");
								int menuNo = sc.nextInt();
								sc.nextLine(); // 개행 문자 소비

								if (menuNo == 1) {

									List<UserVo> userList = adminDao.selectEmployeesAll();
									System.out.println(
											" ────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
									for (UserVo user : userList) {

										System.out.print("사번 :" + user.getEmployeeId() + "\t");
										System.out.print("이름 :" + user.getEmpName() + "\t");
										System.out.print("부서명 :" + user.getDepartmentName() + "\t");
										System.out.print("ID :" + user.getLoginID() + "\t");
										System.out.print("PW:" + user.getLoginPW() + "\t");
										System.out.print("입사일 :" + user.getHireDate() + "\t");
										System.out.print("HP :" + user.getPhoneNumber() + "\t");
										System.out.print("주소 :" + user.getAddRess() + "\t");
										System.out.println("이메일 :" + user.geteMail());

									}
									System.out.println(
											" ────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
									System.out.println("※ 출력이 완료 되었습니다.");

								}
								if (menuNo == 2) {

									List<DepartmentVo> dList = adminDao.departmentList();
									System.out.println(" ───────────────────────────────────────────────────");
									for (DepartmentVo dVo : dList) {

										System.out.print("부서번호 :" + dVo.getDepartmentId() + "\t");
										System.out.print("부서명 :" + dVo.getDepartmentName() + "\t");
										System.out.println("매니저ID :" + dVo.getManagerId() + "\t");

									}
									System.out.println(" ───────────────────────────────────────────────────");
									System.out.println("※ 출력이 완료 되었습니다.");
								} else if (menuNo == 3) {
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  현 부서명을 입력해주세요");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									String currentDept = sc.nextLine();
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  바뀔 부서명을 입력해주세요");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									String newDept = sc.nextLine();
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  현 매니저 번호를 입력하세요 ");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									int mgNum = sc.nextInt();
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  바뀔 매니저 번호를 입력하세요 ");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									int nMgnum = sc.nextInt();

									adminDao.updateDepartment(currentDept, mgNum, newDept, nMgnum);

								} else if (menuNo == 4) {
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  추가할 부서명을 입력하세요: ");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									String deNum = sc.nextLine();
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  추가할 매니저 번호를 입력하세요: ");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									int manage = sc.nextInt();
									sc.nextLine(); // 개행 문자 소비
									adminDao.insertDepartment(deNum, manage);

								} else if (menuNo == 5) {
									System.out.println("┌──────────────────────────────┐");
									System.out.println("  삭제할 부서번호를 입력하세요: ");
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									int deptToDelete = sc.nextInt();
									sc.nextLine(); // 개행 문자 소비
									adminDao.deleteDepartment(deptToDelete);

								} else if (menuNo == 6) {
									System.out.println("┌──────────────────────────────┐");
									System.out.println("            ◆ 검색 ◆ ");
									System.out.println();
									System.out.println("  1. 부서번호로 검색");
									System.out.println("  2. 직원명으로 검색");
									System.out.println("  3. 이전으로");
									System.out.println();
									System.out.println("└──────────────────────────────┘");
									System.out.print("▶입력 : ");
									int searchOption = sc.nextInt();
									sc.nextLine();

									if (searchOption == 1) {
										System.out.println("┌──────────────────────────────┐");
										System.out.println("  부서번호을 입력하세요: ");
										System.out.println("└──────────────────────────────┘");
										System.out.print("▶입력 : ");
										int department = sc.nextInt();
										System.out.println(
												" ────────────────────────────────────────────────────────────────────────────────");
										for (UserVo vo : adminDao.selectdepartment(department)) {
											System.out.print("부서번호 : "
													+ (vo.getDepartmentId() != 0 ? vo.getDepartmentId() : "정보 없음")
													+ "\t");
											System.out.print(
													"부서명 : " + (vo.getDepartmentName() != null ? vo.getDepartmentName()
															: "정보 없음") + "\t");
											System.out.print("사번 : "
													+ (vo.getEmployeeId() != 0 ? vo.getEmployeeId() : "정보 없음") + "\t");
											System.out.print("이름 : "
													+ (vo.getEmpName() != null ? vo.getEmpName() : "정보 없음") + "\t");
											System.out.println(
													"매니저ID : " + (vo.getMgId() != 0 ? vo.getMgId() : "정보 없음") + "\t");

										}
										System.out.println(
												" ────────────────────────────────────────────────────────────────────────────────");
										System.out.println("※ 출력이 완료 되었습니다.");
									} else if (searchOption == 2) {
										System.out.println("┌──────────────────────────────┐");
										System.out.println("  직원명을 입력하세요: ");
										System.out.println("└──────────────────────────────┘");
										System.out.print("▶입력 : ");
										String answer = sc.nextLine();
										System.out.println(
												" ────────────────────────────────────────────────────────────────────────────────");
										for (UserVo vo : adminDao.selectUser(answer)) {
											System.out.print(" 사번 :" + vo.getEmployeeId() + "\t");
											System.out.print(" 이름 :" + vo.getEmpName() + "\t");
											System.out.print(" 부서번호 :" + vo.getDepartmentId() + "\t");
											System.out.print(" 부서명 :" + vo.getDepartmentName() + "\t");
											System.out.println(" 매니저ID :" + vo.getMgId() + "\t");
										}
										System.out.println(
												" ────────────────────────────────────────────────────────────────────────────────");
										System.out.println("※ 출력이 완료 되었습니다.");
									} else if (searchOption == 3) {
										continue;
									}

								} else if (menuNo == 7) {
									break;
								}
							}
						} else if (submenu == 3) {
							System.out.println("(￣ー￣)ﾉ 종료 되었습니다.");
							System.exit(0);
						} else {
							System.out.println("(ಥ﹏ಥ) 잘못된 입력입니다. 다시 시도해주세요.");
						}
					}
				} else {
					System.out.println("(ಥ﹏ಥ) 로그인 실패. 아이디와 비밀번호를 확인하세요.");
				}

			} else if (input == 2) {
				System.out.println("(￣ー￣)ﾉ 종료 되었습니다.");
				break;
			} else {
				System.out.println("(ಥ﹏ಥ) 잘못된 입력입니다. 다시 시도해주세요.");
			}
		}
		sc.close();

	}

}
