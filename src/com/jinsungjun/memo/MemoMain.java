package com.jinsungjun.memo;
/*
 * 간편 메모장 
 * (04.18 작업)
 */

import java.util.Scanner;

public class MemoMain {

	public static void main(String[] args) {

		// 1. 키보드 입력으로 명령어를 먼저 받는다

		// 2. 프로그램이 시작하면 명령어 번호를 보여준다.

		// 3. 1.쓰기 2.읽기 3.수정 4.삭제

		Memo memo = new Memo();

		Scanner scanner = new Scanner(System.in);
		boolean runFlag = true; //반복문을 멈추게 해줄 flag

		while (runFlag) {
			memo.showCommand();
			String cmd = scanner.nextLine();

			switch (cmd) {

			case "1": // 쓰기 - 04.18 완료
				memo.write(scanner);
				break;
			case "2": // 읽기 - 04.18 완료
				memo.read(scanner);
				break;
			case "3": // 수정
				memo.edit(scanner);
				break;
			case "4": // 삭제 - 04.18 완료
				memo.remove(scanner);
				break;
			case "0": // 종료 - 04.18 완료
				runFlag = false;
				break;
			default:
				System.out.println("명령어가 잘못되었습니다");

			}
		}
		System.out.println("프로그램이 종료되었습니다.");
	}

}
