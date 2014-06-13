package com.pow.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.func.model.FuncJDBCDAO;
import com.func.model.FuncVO;
import com.lost.model.LostVO;

public class PowDAO implements PowDAO_interface {

	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e){
			e.printStackTrace();
			}
		}
	
	private static final String INSERT_STMT = 
			"INSERT INTO POWER(empno, funcno) VALUES(?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT empno, funcno FROM POWER order by empno";
	private static final String GET_ONE_STMT = 
			"SELECT empno, funcno FROM POWER where empno=?";
	private static final String UPDATE = 
			"UPDATE POWER set funcno=? where empno=? and funcno=?";
	private static final String DELETE = 
			"DELETE FROM POWER where empno=?";
	private static final String listPow_Byfuncname = 
			"SELECT empno, funcno FROM POWER where funcno = ?";
	private static final String GET_ONE_STMT_BY_PKS = 
			"SELECT empno, funcno from power where empno = ? and funcno = ?";
	
	
	@Override
	public int insert(PowVO powVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, powVO.getEmpno());
			pstmt.setInt(2, powVO.getFuncno());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any SQL errors
		}catch (SQLException se){
			throw new RuntimeException("A datebase error occured." + se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int update(PowVO powVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, powVO.getFuncno());
			pstmt.setInt(2, powVO.getEmpno());
			pstmt.setInt(3, powVO.getFuncno());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any SQL errors
		}catch (SQLException se){
			throw new RuntimeException("A datebase error occured." + se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public int delete(Integer empno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, empno);
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any SQL errors
		}catch (SQLException se){
			throw new RuntimeException("A datebase error occured." + se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}

	@Override
	public List<PowVO> findByPrimaryKey(Integer empno) {
		List<PowVO> list = new ArrayList<PowVO>();
		PowVO powVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				powVO = new PowVO();
				powVO.setEmpno(rs.getInt("empno"));
				powVO.setFuncno(rs.getInt("funcno"));
//				list.add(rs.getInt("empno"));
				list.add(powVO);
			}
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<PowVO> getAll() {
		List<PowVO> list = new ArrayList<PowVO>();
		PowVO powVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				powVO = new PowVO();
				powVO.setEmpno(rs.getInt("empno"));
				powVO.setFuncno(rs.getInt("funcno"));
				list.add(powVO);//Store the row in the vector;
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PowVO> listPow_Byfuncname(Integer funcno) {
		List<PowVO> list = new ArrayList<PowVO>();
		PowVO powVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(listPow_Byfuncname);
			
			pstmt.setInt(1, funcno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				powVO = new PowVO();
				powVO.setEmpno(rs.getInt("empno"));
				powVO.setFuncno(rs.getInt("funcno"));
				list.add(powVO);
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public PowVO getOnePowByPKs(Integer empno, Integer funcno) {
		
		PowVO powVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_PKS);
			
			pstmt.setInt(1, empno);
			pstmt.setInt(2, funcno);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				powVO = new PowVO();
				powVO.setEmpno(rs.getInt("empno"));
				powVO.setFuncno(rs.getInt("funcno"));
				
			}
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
			//Clean up JDBC 
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch (Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return powVO;
	}
	
}
