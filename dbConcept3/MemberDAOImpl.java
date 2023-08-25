package dbConcept3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOImpl implements IMemberDAO{
	/*
	 * 데이터베이스 연결
	 * 데이터베이스로 CRUD(Create, Read, Update, Delete)
	 */
	private Connection con;
	
	public MemberDAOImpl() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public int isExists(String email) {
		String sql = "SELECT count(email) FROM concept WHERE email=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public void insert(MemberDTO member) {
		String sql = "INSERT INTO concept VALUES(?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getEmail());
			ps.setString(2, member.getMobile());
			ps.setString(3, member.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemberDTO selectEmail(String email) {
		String sql = "SELECT * FROM concept WHERE email=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				MemberDTO member = new MemberDTO();
				member.setEmail(email);
				member.setMobile(rs.getString("mobile"));
				member.setName(rs.getString(3));
				
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int delete(String email) {
		String sql = "DELETE FROM concept WHERE email=?";
		PreparedStatement ps = null;
		int reuslt = 0 ;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			reuslt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	@Override
	public ArrayList<MemberDTO> selectAll() {
		String sql = "SELECT * FROM concept ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> members = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setName(rs.getString(3));
				
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return members;
	}

	@Override
	public void update(MemberDTO member) {
		String sql = "UPDATE concept SET mobile=?, name=? WHERE email=?";
		PreparedStatement ps = null;
	
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getMobile());
			ps.setString(2, member.getName());
			ps.setString(3, member.getEmail());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnection() {
		try {
			if(con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}







