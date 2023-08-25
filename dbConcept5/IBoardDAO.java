package dbConcept5;

import java.util.ArrayList;

public interface IBoardDAO {

	public int selectMaxNum(); // 게시글 번호 중 큰 수 반환
	
	public void insert(BoardDTO board); // 게시글 데이터 저장
	
	public BoardDTO selectNum(int num); // 게시글 번호로 게시글 조회
	public void updateHit(int num); // 게시글 조회수 증가
	
	// 게시글 제목으로 게시글 조회
	public ArrayList<BoardDTO> selectSubject(String subject);
	public ArrayList<BoardDTO> selectAll(); //모든 게시글 출력
	
	public int delete(int num); // 게시글 번호로 게시글 삭제
	public void update(BoardDTO board); // 게시글 수정(제목과 내용)
}
