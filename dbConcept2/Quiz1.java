package dbConcept2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Quiz1 {
	public static void main(String[] args) {

		/*
		 * 회원 등록 : 이름(중복 허용), 전화번호(중복 허용), 이메일(중복 허용 안됨) 회원 확인 : 이메일로 검색하여 있으면 이름, 전화번호,
		 * 이메일 출력 / 없으면 없음 출력 회원 삭제 : 이메일로 검색하여 있으면 이름, 전화번호, 이메일 삭제 / 없으면 없음 출력 회원 수정 :
		 * 이메일로 검색하여 있으면 이름과 전화번호 수정 / 없으면 없음 출력
		 */

		/*
		 * ArrayList가 아닌 데이터베이스에 테이블 생성해서
		 * 아래와 같은 메뉴의 등록,확인,삭제,모두보기, 수정 등 기능이 동작되도록 구현
		 * 테이블 이름 concept 
		 * 컬럼(필드) 이름 자유, 
		 * 자료형 크기 자유 
		 * CREATE TABLE concept( 
		 * email VARCHAR2(20), 
		 * mobile VARCHAR2(20), 
		 * name VARCHAR2(21), 
		 * PRIMARY KEY(email) 
		 * );
		 * COMMIT;
		 */
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		Scanner in = new Scanner(System.in);
		String em = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		while (true) {
			System.out.println("1. 회원 등록");
			System.out.println("2. 회원 확인");
			System.out.println("3. 회원 삭제");
			System.out.println("4. 회원 모두 보기");
			System.out.println("5. 회원 수정");
			System.out.println("6. 프로그램 종료");
			System.out.print("선택 > ");
			int select;
			try {
				select = Integer.parseInt(in.next());
			} catch (Exception e) {
				continue;
			}
			switch (select) {
			case 1:
				System.out.println("=== 회원 등록 ===");
				System.out.print("이메일 : ");
				em = in.next();

				// SELECT * FROM concept WHERE email='admin';
				String sql = "SELECT count(email) FROM concept WHERE email=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, em);
					rs = ps.executeQuery();
					int count = -1;
					if(rs.next())
						count = rs.getInt(1); // 1 or 0

					if (count == 0) {
						System.out.print("전화번호 : ");
						String mobile = in.next();
						System.out.print("이름 : ");
						String name = in.next();
						ps = con.prepareStatement("INSERT INTO concept VALUES(?,?,?)");
						ps.setString(1, em);
						ps.setString(2, mobile);
						ps.setString(3, name);
						ps.executeUpdate(); // 명령문을 디비로 전달하고 결과를 받음
						System.out.println("회원정보가 등록되었습니다.");
					} else {
						System.out.println("등록된 회원입니다.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("=== 회원 검색 ===");
				System.out.print("이메일 : ");
				em = in.next();
				sql = "SELECT * FROM concept WHERE email=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, em);
					rs = ps.executeQuery();
					if(rs.next()) {
						System.out.println("이메일 : " + rs.getString(1));
						System.out.println("전화번호 : " + rs.getString(2));
						System.out.println("이름 : " + rs.getString(3));
					} else {
						System.out.println("등록되지 않은 회원 입니다.");
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("=== 회원 삭제 ===");
				System.out.print("이메일 : ");
				em = in.next();
				
				sql = "DELETE FROM concept WHERE email=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, em);
					int result = ps.executeUpdate();
					
					if (result == 1) {
						System.out.println("회원정보 삭제되었습니다.");
					} else {
						System.out.println("등록되지 않은 회원 입니다.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				sql = "SELECT * FROM concept";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					int count = 0;
					while(rs.next()) {
						count++;
						System.out.println("이메일 : " + rs.getString(1));
						System.out.println("전화번호 : " + rs.getString(2));
						System.out.println("이름 : " + rs.getString(3));
						System.out.println();
					}
					if(count == 0)
						System.out.println("회원 등록 후 이용하세요.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("=== 회원 수정 ===");
				System.out.print("이메일 : ");
				em = in.next();
				System.out.print("전화번호 : ");
				String mobile = in.next();
				System.out.print("이름 : ");
				String name = in.next();
				
				sql = "UPDATE concept SET mobile=?, name=? WHERE email=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, mobile);
					ps.setString(2, name);
					ps.setString(3, em);
					int result = ps.executeUpdate();
					
					if (result == 1) {
						System.out.println("회원 정보 수정이 완료되었습니다.");
					} else {
						System.out.println("등록되지 않은 회원 입니다.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				try {
					if(con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.exit(0);
			default:
				System.out.println("메뉴 확인 후 다시 입력하세요.");
			}
			System.out.println();
		}

	}
}
