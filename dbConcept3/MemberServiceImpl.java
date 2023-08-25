package dbConcept3;

import java.util.ArrayList;

public class MemberServiceImpl implements IMemberService {

	/*
	 * 입력 값 전달받아 검증하기. 
	 * DAO로 CRUD를 하기 위한 데이터를 전달 결과를 받을게 있다면 받아서 결과 출력,
	 * 결과를 받을게 없고 출력이 있으면 결과 출력
	 */
	private MemberDAOImpl memberDao;
	
	public MemberServiceImpl() {
		memberDao = new MemberDAOImpl();
	}
	
	@Override
	public void insert(MemberDTO member) {
		int result = memberDao.isExists(member.getEmail());
		if(result == 0) {
			memberDao.insert(member);
			System.out.println("등록되었습니다.");
		}else if(result >= 1){
			System.out.println("등록된 데이터입니다.");
		}else {
			System.out.println("오류가 발생했습니다. 잠시후 다시 시도하세요.");
		}
	}

	@Override
	public void selectEmail(String email) {
		MemberDTO member = memberDao.selectEmail(email);
		if(member != null) {
			System.out.println("이메일 : " + member.getEmail());
			System.out.println("전화번호 : " + member.getMobile());
			System.out.println("이름 : " + member.getName());
		}else {
			System.out.println("등록된 정보가 없습니다.");
		}
	}

	@Override
	public void delete(String email) {
		int result = memberDao.delete(email);
		if(result == 1) {
			System.out.println(email + " 정보가 삭제되었습니다.");
		}else {
			System.out.println("등록된 정보가 없습니다.");
		}
		
	}
	@Override
	public void update(String email, String name, String mobile) {
		MemberDTO member = memberDao.selectEmail(email);
		
		if(member != null) {
			member.setMobile(mobile);
			member.setName(name);
			
			memberDao.update(member);
			System.out.println(email + " 정보가 수정되었습니다.");
		}else {
			System.out.println("등록된 정보가 없습니다.");
		}
		
	}
	@Override
	public void selectAll() {
		ArrayList<MemberDTO> members = memberDao.selectAll();
		if(members.isEmpty()) {
			System.out.println("등록 후 이용하세요.");
		}else {
			for(int i = 0; i < members.size(); i++) {
				MemberDTO member = members.get(i);
				System.out.println("이메일 : " + member.getEmail());
				System.out.println("전화번호 : " + member.getMobile());
				System.out.println("이름 : " + member.getName());
				System.out.println("=================");
			}
		}
	}
	
	@Override
	public void disconnection() {
		memberDao.disconnection();
	}

	
}


