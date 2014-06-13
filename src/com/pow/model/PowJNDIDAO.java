package com.pow.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.func.model.FuncJDBCDAO;
import com.lost.model.LostVO;

public class PowJNDIDAO extends HttpServlet implements PowDAO_interface {

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
			"DELETE FROM POWER where empno=? and funcno=?";
	private static final String listPow_Byfuncname = 
			"SELECT empno, funcno FROM POWER where funcno = ?";
	
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=Big5");
		req.setCharacterEncoding("Big5");
		
		PowJNDIDAO dao = new PowJNDIDAO();
		
		PrintWriter out = resp.getWriter();
		List<PowVO> list = dao.getAll();
		for(PowVO apow : list){
			out.println(apow.getEmpno() + ", ");
			out.println(apow.getFuncno() + "<BR>");
		}
	}

	@Override
	public PowVO getOnePowByPKs(Integer empno, Integer funcno) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args){
//		PowJDBCDAO dao = new PowJDBCDAO();
//		
//		//新增
//		PowVO powVO1 = new PowVO();
//		powVO1.setEmpno(1072);
//		powVO1.setFuncno(4006);
//		int updateCount_insert = dao.insert(powVO1);
//		System.out.println(updateCount_insert);
//		
//		//修改
//		PowVO powVO1 = new PowVO();
//		powVO1.setEmpno(1072);
//		powVO1.setFuncno(4006);
//		int updateCount_update = dao.update(powVO1);
//		System.out.println(updateCount_update);
//		
//		//刪除
//		PowVO powVO2 = new PowVO();
//		powVO2.setEmpno(1072);
//		powVO2.setFuncno(4006);
//		int updateCount_delete = dao.delete(powVO2);
//		System.out.println(updateCount_delete);
//		
//		//查詢
//		PowVO powVO = dao.findByPrimaryKey(1070);
//		System.out.print(powVO.getEmpno() + ", ");
//		System.out.println(powVO.getFuncno());
//		System.out.println("----------------------------------");
//		//查詢
//		List<PowVO> list = dao.getAll();
//		for(PowVO apow : list){
//			System.out.print(apow.getEmpno() + ", ");
//			System.out.println(apow.getFuncno() + ", ");
//		}
//	}
}
