package com.pow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.func.model.FuncJDBCDAO;
import com.lost.model.LostVO;

public class PowJDBCDAO implements PowDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user1";
	String passwd = "u111";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, powVO.getEmpno());
			pstmt.setInt(2, powVO.getFuncno());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load datebase driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, powVO.getFuncno());
			pstmt.setInt(2, powVO.getEmpno());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load datebase driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, empno);
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load datebase driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			//Handle any driver errors
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occured." + se.getMessage());
//			Clean up JDBC 
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				powVO = new PowVO();
				powVO.setEmpno(rs.getInt("empno"));
				powVO.setFuncno(rs.getInt("funcno"));
				list.add(powVO);//Store the row in the vector;
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(listPow_Byfuncname);
			
			pstmt.setInt(1, funcno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				powVO = new PowVO();
				powVO.setEmpno(rs.getInt("empno"));
				powVO.setFuncno(rs.getInt("funcno"));
				list.add(powVO);
			}
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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

	public static void main(String[] args){
		PowJDBCDAO dao = new PowJDBCDAO();
		
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
//		List<PowVO> list = dao.findByPrimaryKey(1001);
//		for(PowVO apow : list){
//		System.out.print(apow.getEmpno() + ", ");
//		System.out.println(apow.getFuncno());
//		}
//		System.out.println("----------------------------------");
//		//查詢
		List<PowVO> list = dao.getAll();
		for(PowVO apow : list){
			System.out.print(apow.getEmpno() + ", ");
			System.out.println(apow.getFuncno() + ", ");
		}
	}

	@Override
	public PowVO getOnePowByPKs(Integer empno, Integer funcno) {
		// TODO Auto-generated method stub
		return null;
	}
}
