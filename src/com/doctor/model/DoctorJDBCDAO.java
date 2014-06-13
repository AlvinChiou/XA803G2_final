package com.doctor.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorJDBCDAO implements DoctorDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user2";
	String passwd = "u222";

	private static final String INSERT_STMT = "INSERT INTO DOCTOR (drNo, drName, drExp, drSex, drPic, drBirth, drAdd, drTel) VALUES (doctor_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT drNo, drName, drExp, drSex, drPic, drBirth, drAdd, drTel FROM DOCTOR order by drNo";
	private static final String GET_ONE_STMT = "SELECT drNo, drName, drExp, drSex, drPic, drBirth, drAdd, drTel FROM DOCTOR where drNo = ?";
	private static final String DELETE = "DELETE FROM DOCTOR where drNo = ?";
	private static final String UPDATE = "UPDATE DOCTOR set drName = ?, drExp=?, drSex= ?, drPic= ?, drBirth= ?, drAdd= ?, drTel= ? where drNo = ?";

	@Override
	public void insert(DoctorVO doctorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, doctorVO.getDrName());
			pstmt.setString(2, doctorVO.getDrExp());
			pstmt.setString(3, doctorVO.getDrSex());
			pstmt.setBytes(4, doctorVO.getDrPic());
			pstmt.setDate(5, doctorVO.getDrBirth());
			pstmt.setString(6, doctorVO.getDrAdd());
			pstmt.setString(7, doctorVO.getDrTel());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	}

	@Override
	public void update(DoctorVO doctorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, doctorVO.getDrName());
			pstmt.setString(2, doctorVO.getDrExp());
			pstmt.setString(3, doctorVO.getDrSex());
			pstmt.setBytes(4, doctorVO.getDrPic());
			pstmt.setDate(5, doctorVO.getDrBirth());
			pstmt.setString(6, doctorVO.getDrAdd());
			pstmt.setString(7, doctorVO.getDrTel());
			pstmt.setInt(8, doctorVO.getDrNo());

			pstmt.executeUpdate();

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
	}

	@Override
	public void delete(Integer drNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, drNo);

			pstmt.executeUpdate();

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
	}

	@Override
	public DoctorVO findByPrimaryKey(Integer drNo) {
		DoctorVO doctorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, drNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// drVo 也稱為 Domain objects
				doctorVO = new DoctorVO();
				doctorVO.setDrNo(rs.getInt("drNo"));
				doctorVO.setDrName(rs.getString("drName"));
				doctorVO.setDrExp(rs.getString("drExp"));
				doctorVO.setDrSex(rs.getString("drSex"));
				doctorVO.setDrPic(rs.getBytes("drPic"));
				doctorVO.setDrBirth(rs.getDate("drBirth"));
				doctorVO.setDrAdd(rs.getString("drAdd"));
				doctorVO.setDrTel(rs.getString("drTel"));
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
		return doctorVO;
	}

	@Override
	public List<DoctorVO> getAll() {
		List<DoctorVO> list = new ArrayList<DoctorVO>();
		DoctorVO doctorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// doctorVO 也稱為 Domain objects
				doctorVO = new DoctorVO();
				doctorVO.setDrNo(rs.getInt("drNo"));
				doctorVO.setDrName(rs.getString("drName"));
				doctorVO.setDrExp(rs.getString("drExp"));
				doctorVO.setDrSex(rs.getString("drSex"));
				doctorVO.setDrPic(rs.getBytes("drPic"));
				doctorVO.setDrBirth(rs.getDate("drBirth"));
				doctorVO.setDrAdd(rs.getString("drAdd"));
				doctorVO.setDrTel(rs.getString("drTel"));

				list.add(doctorVO); // Store the row in the vector
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

	public static void main(String[] args) throws Exception {

		DoctorJDBCDAO dao = new DoctorJDBCDAO();

		// 新增
		// DoctorVO drVO1 = new DoctorVO();
		// drVO1.setDrName("HAHA12");
		// drVO1.setDrExp("前台灣大學獸醫學系學士");
		// drVO1.setDrSex("M");
		//
		// FileInputStream fis = new
		// FileInputStream("C:/apache-tomcat-7.0.50/webapps/ROOT/tomcat.gif");
		// int len = fis.available();
		// byte[] buffer = new byte[len];
		// fis.read(buffer);
		// drVO1.setDrPic(buffer);
		// fis.close();
		//
		// drVO1.setDrBirth(java.sql.Date.valueOf("1981-11-18"));
		// drVO1.setDrAdd("桃園縣平鎮市永康路590號");
		// drVO1.setDrTel("0915483632");
		// int updateCount_insert = dao.insert(drVO1);
		// System.out.println(updateCount_insert);

		// 修改
		// DoctorVO drVO2 = new DoctorVO();
		// drVO2.setDrName("NANA2");
		// drVO2.setDrExp("前台灣大學附設動物醫院外科住院醫師");
		// drVO2.setDrSex("M");
		//
		// FileInputStream fis = new
		// FileInputStream("C:/apache-tomcat-7.0.50/webapps/ROOT/tomcat-power.gif");
		// int len = fis.available();
		// byte[] buffer = new byte[len];
		// fis.read(buffer);
		// drVO2.setDrPic(buffer);
		// fis.close();
		//
		// drVO2.setDrBirth(java.sql.Date.valueOf("1987-10-18"));
		// drVO2.setDrAdd("桃園縣中壢市中正路160號");
		// drVO2.setDrTel("09114581542");
		// drVO2.setDrNo(11);
		// int updateCount_update = dao.update(drVO2);
		// System.out.println(updateCount_update);

		// 刪除
		// int updateCount_delete = dao.delete("5");
		// System.out.println(updateCount_delete);

		// 查詢
		DoctorVO drVO3 = dao.findByPrimaryKey(7);
		System.out.print(drVO3.getDrNo() + ",");
		System.out.print(drVO3.getDrName() + ",");
		System.out.print(drVO3.getDrExp() + ",");
		System.out.print(drVO3.getDrSex() + ",");
		System.out.println(drVO3.getDrPic());
		System.out.print(drVO3.getDrBirth() + ",");
		System.out.print(drVO3.getDrAdd() + ",");
		System.out.print(drVO3.getDrTel() + ",");
		System.out.println("---------------------");

		// 查詢
		// List<DoctorVO> list = dao.getAll();
		// for (DoctorVO aDoctor : list) {
		// System.out.print(aDoctor.getDrNo() + ",");
		// System.out.print(aDoctor.getDrName() + ",");
		// System.out.print(aDoctor.getDrExp() + ",");
		// System.out.print(aDoctor.getDrSex() + ",");
		// System.out.println(aDoctor.getDrPic());
		// System.out.print(aDoctor.getDrBirth() + ",");
		// System.out.print(aDoctor.getDrAdd() + ",");
		// System.out.print(aDoctor.getDrTel() + ",");
		// System.out.println();
		// }
	}
}
