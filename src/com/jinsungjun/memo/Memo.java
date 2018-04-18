/**
 * 
 */
package com.jinsungjun.memo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * �޸� 2018.04.18 �۾� ������ 1. Path Ŭ������ �ѱ��ν� ���� - 04.18 ReaderWriter Ȱ��
 * 
 * @author jinsungjun
 *
 */
public class Memo {

	public static final String MEMO_DIR = "/Temp/memo/";

	// ���� Ŀ�ǵ带 ����� ����

	public static final String EXIT = "/exit";

	// �����ڿ��� �޸� ������ ���丮�� �������ش�
	public Memo() {
		
		// ���丮�� ���翩�� Ȯ��
		File dir = new File(MEMO_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	// ��ɾ ����ϴ� �Լ�

	public void showCommand() {

		System.out.println("�Ʒ� ��� ��ȣ�� �Է��ϼ���. ---");
		System.out.println("1.���� 2.�б� 3.���� 4.���� 0.���α׷� ����");
	}

	// ����
	public void write(Scanner scanner) {
		System.out.println("---���� ��� ---");
		// ��ü ���� ������ ����
		StringBuilder content = new StringBuilder();

		// Ű���� �Է��� �޴´�.
		while (true) {
			String line = scanner.nextLine();
			if (line.equals(EXIT)) {
				break;
			} else {
				content.append(line + "\r\n");
			}
		}

		// �ۼ��� ������ ������ ���Ϸ� ����
		if (!content.toString().equals("")) {
			// ��. ���� �ð� �����ͼ� ���ϸ����� �����.
			long now = System.currentTimeMillis();
			// ��. �����_�ú���.txt ���Ϸ� ������
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String filename = sdf.format(now) + ".txt";

			// ��. ������ ������ ���� ��� ����
			Path path = Paths.get(MEMO_DIR, filename);
			try {
				// ��. ���� ����
				Files.write(path, content.toString().getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("�޸� ����Ͽ����ϴ�.");
		}

	}

	// �޸��� ����Ʈ�� �����ִ� �Լ�
	public void list() {

		File file = new File(MEMO_DIR);
		String[] list = file.list();

		for (String lists : list) {
			System.out.println(lists);
		}
		System.out.println("");
	}

	// �б�
	public void read(Scanner scanner) {

		String result = "";

		list(); // ����Ǿ� �ִ� �޸��� ����Ʈ�� �����Ѵ�.

		System.out.println("---�б� ���---");
		System.out.println("�а� ���� �޸� �����Ͻÿ� : ");

		String name = scanner.nextLine();
		String filePath = MEMO_DIR.concat(name);

		File file = new File(filePath);

		if (file.exists()) {

			try (FileReader fr = new FileReader(file)) { // FileReader Ŭ���� Ȱ��

				int oneChar = 0; //������ ������ �о���� int�� ���� ����

				while (true) {

					oneChar = fr.read();

					if (oneChar == -1) // �� �̻� �о���� ������ ������
						break; // �ݺ����� ����������
					else {
						result = result + (char) oneChar; // ������ ������ ����, ���ڷ� ������ ������ �о�������� char������ ĳ����
					}
				}

				System.out.println(result);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			
			System.err.println("�ش�Ǵ� �̸��� ������ �����ϴ�."); //�ش�Ǵ� �̸��� ������ ������ ���� �޼��� ���.
		}

	}
	
	//����
	public void remove(Scanner scanner) {
		list(); // ����Ǿ� �ִ� �޸��� ����Ʈ�� �����Ѵ�.

		System.out.println("---���� ���---");
		System.out.println("���� �ϰ� ���� �޸� �����Ͻÿ� : ");

		String name = scanner.nextLine(); // ����ڷκ��� �޸��� �̸��� �޴´�.
		String filePath = MEMO_DIR.concat(name); // ����� ���� ��θ� ���� ��ü����
		// ���� �ּҸ� �����.

		File file = new File(filePath);
		if (!file.delete()) { // ����� �����Ǿ����� Ȯ��
			System.err.println("������ ���������� ������� �ʾҽ��ϴ�.");
		} else
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");

		list(); // ���� �� ����Ʈ ���

	}

	public void edit(Scanner scanner) {

		// ���� �� ����

		list(); // ���� ����Ǿ� �ִ� �޸��� list ���

		System.out.println("---���� ���---");
		System.out.println("���� �ϰ� ���� �޸� �����Ͻÿ� : ");

		String name = scanner.nextLine(); // ���� �ϰ� ���� �޸��� �̸��� ����ڷκ��� �Է¹޴´�.
		String filePath = MEMO_DIR.concat(name);

		File file = new File(filePath);
		if (!file.delete()) { // ����� �����Ǿ����� Ȯ��
			System.err.println("���� ��,������ ���������� ������� �ʾҽ��ϴ�.");
		}

		System.out.println("���� �� ������ �Է��ϼ���.");

		StringBuilder content = new StringBuilder();

		while (true) {
			String line = scanner.nextLine();
			if (line.equals(EXIT))
				break;
			else {
				content.append(line + "\r\n");
			}
		}

		if (!content.toString().equals("")) {

			Path path = Paths.get(filePath);
			try {
				Files.write(path, content.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("������ �Ϸ�Ǿ����ϴ�.");

	}

}
