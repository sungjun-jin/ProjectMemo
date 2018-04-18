package com.jinsungjun.memo;
/*
 * ���� �޸��� 
 * (04.18 �۾�)
 */

import java.util.Scanner;

public class MemoMain {

	public static void main(String[] args) {

		// 1. Ű���� �Է����� ��ɾ ���� �޴´�

		// 2. ���α׷��� �����ϸ� ��ɾ� ��ȣ�� �����ش�.

		// 3. 1.���� 2.�б� 3.���� 4.����

		Memo memo = new Memo();

		Scanner scanner = new Scanner(System.in);
		boolean runFlag = true; //�ݺ����� ���߰� ���� flag

		while (runFlag) {
			memo.showCommand();
			String cmd = scanner.nextLine();

			switch (cmd) {

			case "1": // ���� - 04.18 �Ϸ�
				memo.write(scanner);
				break;
			case "2": // �б� - 04.18 �Ϸ�
				memo.read(scanner);
				break;
			case "3": // ����
				memo.edit(scanner);
				break;
			case "4": // ���� - 04.18 �Ϸ�
				memo.remove(scanner);
				break;
			case "0": // ���� - 04.18 �Ϸ�
				runFlag = false;
				break;
			default:
				System.out.println("��ɾ �߸��Ǿ����ϴ�");

			}
		}
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}

}
