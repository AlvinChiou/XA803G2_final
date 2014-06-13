package com.mem.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


public class MemJNDIDAO extends HttpServlet implements MemDAO_interface {
	
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
	private static final String LISTMEM_BySTATE = 
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
	public Set<MemVO> listMem_ByState(Integer memstate) {
		Set<MemVO> set = new LinkedHashSet<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LISTMEM_BySTATE);
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html; charset=Big5");
		
		req.setCharacterEncoding("Big5");
		
		MemJNDIDAO dao = new MemJNDIDAO();
		
		PrintWriter out = resp.getWriter();
		
		MemVO memVO = dao.findByMemid("tiger");
		out.print(memVO.getMemno() + ", ");
		out.print(memVO.getMemid() + ", ");
		out.print(memVO.getMempassword() + ", ");
		out.print(memVO.getMemname() + ", ");
		out.print(memVO.getMemidno() + ", ");
		out.print(memVO.getMememail() + ", ");
		out.print(memVO.getMembirth() + ", ");
		out.print(memVO.getMemadd() + ", ");
		out.print(memVO.getMemsex() + ", ");
		out.print(memVO.getMemtel() + ", ");
		out.println(memVO.getMemstate());
		out.println("---------------------------------");
	}
	
	
//	public static void main(String[] args){
//		MemJDBCDAO dao = new MemJDBCDAO();
//		
//		//新增
//		MemVO memVO = new MemVO();
//		memVO.setMemId("qwe147");
//		memVO.setMemPassword("qazwsx");
//		memVO.setMemName("吳永志1");
//		memVO.setMemIdno("C123456789");
//		memVO.setMemEmail("qwe147@hotmail.com");
//		memVO.setMemBirth(java.sql.Date.valueOf("1989-02-13"));
//		memVO.setMemAdd("花蓮縣吉安鄉信封路21號");
//		memVO.setMemSex("女");
//		memVO.setMemTel("0988709200");
//		memVO.setMemState("待驗證");
//		int updateCount_insert = dao.insert(memVO);
//		System.out.println(updateCount_insert);
//		
//		//修改
//		MemVO memVO = new MemVO();
//		memVO.setMemNo(7002);
//		memVO.setMemName("WU Knifeman");
//		memVO.setMemPassword("4567890");
//		memVO.setMemEmail("ccc@hotmail.com");
//		memVO.setMemAdd("中壢市中大路300-2號");
//		memVO.setMemTel("0923456789");
//		int updateCount_update = dao.update(memVO);
//		System.out.println(updateCount_update);
//		
//		//查詢
//		MemVO memVO = dao.findByPrimaryKey(7003);
//		System.out.print(memVO.getMemNo() + ", ");
//		System.out.print(memVO.getMemId() + ", ");
//		System.out.print(memVO.getMemPassword() + ", ");
//		System.out.print(memVO.getMemName() + ", ");
//		System.out.print(memVO.getMemIdno() + ", ");
//		System.out.print(memVO.getMemEmail() + ", ");
//		System.out.print(memVO.getMemBirth() + ", ");
//		System.out.print(memVO.getMemAdd() + ", ");
//		System.out.print(memVO.getMemSex() + ", ");
//		System.out.print(memVO.getMemTel() + ", ");
//		System.out.println(memVO.getMemState());
//		System.out.println("---------------------------------");
//
//		//查詢
//		List<MemVO> list = dao.getAll();
//		for(MemVO aMem : list){
//			System.out.print(aMem.getMemNo() + ", ");
//			System.out.print(aMem.getMemId() + ", ");
//			System.out.print(aMem.getMemPassword() + ", ");
//			System.out.print(aMem.getMemName() + ", ");
//			System.out.print(aMem.getMemIdno() + ", ");
//			System.out.print(aMem.getMemEmail() + ", ");
//			System.out.print(aMem.getMemBirth() + ", ");
//			System.out.print(aMem.getMemAdd() + ", ");
//			System.out.print(aMem.getMemSex() + ", ");
//			System.out.print(aMem.getMemTel() + ", ");
//			System.out.println(aMem.getMemState());
//		}
//	}
}
