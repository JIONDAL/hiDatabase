package dbConcept2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDAO2 {
	private Connection con;
	
	public QuizDAO2() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(QuizDTO2 quizDto) {	
		String sql = "INSERT INTO concept2 VALUES(?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, quizDto.getId());
			ps.setString(2, quizDto.getPassword());
			ps.setString(3, quizDto.getName());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public QuizDTO2 get(String id) {
		String sql = "SELECT * FROM concept2 WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				QuizDTO2 quizDto = new QuizDTO2();
				quizDto.setId(rs.getString("id"));
				String password = rs.getString("password");
				quizDto.setPassword(password);
				quizDto.setName(rs.getString("name"));
				return quizDto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void remove(String id) {	
		String sql = "DELETE FROM concept2 WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void set(QuizDTO2 quizDto) {	
		String sql = "UPDATE concept2 SET password=? WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, quizDto.getPassword());
			ps.setString(2, quizDto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<QuizDTO2> getAll() {
		ArrayList<QuizDTO2> members = new ArrayList<>();
		String sql = "SELECT * FROM concept2";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				QuizDTO2 quizDto = new QuizDTO2();
				
				String id = rs.getString("id");
				quizDto.setId(id);
				
				String password = rs.getString("password");
				quizDto.setPassword(password);
				
				quizDto.setName(rs.getString("name"));
				
				members.add(quizDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	
	
	public void disconnection() {
		try {
			if(con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}













