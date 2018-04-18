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
 * 메모 2018.04.18 작업 문제점 1. Path 클래스의 한글인식 부족 - 04.18 ReaderWriter 활용
 * 
 * @author jinsungjun
 *
 */
public class Memo {

	public static final String MEMO_DIR = "/Temp/memo/";

	// 종료 커맨드를 상수로 정의

	public static final String EXIT = "/exit";

	// 생성자에서 메모를 저장할 디렉토리를 생성해준다
	public Memo() {
		
		// 디렉토리의 존재여부 확인
		File dir = new File(MEMO_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	// 명령어를 출력하는 함수

	public void showCommand() {

		System.out.println("아래 명령 번호를 입력하세요. ---");
		System.out.println("1.쓰기 2.읽기 3.수정 4.삭제 0.프로그램 종료");
	}

	// 쓰기
	public void write(Scanner scanner) {
		System.out.println("---쓰기 모드 ---");
		// 전체 글을 저장할 변수
		StringBuilder content = new StringBuilder();

		// 키보드 입력을 받는다.
		while (true) {
			String line = scanner.nextLine();
			if (line.equals(EXIT)) {
				break;
			} else {
				content.append(line + "\r\n");
			}
		}

		// 작성한 내용이 있으면 파일로 쓴다
		if (!content.toString().equals("")) {
			// 가. 현재 시간 가져와서 파일명으로 만든다.
			long now = System.currentTimeMillis();
			// 나. 년월일_시분초.txt 파일로 포맷팅
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String filename = sdf.format(now) + ".txt";

			// 다. 내용을 저장할 파일 경로 설정
			Path path = Paths.get(MEMO_DIR, filename);
			try {
				// 라. 파일 쓰기
				Files.write(path, content.toString().getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("메모를 등록하였습니다.");
		}

	}

	// 메모의 리스트를 보여주는 함수
	public void list() {

		File file = new File(MEMO_DIR);
		String[] list = file.list();

		for (String lists : list) {
			System.out.println(lists);
		}
		System.out.println("");
	}

	// 읽기
	public void read(Scanner scanner) {

		String result = "";

		list(); // 저장되어 있는 메모의 리스트를 나열한다.

		System.out.println("---읽기 모드---");
		System.out.println("읽고 싶은 메모를 선택하시오 : ");

		String name = scanner.nextLine();
		String filePath = MEMO_DIR.concat(name);

		File file = new File(filePath);

		if (file.exists()) {

			try (FileReader fr = new FileReader(file)) { // FileReader 클래스 활용

				int oneChar = 0; //파일의 내용을 읽어들일 int형 변수 선언

				while (true) {

					oneChar = fr.read();

					if (oneChar == -1) // 더 이상 읽어들일 파일이 없으면
						break; // 반복문을 빠져나간다
					else {
						result = result + (char) oneChar; // 파일의 내용을 누적, 숫자로 파일의 내용을 읽어들임으로 char형으로 캐스팅
					}
				}

				System.out.println(result);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			
			System.err.println("해당되는 이름의 파일이 없습니다."); //해당되는 이름의 파일이 없으면 에러 메세지 출력.
		}

	}
	
	//삭제
	public void remove(Scanner scanner) {
		list(); // 저장되어 있는 메모의 리스트를 나열한다.

		System.out.println("---삭제 모드---");
		System.out.println("삭제 하고 싶은 메모를 선택하시오 : ");

		String name = scanner.nextLine(); // 사용자로부터 메모의 이름을 받는다.
		String filePath = MEMO_DIR.concat(name); // 제목과 파일 경로를 합쳐 전체적인
		// 파일 주소를 만든다.

		File file = new File(filePath);
		if (!file.delete()) { // 제대로 삭제되었는지 확인
			System.err.println("삭제가 정상적으로 진행되지 않았습니다.");
		} else
			System.out.println("삭제가 완료되었습니다.");

		list(); // 삭제 후 리스트 출력

	}

	public void edit(Scanner scanner) {

		// 삭제 후 쓰기

		list(); // 현재 저장되어 있는 메모의 list 출력

		System.out.println("---수정 모드---");
		System.out.println("수정 하고 싶은 메모를 선택하시오 : ");

		String name = scanner.nextLine(); // 삭제 하고 싶은 메모의 이름을 사용자로부터 입력받는다.
		String filePath = MEMO_DIR.concat(name);

		File file = new File(filePath);
		if (!file.delete()) { // 제대로 삭제되었는지 확인
			System.err.println("수정 전,삭제가 정상적으로 진행되지 않았습니다.");
		}

		System.out.println("수정 할 내용을 입력하세요.");

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
		System.out.println("수정이 완료되었습니다.");

	}

}
