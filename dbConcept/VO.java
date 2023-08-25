package dbConcept;

public class VO {
	private String id;
	private String pw;
	private String name;
	private int num;
	
	public VO(String id, String pw, String name, int num) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.num = num;
	}
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public int getNum() {
		return num;
	}
	
	

	
	
}
