package com.project.work_admin;

import java.util.Scanner;

public class AdminApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AdminDao adminDao = new AdminDao();

		while (true) {
			System.out.println("==========관리자 프로그램===========");
			System.out.println("1. 로그인 2. 종료");
			System.out.println("===============================");
			System.out.print("> 입력 : ");
			int input = sc.nextInt();
			sc.nextLine();

			if (input == 1) {
				boolean login = false;

				System.out.println("로그인 정보를 입력해주세요.");
				System.out.print("ID : ");
				String id = sc.nextLine();
				System.out.print("PW : ");
				String pw = sc.nextLine();

				login = adminDao.loginProcess(id, pw);

				if (login) {
					System.out.println("로그인 성공!");
					while (true) {
						System.out.println("===============================");
						System.out.println("1. 직원근태 ");
						System.out.println("2. 부서/직원 정보 ");
						System.out.println("3. 종료 ");
						System.out.print("> 입력 : ");
						int submenu = sc.nextInt();
						sc.nextLine(); // 버퍼 클리어

						if (submenu == 1) {
							while (true) {
								System.out.println("===============================");
								System.out.println("<<직원근태현황>>");
								System.out.println("1. 일일현황 ");
								System.out.println("2. 근태 상태 수정  ");
								System.out.println("3. 이전으로 ");
								System.out.print("> 입력 : ");
								int workmenu = sc.nextInt();
								sc.nextLine();
								if (workmenu == 1) {
									System.out.println("===============================");
									adminDao.workAllList();
									System.out.println(">> 출력이 완료 되었습니다.");
								} else if (workmenu == 2) {
									System.out.println("===============================");
									System.out.println(" 수정을 원하는 직원의 사번을 입력해주세요.");
									System.out.print(">입력 : ");
									int employeeID = sc.nextInt();

									System.out.println("================================");
									System.out.println("<" + employeeID + ">의 정보를 불러옵니다.");
									adminDao.workSelectList(employeeID);

									System.out.println("================================");
									System.out.println("원하는 숫자를 입력하세요.");
									System.out.println("1.출근 ");
									System.out.println("2.지각 ");
									System.out.println("3.조퇴 ");
									System.out.println("4.퇴근 ");
									System.out.println("5.휴가 ");
									System.out.println("6.이전으로");
									System.out.print(">입력 : ");
									int workNum = sc.nextInt();
									if (workNum == 1) {
										adminDao.updateWorkStart(workNum);
									} else if (workNum == 2) {
										adminDao.updateWorkLate(workNum);
									} else if (workNum == 3) {
										adminDao.updateWorkEarly(workNum);
									} else if (workNum == 4) {
										adminDao.updateWorkLeave(workNum);
									} else if (workNum == 5) {
										adminDao.updateWorkVacation(workNum);
									} else if (workNum == 6) {
										break;
									} else {
										System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
									}

								} else if (workmenu == 3) {
									break;// 이전으로 만들어야 됨
								}
							}

						} else if (submenu == 2) {
							System.out.println("부서/직원 정보 선택됨.");
							// 부서/직원 정보 관련 로직
						} else if (submenu == 3) {
							System.out.println("종료.");
							break;
						} else {
							System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
						}
					}
				} else {
					System.out.println("로그인 실패. 아이디와 비밀번호를 확인하세요.");
				}

			} else if (input == 2) {
				System.out.println("종료 되었습니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
			}
		}
		sc.close();
	}
}
