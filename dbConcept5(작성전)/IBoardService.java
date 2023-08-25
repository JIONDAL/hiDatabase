package dbConcept5;

public interface IBoardService {
	public void insert(BoardDTO board);
	// 게시글 번호와 일치하는 게시글의 모든 데이터 출력.
	// 게시글 내용을 보면 조회수 증가.
	public void selectNum(String n);
	
	// 입력한 제목을 포함하고 있으면 모든 게시글의 데이터(게시글내용, 게시글작성일 제외) 출력
	public void selectSubject(String subject);
	public void selectAll();
	public void delete(String n);
	public void update(String n, String subject, String content);


}
