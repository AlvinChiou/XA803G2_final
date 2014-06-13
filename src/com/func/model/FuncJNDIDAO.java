package com.func.model;

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

import com.news.model.NewsJDBCDAO;
import com.news.model.NewsVO;
import com.pow.model.PowVO;

public class FuncJNDIDAO extends HttpServlet implements FuncDAO_interface {

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
			"INSERT INTO FUNCTION_item VALUES(func_seq.NEXTVAL,?)";
	private static final String GET_ALL_STMT = 
			"SELECT funcno, funcname FROM FUNCTION_item order by funcno";
	private static final String GET_ONE_STMT = 
			"SELECT funcno, funcname FROM FUNCTION_item where funcno = ?";
	private static final String UPDATE = 
			"UPDATE FUNCTION_item set funcname=? where funcno=?";
	private static final String DELETE = 
			"DELETE FROM FUNCTION_item where funcno=?";
	
	@Override
	public int insert(FuncVO funcVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, funcVO.getFuncname());
			
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
	public int update(FuncVO funcVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, funcVO.getFuncname());
			pstmt.setInt(2, funcVO.getFuncno());
			
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
	public int delete(Integer funcno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, funcno);
			
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
	public FuncVO findByPrimaryKey(Integer funcno) {
		FuncVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, funcno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getInt("funcno"));
				funcVO.setFuncname(rs.getString("funcname"));
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
		return funcVO;
	}

	@Override
	public List<FuncVO> getAll() {
		List<FuncVO> list = new ArrayList<FuncVO>();
		FuncVO funcVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				funcVO = new FuncVO();
				funcVO.setFuncno(rs.getInt("funcno"));
				funcVO.setFuncname(rs.getString("funcname"));
				list.add(funcVO);//Store the row in the vector;
				
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
		
		FuncJNDIDAO dao = new FuncJNDIDAO();
		
		PrintWriter out = resp.getWriter();
		List<FuncVO> list = dao.getAll();
		for(FuncVO afunc : list){
			out.println(afunc.getFuncno() + ", ");
			out.println(afunc.getFuncname() + "<BR>");
		}
	}

//	public static void main(String[] args){
//		FuncJDBCDAO dao = new FuncJDBCDAO();
//		
//		//新增
//		FuncVO funcVO1 = new FuncVO();
//		funcVO1.setFuncname("寵物管理1");
//		int updateCount_insert = dao.insert(funcVO1);
//		System.out.println(updateCount_insert);
//		
//		//修改
//		FuncVO funcVO2 = new FuncVO();
//		funcVO2.setFuncno(4007);
//		funcVO2.setFuncname("倉庫管理");
//		int updateCount_update = dao.update(funcVO2);
//		System.out.println(updateCount_update);
//		
//		//刪除
//		int updateCount_delete = dao.delete(4008);
//		System.out.println(updateCount_delete);
//		
//		//查詢
//		FuncVO funcVO = dao.findByPrimaryKey(4005);
//		System.out.print(funcVO.getFuncno() + ", ");
//		System.out.println(funcVO.getFuncname());
//		System.out.println("----------------------------------");
//		//查詢
//		List<FuncVO> list = dao.getAll();
//		for(FuncVO afunc : list){
//			System.out.print(afunc.getFuncno() + ", ");
//			System.out.println(afunc.getFuncname() + ", ");
//		}
//	}
}
