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


public class LostJNDIDAO extends HttpServlet implements LostDAO_interface {

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
			"SELECT lost_no, lost_title, lost_pic, lost_content, to_char(lost_time, 'yyyy-mm-dd')lost_time, lost_state, mem_no FROM LOST order by lost_no";
	private static final String GET_ONE_STMT = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST where lostno = ?";
	private static final String UPDATE = 
			"UPDATE LOST set lost_title=?, lost_pic=?, lost_content=?, lost_state=? where lost_no=?";
	private static final String DELETE = 
			"DELETE FROM LOST where lost_no=?";
	private static final String LISTLOST_BYSTATE = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST where loststate = ? order by lostno";
	private static final String LISTLOST_BYMEMNO = 
			"SELECT lostno, losttitle, lostpic, lostcontent, to_char(losttime, 'yyyy-mm-dd')losttime, loststate, memno FROM LOST where memno = ?";
	
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
			pstmt.setInt(4, lostVO.getLoststate());
			pstmt.setInt(5, lostVO.getLostno());
			
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
				lostVO.setLostno(rs.getInt("lost_no"));
				lostVO.setLosttitle(rs.getString("lost_title"));
				lostVO.setLostpic(rs.getBytes("lost_pic"));
				lostVO.setLostcontent(rs.getString("lost_content"));
				lostVO.setLosttime(rs.getDate("lost_time"));
				lostVO.setLoststate(rs.getInt("lost_state"));
				lostVO.setMemno(rs.getInt("mem_no"));
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
				set.add(lostVO);
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=Big5");
		
		req.setCharacterEncoding("Big5");
		
		LostJNDIDAO dao = new LostJNDIDAO();
		
		LostVO lostVO1 = new LostVO();
		lostVO1.setLosttitle("幫忙找找我家的貓貓!!");
		
		FileInputStream fis = new FileInputStream("C:/images/R8.jpg");
		int len = fis.available();
		byte[] buffer = new byte[len];
		fis.read(buffer);
		lostVO1.setLostpic(buffer);
		fis.close();
		
		lostVO1.setLostcontent("寵物協尋");
		lostVO1.setLosttime(java.sql.Date.valueOf("2014-04-22"));
		lostVO1.setLoststate(0);
		lostVO1.setMemno(7005);
		int updateCount_insert = dao.insert(lostVO1);
		System.out.println(updateCount_insert);
	}

//	public static void main(String[] args) throws Exception{
//		LostJDBCDAO dao = new LostJDBCDAO();
//		
//		//新增
//		LostVO lostVO1 = new LostVO();
//		lostVO1.setLosttitle("寵物協尋");
//		
//		FileInputStream fis = new FileInputStream("C:/images/R8.jpg");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
//		fis.read(buffer);
//		lostVO1.setLostpic(buffer);
//		fis.close();
//		
//		lostVO1.setLostcontent("寵物協尋");
//		lostVO1.setLosttime(java.sql.Date.valueOf("2014-04-22"));
//		lostVO1.setLoststate("失蹤");
//		lostVO1.setMemno(7002);
//		int updateCount_insert = dao.insert(lostVO1);
//		System.out.println(updateCount_insert);
//		
//		//修改
//		LostVO lostVO2 = new LostVO();
//		lostVO2.setLostno(5001);
//		lostVO2.setLosttitle("WU Knifeman");
//		
//		FileInputStream fis = new FileInputStream("C:/images/R8.jpg");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
//		fis.read(buffer);
//		lostVO2.setLostpic(buffer);
//		fis.close();
//		
//		lostVO2.setLostcontent("協尋");
//		lostVO2.setLoststate("失蹤");
//		int updateCount_update = dao.update(lostVO2);
//		System.out.println(updateCount_update);
//		
//		//刪除
//		int updateCount_delete = dao.delete(5005);
//		System.out.println(updateCount_delete);
//		
		//查詢
//		LostVO lostVO3 = dao.findByPrimaryKey(5002);
//		System.out.print(lostVO3.getLostno() + ", ");
//		System.out.print(lostVO3.getLosttitle() + ", ");
//		System.out.print(lostVO3.getLostpic() + ", ");
//		System.out.print(lostVO3.getLostcontent() + ", ");
//		System.out.print(lostVO3.getLosttime() + ", ");
//		System.out.println(lostVO3.getMemno());
//		System.out.println("----------------------------------");
////		//查詢
//		List<LostVO> list = dao.getAll();
//		for(LostVO aLost : list){
//			System.out.print(aLost.getLostno() + ", ");
//			System.out.print(aLost.getLosttitle() + ", ");
//			System.out.print(aLost.getLostpic() + ", ");
//			System.out.print(aLost.getLostcontent() + ", ");
//			System.out.print(aLost.getLosttime() + ", ");
//			System.out.println(aLost.getMemno());
//		}
//	}
}
