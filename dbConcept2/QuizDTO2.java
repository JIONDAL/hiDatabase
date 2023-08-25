package dbConcept2;
/*
CREATE TABLE concept2(
    id VARCHAR2(20), 
    password VARCHAR2(20),
    name VARCHAR2(21),
    PRIMARY KEY(id)
);
COMMIT;
 */
public class QuizDTO2 {
	private String id;
	private String password;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
