package dbConcept5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO implements IBoardDAO{
	private Connection con;

	public BoardDAO() {
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
	public int selectMaxNum() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try {
			ps = con.prepareStatement("SELECT nvl(max(num), 0) FROM concept5");
			rs = ps.executeQuery();
			if(rs.next())
				num = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public void insert(BoardDTO board) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO concept5 VALUES(?,?,?,?,?,0)");
			ps.setInt(1, board.getNum());
			ps.setString(2, board.getSubject());
			ps.setString(3, board.getContent());
			ps.setString(4, board.getWriter());
			ps.setString(5, board.getWriteTime());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BoardDTO selectNum(int num) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM concept5 WHERE num=?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteTime(rs.getString("write_time"));
				board.setHit(rs.getInt("hit"));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateHit(int num) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE concept5 SET hit=hit+1 WHERE num=?");
			ps.setInt(1, num);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<BoardDTO> selectSubject(String subject) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> boards = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT * FROM concept5 WHERE subject like ?");
			ps.setString(1, "%" + subject + "%");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteTime(rs.getString("write_time"));
				board.setHit(rs.getInt("hit"));
				
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boards;
	}

	@Override
	public ArrayList<BoardDTO> selectAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> boards = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT * FROM concept5");
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteTime(rs.getString("write_time"));
				board.setHit(rs.getInt("hit"));
				
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boards;
	}

	@Override
	public int delete(int num) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = con.prepareStatement("DELETE FROM concept5 WHERE num=?");
			ps.setInt(1, num);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void update(BoardDTO board) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE concept5 SET subject=?, content=? WHERE num=?");
			ps.setString(1, board.getSubject());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getNum());
			ps.executeUpdate();
		} catch (SQLException e) {
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
}




