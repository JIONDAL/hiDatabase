package dbConcept5;

import java.util.Scanner;

public class Main {
	private	static Scanner in = new Scanner(System.in);
	private static BoardService boardService = new BoardService();
	
	public static void main(String[] args) {
		while (true) {
			System.out.println();
			System.out.println("1. 게시글 등록");
			System.out.println("2. 게시글 번호 검색");
			System.out.println("3. 게시글 제목 검색");
			System.out.println("4. 게시글 모두 보기");
			System.out.println("5. 게시글 삭제");
			System.out.println("6. 게시글 수정");
			System.out.println("7. 프로그램 종료");
			System.out.print("선택 > ");
			int select;
			try {
				select = Integer.parseInt(in.next());
			} catch (Exception e) {
				continue;
			}
			switch (select) {
			case 1:	insert(); break;
			case 2:	selectNum(); break;
			case 3:	selectSubject(); break;
			case 4:	selectAll(); break;
			case 5:	delete(); break;
			case 6: update(); break;
			case 7: disconnection();
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
			default:
				System.out.println("메뉴 확인 후 다시 입력하세요.");
			}
		}
	}
	
	private static void insert() {
		in.nextLine();
		System.out.print("게시글 제목 : "); String subject = in.nextLine();
		System.out.print("게시글 내용 : "); String content = in.nextLine();
		System.out.print("게시글 작성자 : "); String writer = in.next();
		
		BoardDTO board = new BoardDTO();
		board.setSubject(subject);
		board.setContent(content);
		board.setWriter(writer);
		boardService.insert(board);
	}
	
	private static void selectNum() {
		System.out.print("게시글 번호 : "); 
		String n = in.next();
		boardService.selectNum(n);
	}
	private static void selectSubject() {
		in.nextLine();
		System.out.print("게시글 제목 : "); 
		String subject = in.nextLine();
		boardService.selectSubject(subject);
	}
	
	private static void selectAll() {
		boardService.selectAll();
	}
	private static void delete() {
		System.out.print("게시글 번호 : "); 
		String n = in.next();
		boardService.delete(n);
	}

	private static void update() {
		System.out.print("게시글 번호 : "); 
		String n = in.next();
		in.nextLine();
		System.out.print("게시글 제목 : "); String subject = in.nextLine();
		System.out.print("게시글 내용 : "); String content = in.nextLine();
		boardService.update(n, subject, content);
	}

	private static void disconnection() {
		boardService.disconnection();
	}
}
