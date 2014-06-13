package com.news.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



public class NewsDAO implements NewsDAO_interface {

	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e){
			e.printStackTrace();
			}
		}
	
	private static final String INSERT_STMT = 
			"INSERT INTO NEWS(newsno, newstitle, newstype, newscontent, newspic, newspotime, empno) VALUES(news_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT newsno, newstitle, newstype, newscontent, newspic, to_char(newspotime, 'yyyy-mm-dd')newspotime, empno FROM NEWS order by newsno desc";
	private static final String GET_ONE_STMT = 
			"SELECT newsno, newstitle, newstype, newscontent, newspic, to_char(newspotime, 'yyyy-mm-dd')newspotime, empno FROM NEWS where newsno = ?";
	private static final String UPDATE = 
			"UPDATE NEWS set newstitle=?, newstype=?, newscontent=?, newspic=?, newspotime=?, empno=? where newsno=?";
	private static final String DELETE = 
			"DELETE FROM NEWS where newsno=?";
	private static final String LISTNEWS_BYTYPE = 
			"SELECT newsno, newstitle, newstype, newscontent, newspic, to_char(newspotime, 'yyyy-mm-dd')newspotime, empno FROM NEWS where newstype = ? order by newsno desc";
	
	@Override
	public void insert(NewsVO newsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, newsVO.getNewstitle());
			pstmt.setInt(2, newsVO.getNewstype());
			pstmt.setString(3, newsVO.getNewscontent());
			pstmt.setBytes(4, newsVO.getNewspic());
			pstmt.setDate(5, newsVO.getNewspotime());
			pstmt.setInt(6, newsVO.getEmpno());
			
			pstmt.executeUpdate();
			
			//Handle any SQL errors
		}catch (SQLException se){
			throw new RuntimeException("A datebase error occured." + se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(NewsVO newsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, newsVO.getNewstitle());
			pstmt.setInt(2, newsVO.getNewstype());
			pstmt.setString(3, newsVO.getNewscontent());
			pstmt.setBytes(4, newsVO.getNewspic());
			pstmt.setDate(5, newsVO.getNewspotime());
			pstmt.setInt(6, newsVO.getEmpno());
			pstmt.setInt(7, newsVO.getNewsno());
			
			pstmt.executeUpdate();
			
			//Handle any SQL errors
		}catch (SQLException se){
			throw new RuntimeException("A datebase error occured." + se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(Integer newsno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, newsno);
			
			pstmt.executeUpdate();
			
			//Handle any SQL errors
		}catch (SQLException se){
			throw new RuntimeException("A datebase error occured." + se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	
	}

	@Override
	public NewsVO findByPrimaryKey(Integer newsno) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, newsno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO ¤]ºÙ¬° Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getInt("newsno"));
				newsVO.setNewstitle(rs.getString("newstitle"));
				newsVO.setNewstype(rs.getInt("newstype"));
				newsVO.setNewscontent(rs.getString("newscontent"));
				newsVO.setNewspic(rs.getBytes("newspic"));
				newsVO.setNewspotime(rs.getDate("newspotime"));
				newsVO.setEmpno(rs.getInt("empno"));
			}
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getInt("newsno"));
				newsVO.setNewstitle(rs.getString("newstitle"));
				newsVO.setNewstype(rs.getInt("newstype"));
				newsVO.setNewscontent(rs.getString("newscontent"));
				newsVO.setNewspic(rs.getBytes("newspic"));
				newsVO.setNewspotime(rs.getDate("newspotime"));
				newsVO.setEmpno(rs.getInt("empno"));
				list.add(newsVO);//Store the row in the vector;
				
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<NewsVO> listNews_ByType(Integer newstype) {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTNEWS_BYTYPE);
			
			pstmt.setInt(1, newstype);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getInt("newsno"));
				newsVO.setNewstitle(rs.getString("newstitle"));
				newsVO.setNewstype(rs.getInt("newstype"));
				newsVO.setNewscontent(rs.getString("newscontent"));
				newsVO.setNewspic(rs.getBytes("newspic"));
				newsVO.setNewspotime(rs.getDate("newspotime"));
				newsVO.setEmpno(rs.getInt("empno"));
				list.add(newsVO);//Store the row in the vector;
				
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
