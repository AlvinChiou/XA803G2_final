package com.lost.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mem.model.MemVO;


public class LostDAO implements LostDAO_interface {

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
			"INSERT INTO LOST(lostno, losttitle, lostpic, lostcontent, losttime, loststate, memno) VALUES (lost_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST order by lostno desc";
	private static final String GET_ONE_STMT = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST where lostno = ?";
	private static final String UPDATE = 
			"UPDATE LOST set losttitle=?, lostpic=?, lostcontent=?, losttime=?, loststate=? where lostno=?";
	private static final String DELETE = 
			"DELETE FROM LOST where lostno=?";
	private static final String LISTLOST_BYSTATE = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST where loststate = ? order by lostno desc";
	private static final String LISTLOST_BYMEMNO = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST where memno = ? order by lostno desc";
	
	@Override
	public int insert(LostVO lostVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, lostVO.getLosttitle());
			pstmt.setBytes(2, lostVO.getLostpic());
			pstmt.setString(3, lostVO.getLostcontent());
			pstmt.setDate(4, lostVO.getLosttime());
			pstmt.setInt(5, lostVO.getLoststate());
			pstmt.setInt(6, lostVO.getMemno());
			
			updateCount = pstmt.executeUpdate();
			
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
		return updateCount;
	}
		
	@Override
	public int update(LostVO lostVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, lostVO.getLosttitle());
			pstmt.setBytes(2, lostVO.getLostpic());
			pstmt.setString(3, lostVO.getLostcontent());
			pstmt.setDate(4, lostVO.getLosttime());
			pstmt.setInt(5, lostVO.getLoststate());
			pstmt.setInt(6, lostVO.getLostno());
			
			updateCount = pstmt.executeUpdate();
			
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
		return updateCount;
	}

	@Override
	public int delete(Integer lostno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, lostno);
			
			updateCount = pstmt.executeUpdate();
			
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
		return updateCount;
	}

	@Override
	public LostVO findByPrimaryKey(Integer lostno) {
		LostVO lostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, lostno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				lostVO = new LostVO();
				lostVO.setLostno(rs.getInt("lostno"));
				lostVO.setLosttitle(rs.getString("losttitle"));
				lostVO.setLostpic(rs.getBytes("lostpic"));
				lostVO.setLostcontent(rs.getString("lostcontent"));
				lostVO.setLosttime(rs.getDate("losttime"));
				lostVO.setLoststate(rs.getInt("loststate"));
				lostVO.setMemno(rs.getInt("memno"));
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
		return lostVO;
	}

	@Override
	public List<LostVO> getAll() {
		List<LostVO> list = new ArrayList<LostVO>();
		LostVO lostVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				lostVO = new LostVO();
				lostVO.setLostno(rs.getInt("lostno"));
				lostVO.setLosttitle(rs.getString("losttitle"));
				lostVO.setLostpic(rs.getBytes("lostpic"));
				lostVO.setLostcontent(rs.getString("lostcontent"));
				lostVO.setLosttime(rs.getDate("losttime"));
				lostVO.setLoststate(rs.getInt("loststate"));
				lostVO.setMemno(rs.getInt("memno"));
				list.add(lostVO);//Store the row in the vector;
				
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
	public Set<LostVO> listLost_ByState(Integer loststate) {
		Set<LostVO> set = new LinkedHashSet<LostVO>();
		LostVO lostVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTLOST_BYSTATE);
			pstmt.setInt(1, loststate);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				lostVO = new LostVO();
				lostVO.setLostno(rs.getInt("lostno"));
				lostVO.setLosttitle(rs.getString("losttitle"));
				lostVO.setLostpic(rs.getBytes("lostpic"));
				lostVO.setLostcontent(rs.getString("lostcontent"));
				lostVO.setLosttime(rs.getDate("losttime"));
				lostVO.setLoststate(rs.getInt("loststate"));
				lostVO.setMemno(rs.getInt("memno"));
				set.add(lostVO);// Store the row in the vector
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
		return set;
	}

	@Override
	public Set<LostVO> listLost_ByMemno(Integer memno) {
		Set<LostVO> set = new LinkedHashSet<LostVO>();
		LostVO lostVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTLOST_BYMEMNO);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				lostVO = new LostVO();
				lostVO.setLostno(rs.getInt("lostno"));
				lostVO.setLosttitle(rs.getString("losttitle"));
				lostVO.setLostpic(rs.getBytes("lostpic"));
				lostVO.setLostcontent(rs.getString("lostcontent"));
				lostVO.setLosttime(rs.getDate("losttime"));
				lostVO.setLoststate(rs.getInt("loststate"));
				lostVO.setMemno(rs.getInt("memno"));
				set.add(lostVO);// Store the row in the vector
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
		return set;
	}
	
	
}
