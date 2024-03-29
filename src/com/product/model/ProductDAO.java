package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Product;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;
public class ProductDAO implements ProductDAO_Interface{
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Product(prono, productname, category, price, image1, image2, image3,"
			+ "quantity, minimumquantity, status, keyword, relatedproducts, priority, discount, score, description)"
			+ "VALUES (PRODUCT_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT prono, productname, category, price, image1, image2, image3,"
			+ "quantity, minimumquantity, status, keyword, "
			+ "relatedproducts, priority, discount, score, description FROM Product ORDER BY prono";
	private static final String GET_ONE_STMT = "SELECT prono, productname, category, price, image1, image2, image3,"
			+ "quantity, minimumquantity, status, keyword, "
			+ "relatedproducts, priority, discount, score, description FROM Product WHERE prono = ?";
	private static final String DELETE = "DELETE FROM Product WHERE prono = ?";
	private static final String UPDATE = "UPDATE Product SET productname = ?, category = ?, price = ?, image1 = ?,"
			+ "image2 = ?, image3 = ?, quantity = ?, minimumquantity = ?, status = ?, keyword = ?,"
			+ "relatedproducts = ?, priority = ?, discount = ?, score = ?, description = ? WHERE prono = ?";
	private static final String UPDATE_QUANTITY = "UPDATE product SET quantity = ? WHERE prono = ?";
	private static final String UPDATE_STATUS = "UPDATE product SET status = ? WHERE prono = ?";
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, productVO.getProductname());
			pstmt.setString(2, productVO.getCategory());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setBytes(4, productVO.getImage1());
			pstmt.setBytes(5, productVO.getImage2());
			pstmt.setBytes(6, productVO.getImage3());
			pstmt.setInt(7, productVO.getQuantity());
			pstmt.setInt(8, productVO.getMinimumquantity());
			pstmt.setInt(9, productVO.getStatus());
			pstmt.setString(10, productVO.getKeyword());			
			pstmt.setString(11, productVO.getRelatedProducts());
			pstmt.setInt(12, productVO.getPriority());
			pstmt.setDouble(13, productVO.getDiscount());
			pstmt.setInt(14, productVO.getScore());
			pstmt.setString(15, productVO.getDescription());
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally{
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productVO.getProductname());
			pstmt.setString(2, productVO.getCategory());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setBytes(4, productVO.getImage1());
			pstmt.setBytes(5, productVO.getImage2());
			pstmt.setBytes(6, productVO.getImage3());
			pstmt.setInt(7, productVO.getQuantity());
			pstmt.setInt(8, productVO.getMinimumquantity());
			pstmt.setInt(9, productVO.getStatus());
			pstmt.setString(10, productVO.getKeyword());			
			pstmt.setString(11, productVO.getRelatedProducts());
			pstmt.setInt(12, productVO.getPriority());
			pstmt.setDouble(13, productVO.getDiscount());
			pstmt.setInt(14, productVO.getScore());			
			pstmt.setString(15, productVO.getDescription());
			pstmt.setInt(16, productVO.getProno());
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try{
				con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(Integer prono) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, prono);
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public ProductVO findByPrimaryKey(Integer prono) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, prono);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getInt("prono"));
				productVO.setProductname(rs.getString("productname"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setImage1(rs.getBytes("image1"));
				productVO.setImage2(rs.getBytes("image2"));
				productVO.setImage3(rs.getBytes("image3"));
				productVO.setQuantity(rs.getInt("quantity"));
				productVO.setMinimumquantity(rs.getInt("minimumquantity"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setKeyword(rs.getString("keyword"));				
				productVO.setRelatedProducts(rs.getString("relatedproducts"));
				productVO.setPriority(rs.getInt("priority"));
				productVO.setDiscount(rs.getDouble("discount"));
				productVO.setScore(rs.getInt("score"));
				productVO.setDescription(rs.getString("description"));
			}
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
		return productVO;
	}
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getInt("prono"));
				productVO.setProductname(rs.getString("productname"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setImage1(rs.getBytes("image1"));
				productVO.setImage2(rs.getBytes("image2"));
				productVO.setImage3(rs.getBytes("image3"));
				productVO.setQuantity(rs.getInt("quantity"));
				productVO.setMinimumquantity(rs.getInt("minimumquantity"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setKeyword(rs.getString("keyword"));				
				productVO.setRelatedProducts(rs.getString("relatedproducts"));
				productVO.setPriority(rs.getInt("priority"));
				productVO.setDiscount(rs.getDouble("discount"));
				productVO.setScore(rs.getInt("score"));
				productVO.setDescription(rs.getString("description"));
				list.add(productVO);
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
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
	@Override
	public List<ProductVO> getAll(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			String finalSQL = "SELECT * FROM product"
					+jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
					+" ORDER BY prono";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getInt("prono"));
				productVO.setProductname(rs.getString("productname"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setImage1(rs.getBytes("image1"));
				productVO.setImage2(rs.getBytes("image2"));
				productVO.setImage3(rs.getBytes("image3"));
				productVO.setQuantity(rs.getInt("quantity"));
				productVO.setMinimumquantity(rs.getInt("minimumquantity"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setKeyword(rs.getString("keyword"));				
				productVO.setRelatedProducts(rs.getString("relatedproducts"));
				productVO.setPriority(rs.getInt("priority"));
				productVO.setDiscount(rs.getDouble("discount"));
				productVO.setScore(rs.getInt("score"));
				productVO.setDescription(rs.getString("description"));
				list.add(productVO);
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally{
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
	@Override
	public void updateByPrimaryKey(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_QUANTITY);						
			pstmt.setInt(1, productVO.getQuantity());
			pstmt.setInt(2, productVO.getProno());
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		}catch(SQLException se){
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try{
				con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void updateStatusByPrimaryKey(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);			
			pstmt.setInt(1, productVO.getStatus());			
			pstmt.setInt(2, productVO.getProno());
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try{
				con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
		
}
