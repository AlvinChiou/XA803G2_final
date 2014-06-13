package com.shift.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class ShiftDAO extends HttpServlet implements ShiftDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@192.168.11.128:1521:orcl";
//	String userid = "pet";
//	String passwd = "pet";

	
	private static final String INSERT_STMT = "INSERT INTO SHIFT (shiftNo, shiftDate, shiftMaximum, shiftPeriod, drNo) "
			+ "VALUES (shift_seq.NEXTVAL, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT shiftNo, shiftDate, shiftMaximum, shiftPeriod, drNo FROM SHIFT order by shiftDate";
	private static final String GET_ONE_STMT ="SELECT shiftNo, shiftDate, shiftMaximum, shiftPeriod, drNo FROM SHIFT WHERE shiftNo = ?";
	private static final String DELETE = "DELETE FROM SHIFT where shiftNo = ?";
	private static final String UPDATE = "UPDATE SHIFT set shiftDate = ?, shiftMaximum= ?, shiftPeriod= ?, drNo = ? where shiftNo = ?";
//	private static final String GET_SHIFTPERIOD_ONE_DAY = "SELECT shiftperiod FROM SHIFT WHERE shiftdate = ?";
	private static final String GET_FUTURE20_ALL = "SELECT shiftNo, shiftDate, shiftmaximum, shiftperiod, Drno FROM SHIFT WHERE shiftDate >= sysDate - 1 and shiftDate <= sysDate + 19";
	private static final String GET_ONE_ShiftNO = "SELECT shiftno FROM shift where shiftDate = ? and shiftPeriod = ? "; 

	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			 ds = (DataSource)ctx.lookup("java:comp/env/jdbc/iPetCaresDB");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public int insert(ShiftVO shiftVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, shiftVO.getShiftDate());
			pstmt.setInt(2, shiftVO.getShiftMaximum());
			pstmt.setString(3, shiftVO.getShiftPeriod());
			pstmt.setString(4, shiftVO.getDrNo());
	
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
	public int update(ShiftVO shiftVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, shiftVO.getShiftDate());
			pstmt.setInt(2, shiftVO.getShiftMaximum());
			pstmt.setString(3, shiftVO.getShiftPeriod());
			pstmt.setString(4, shiftVO.getDrNo());
			pstmt.setString(5, shiftVO.getShiftNo());
			
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
	public int delete(String shiftNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, shiftNo);

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
	public ShiftVO findByPrimaryKey(String shiftNo) {
		ShiftVO shiftVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shiftNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shiftVO = new ShiftVO();
				shiftVO.setShiftNo(rs.getString("shiftNo"));
				shiftVO.setShiftDate( (rs.getDate("shiftDate") ) );
				shiftVO.setShiftMaximum(rs.getInt("shiftMaximum"));
				shiftVO.setShiftPeriod(rs.getString("shiftPeriod"));
				shiftVO.setDrNo(rs.getString("drNo"));
			
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
		return shiftVO;
	}

	@Override
	public List<ShiftVO> getAll() {
		List<ShiftVO> list = new ArrayList<ShiftVO>();
		ShiftVO shiftVO = null;

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
				// empVO 也稱為 Domain objects
				shiftVO = new ShiftVO();
				shiftVO.setShiftNo(rs.getString("shiftNo"));
				shiftVO.setShiftDate(rs.getDate("shiftDate"));
				shiftVO.setShiftMaximum(rs.getInt("shiftMaximum"));
				shiftVO.setShiftPeriod(rs.getString("shiftPeriod"));
				shiftVO.setDrNo(rs.getString("drNo"));
				list.add(shiftVO);
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
	public String[] findShiftPeriodByDate(Date shiftDate) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String[] shiftPeriod = new String[5000];
		try {

		/*	Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);*/
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT shiftPeriod FROM SHIFT WHERE shiftdate = ?");
			pstmt.setDate(1, shiftDate);
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				shiftPeriod[i++] = rs.getString("shiftPeriod");
			}

			// Handle any driver errors
		} 
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
		return shiftPeriod;
	}

	@Override
	public List<ShiftVO> getFuture20All() {
		List<ShiftVO> list = new ArrayList<ShiftVO>();
		ShiftVO shiftVO = null;

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
				// empVO 也稱為 Domain objects
				shiftVO = new ShiftVO();
				shiftVO.setShiftNo(rs.getString("shiftNo"));
				shiftVO.setShiftDate(rs.getDate("shiftDate"));
				shiftVO.setShiftMaximum(rs.getInt("shiftMaximum"));
				shiftVO.setShiftPeriod(rs.getString("shiftPeriod"));
				shiftVO.setDrNo(rs.getString("drNo"));
				list.add(shiftVO);
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
	public String getShiftNo(Date shiftDate, String shiftPeriod) {
		String shiftNo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ShiftNO);
			pstmt.setDate(1, shiftDate);
			pstmt.setString(2, shiftPeriod);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				shiftNo = rs.getString("shiftNo");
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
		
		return shiftNo;
	}

	

}
