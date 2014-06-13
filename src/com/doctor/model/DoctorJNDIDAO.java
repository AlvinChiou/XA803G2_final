package com.doctor.model;

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

public class DoctorJNDIDAO implements DoctorDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, drNo);

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
	public DoctorVO findByPrimaryKey(Integer drNo) {
		DoctorVO doctorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}
