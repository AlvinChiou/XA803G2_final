package com.emp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDAO implements EmployeeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (empNo, empName, empBirth, empTel, empSex, empPos, empSalary, empArrDate, empOff, empID, empAdd, empPic, empPassword) VALUES (employee_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT empNo, empName, empBirth, empTel, empSex, empPos, empSalary, empArrDate, empOff,empID, empAdd, empPic, empPassword FROM EMPLOYEE order by empNo";
	private static final String GET_ONE_STMT = "SELECT empNo, empName, empBirth, empTel, empSex, empPos, empSalary, empArrDate, empOff,empID, empAdd, empPic, empPassword FROM EMPLOYEE where empNo = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE where empNo = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE set empName = ?, empBirth= ?, empTel= ?, empSex= ?, empPos= ?, empSalary= ?, empArrDate= ?, empOff= ?, empID= ?, empAdd= ?, empPic= ?, empPassword= ? where empNo = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
	public void update(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empNo);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// employeeVo 也稱為 Domain objects
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// employeeVO 也稱為 Domain objects
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
				list.add(employeeVO); // Store the row in the list
			}

			// Handle any driver errors
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
}