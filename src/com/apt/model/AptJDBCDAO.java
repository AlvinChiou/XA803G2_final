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

public class AptJDBCDAO implements AptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.11.128:1521:orcl";
	String userid = "pet";
	String passwd = "pet";

	private static final String INSERT_STMT = "INSERT INTO APPOINTMENT (apt_No, apt_date, apt_Period, apt_No_Slip, apt_Reg_Time, petNo) "
			+ "VALUES (APPOINTMENT_seq.NEXTVAL, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT apt_No, apt_date, apt_Period, apt_No_Slip, apt_Reg_Time, petNo FROM APPOINTMENT order by apt_No";
	private static final String GET_ONE_STMT = "SELECT apt_No, apt_date, apt_Period, apt_No_Slip, apt_Reg_Time, petNo FROM APPOINTMENT  where apt_No = ?";
	private static final String DELETE = "DELETE FROM APPOINTMENT where apt_No = ?";
	private static final String UPDATE = "UPDATE APPOINTMENT set apt_date=?, apt_Period=?, apt_No_Slip=?, apt_Reg_Time=?, petNo=? WHERE apt_No = ?";
	private static final String GET_FUTURE20_ALL = "SELECT aptNo, aptdate, aptperiod, aptnoslip, aptregtime, petno FROM appointment WHERE aptdate >= sysDate - 1 and aptdate <= sysDate + 19";

	private static  DataSource ds;
	
	@Override
	public int insert(AptVO aptVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
	public int update(AptVO aptVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
	public int delete(String apt_No) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, apt_No);

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
	public AptVO findByPrimaryKey(String apt_No) {
		AptVO aptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, apt_No);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("apt_No"));
				aptVO.setAptDate(rs.getDate("apt_date"));
				aptVO.setAptPeriod(rs.getString("apt_Period"));
				aptVO.setAptNoSlip(rs.getInt("apt_No_Slip"));
				aptVO.setAptRegTime(rs.getTimestamp("apt_Reg_Time"));
				aptVO.setPetNo(rs.getString("petNo"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// aptVO  Domain objects
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("apt_No"));
				aptVO.setAptDate(rs.getDate("apt_date"));
				aptVO.setAptPeriod(rs.getString("apt_Period"));
				aptVO.setAptNoSlip(rs.getInt("apt_No_Slip"));
				aptVO.setAptRegTime(rs.getTimestamp("apt_Reg_Time"));
				aptVO.setPetNo(rs.getString("petNo"));
				list.add(aptVO); // Store the row in the vector
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
	public List<AptVO> getFuture20All() {
		List<AptVO> list = new ArrayList<AptVO>();
		AptVO aptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
//			con = ds.getConnection();
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
	public static void main(String[] args) {
		AptJDBCDAO dao =  new AptJDBCDAO();
		List<AptVO> list = dao.getFuture20All();
		System.out.println(list);
	}

	@Override
	public int getLastAptNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AptVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
