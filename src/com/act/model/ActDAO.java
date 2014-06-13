package com.act.model;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ActDAO extends HttpServlet implements IActDAO {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//	String userid = "user1";
//	String passwd = "u111";

	private static final String INSERT_STMT = "INSERT INTO Act (actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee,actStatus,memNo,empNo) VALUES (Act_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee,actStatus,memNo,empNo FROM Act order by actNo desc";
	private static final String GET_ALL_STMT_By_Date = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee,actStatus,memNo,empNo FROM Act where substr(actStartTime,1,9) = ? order by actNo";
	private static final String GET_ALL_STMT_By_MemNo = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee,actStatus,memNo,empNo FROM Act where memNo = ? order by actNo";
	private static final String GET_ONE_STMT = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee,actStatus,memNo,empNo FROM Act where actNo = ?";
	private static final String GET_ONE_STMT_By_Date = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee,actStatus,memNo,empNo FROM Act where substr(actStartTime,1,9) = ?";
	private static final String DELETE = "DELETE FROM Act where actNo = ?";
	private static final String UPDATE = "UPDATE Act set actName=?,actContent=?,actStartTime=?,actEndTime=?,actPic=?,actEquipment=?,actDeposit=?,actHostFee=?,actRegFee=?,actStatus=?,memNo=?,empNo=? where actNo = ?";
	private static final String UPDATE_STATUS = "update Act set actStatus = ? where actEndTime < SYSDATE";
	
	
	@Override
	public int insert(ActVO act) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, act.getActName());
			pstmt.setString(2, act.getActContent());
			pstmt.setTimestamp(3, act.getActStartTime());
			pstmt.setTimestamp(4, act.getActEndTime());
			pstmt.setBytes(5, act.getActPic());
			pstmt.setString(6, act.getActEquipment());
			pstmt.setDouble(7, act.getActDeposit());
			pstmt.setDouble(8, act.getActHostFee());
			pstmt.setDouble(9, act.getActRegFee());
			pstmt.setString(10, act.getActStatus());
			pstmt.setInt(11, act.getMemNo());
			pstmt.setInt(12, act.getEmpNo());
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (SQLException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return updateCount;
	}

	@Override
	public int update(ActVO act) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, act.getActName());
			pstmt.setString(2, act.getActContent());
			pstmt.setTimestamp(3, act.getActStartTime());
			pstmt.setTimestamp(4, act.getActEndTime());
			pstmt.setBytes(5, act.getActPic());
			pstmt.setString(6, act.getActEquipment());
			pstmt.setDouble(7, act.getActDeposit());
			pstmt.setDouble(8, act.getActHostFee());
			pstmt.setDouble(9, act.getActRegFee());
			pstmt.setString(10, act.getActStatus());
			pstmt.setInt(11, act.getMemNo());
			pstmt.setInt(12, act.getEmpNo());
			pstmt.setDouble(13, act.getActNo());
			
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return updateCount;
	}

	@Override
	public int delete(Integer actNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actNo);

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return updateCount;
	}

	@Override
	public ActVO findByPrimaryKey(Integer actNo) {
		ActVO act = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				act = new ActVO();
				act.setActNo(rs.getInt("actNo"));
				act.setActName(rs.getString("actName"));
				act.setActContent(rs.getString("actContent"));
				act.setActStartTime(rs.getTimestamp("actStartTime"));
				act.setActEndTime(rs.getTimestamp("actEndTime"));
				act.setActPic(rs.getBytes("actPic"));
				act.setActEquipment(rs.getString("actEquipment"));
				act.setActDeposit(rs.getDouble("actDeposit"));
				act.setActHostFee(rs.getDouble("actHostFee"));
				act.setActRegFee(rs.getDouble("actRegFee"));
				act.setActStatus(rs.getString("actStatus"));
				act.setMemNo(rs.getInt("memNo"));
				act.setEmpNo(rs.getInt("empNo"));
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return act;
	}

	@Override
	public List<ActVO> getAll() {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO act = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				act = new ActVO();
				act.setActNo(rs.getInt("actNo"));
				act.setActName(rs.getString("actName"));
				act.setActContent(rs.getString("actContent"));
				act.setActStartTime(rs.getTimestamp("actStartTime"));
				act.setActEndTime(rs.getTimestamp("actEndTime"));
				act.setActPic(rs.getBytes("actPic"));
				act.setActEquipment(rs.getString("actEquipment"));
				act.setActDeposit(rs.getDouble("actDeposit"));
				act.setActHostFee(rs.getDouble("actHostFee"));
				act.setActRegFee(rs.getDouble("actRegFee"));
				act.setActStatus(rs.getString("actStatus"));
				act.setMemNo(rs.getInt("memNo"));
				act.setEmpNo(rs.getInt("empNo"));
				list.add(act);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public List<ActVO> getAll(Map<String, String[]> map) {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Act "
		          + jdbcUtil_CompositeQuery_Act.get_WhereCondition(map)
		          + "order by actNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				actVO = new ActVO();
				actVO.setActNo(rs.getInt("actNo"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActContent(rs.getString("actContent"));
				actVO.setActStartTime(rs.getTimestamp("actStartTime"));
				actVO.setActEndTime(rs.getTimestamp("actEndTime"));
				actVO.setActPic(rs.getBytes("actPic"));
				actVO.setActEquipment(rs.getString("actEquipment"));
				actVO.setActDeposit(rs.getDouble("actDeposit"));
				actVO.setActHostFee(rs.getDouble("actHostFee"));
				actVO.setActRegFee(rs.getDouble("actRegFee"));
				actVO.setActStatus(rs.getString("actStatus"));
				actVO.setMemNo(rs.getInt("memNo"));
				actVO.setEmpNo(rs.getInt("empNo"));
				list.add(actVO); // Store the row in the List
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

	@Override
	public ActVO findActsByDate(Timestamp actStartTime) {
		ActVO act = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_By_Date);

			pstmt.setTimestamp(1, actStartTime);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				act = new ActVO();
				act.setActNo(rs.getInt("actNo"));
				act.setActName(rs.getString("actName"));
				act.setActContent(rs.getString("actContent"));
				act.setActStartTime(rs.getTimestamp("actStartTime"));
				act.setActEndTime(rs.getTimestamp("actEndTime"));
				act.setActPic(rs.getBytes("actPic"));
				act.setActEquipment(rs.getString("actEquipment"));
				act.setActDeposit(rs.getDouble("actDeposit"));
				act.setActHostFee(rs.getDouble("actHostFee"));
				act.setActRegFee(rs.getDouble("actRegFee"));
				act.setActStatus(rs.getString("actStatus"));
				act.setMemNo(rs.getInt("memNo"));
				act.setEmpNo(rs.getInt("empNo"));
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return act;
	}

	@Override
	public List<ActVO> getAllActsByDate(String actStartDate) {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO act = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_By_Date);
			
			pstmt.setString(1, actStartDate);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				act = new ActVO();
				act.setActNo(rs.getInt("actNo"));
				act.setActName(rs.getString("actName"));
				act.setActContent(rs.getString("actContent"));
				act.setActStartTime(rs.getTimestamp("actStartTime"));
				act.setActEndTime(rs.getTimestamp("actEndTime"));
				act.setActPic(rs.getBytes("actPic"));
				act.setActEquipment(rs.getString("actEquipment"));
				act.setActDeposit(rs.getDouble("actDeposit"));
				act.setActHostFee(rs.getDouble("actHostFee"));
				act.setActRegFee(rs.getDouble("actRegFee"));
				act.setActStatus(rs.getString("actStatus"));
				act.setMemNo(rs.getInt("memNo"));
				act.setEmpNo(rs.getInt("empNo"));
				list.add(act);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public List<ActVO> getAllActsByMemNo(Integer memNo) {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO act = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_By_MemNo);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				act = new ActVO();
				act.setActNo(rs.getInt("actNo"));
				act.setActName(rs.getString("actName"));
				act.setActContent(rs.getString("actContent"));
				act.setActStartTime(rs.getTimestamp("actStartTime"));
				act.setActEndTime(rs.getTimestamp("actEndTime"));
				act.setActPic(rs.getBytes("actPic"));
				act.setActEquipment(rs.getString("actEquipment"));
				act.setActDeposit(rs.getDouble("actDeposit"));
				act.setActHostFee(rs.getDouble("actHostFee"));
				act.setActRegFee(rs.getDouble("actRegFee"));
				act.setActStatus(rs.getString("actStatus"));
				act.setMemNo(rs.getInt("memNo"));
				act.setEmpNo(rs.getInt("empNo"));
				list.add(act);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void updateStatus(String actStatus){
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setString(1,actStatus);

			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException se) {

					 se.printStackTrace();
			// Clean up JDBC resources
		} finally {
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
		
	}
	
	}

	//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		super.doGet(req, resp);
//		
//		resp.setContentType("text/html; charset=Big5");
//		resp.setCharacterEncoding("Big5");
//		ActDAO dao = new ActDAO();
//		PrintWriter out = resp.getWriter(); 
//		
//		List<ActVO> list = dao.getAll();
//		for (ActVO vo4 : list) {
//
//			out.print(vo4.getActNo() + ",");
//			 out.print(vo4.getActName() + ",");
//			 out.print(vo4.getActContent() + ",");
//			 out.print(vo4.getActStartTime() + ",");
//			 out.print(vo4.getActEndTime() + ",");
//			 out.print(vo4.getActPic() + ",");
//			 out.print(vo4.getActEquipment() + ",");
//			 out.print(vo4.getActDeposit() + ",");
//			 out.print(vo4.getActHostFee() + ",");
//			 out.println(vo4.getActRegFee() + ",");
//			 out.println("<br>");
//		}
//
//	public static void main(String[] args) throws Exception {
//		ActDAO dao = new ActDAO();
//
//		// 查詢單筆
//		ActVO vo1 = dao.findByPrimaryKey(1);
//
//		System.out.print(vo1.getActNo() + ",");
//		System.out.print(vo1.getActName() + ",");
//		System.out.print(vo1.getActContent() + ",");
//		System.out.print(vo1.getActStartTime() + ",");
//		System.out.print(vo1.getActEndTime() + ",");
//		System.out.print(vo1.getActPic() + ",");
//		System.out.print(vo1.getActEquipment() + ",");
//		System.out.print(vo1.getActDeposit() + ",");
//		System.out.print(vo1.getActHostFee() + ",");
//		System.out.println(vo1.getActRegFee() + ",");
//
//		System.out.println("---------------------");
//
//		// 新增
////		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
////		int len = fis.available();
////		byte[] buffer = new byte[len];
////        fis.read(buffer);
////        fis.close();
////		
////		ActVO vo2 = new ActVO();
////		vo2.setActName("1111");
////		vo2.setActContent("boy");
////		vo2.setActStartTime(java.sql.Timestamp.valueOf("2005-01-01 09:00:00"));
////		vo2.setActEndTime(java.sql.Timestamp.valueOf("2005-01-20 16:00:00"));
////		vo2.setActPic(buffer);
////		vo2.setActEquipment(null);
////		vo2.setActDeposit(2000);
////		vo2.setActHostFee(1000);
////		vo2.setActRegFee(500);
////		int updateCount_insert = dao.insert(vo2);
////		System.out.println(updateCount_insert);
////
////		System.out.println("---------------------");
//		
////		// 修改
////		ActVO vo3 = new ActVO();
////		vo3.setActNo(2);
////		vo3.setActName("123456");
////		vo3.setActContent("boy");
////		vo3.setActStartTime(java.sql.Timestamp.valueOf("2005-01-01 08:30:00"));
////		vo3.setActEndTime(java.sql.Timestamp.valueOf("2005-01-20 12:30:00"));
////		vo3.setActPic(null);
////		vo3.setActEquipment(null);
////		vo3.setActDeposit(2000);
////		vo3.setActHostFee(1000);
////		vo3.setActRegFee(500);
////		int updateCount_update = dao.update(vo3);
////		System.out.println(updateCount_update);
////
////		System.out.println("---------------------");
//		
//		// 刪除
////		int updateCount_delete = dao.delete(2);
////		System.out.println(updateCount_delete);
////		
////		System.out.println("---------------------");
//		
//        // 查詢多筆
//
//		List<ActVO> list = dao.getAll();
//		for (ActVO vo4 : list) {
//
//			System.out.print(vo4.getActNo() + ",");
//			System.out.print(vo4.getActName() + ",");
//			System.out.print(vo4.getActContent() + ",");
//			System.out.print(vo4.getActStartTime() + ",");
//			System.out.print(vo4.getActEndTime() + ",");
//			System.out.print(vo4.getActPic() + ",");
//			System.out.print(vo4.getActEquipment() + ",");
//			System.out.print(vo4.getActDeposit() + ",");
//			System.out.print(vo4.getActHostFee() + ",");
//			System.out.println(vo4.getActRegFee() + ",");
//			System.out.println();
//			
//			}
//		System.out.println("---------------------");
//	}

	
	
