package com.pet.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.lost.controller.ImageUtil;
import com.lost.model.LostService;
import com.lost.model.LostVO;
import com.pet.model.PetService;
import com.pet.model.PetVO;

public class DBGifReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Big5");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		Integer petNo = new Integer(req.getParameter("petNo"));
		PetService petSvc = new PetService();
		PetVO petVO = petSvc.getOnePet(petNo);
		
		//byte[] srcImageData = lostVO.getLostpic();
		byte[] srcImageData = ImageUtil.shrink(petVO.getPetPic(), 80);
		out.write(srcImageData);
		
//		try {
//			Statement stmt = con.createStatement();
//			 String petNo = req.getParameter("petNo");
//		     String petNo2= new String(petNo.getBytes("ISO-8859-1"),"Big5");
//			ResultSet rs = stmt.executeQuery("SELECT petPic from PET where petNo='"+petNo2+"'");
//     
//			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("petPic"));
//				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
//				in.close();
//			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
//			}
//			rs.close();
//			stmt.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (Exception e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} 
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
