package dbConcept5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardService implements IBoardService{
	private BoardDAO boardDao;
	
	public BoardService() {
		boardDao = new BoardDAO();
	}
	
	@Override
	public void insert(BoardDTO board) {
		int num = boardDao.selectMaxNum();
		board.setNum(num+1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		board.setWriteTime(format);
		
		boardDao.insert(board);
		System.out.println("게시글 작성되었습니다.");
	}

	@Override
	public void selectNum(String n) {
		int num = 0;
		try {
			num = Integer.parseInt(n);
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			return;
		}
		
		BoardDTO board = boardDao.selectNum(num);
		if(board == null) {
			System.out.println("다시 시도하세요.");
			return;
		}
		
		board.setHit(board.getHit()+1);
		
		System.out.println("번호\t제목\t내용\t작성자\t작성일\t조회수");
		System.out.print(board.getNum() + "\t");
		System.out.print(board.getSubject() + "\t");
		System.out.print(board.getContent() + "\t");
		System.out.print(board.getWriter() + "\t");
		System.out.print(board.getWriteTime() + "\t");
		System.out.println(board.getHit() );
		
		boardDao.updateHit(num);
	}

	@Override
	public void selectSubject(String subject) {
		// 모든 게시글의 데이터(게시글 내용, 게시글작성일 제외) 출력
		
		ArrayList<BoardDTO> boards = boardDao.selectSubject(subject);
		if(boards.isEmpty())
			System.out.println("검색 결과가 없습니다.");
		else {
			System.out.println("번호\t제목\t작성자\t조회수");
			for(BoardDTO board : boards) {
				System.out.print(board.getNum() + "\t");
				System.out.print(board.getSubject() + "\t");
				System.out.print(board.getWriter() + "\t");
				System.out.println(board.getHit() );
			}
		}
		
	}

	@Override
	public void selectAll() {
		ArrayList<BoardDTO> boards = boardDao.selectAll();
		if(boards.isEmpty())
			System.out.println("게시글 정보가 없습니다.");
		else {
			System.out.println("번호\t제목\t내용\t작성자\t작성일\t조회수");
			for(BoardDTO board : boards) {
				System.out.print(board.getNum() + "\t");
				System.out.print(board.getSubject() + "\t");
				System.out.print(board.getContent() + "\t");
				System.out.print(board.getWriter() + "\t");
				System.out.print(board.getWriteTime() + "\t");
				System.out.println(board.getHit() );
			}
		}
	}

	@Override
	public void delete(String n) {
		int num = 0;
		try {
			num = Integer.parseInt(n);
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			return;
		}
		
		int result = boardDao.delete(num);
		if(result == 1) {
			System.out.println("게시글이 삭제되었습니다.");
		}else {
			System.out.println("게시글 삭제에 실패했습니다.");
		}
	}

	@Override
	public void update(String n, String subject, String content) {
		int num = 0;
		try {
			num = Integer.parseInt(n);
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			return;
		}
		
		BoardDTO board = boardDao.selectNum(num);
		if(board == null) {
			System.out.println("비정상적인 게시글 번호 입니다.");
			return;
		}
		
		//if(subject.equals("") == false)
		board.setSubject(subject);
		board.setContent(content);
		
		boardDao.update(board);
	}

	public void disconnection() {
		boardDao.disconnection();
	}

}








