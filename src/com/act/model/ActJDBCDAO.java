package com.act.model;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class ActJDBCDAO implements IActDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user1";
	String passwd = "u111";

	private static final String INSERT_STMT = "INSERT INTO Act (actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee) VALUES (Act_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee FROM Act order by actNo";
	private static final String GET_ONE_STMT = "SELECT actNo,actName,actContent,actStartTime,actEndTime,actPic,actEquipment,actDeposit,actHostFee,actRegFee FROM Act where actNo = ?";
	private static final String DELETE = "DELETE FROM Act where actNo = ?";
	private static final String UPDATE = "UPDATE Act set actName=?,actContent=?,actStartTime=?,actEndTime=?,actPic=?,actEquipment=?,actDeposit=?,actHostFee=?,actRegFee=? where actNo = ?";

	@Override
	public int insert(ActVO act) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public int update(ActVO act) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setDouble(10, act.getActNo());

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
	public int delete(Integer actNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, actNo);

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
	public ActVO findByPrimaryKey(Integer actNo) {
		ActVO act = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(act);
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

	@Override
	public List<ActVO> getAll(Map<String, String[]> map) {

		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		ActJDBCDAO dao = new ActJDBCDAO();

		// 查詢單筆
		ActVO vo1 = dao.findByPrimaryKey(2);

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
//		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
//		int len = fis.available();
//		byte[] buffer = new byte[len];
//        fis.read(buffer);
//        fis.close();
//		
//		ActVO vo2 = new ActVO();
//		vo2.setActName("1111");
//		vo2.setActContent("boy");
//		vo2.setActStartTime(java.sql.Timestamp.valueOf("2005-01-01"));
//		vo2.setActEndTime(java.sql.Timestamp.valueOf("2005-01-20"));
//		vo2.setActPic(buffer);
//		vo2.setActEquipment(null);
//		vo2.setActDeposit(2000);
//		vo2.setActHostFee(1000);
//		vo2.setActRegFee(500);
//		int updateCount_insert = dao.insert(vo2);
//		System.out.println(updateCount_insert);
//
//		System.out.println("---------------------");
//		
//		// 修改
//		ActVO vo3 = new ActVO();
//		vo3.setActNo(4);
//		vo3.setActName("123456");
//		vo3.setActContent("boy");
//		vo3.setActStartTime(java.sql.Timestamp.valueOf("2005-01-01"));
//		vo3.setActEndTime(java.sql.Timestamp.valueOf("2005-01-20"));
//		vo3.setActPic(null);
//		vo3.setActEquipment(null);
//		vo3.setActDeposit(2000);
//		vo3.setActHostFee(1000);
//		vo3.setActRegFee(500);
//		int updateCount_update = dao.update(vo3);
//		System.out.println(updateCount_update);
//
//		System.out.println("---------------------");
//		
//		// 刪除
////		int updateCount_delete = dao.delete(2);
////		System.out.println(updateCount_delete);
//		
////		System.out.println("---------------------");
//		// 查詢多筆
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
	}

	@Override
	public ActVO findActsByDate(Timestamp actStartTime) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<ActVO> getAllActsByDate(String actStartDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActVO> getAllActsByMemNo(Integer memNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(String actStatus) {
		// TODO Auto-generated method stub
		
	}

	

}