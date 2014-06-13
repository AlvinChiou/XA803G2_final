package com.gb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_GB;

public class GbDAO implements GbDAO_interface{

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
			"INSERT INTO GB(gbno, gbtitle, gbcontent, gbtime, lostno, memno) VALUES (gb_seq.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT gbno, gbtitle, gbcontent, gbtime, lostno, memno FROM GB order by gbno";
	private static final String GET_ONE_STMT = 
			"SELECT gbno, gbtitle, gbcontent, gbtime, lostno, memno FROM GB where gbno = ?";
	private static final String UPDATE = 
			"UPDATE GB set gbtitle=?, gbcontent=?, gbtime=?, lostno=?, memno=? where gbno=?";
	private static final String DELETE = 
			"DELETE FROM GB where gbno=?";
	private static final String LISTGB_BYLOSTNO = 
			"SELECT gbno, gbtitle, gbcontent, gbtime, lostno, memno FROM GB WHERE lostno = ? order by gbno";
	private static final String LISTGB_BYMEMNO = 
			"SELECT gbno, gbtitle, gbcontent, gbtime, lostno, memno FROM GB WHERE memno = ? order by gbno";
	
	@Override
	public int insert(GbVO gbVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, gbVO.getGbtitle());
			pstmt.setString(2, gbVO.getGbcontent());
			pstmt.setTimestamp(3, gbVO.getGbtime());
			pstmt.setInt(4, gbVO.getLostno());
			pstmt.setInt(5, gbVO.getMemno());
			
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
	public int update(GbVO gbVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, gbVO.getGbtitle());
			pstmt.setString(2, gbVO.getGbcontent());
			pstmt.setTimestamp(3, gbVO.getGbtime());
			pstmt.setInt(4, gbVO.getLostno());
			pstmt.setInt(5, gbVO.getMemno());
			pstmt.setInt(6, gbVO.getGbno());
			
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
	public int delete(Integer gbno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, gbno);
			
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
	public GbVO findByPrimaryKey(Integer gbno) {
		GbVO gbVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, gbno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO ¤]ºÙ¬° Domain objects
				gbVO = new GbVO();
				gbVO.setGbno(rs.getInt("gbno"));
				gbVO.setGbtitle(rs.getString("gbtitle"));
				gbVO.setGbcontent(rs.getString("gbcontent"));
				gbVO.setGbtime(rs.getTimestamp("gbtime"));
				gbVO.setLostno(rs.getInt("lostno"));
				gbVO.setMemno(rs.getInt("memno"));
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
		return gbVO;
	}

	@Override
	public List<GbVO> getAll() {
		List<GbVO> list = new ArrayList<GbVO>();
		GbVO gbVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				gbVO = new GbVO();
				gbVO.setGbno(rs.getInt("gbno"));
				gbVO.setGbtitle(rs.getString("gbtitle"));
				gbVO.setGbcontent(rs.getString("gbcontent"));
				gbVO.setGbtime(rs.getTimestamp("gbtime"));
				gbVO.setLostno(rs.getInt("lostno"));
				gbVO.setMemno(rs.getInt("memno"));
				list.add(gbVO);//Store the row in the vector;
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
	public List<GbVO> listGb_ByLostno(Integer lostno) {
		List<GbVO> list = new ArrayList<GbVO>();
		GbVO gbVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTGB_BYLOSTNO);
			
			pstmt.setInt(1, lostno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				gbVO = new GbVO();
				gbVO.setGbno(rs.getInt("gbno"));
				gbVO.setGbtitle(rs.getString("gbtitle"));
				gbVO.setGbcontent(rs.getString("gbcontent"));
				gbVO.setGbtime(rs.getTimestamp("gbtime"));
				gbVO.setLostno(rs.getInt("lostno"));
				gbVO.setMemno(rs.getInt("memno"));
				list.add(gbVO);//Store the row in the vector;
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
	public List<GbVO> listGb_ByMemno(Integer memno) {
		List<GbVO> list = new ArrayList<GbVO>();
		GbVO gbVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTGB_BYMEMNO);
			
			pstmt.setInt(1, memno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				gbVO = new GbVO();
				gbVO.setGbno(rs.getInt("gbno"));
				gbVO.setGbtitle(rs.getString("gbtitle"));
				gbVO.setGbcontent(rs.getString("gbcontent"));
				gbVO.setGbtime(rs.getTimestamp("gbtime"));
				gbVO.setLostno(rs.getInt("lostno"));
				gbVO.setMemno(rs.getInt("memno"));
				list.add(gbVO);//Store the row in the vector;
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
	public List<GbVO> getAll(Map<String, String[]> map) {
		List<GbVO> list = new ArrayList<GbVO>();
		GbVO gbVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from gb "
		          + jdbcUtil_CompositeQuery_GB.get_WhereCondition(map)
		          + "order by gbno";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("¡´¡´finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				gbVO = new GbVO();
				gbVO.setGbno(rs.getInt("gbno"));
				gbVO.setGbtitle(rs.getString("gbtitle"));
				gbVO.setGbcontent(rs.getString("gbcontent"));
				gbVO.setGbtime(rs.getTimestamp("gbtime"));
				gbVO.setLostno(rs.getInt("lostno"));
				gbVO.setMemno(rs.getInt("memno"));
				list.add(gbVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
}
