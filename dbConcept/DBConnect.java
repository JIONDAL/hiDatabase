package dbConcept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static void main(String[] args) {
		/*
		 * IP Address 와 Port Number
		 * IP : Internet Protocol 
		 * IP : 컴퓨터의 주소
		 * IP : 서울특별시 마포구 백범로 3길
		 * Port : 4층 5강의장, (401호) 
		 * 
		 * IP : xxx.xxx.xxx.xxx / 192.168.0.83
		 * Port : 0 ~ 65535 / 1521
		 * 
		 * 자기자신 : this
		 * IP주소 == 자기자신 == localhost == 127.0.0.1
		 */
		
		// 드라이버 실행(로드, Load)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// URL로 DB에 접근하여 user/password 입력 후 로그인 함
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "oracle"; // DB 아이디
		String password = "oracle"; // DB 비밀번호
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 데이터베이스와 데이터를 주고 받는 코드
		
		// 디비 연결 종료
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("다음 코드");
	}
}
















