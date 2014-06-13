package com.pet.model;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.act.model.ActVO;
import com.act.model.jdbcUtil_CompositeQuery_Act;
import com.apt.model.AptVO;
import com.drRecord.model.DrRecordVO;

public class PetDAO implements IPetDAO {
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//	String userid = "user1";
//	String passwd = "u111";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Pet (petNo,petName,petSex,petType,petPic,petAge,memNo) VALUES (Pet_seq.NEXTVAL, ?, ?, ?, ?, ? ,?)";
	private static final String GET_ALL_STMT = 
			"SELECT petNo,petName,petSex,petType,petPic,petAge,memNo FROM Pet order by petNo";
	private static final String GET_ALL_Pets_From_Member = 
			"SELECT petNo,petName,petSex,petType,petPic,petAge,memNo FROM Pet where MemNo=? order by petNo";
	private static final String GET_ALL_DrRecords_From_PetNo = 
			"SELECT drRecNo,drRecTime,drRecPres,drNo,petNo FROM DrRecord where petNo=? order by petNo";
	private static final String GET_ONE_STMT = 
			"SELECT petNo,petName,petSex,petType,petPic,petAge,memNo FROM Pet where petNo = ?";
	private static final String GET_ONE_STMT_From_Member = 
			"SELECT petNo,petName,petSex,petType,petPic,petAge,memNo FROM Pet where memNo = ?";
	private static final String DELETE = 
			"DELETE FROM Pet where petNo = ?";
	private static final String UPDATE = 
			"UPDATE Pet set petName=?,petSex=?,petType=?,petPic=?,petAge=?,memNo=? where petNo = ?";
	private static final String GET_ALL_Apts_By_PetNo = 
			"SELECT AptNo, AptDate, AptNoSlip, AptPeriod, AptRegTime, petNo FROM appointment where petNo=?";
	
	@Override
	public int insert(PetVO pet) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, pet.getPetName());
			pstmt.setString(2, pet.getPetSex());
			pstmt.setString(3, pet.getPetType());
			pstmt.setBytes(4, pet.getPetPic());
			pstmt.setDouble(5, pet.getPetAge());
			pstmt.setInt(6, pet.getMemNo());
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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
		return updateCount;
	}
	

	@Override
	public int update(PetVO pet) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pet.getPetName());
			pstmt.setString(2, pet.getPetSex());
			pstmt.setString(3, pet.getPetType());
			pstmt.setBytes(4, pet.getPetPic());
			pstmt.setDouble(5, pet.getPetAge());
			pstmt.setInt(6, pet.getMemNo());
			pstmt.setInt(7,  pet.getPetNo());
			

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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
		return updateCount;
	}

	@Override
	public int delete(Integer petNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, petNo);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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
		return updateCount;
	}

	@Override
	public PetVO findByPrimaryKey(Integer petNo) {
		PetVO pet = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, petNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				// empVo 也稱為 Domain objects
				pet = new PetVO();
				pet.setPetNo(rs.getInt("petNo"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetSex(rs.getString("petSex"));
				pet.setPetType(rs.getString("petType"));
				pet.setPetPic(rs.getBytes("petPic"));
				pet.setPetAge(rs.getDouble("petAge"));
				pet.setMemNo(rs.getInt("memNo"));
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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
		return pet;
	}
	
	@Override
	public PetVO findByPrimaryKeyFromMember(Integer memNo) {
		PetVO pet = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_From_Member);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				// empVo 也稱為 Domain objects
				pet = new PetVO();
				pet.setPetNo(rs.getInt("petNo"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetSex(rs.getString("petSex"));
				pet.setPetType(rs.getString("petType"));
				pet.setPetPic(rs.getBytes("petPic"));
				pet.setPetAge(rs.getDouble("petAge"));
				pet.setMemNo(rs.getInt("memNo"));
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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
		return pet;
	}
	
	@Override
	public List<PetVO> getAll() {
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO pet = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				pet = new PetVO();
				pet.setPetNo(rs.getInt("petNo"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetSex(rs.getString("petSex"));
				pet.setPetType(rs.getString("petType"));
				pet.setPetPic(rs.getBytes("petPic"));
				pet.setPetAge(rs.getDouble("petAge"));
				pet.setMemNo(rs.getInt("memNo"));
				
				list.add(pet);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} 
		catch (SQLException se) {
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

	@Override
	public List<PetVO> getAllPetsFromMember(Integer memNo) {
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO pet = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_Pets_From_Member);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				pet = new PetVO();
				pet.setPetNo(rs.getInt("petNo"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetSex(rs.getString("petSex"));
				pet.setPetType(rs.getString("petType"));
				pet.setPetPic(rs.getBytes("petPic"));
				pet.setPetAge(rs.getDouble("petAge"));
				pet.setMemNo(rs.getInt("memNo"));
				
				list.add(pet);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		}
		catch (SQLException se) {
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


	@Override
	public Set<DrRecordVO> getDrRecordsByPetNo(Integer petNo) {
		
		Set<DrRecordVO> set = new LinkedHashSet<DrRecordVO>();
		DrRecordVO drRecord = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_DrRecords_From_PetNo);
			
			pstmt.setInt(1, petNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				drRecord = new DrRecordVO();
				drRecord.setDrRecNo(rs.getInt("drRecNo"));
				drRecord.setDrRecTime(rs.getDate("drRecTime"));
				drRecord.setDrRecPres(rs.getString("drRecPres"));
				drRecord.setDrNo(rs.getInt("drNo"));
				drRecord.setPetNo(rs.getInt("petNo"));
				
				
				set.add(drRecord);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		}
		catch (SQLException se) {
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
		return set;
	}


	@Override
	public Set<AptVO> getAptsByPetNo(String petNo) {
		Set<AptVO> set = new LinkedHashSet<AptVO>();
		AptVO aptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_Apts_By_PetNo);
			
			pstmt.setString(1, petNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				aptVO = new AptVO();
				aptVO.setAptNo(rs.getString("aptNo"));
				aptVO.setAptDate(rs.getDate("aptDate"));
				aptVO.setAptPeriod(rs.getString("aptPeriod"));
				aptVO.setAptNoSlip(rs.getInt("aptNoSlip"));
				aptVO.setAptRegTime(rs.getTimestamp("aptRegTime"));
				aptVO.setPetNo(rs.getString("petNo"));

				
				set.add(aptVO);
			}

			// Handle any driver errors
		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		}
		catch (SQLException se) {
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
		return set;	}


	@Override
	public List<PetVO> getAll(Map<String, String[]> map) {
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO petVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Pet "
		          + jdbcUtil_CompositeQuery_Pet.get_WhereCondition(map)
		          + "order by petNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				petVO = new PetVO();
				petVO.setPetNo(rs.getInt("petNo"));
				petVO.setPetName(rs.getString("petName"));
				petVO.setPetSex(rs.getString("petSex"));
				petVO.setPetType(rs.getString("petType"));
				petVO.setPetPic(rs.getBytes("petPic"));
				petVO.setPetAge(rs.getDouble("petAge"));
				petVO.setMemNo(rs.getInt("memNo"));
				
				
				list.add(petVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

//	public static void main(String[] args) throws Exception{
//		PetDAO dao = new PetDAO();
//		
//		
//		//查詢單筆
//		PetVO vo1 = dao.findByPrimaryKey(4);
//		
//		System.out.print(vo1.getPetNo() + ",");
//		System.out.print(vo1.getPetName() + ",");
//		System.out.print(vo1.getPetSex() + ",");
//		System.out.print(vo1.getPetType() + ",");
//		System.out.print(vo1.getPetPic() + ",");
//		System.out.println(vo1.getPetAge() + ",");
//		
//		
//		System.out.println("---------------------");
//		
//		//新增
////		FileInputStream fis = new FileInputStream("C:\\images\\tomcat.gif");
////		int len = fis.available();
////		byte[] buffer = new byte[len];
////		
////		PetVO vo2 = new PetVO();
////		vo2.setPetName("555");
////		vo2.setPetSex("boy");
////		vo2.setPetType("dog");
////		vo2.setPetPic(buffer);
////		vo2.setPetAge(3);
////		int updateCount_insert = dao.insert(vo2);
////		System.out.println(updateCount_insert);
////		
//		System.out.println("---------------------");
//		
//		//修改
//		
////		PetVO vo3 = new PetVO();
////		vo3.setPetNo(4);
////		vo3.setPetName("123");
////		vo3.setPetSex("boy");
////		vo3.setPetType("cat");
////		vo3.setPetPic(null);
////		vo3.setPetAge(4);
////		int updateCount_update = dao.update(vo3);
////		System.out.println(updateCount_update);
//		
//		//刪除
//		
////		int updateCount_delete = dao.delete(2);
////		System.out.println(updateCount_delete);
//		
//		//查詢多筆
//		
//		List<PetVO> list = dao.getAll();
//		for(PetVO vo4 : list){
//			
//			System.out.print(vo4.getPetNo() + ",");
//			System.out.print(vo4.getPetName() + ",");
//			System.out.print(vo4.getPetSex() + ",");
//			System.out.print(vo4.getPetType() + ",");
//			System.out.print(vo4.getPetPic() + ",");
//			System.out.print(vo4.getPetAge() + ",");
//			System.out.println();
//			
//		}
//	}
}
	

	