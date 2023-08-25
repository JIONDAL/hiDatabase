package dbConcept4.DTO;
/*
CREATE TABLE concept4(
    num number,
    id VARCHAR2(20), 
    pw VARCHAR2(20),
    name VARCHAR2(21),
    email VARCHAR2(100),
    PRIMARY KEY(id, num)
);
COMMIT;
 */
public class MemberDTO {
	private int num;
	private String id;
	private String pw;
	private String name;
	private String email;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
