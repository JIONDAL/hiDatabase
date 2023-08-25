package dbConcept4.DAO;

import java.util.ArrayList;
import dbConcept4.DTO.MemberDTO;

public interface IDAO {
	public MemberDTO selectId(String id);
	public int insertMember(MemberDTO member);
	public ArrayList<MemberDTO> selectAll();
	public int updateMember(MemberDTO member);
	public int deleteMember(String id);
}

