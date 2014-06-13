package com.apt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Emp2;

public class AptDAO implements AptDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@192.168.11.128:1521:orcl";
//	String userid = "pet";
//	String passwd = "pet";

	private static final String INSERT_STMT = "INSERT INTO APPOINTMENT (aptNo, aptDate, aptPeriod, aptNoSlip, aptRegTime, petNo) "
			+ "VALUES (APPOINTMENT_seq.NEXTVAL, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT aptNo, aptDate, aptPeriod, aptNoSlip, aptRegTime, petNo FROM APPOINTMENT order by aptNo";
	private static final String GET_ONE_STMT = "SELECT aptNo, aptDate, aptPeriod, aptNoSlip, aptRegTime, petNo FROM APPOINTMENT  where aptNo = ?";
	private static final String DELETE = "DELETE FROM APPOINTMENT where aptNo = ?";
	private static final String UPDATE = "UPDATE APPOINTMENT set aptDate=?, aptPeriod=?, aptNoSlip=?, aptRegTime=?, petNo=? WHERE aptNo = ?";
	private static final String GET_FUTURE20_ALL = "SELECT aptNo, aptdate, aptperiod, aptnoslip, aptregtime, petno FROM appointment WHERE aptdate >= sysDate - 1 and aptdate <= sysDate + 19 ORDER BY aptdate, aptperiod, aptnoslip";
	private static final String GET_LAST_APTNO = "SELECT aptNo FROM appointment ORDER BY aptno DESC";
	private static  DataSource ds;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			 ds = (DataSource)ctx.lookup("java:comp/env/jdbc/iPetCaresDB");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int insert(AptVO aptVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setDate(1, aptVO.getAptDate());
			pstmt.setString(2, aptVO.getAptPeriod());
			pstmt.setInt(3, aptVO.getAptNoSlip());
			pstmt.setTimestamp(4, aptVO.getAptRegTime());
			pstmt.setString(5, aptVO.getPetNo());

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
	public int update(AptVO aptVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1, aptVO.getAptDate());
			pstmt.setString(2, aptVO.getAptPeriod());
			pstmt.setInt(3, aptVO.getAptNoSlip());
			pstmt.setTimestamp(4, aptVO.getAptRegTime());
			pstmt.setString(5, aptVO.getPetNo());
			
			pstmt.setString(6, aptVO.getAptNo());

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
	public int delete(String aptNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, aptNo);

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
	public AptVO findByPrimaryKey(String aptNo) {
		AptVO aptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, aptNo);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("aptNo"));
				aptVO.setAptDate(rs.getDate("aptDate"));
				aptVO.setAptPeriod(rs.getString("aptPeriod"));
				aptVO.setAptNoSlip(rs.getInt("aptNoSlip"));
				aptVO.setAptRegTime(rs.getTimestamp("aptRegTime"));
				aptVO.setPetNo(rs.getString("petNo"));
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
		return aptVO;
	}

	@Override
	public List<AptVO> getAll() {
		List<AptVO> list = new ArrayList<AptVO>();
		AptVO aptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// aptVO  Domain objects
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("aptNo"));
				aptVO.setAptDate(rs.getDate("aptDate"));
				aptVO.setAptPeriod(rs.getString("aptPeriod"));
				aptVO.setAptNoSlip(rs.getInt("aptNoSlip"));
				aptVO.setAptRegTime(rs.getTimestamp("aptRegTime"));
				aptVO.setPetNo(rs.getString("petNo"));
				list.add(aptVO); // Store the row in the vector
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
	public List<AptVO> getFuture20All() {
		List<AptVO> list = new ArrayList<AptVO>();
		AptVO aptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FUTURE20_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// aptVO  Domain objects
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("aptNo"));
				aptVO.setAptDate(rs.getDate("aptDate"));
				aptVO.setAptPeriod(rs.getString("aptPeriod"));
				aptVO.setAptNoSlip(rs.getInt("aptNoSlip"));
				aptVO.setAptRegTime(rs.getTimestamp("aptRegTime"));
				aptVO.setPetNo(rs.getString("petNo"));
				list.add(aptVO); // Store the row in the vector
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
	public int getLastAptNo() {
		
		AptVO aptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LAST_APTNO);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// aptVO  Domain objects
				
				return Integer.parseInt( rs.getString("aptNo") );
			
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
		return 0;
	}

	@Override
	public List<AptVO> getAll(Map<String, String[]> map) {
		List<AptVO> list = new ArrayList<AptVO>();
		AptVO aptVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from appointment "
		          + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
		          + "order by aptNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("¡´¡´finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("aptNo"));
				aptVO.setAptDate(rs.getDate("aptDate"));
				aptVO.setAptPeriod(rs.getString("aptPeriod"));
				aptVO.setAptNoSlip(rs.getInt("aptNoSlip"));
				aptVO.setAptRegTime(rs.getTimestamp("aptRegTime"));
				aptVO.setPetNo(rs.getString("petNo"));
				list.add(aptVO); // Store the row in the vector
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
