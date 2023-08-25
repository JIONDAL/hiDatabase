package dbConcept5;
/*
CREATE TABLE concept5(
	num number,
	subject varchar2(20),
	content varchar2(20),
	writer varchar2(20),
	write_time varchar2(10),
	hit number,
	primary key(num)
);
Commit;
 */
public class BoardDTO {
	private int num; // 게시글 번호
	private String subject; // 게시글 제목
	private String content; // 게시글 내용
	private String writer; // 게시글 작성자
	private String writeTime; // 게시글 작성일(연월일)
	private int hit; // 게시글 조회수
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
