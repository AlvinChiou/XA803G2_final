package com.act.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.lost.controller.ImageUtil;
import com.pet.model.PetService;
import com.pet.model.PetVO;

public class DBGifReader3 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Big5");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		Integer actNo = new Integer(req.getParameter("actNo"));
		ActService actSvc = new ActService();
		ActVO actVO = actSvc.getOneAct(actNo);
		
		//byte[] srcImageData = lostVO.getLostpic();
		byte[] srcImageData = ImageUtil.shrink(actVO.getActPic(), 200);
		out.write(srcImageData);

//		try {
//			Statement stmt = con.createStatement();
//			 String actNo = req.getParameter("actNo");
//		     String actNo2= new String(actNo.getBytes("ISO-8859-1"),"UTF-8");
//			ResultSet rs = stmt.executeQuery("SELECT actPic from ACT where actNo='"+actNo2+"'");
//     
//			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("actPic"));
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
