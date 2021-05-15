package com.testweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.testweb.util.JdbcUtil;

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	
	
	private UserDAO() {
		
		try {
			InitialContext ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static UserDAO getInstance() {
		
		return instance;
	}

	private String url="jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private String uid="JSP";
	private String upw="JSP";
	
	
	private Connection conn= null;
	private PreparedStatement pstmt= null;
	private ResultSet rs = null;
	
	private DataSource ds;
	
	public int checkId(String id) {
		int result= 0;
		String sql="select* from users where id=?";
		
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {	//rs.next()가 true라면 중복
				result=0;
			}else {
				result=1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return result;
	}
	public int join(UserVO vo) {
		int result=0;
		String sql="insert into users(id,pw,name,phone1,phone2,phone3,email,email2,addressbasic,addressdetail) values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getId() );
			pstmt.setString(2,vo.getPw());
			pstmt.setString(3,vo.getName() );
			pstmt.setInt(4, vo.getPhone1());
			pstmt.setInt(5, vo.getPhone2());
			pstmt.setInt(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getEmail2());
			pstmt.setString(9,vo.getAddressbasic() );
			pstmt.setString(10, vo.getAddressdetail());
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		return result;
		
	}
}
