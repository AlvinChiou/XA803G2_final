package com.drRecord.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DrRecordJDBCDAO implements IdrRecordDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user1";
	String passwd = "u111";
	
	private static final String INSERT_STMT = 
			"INSERT INTO DRRECORD (drRecNo,to_Date(drRecTime,'yyyy-mm-dd') drRecTime,drRecPres) VALUES (ACTREGISTER_seq.NEXTVAL, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT drRecNo,to_Date(drRecTime,'yyyy-mm-dd') drRecTime,drRecPres FROM DRRECORD order by drRecNo";
		private static final String GET_ONE_STMT = 
			"SELECT drRecNo,to_Date(drRecTime,'yyyy-mm-dd') drRecTime,drRecPres FROM DRRECORD where drRecNo = ?";
		private static final String DELETE = 
			"DELETE FROM DRRECORD where drRecNo = ?";
		private static final String UPDATE = 
			"UPDATE DRRECORD set drRecTime=?, drRecPres=? where drRecNo = ?";
	
	@Override
	public int insert(DrRecordVO drRec) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setDate(1, drRec.getDrRecTime());
			pstmt.setString(2, drRec.getDrRecPres());
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public int update(DrRecordVO drRec) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, drRec.getDrRecTime());
			pstmt.setString(2, drRec.getDrRecPres());
			pstmt.setInt(3, drRec.getDrRecNo());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public int delete(Integer drRecNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, drRecNo);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public DrRecordVO findByPrimaryKey(Integer drRecNo) {
		DrRecordVO drRec = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, drRecNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				drRec = new DrRecordVO();
				drRec.setDrRecNo(rs.getInt("drRecNo"));
				drRec.setDrRecTime(rs.getDate("drRecTime"));
				drRec.setDrRecPres(rs.getString("drRecPres"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
		return drRec;
	}

	@Override
	public List<DrRecordVO> getAll() {
		List<DrRecordVO> list = new ArrayList<DrRecordVO>();
		DrRecordVO drRec = null;

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
				drRec = new DrRecordVO();
				drRec.setDrRecNo(rs.getInt("drRecNo"));
				drRec.setDrRecTime(rs.getDate("drRecTime"));
				drRec.setDrRecPres(rs.getString("drRecPres"));
				
				list.add(drRec);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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



	public static void main(String[] args) throws Exception{
		DrRecordJDBCDAO dao = new DrRecordJDBCDAO();
		
		//查詢單筆
		DrRecordVO vo1 = dao.findByPrimaryKey(1);
		
		System.out.print(vo1.getDrRecNo() + ",");
		System.out.print(vo1.getDrRecTime() + ",");
		System.out.print(vo1.getDrRecPres() + ",");
		
		
		System.out.println("---------------------");
		
		//新增
//		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
		
		DrRecordVO vo2 = new DrRecordVO();
		
		vo2.setDrRecTime(java.sql.Date.valueOf("2014-5-1"));
		vo2.setDrRecPres("打預防針");
		
		int updateCount_insert = dao.insert(vo2);
		System.out.println(updateCount_insert);
		
		System.out.println("---------------------");
		
		//修改
		
		DrRecordVO vo3 = new DrRecordVO();
		
		
		vo3.setDrRecTime(java.sql.Date.valueOf("2005-04-30"));
		vo3.setDrRecPres("打預防針");
		vo3.setDrRecNo(2);
		
		int updateCount_update = dao.update(vo3);
		System.out.println(updateCount_update);
		
		//刪除
		
		int updateCount_delete = dao.delete(3);
		System.out.println(updateCount_delete);
		
		//查詢多筆
		
		List<DrRecordVO> list = dao.getAll();
		for(DrRecordVO vo4 : list){
			
			System.out.print(vo4.getDrRecNo() + ",");
			System.out.print(vo4.getDrRecTime() + ",");
			System.out.print(vo4.getDrRecPres() + ",");
			System.out.println();
			
		}
	}
	
}	
