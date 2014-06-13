package com.emp.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.*;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user2";
	String passwd = "u222";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (empNo, empName, empBirth, empTel, empSex, empPos, empSalary, empArrDate, empOff, empID, empAdd, empPic, empPassword) VALUES (employee_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT empNo, empName, empBirth, empTel, empSex, empPos, empSalary, empArrDate, empOff, empID, empAdd, empPic, empPassword FROM EMPLOYEE order by empNo";
	private static final String GET_ONE_STMT = "SELECT empNo, empName, empBirth, empTel, empSex, empPos, empSalary, empArrDate, empOff,empID, empAdd, empPic, empPassword FROM EMPLOYEE where empNo = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE where empNo = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE set empName = ?, empBirth= ?, empTel= ?, empSex= ?, empPos= ?, empSalary= ?, empArrDate= ?, empOff= ?, empID= ?, empAdd= ?, empPic= ?, empPassword= ? where empNo = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setDate(2, employeeVO.getEmpBirth());
			pstmt.setString(3, employeeVO.getEmpTel());
			pstmt.setString(4, employeeVO.getEmpSex());
			pstmt.setString(5, employeeVO.getEmpPos());
			pstmt.setDouble(6, employeeVO.getEmpSalary());
			pstmt.setDate(7, employeeVO.getEmpArrDate());
			pstmt.setDate(8, employeeVO.getEmpOff());
			pstmt.setString(9, employeeVO.getEmpID());
			pstmt.setString(10, employeeVO.getEmpAdd());
			pstmt.setBytes(11, employeeVO.getEmpPic());
			pstmt.setString(12, employeeVO.getEmpPassword());
			
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
	public void update(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setDate(2, employeeVO.getEmpBirth());
			pstmt.setString(3, employeeVO.getEmpTel());
			pstmt.setString(4, employeeVO.getEmpSex());
			pstmt.setString(5, employeeVO.getEmpPos());
			pstmt.setDouble(6, employeeVO.getEmpSalary());
			pstmt.setDate(7, employeeVO.getEmpArrDate());
			pstmt.setDate(8, employeeVO.getEmpOff());
			pstmt.setString(9, employeeVO.getEmpID());
			pstmt.setString(10, employeeVO.getEmpAdd());
			pstmt.setBytes(11, employeeVO.getEmpPic());
			pstmt.setString(12, employeeVO.getEmpPassword());
			pstmt.setInt(13, employeeVO.getEmpNo());

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
	public void delete(Integer empNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empNo);

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
	public EmployeeVO findByPrimaryKey(Integer empNo) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmpNo(rs.getInt("empNo"));
				employeeVO.setEmpName(rs.getString("empName"));
				employeeVO.setEmpBirth(rs.getDate("empBirth"));
				employeeVO.setEmpTel(rs.getString("empTel"));
				employeeVO.setEmpSex(rs.getString("empSex"));
				employeeVO.setEmpPos(rs.getString("empPos"));
				employeeVO.setEmpSalary(rs.getDouble("empSalary"));
				employeeVO.setEmpArrDate(rs.getDate("empArrDate"));
				employeeVO.setEmpOff(rs.getDate("empOff"));
				employeeVO.setEmpID(rs.getString("empID"));
				employeeVO.setEmpAdd(rs.getString("empAdd"));
				employeeVO.setEmpPic(rs.getBytes("empPic"));
				employeeVO.setEmpPassword(rs.getString("empPassword"));
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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

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
				employeeVO = new EmployeeVO();
				employeeVO.setEmpNo(rs.getInt("empNo"));
				employeeVO.setEmpName(rs.getString("empName"));
				employeeVO.setEmpBirth(rs.getDate("empBirth"));
				employeeVO.setEmpTel(rs.getString("empTel"));
				employeeVO.setEmpSex(rs.getString("empSex"));
				employeeVO.setEmpPos(rs.getString("empPos"));
				employeeVO.setEmpSalary(rs.getDouble("empSalary"));
				employeeVO.setEmpArrDate(rs.getDate("empArrDate"));
				employeeVO.setEmpOff(rs.getDate("empOff"));
				employeeVO.setEmpID(rs.getString("empID"));
				employeeVO.setEmpAdd(rs.getString("empAdd"));
				employeeVO.setEmpPic(rs.getBytes("empPic"));
				employeeVO.setEmpPassword(rs.getString("empPassword"));

				list.add(employeeVO); // Store the row in the vector
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

	public static void main(String[] args)  throws Exception{

		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

		// 新增
		  EmployeeVO empVO1 = new EmployeeVO();
		  empVO1.setEmpName("TAKA19");
		  empVO1.setEmpBirth(java.sql.Date.valueOf("1990-06-04"));
		  empVO1.setEmpTel("0912345678");
		  empVO1.setEmpSex("F");
		  empVO1.setEmpPos("職員");
		  empVO1.setEmpSalary(15000);
		  empVO1.setEmpArrDate(java.sql.Date.valueOf("2005-10-01"));
		  empVO1.setEmpOff(java.sql.Date.valueOf("2008-03-30"));
		  empVO1.setEmpID("C135845261");
		  empVO1.setEmpAdd("台北市中山區松江路156之12號");
		
		  FileInputStream fis = new FileInputStream("C:/apache-tomcat-7.0.50/webapps/ROOT/tomcat.gif");
		  int len = fis.available();
		  byte[] buffer = new byte[len];
		  fis.read(buffer);
		  empVO1.setEmpPic(buffer);
		  fis.close();
		
		  dao.insert(empVO1);

		// 修改
		// EmployeeVO empVO2 = new EmployeeVO();
		// empVO2.setEmpName("FORD2");
		// empVO2.setEmpBirth(java.sql.Date.valueOf("1990-06-04"));
		// empVO2.setEmpTel("0945723142");
		// empVO2.setEmpSex("M");
		// empVO2.setEmpPos("30");
		// empVO2.setEmpSalary(7000);
		// empVO2.setEmpArrDate(java.sql.Date.valueOf("2012-10-30"));
		// empVO2.setEmpOff(java.sql.Date.valueOf("2013-11-12"));
		// empVO2.setEmpID("J209611662");
		// empVO2.setEmpAdd("台北市中山區中正路160號");
		// empVO2.setEmpPic(null);
		// empVO2.setEmpNo(11);
		// dao.update(empVO2);

		// 刪除
		// dao.delete("11");
		//

		// 查詢
		// EmployeeVO empVO3 = dao.findByPrimaryKey(2);
		// System.out.print(empVO3.getEmpNo() + ",");
		// System.out.print(empVO3.getEmpName() + ",");
		// System.out.print(empVO3.getEmpBirth() + ",");
		// System.out.print(empVO3.getEmpTel() + ",");
		// System.out.print(empVO3.getEmpSex() + ",");
		// System.out.print(empVO3.getEmpPos() + ",");
		// System.out.print(empVO3.getEmpSalary() + ",");
		// System.out.print(empVO3.getEmpArrDate() + ",");
		// System.out.print(empVO3.getEmpOff() + ",");
		// System.out.print(empVO3.getEmpID() + ",");
		// System.out.print(empVO3.getEmpAdd() + ",");
		// System.out.println(empVO3.getEmpPic());
		// System.out.println("---------------------");

		// 查詢
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO aEmployee : list) {
//			System.out.print(aEmployee.getEmpNo() + ",");
//			System.out.print(aEmployee.getEmpName() + ",");
//			System.out.print(aEmployee.getEmpBirth() + ",");
//			System.out.print(aEmployee.getEmpTel() + ",");
//			System.out.print(aEmployee.getEmpSex() + ",");
//			System.out.print(aEmployee.getEmpPos() + ",");
//			System.out.print(aEmployee.getEmpSalary() + ",");
//			System.out.print(aEmployee.getEmpArrDate() + ",");
//			System.out.print(aEmployee.getEmpOff() + ",");
//			System.out.print(aEmployee.getEmpID() + ",");
//			System.out.print(aEmployee.getEmpAdd() + ",");
//			System.out.println(aEmployee.getEmpPic());
//			System.out.println();
//		}
	}
}
