package com.emp.controller;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.sql.DataSource;

import com.emp.model.EmployeeService;
import com.emp.model.EmployeeVO;

public class DBGifReader3 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Big5");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		Integer empNo = new Integer(req.getParameter("empNo"));
		EmployeeService empSvc = new EmployeeService();
		EmployeeVO employeeVO = empSvc.getOneEmp(empNo);
		
		byte[] srcImageData = ImageUtil.shrink(employeeVO.getEmpPic(), 200);
		
		out.write(srcImageData);
//		try {
//
//			Statement stmt = con.createStatement();
//			String empNo = req.getParameter("empNo");
//			String empNo2 = new String(empNo.getBytes("ISO-8859-1"), "Big5");
//			ResultSet rs = stmt	.executeQuery("SELECT empPic FROM EMPLOYEE where empNo = '"	+ empNo2 + "'");
//
//			if (rs.next()) {
//				BufferedInputStream in = new BufferedInputStream(
//						rs.getBinaryStream("empPic"));
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
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
