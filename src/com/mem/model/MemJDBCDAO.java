package com.mem.model;

import java.sql.*;
import java.util.*;

import javax.management.RuntimeErrorException;


public class MemJDBCDAO implements MemDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl3";
	String userid = "user1";
	String passwd = "u111";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			//Handle any driver errors
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load datebase driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			//Handle any driver errors
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load datebase driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			//Handle any driver errors
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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

	public static void main(String[] args){
		MemJDBCDAO dao = new MemJDBCDAO();
		
//		//新增
//		MemVO memVO = new MemVO();
//		memVO.setMemid("qwe147");
//		memVO.setMempassword("qazwsx");
//		memVO.setMemname("吳永志1");
//		memVO.setMemidno("C123456789");
//		memVO.setMememail("qwe147@hotmail.com");
//		memVO.setMembirth(java.sql.Date.valueOf("1989-02-13"));
//		memVO.setMemadd("花蓮縣吉安鄉信封路21號");
//		memVO.setMemsex("女");
//		memVO.setMemtel("0988709200");
//		memVO.setMemstate("待驗證");
//		int updateCount_insert = dao.insert(memVO);
//		System.out.println(updateCount_insert);
		
//		//修改
//		MemVO memVO = new MemVO();
//		memVO.setMemno(7001);
//		memVO.setMempassword("4567890");
//		memVO.setMemname("WU Knifeman");
//		memVO.setMememail("ccc@hotmail.com");
//		memVO.setMemadd("中壢市中大路300-2號");
//		memVO.setMemtel("0923456789");
//		memVO.setMemstate("已停權");
//		int updateCount_update = dao.update(memVO);
//		System.out.println(updateCount_update);
		
//		//查詢
		MemVO memVO = dao.findByMemid("tiger");
		System.out.print(memVO.getMemno() + ", ");
		System.out.print(memVO.getMemid() + ", ");
		System.out.print(memVO.getMempassword() + ", ");
		System.out.print(memVO.getMemname() + ", ");
		System.out.print(memVO.getMemidno() + ", ");
		System.out.print(memVO.getMememail() + ", ");
		System.out.print(memVO.getMembirth() + ", ");
		System.out.print(memVO.getMemadd() + ", ");
		System.out.print(memVO.getMemsex() + ", ");
		System.out.print(memVO.getMemtel() + ", ");
		System.out.println(memVO.getMemstate());
		System.out.println("---------------------------------");

//		//查詢
//		List<MemVO> list = dao.getAll();
//		for(MemVO aMem : list){
//			System.out.print(aMem.getMemno() + ", ");
//			System.out.print(aMem.getMemid() + ", ");
//			System.out.print(aMem.getMempassword() + ", ");
//			System.out.print(aMem.getMemname() + ", ");
//			System.out.print(aMem.getMemidno() + ", ");
//			System.out.print(aMem.getMememail() + ", ");
//			System.out.print(aMem.getMembirth() + ", ");
//			System.out.print(aMem.getMemadd() + ", ");
//			System.out.print(aMem.getMemsex() + ", ");
//			System.out.print(aMem.getMemtel() + ", ");
//			System.out.println(aMem.getMemstate());
//		}
	}
}
