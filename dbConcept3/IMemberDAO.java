package dbConcept3;

import java.util.ArrayList;

public interface IMemberDAO {

	public int isExists(String email);
	public void insert(MemberDTO member);
	public MemberDTO selectEmail(String email);
	public int delete(String email);
	public ArrayList<MemberDTO> selectAll();
	public void update(MemberDTO member);
	public void disconnection();
}
