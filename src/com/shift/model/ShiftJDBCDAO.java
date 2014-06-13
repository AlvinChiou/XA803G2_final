package com.shift.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import sun.util.calendar.Gregorian;

public class ShiftJDBCDAO  implements ShiftDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.11.128:1521:orcl";
	String userid = "pet";
	String passwd = "pet";

	private static final String INSERT_STMT = "INSERT INTO SHIFT (shift_No, shift_Date, shift_Maximum, shift_Period, dr_No) "
			+ "VALUES (shift_seq.NEXTVAL, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT shiftNo, shiftDate, shiftMaximum, shiftPeriod, drNo FROM SHIFT  order by shiftDate";
	private static final String GET_ONE_STMT ="SELECT shift_No, shift_Date, shift_Maximum, shift_Period, dr_No FROM SHIFT WHERE shift_No = ?";
	private static final String DELETE = "DELETE FROM SHIFT where shift_No = ?";
	private static final String UPDATE = "UPDATE SHIFT set shift_Date = ?, shift_Maximum= ?, shift_Period= ?, dr_No = ? where shift_No = ?";
	//private static final String GET_SHIFTPERIOD_ONE_DAY = "SELECT shiftPeriod FROM SHIFT WHERE shiftdate = ?";
	private static final String GET_FUTURE20_ALL = "SELECT shiftNo, shiftDate, shiftmaximum, shiftperiod, Drno FROM SHIFT WHERE shiftDate >= sysDate - 1 and shiftDate <= sysDate + 19";

	@Override
	public int insert(ShiftVO shiftVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, shiftVO.getShiftDate());
			pstmt.setInt(2, shiftVO.getShiftMaximum());
			pstmt.setString(3, shiftVO.getShiftPeriod());
			pstmt.setString(4, shiftVO.getDrNo());
	
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, shiftVO.getShiftDate());
			pstmt.setInt(2, shiftVO.getShiftMaximum());
			pstmt.setString(3, shiftVO.getShiftPeriod());
			pstmt.setString(4, shiftVO.getDrNo());
			pstmt.setString(5, shiftVO.getShiftNo());
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} 
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, shiftNo);

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} 
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shiftNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shiftVO = new ShiftVO();
				shiftVO.setShiftNo(rs.getString("shift_No"));
				shiftVO.setShiftDate( (rs.getDate("shift_Date") ) );
				shiftVO.setShiftMaximum(rs.getInt("shift_Maximum"));
				shiftVO.setShiftPeriod(rs.getString("shift_Period"));
				shiftVO.setDrNo(rs.getString("dr_No"));
			
			}

			// Handle any driver errors
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return list;
	}

	@Override
	public String[] findShiftPeriodByDate(Date shiftDate) {
		ShiftVO shiftVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String[] shiftPeriod = new String[2];
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement("SELECT shiftPeriod FROM SHIFT WHERE shiftdate = ?");
			pstmt.setDate(1, shiftDate);
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				shiftPeriod[i++] = rs.getString("shiftPeriod");
			}

			// Handle any driver errors
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		ShiftJDBCDAO dao = new ShiftJDBCDAO();
		String[] s = dao.findShiftPeriodByDate( new Date( new GregorianCalendar(2014, 4, 7).getTimeInMillis() ) );

		//String[] s = dao.findShiftPeriodByDate( new Date( new java.util.Date().getTime() ) );
		for ( int i = 0; i < s.length; i++ ) {
			System.out.println( s[i] );
		}
		System.out.println( new Date( new GregorianCalendar(2014, 4, 7).getTimeInMillis() ));
		System.out.println( new Date( new java.util.Date().getTime() ) );
	}

	@Override
	public List<ShiftVO> getFuture20All() {
		List<ShiftVO> list = new ArrayList<ShiftVO>();
		ShiftVO shiftVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//con = ds.getConnection();
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
		catch (SQLException | ClassNotFoundException se) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
