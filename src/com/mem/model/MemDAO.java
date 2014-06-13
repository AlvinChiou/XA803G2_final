package com.mem.model;

import java.sql.Connection;
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
import javax.sql.DataSource;


public class MemDAO implements MemDAO_interface {
	
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
			"INSERT INTO MEMBER(memno, memid, mempassword, memname, memidno, mememail, membirth, memadd, memsex, memtel, memstate) VALUES (mem_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT memno, memid, mempassword, memname, memidno, mememail, to_char(membirth, 'yyyy-mm-dd')membirth, memadd, memsex, memtel, memstate FROM MEMBER order by memno";
	private static final String GET_ONE_STMT = 
			"SELECT memno, memid, mempassword, memname, memidno, mememail, to_char(membirth, 'yyyy-mm-dd')membirth, memadd, memsex, memtel, memstate FROM MEMBER where memno = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER set memid=?, mempassword=?, memname=?, memidno=?, mememail=?, membirth=?, memadd=?, memsex=?, memtel=?, memstate=? where memno= ?";
	private static final String LISTMEM_BYSTATE = 
			"SELECT memno, memid, mempassword, memname, memidno, mememail, to_char(membirth, 'yyyy-mm-dd')membirth, memadd, memsex, memtel, memstate FROM MEMBER where memstate = ? order by memno";
	private static final String GET_ONE_BYMEMID = 
			"SELECT memno, memid, mempassword, memname, memidno, mememail, to_char(membirth, 'yyyy-mm-dd')membirth, memadd, memsex, memtel, memstate FROM MEMBER where memid = ?";
	
	@Override
	public int insert(MemVO memVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getMemid());
			pstmt.setString(2, memVO.getMempassword());
			pstmt.setString(3, memVO.getMemname());
			pstmt.setString(4, memVO.getMemidno());
			pstmt.setString(5, memVO.getMememail());
			pstmt.setDate(6, memVO.getMembirth());
			pstmt.setString(7, memVO.getMemadd());
			pstmt.setInt(8, memVO.getMemsex());
			pstmt.setString(9, memVO.getMemtel());
			pstmt.setInt(10, memVO.getMemstate());
			
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
 	public int update(MemVO memVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getMemid());
			pstmt.setString(2, memVO.getMempassword());
			pstmt.setString(3, memVO.getMemname());
			pstmt.setString(4, memVO.getMemidno());
			pstmt.setString(5, memVO.getMememail());
			pstmt.setDate(6, memVO.getMembirth());
			pstmt.setString(7, memVO.getMemadd());
			pstmt.setInt(8, memVO.getMemsex());
			pstmt.setString(9, memVO.getMemtel());
			pstmt.setInt(10, memVO.getMemstate());
			pstmt.setInt(11, memVO.getMemno());
			
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
	public MemVO findByPrimaryKey(Integer memno) {
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, memno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemidno(rs.getString("memidno"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMemsex(rs.getInt("memsex"));
				memVO.setMemtel(rs.getString("memtel"));
				memVO.setMemstate(rs.getInt("memstate"));
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
		return memVO;
	}

	@Override
	public MemVO findByMemid(String memid) {
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYMEMID);
			
			pstmt.setString(1, memid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemidno(rs.getString("memidno"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMemsex(rs.getInt("memsex"));
				memVO.setMemtel(rs.getString("memtel"));
				memVO.setMemstate(rs.getInt("memstate"));
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemidno(rs.getString("memidno"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMemsex(rs.getInt("memsex"));
				memVO.setMemtel(rs.getString("memtel"));
				memVO.setMemstate(rs.getInt("memstate"));
				list.add(memVO);//Store the row in the vector;
				
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
	public Set<MemVO> listMem_ByState(Integer memstate) {
		Set<MemVO> set = new LinkedHashSet<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTMEM_BYSTATE);
			pstmt.setInt(1, memstate);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemidno(rs.getString("memidno"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMemsex(rs.getInt("memsex"));
				memVO.setMemtel(rs.getString("memtel"));
				memVO.setMemstate(rs.getInt("memstate"));
				set.add(memVO); // Store the row in the vector
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
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
