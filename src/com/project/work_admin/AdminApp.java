package com.project.work_admin;

import java.util.Scanner;

public class AdminApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("==========관리자 프로그램===========");
			System.out.println("1. 로그인 2. 종료");
			System.out.println("===============================");
			System.out.print("> 입력 : ");
			int connect = sc.nextInt();

			if (connect == 1) {

				//while (true) {
					System.out.println("로그인 정보를 입력해주세요.");
					System.out.print("ID : ");
					String id = sc.nextLine();
					sc.nextLine();
					System.out.print("PW : ");
					String pw = sc.nextLine();
					sc.nextLine();
					
//					if() {
//						System.out.println("> 로그인에 실패하였습니다. ID/PW를 다시 입력해주세요.");
//					}else {
//						break;
//					}
				//}
					
	
					
			} else if (connect == 2) {
				System.out.println("종료 되었습니다.");
				break;

			}
			System.out.println("===============================");
			System.out.println("원하시는 번호를 입력해주세요.");
			System.out.println("1. 직원근태 ");
			System.out.println("2. 부서/직원 정보 ");
			System.out.println("3. 종료 ");
			System.out.print(">입력 : ");
			
		}sc.close();

	}

}
