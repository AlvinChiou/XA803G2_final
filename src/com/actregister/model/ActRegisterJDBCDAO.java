package com.actregister.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ActRegisterJDBCDAO implements IActRegisterDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user1";
	String passwd = "u111";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ACTREGISTER (actRegNo,actRegName,actRegDate,actRegTime,actRegPayState) VALUES (ACTREGISTER_seq.NEXTVAL, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT actRegNo,actRegName,actRegDate,actRegTime,actRegPayState FROM ACTREGISTER order by actRegNo";
		private static final String GET_ONE_STMT = 
			"SELECT actRegNo,actRegName,actRegDate,actRegTime,actRegPayState FROM ACTREGISTER where actRegNo = ?";
		private static final String DELETE = 
			"DELETE FROM ACTREGISTER where actRegNo = ?";
		private static final String UPDATE = 
			"UPDATE ACTREGISTER set actRegName=?, actRegDate=?, actRegTime=?, actRegPayState=? where actRegNo = ?";
	
	@Override
	public int insert(ActRegisterVO actReg) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, actReg.getActRegName());
			pstmt.setTimestamp(2, actReg.getActRegDate());
			pstmt.setTimestamp(3, actReg.getActRegTime());
			pstmt.setString(4, actReg.getActRegPayState());
			
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
	public int update(ActRegisterVO actReg) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, actReg.getActRegName());
			pstmt.setTimestamp(2, actReg.getActRegDate());
			pstmt.setTimestamp(3, actReg.getActRegTime());
			pstmt.setString(4, actReg.getActRegPayState());
			pstmt.setInt(5, actReg.getActRegNo());

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
	public int delete(Integer actRegNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, actRegNo);
			
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
	public ActRegisterVO findByPrimaryKey(Integer actRegNo) {
		ActRegisterVO actReg = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, actRegNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				actReg = new ActRegisterVO();
				actReg.setActRegNo(rs.getInt("actRegNo"));
				actReg.setActRegName(rs.getString("actRegName"));
				actReg.setActRegDate(rs.getTimestamp("actRegDate"));
				actReg.setActRegTime(rs.getTimestamp("actRegTime"));
				actReg.setActRegPayState(rs.getString("actRegPayState"));
				
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
		return actReg;
	}

	@Override
	public List<ActRegisterVO> getAll() {
		List<ActRegisterVO> list = new ArrayList<ActRegisterVO>();
		ActRegisterVO actReg = null;

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
				actReg = new ActRegisterVO();
				actReg.setActRegNo(rs.getInt("actRegNo"));
				actReg.setActRegName(rs.getString("actRegName"));
				actReg.setActRegDate(rs.getTimestamp("actRegDate"));
				actReg.setActRegTime(rs.getTimestamp("actRegTime"));
				actReg.setActRegPayState(rs.getString("actRegPayState"));
				
				list.add(actReg);
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
		ActRegisterJDBCDAO dao = new ActRegisterJDBCDAO();
		
		//查詢單筆
//		ActRegisterVO vo1 = dao.findByPrimaryKey(1);
//		
//		System.out.print(vo1.getActRegNo() + ",");
//		System.out.print(vo1.getActRegName() + ",");
//		System.out.print(vo1.getActRegDate() + ",");
//		System.out.print(vo1.getActRegTime() + ",");
//		System.out.println(vo1.getActRegPayState() + ",");
//		
//		System.out.println("---------------------");
		
		//新增
		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
		int len = fis.available();
		byte[] buffer = new byte[len];
		
		ActRegisterVO vo2 = new ActRegisterVO();
		vo2.setActRegName("狗狗生日會");
		vo2.setActRegDate(java.sql.Timestamp.valueOf("2005-01-15"));
		vo2.setActRegTime(java.sql.Timestamp.valueOf("2005-01-15"));
		vo2.setActRegPayState("y");
		
		int updateCount_insert = dao.insert(vo2);
		System.out.println(updateCount_insert);
		
		System.out.println("---------------------");
		
		//修改
		
//		ActRegisterVO vo3 = new ActRegisterVO();
//		
//		vo3.setActRegName("狗狗同好會");
//		vo3.setActRegDate(java.sql.Timestamp.valueOf("2005-01-01"));
//		vo3.setActRegTime(java.sql.Timestamp.valueOf("2005-02-01"));
//		vo3.setActRegPayState("y");
//		vo3.setActRegNo(4);
//		
//		int updateCount_update = dao.update(vo3);
//		System.out.println(updateCount_update);
		
//		//刪除
//		
//		int updateCount_delete = dao.delete(2);
//		System.out.println(updateCount_delete);
//		
//		//查詢多筆
//		
		List<ActRegisterVO> list = dao.getAll();
		for(ActRegisterVO vo4 : list){
			
			System.out.print(vo4.getActRegNo() + ",");
			System.out.print(vo4.getActRegName() + ",");
			System.out.print(vo4.getActRegDate() + ",");
			System.out.print(vo4.getActRegTime() + ",");
			System.out.print(vo4.getActRegPayState() + ",");
			System.out.println();
			
		}
	}

	@Override
	public List<ActRegisterVO> getAllActRegistersByMemNo(Integer MemNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActRegisterVO> getAllActRegistersByActNo(Integer actNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByMemNoAndActNo(Integer memNo, Integer actNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}	
