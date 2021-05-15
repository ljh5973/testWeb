package com.testweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.testweb.util.JdbcUtil;

public class BoardDAO {
	
		private static BoardDAO instance= new BoardDAO();
		
		
		public BoardDAO() {
				try {
				InitialContext ctx= new InitialContext();
				ds=(DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
				} catch (Exception e) {

				
				}
				
				
		}
		public static BoardDAO getInstance() {
			return instance;
		}
		private String url= "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		private String uid="JSP";
		private String upw="JSP";
		
		private DataSource ds;
		
		private Connection conn=null;
		private PreparedStatement pstmt=null;
		private ResultSet rs=null;
		
		
		public void regist(String writer, String title, String content) {
			String sql="insert into board2(bno,writer , title, content) values(board2_seq.nextval,?,?,?)";
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
		}
		
		public ArrayList<BoardVO> getList(int pageNum, int amount){
			ArrayList<BoardVO> list=new ArrayList<>();
			
			String sql="select * from \r\n" + 
					"            (select rownum rn,a.*  from\r\n" + 
					"                    (select * from board2 order by bno desc)a\r\n" + 
					"                    )\r\n" + 
					"where rn>? and rn<=?";
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, (pageNum-1)*amount);
				pstmt.setInt(2, pageNum*amount);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					int bno=rs.getInt("bno");
					String writer=rs.getString("writer");
					String title=rs.getString("title");
					String content=rs.getString("content");
					Timestamp regdate=rs.getTimestamp("regdate");
					int hit=rs.getInt("hit");
					
					//한바퀴 회전당 VO하나씩 생성
					BoardVO vo= new BoardVO(bno, writer, title, content, regdate, hit);
					list.add(vo);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			return list;
		}
		
		//전체 게시글 수
		public int getTotal() {
			int result=0;
			
			String sql="select count(*) as total from board2";
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					result=rs.getInt("total");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			return result;
		}
		public BoardVO getContent(String bno) {
			BoardVO vo=null;
			String sql="select * from board2 where bno=?";
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, bno);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					
					int bno2=rs.getInt("bno");
					String writer=rs.getString("writer");
					String content=rs.getString("content");
					String title=rs.getString("title");
					Timestamp regdate=rs.getTimestamp("regdate");
					int hit=rs.getInt("hit");
					vo=new BoardVO(bno2, writer, title, content, regdate, hit);
					
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
					
					return vo;
		}
		public void Update(String title, String content, int bno) {
			String sql="update board2 set title=?, content=?, regdate=sysdate where bno=?";
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setInt(3, bno);
				
				pstmt.executeUpdate();

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
		}

}
