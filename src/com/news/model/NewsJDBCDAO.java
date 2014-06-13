package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class NewsJDBCDAO implements NewsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "user2";
	String passwd = "u222";
	
	private static final String INSERT_STMT = 
			"INSERT INTO NEWS(newsno, newstitle, newstype, newscontent, newspic, newspotime, empno) VALUES(news_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT newsno, newstitle, newstype, newscontent, newspic, to_char(newspotime, 'yyyy-mm-dd')newspotime, emp_no FROM NEWS order by newsno";
	private static final String GET_ONE_STMT = 
			"SELECT newsno, newstitle, newstype, newscontent, newspic, to_char(newspotime, 'yyyy-mm-dd')newspotime, emp_no FROM NEWS where newsno = ?";
	private static final String UPDATE = 
			"UPDATE NEWS set newstitle=?, newstype=?, newscontent=?, newspic=?, newspotime=?, empno=? where newsno=?";
	private static final String DELETE = 
			"DELETE FROM NEWS where newsno=?";
	
	@Override
	public void insert(NewsVO newsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, newsVO.getNewstitle());
			pstmt.setInt(2, newsVO.getNewstype());
			pstmt.setString(3, newsVO.getNewscontent());
			pstmt.setBytes(4, newsVO.getNewspic());
			pstmt.setDate(5, newsVO.getNewspotime());
			pstmt.setInt(6, newsVO.getEmpno());
			
			pstmt.executeUpdate();
			
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
		
	}

	@Override
	public void update(NewsVO newsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, newsVO.getNewstitle());
			pstmt.setInt(2, newsVO.getNewstype());
			pstmt.setString(3, newsVO.getNewscontent());
			pstmt.setBytes(4, newsVO.getNewspic());
			pstmt.setDate(5, newsVO.getNewspotime());
			pstmt.setInt(6, newsVO.getEmpno());
			pstmt.setInt(7, newsVO.getNewsno());
			
			pstmt.executeUpdate();
			
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
		
	}

	@Override
	public void delete(Integer newsno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, newsno);
			
			pstmt.executeUpdate();
			
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
		
	}

	@Override
	public NewsVO findByPrimaryKey(Integer newsno) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, newsno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//MemVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getInt("newsno"));
				newsVO.setNewstitle(rs.getString("newstitle"));
				newsVO.setNewstype(rs.getInt("newstype"));
				newsVO.setNewscontent(rs.getString("newscontent"));
				newsVO.setNewspic(rs.getBytes("newspic"));
				newsVO.setNewspotime(rs.getDate("newspotime"));
				newsVO.setEmpno(rs.getInt("empno"));
			}
			
			//Handle any driver errors
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
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getInt("newsno"));
				newsVO.setNewstitle(rs.getString("newstitle"));
				newsVO.setNewstype(rs.getInt("newstype"));
				newsVO.setNewscontent(rs.getString("newscontent"));
				newsVO.setNewspic(rs.getBytes("newspic"));
				newsVO.setNewspotime(rs.getDate("newspotime"));
				newsVO.setEmpno(rs.getInt("empno"));
				list.add(newsVO);//Store the row in the vector;
				
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
		NewsJDBCDAO dao = new NewsJDBCDAO();
		
//		//新增
//		NewsVO newsVO1 = new NewsVO();
//		newsVO1.setNewstitle("破盤大特價2");
//		newsVO1.setNewstype("商品類");
//		newsVO1.setNewscontent("所有商品一律八折");
//		newsVO1.setNewspic(null);
//		newsVO1.setNewspotime(java.sql.Date.valueOf("2014-04-27"));
//		newsVO1.setEmpno(1009);
//		int updateCount_insert = dao.insert(newsVO1);
//		System.out.println(updateCount_insert);
		
//		//修改
//		NewsVO newsVO2 = new NewsVO();
//		newsVO2.setNewsno(6010);
//		newsVO2.setNewstitle("跳樓大拍賣");
//		newsVO2.setNewstype("商品類");
//		newsVO2.setNewscontent("所有商品一律八折");
//		newsVO2.setNewspic(null);
//		newsVO2.setNewspotime(java.sql.Date.valueOf("2014-04-28"));
//		newsVO2.setEmpno(1005);
//		int updateCount_update = dao.update(newsVO2);
//		System.out.println(updateCount_update);
//		
//		//刪除
//		int updateCount_delete = dao.delete(6010);
//		System.out.println(updateCount_delete);
//		
		//查詢
//		NewsVO newsVO3 = dao.findByPrimaryKey(6005);
//		System.out.print(newsVO3.getNewsno() + ", ");
//		System.out.print(newsVO3.getNewstitle() + ", ");
//		System.out.print(newsVO3.getNewstype() + ", ");
//		System.out.print(newsVO3.getNewscontent() + ", ");
//		System.out.print(newsVO3.getNewspic() + ", ");
//		System.out.print(newsVO3.getNewspotime() + ", ");
//		System.out.println(newsVO3.getEmpno());
//		System.out.println("----------------------------------");
		//查詢
		List<NewsVO> list = dao.getAll();
		for(NewsVO aNews : list){
			System.out.print(aNews.getNewsno() + ", ");
			System.out.print(aNews.getNewstitle() + ", ");
			System.out.print(aNews.getNewstype() + ", ");
			System.out.print(aNews.getNewscontent() + ", ");
			System.out.print(aNews.getNewspic() + ", ");
			System.out.print(aNews.getNewspotime() + ", ");
			System.out.println(aNews.getEmpno());
		}
	}

	@Override
	public List<NewsVO> listNews_ByType(Integer newstype) {
		// TODO Auto-generated method stub
		return null;
	}
}
