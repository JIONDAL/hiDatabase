package dbConcept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("### 회원 가입 ###");
		System.out.print("아이디 : "); String id = sc.next();
		System.out.print("비밀번호 : "); String pw = sc.next();
		System.out.print("이름 : "); String name = sc.next();
		
		PreparedStatement ps = null;
//		con.prepareStatement("INSERT INTO test VALUES('"+id+"', '"+pw+"', '"+name+ "', " +4+")");
		ResultSet rs = null;
		int maxNum = 0;
		try {
			ps = con.prepareStatement("SELECT max(num) as max_num FROM test");
			rs = ps.executeQuery();
			if(rs.next()) {
//				rs.getInt("max_num");
				maxNum = rs.getInt(1);
			}
			
			ps = con.prepareStatement("INSERT INTO test VALUES(?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, ++maxNum);
			
			int rowCount = ps.executeUpdate(); // 명령문을 디비로 전달하고 결과를 받음
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}





