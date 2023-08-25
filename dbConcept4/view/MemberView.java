package dbConcept4.view;

import java.util.ArrayList;
import java.util.Scanner;

import dbConcept4.DAO.MemberDAO;
import dbConcept4.DTO.MemberDTO;

public class MemberView implements IView{
	private Scanner in =  new Scanner(System.in);
	public void mainMenu() {
		while (true) {
			System.out.println("1. 회원 전체 정보 출력");
			System.out.println("2. 회원 정보 조회");
			System.out.println("3. 회원 정보 입력");
			System.out.println("4. 회원 정보 변경");
			System.out.println("5. 회원 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 > ");
			int select;
			try {
				select = Integer.parseInt(in.next());
			} catch (Exception e) {
				continue;
			}
			switch (select) {
			case 1:	selectAll(); break;
			case 2:	selectId(); break;
			case 3:	insertMember(); break;
			case 4:	updateMember(); break;
			case 5: deleteMember(); break;
			case 6: disconnection();
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
			default:
				System.out.println("메뉴 확인 후 다시 입력하세요.");
			}
		}
		
	}
	
	private MemberDAO memberDao = new MemberDAO();
	
	public void disconnection() {
		memberDao.disconnection();
	}
	
	@Override
	public void deleteMember() {
		System.out.print("아이디 : "); String id = in.next();
		
		int result = memberDao.deleteMember(id);
		if(result == 1)
			System.out.println("회원 정보가 삭제되었습니다.");
		else
			System.out.println("회원 정보가 삭제되지 않았습니다.");
	}

	@Override
	public void updateMember() {
		System.out.print("아이디 : "); String id = in.next();
		System.out.print("비밀번호 : "); String pw = in.next();
		System.out.print("이름 : "); String name = in.next();
		System.out.print("이메일 : "); String email = in.next();
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		
		int result = memberDao.updateMember(member);
		if(result == 1)
			System.out.println("회원 정보가 수정되었습니다.");
		else
			System.out.println("회원 정보가 수정되지 않았습니다.");
	}

	@Override
	public void insertMember() {
		System.out.print("아이디 : "); String id = in.next();
		System.out.print("비밀번호 : "); String pw = in.next();
		System.out.print("이름 : "); String name = in.next();
		System.out.print("이메일 : "); String email = in.next();
		
		MemberDTO member = memberDao.selectId(id);
		if(member == null) {
			member = new MemberDTO();
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setEmail(email);
			memberDao.insertMember(member);
			System.out.println("회원 정보가 등록되었습니다.");
		}
		else
			System.out.println("중복된 아이디로 등록되지 않았습니다.");
	}

	@Override
	public void selectId() {
		System.out.print("아이디 : "); String id = in.next();
		MemberDTO member = memberDao.selectId(id);
		if(member == null) {
			System.out.println("검색 결과가 존재하지 않습니다.");
		}else {
			System.out.println("회원번호\t아이디\t비밀번호\t이름\t이메일");
			System.out.print(member.getNum() + "\t");
			System.out.print(member.getId() + "\t");
			System.out.print(member.getPw() + "\t");
			System.out.print(member.getName() + "\t");
			System.out.println(member.getEmail());
		}
	}

	@Override
	public void selectAll() {
		ArrayList<MemberDTO> members = memberDao.selectAll();
		if(members.isEmpty()) {
			System.out.println("등록 후 이용하세요.");
		}else {
			System.out.println("회원번호\t아이디\t비밀번호\t이름\t이메일");
			for(MemberDTO member : members) {
				System.out.print(member.getNum() + "\t");
				System.out.print(member.getId() + "\t");
				System.out.print(member.getPw() + "\t");
				System.out.print(member.getName() + "\t");
				System.out.println(member.getEmail());
			}
		}
	}

}
