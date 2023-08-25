package dbConcept4.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbConcept4.DTO.MemberDTO;

public class MemberDAO implements IDAO{
	
	private Connection con;

	public MemberDAO() {
		connection();
	}
	
	public void connection() {
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
	
	public void disconnection() {
		try {
			if(con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<MemberDTO> selectAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> members = new ArrayList<MemberDTO>();
		try {
			ps = con.prepareStatement("SELECT * FROM concept4");
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				
				members.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	
	
	@Override
	public MemberDTO selectId(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM concept4 WHERE id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertMember(MemberDTO member) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			ps = con.prepareStatement("SELECT nvl(max(num), 0)+1 FROM concept4");
			rs = ps.executeQuery();
			int num = 0;
			if(rs.next())
				num = rs.getInt(1);
			
			ps = con.prepareStatement("INSERT INTO concept4 VALUES(?,?,?,?,?)");
			ps.setInt(1, num);
			ps.setString(2, member.getId());
			ps.setString(3, member.getPw());
			ps.setString(4, member.getName());
			ps.setString(5, member.getEmail());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}



	@Override
	public int updateMember(MemberDTO member) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = con.prepareStatement("UPDATE concept4 SET pw=?, name=?, email=? WHERE id=?");
			ps.setString(1, member.getPw());
			ps.setString(2, member.getName());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteMember(String id) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = con.prepareStatement("DELETE FROM concept4 WHERE id=?");
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
