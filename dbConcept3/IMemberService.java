package dbConcept3;

public interface IMemberService {
	public void insert(MemberDTO member);
	public void selectEmail(String email);
	public void delete(String email);
	public void selectAll();
	public void update(String email, String name, String mobile);
	public void disconnection();
}
