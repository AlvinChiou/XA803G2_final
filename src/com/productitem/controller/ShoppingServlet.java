package com.productitem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.order.model.OrderDAO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productitem.model.*;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class ProductItem
 */
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if ("ADD".equals(action) || "ADDFROMITEM".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			List<String> ststusMsgs = new LinkedList<String>();
			request.setAttribute("ststusMsgs", ststusMsgs);
			try {
				Integer prono = new Integer(request.getParameter("prono")
						.trim());
				String productname = new String(request.getParameter(
						"productname").trim());
				Integer price = new Integer(request.getParameter("price")
						.trim());
				Integer itemqty = new Integer(request.getParameter("itemqty")
						.trim());

				ProdItemVO prodItemVO = new ProdItemVO();
				prodItemVO.setItemqty(itemqty);
				prodItemVO.setProno(prono);
				prodItemVO.setPrice(price);

				Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
						.getAttribute("shoppingcart");
				boolean match = false;

				if (buylist == null) {
					buylist = new Vector<ProdItemVO>();
					buylist.add(prodItemVO);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProdItemVO prodItemVO2 = buylist.get(i);
						if (prodItemVO2.getProno()
								.equals(prodItemVO.getProno())) {
							prodItemVO2.setItemqty(prodItemVO2.getItemqty()
									+ prodItemVO.getItemqty());
							buylist.setElementAt(prodItemVO2, i);
							match = true;
						}// end if
					}// end for loop
					if (!match) {
						buylist.add(prodItemVO);
					}
				}// end else
				session.setAttribute("shoppingcart", buylist);
				int total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					prodItemVO = buylist.get(i);
					price = prodItemVO.getPrice();
					itemqty = (prodItemVO.getItemqty());
					total += (price * itemqty);
				}
				String amount = String.valueOf(total);

				// System.out.println("印出加總金額="+amount);

				session.setAttribute("amount1", amount);
				ststusMsgs.add("新增成功");
				// String url = "/ORDERITEM/item.jsp";
				String url = "/frontend/ORDERITEM/market.jsp";
				if ("ADD".equals(action)) {
					url = "/frontend/ORDERITEM/market.jsp";
				} else if ("ADDFROMITEM".equals(action)) {
					url = "/backend/PRODUCT/product.do?action=listItem&prono="
							+ prono + "&pageFrom=item";
				}

				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				// 其他可能錯誤處理
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/frontend/ORDERITEM/additem.jsp");
				failureView.forward(request, response);
				return;
			}
		}

		if ("CHECKOUT".equals(action)) {
			Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
					.getAttribute("shoppingcart");
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProdItemVO prodItemVO = buylist.get(i);
				int price = prodItemVO.getPrice();
				// Integer itemqty = new Integer(request.getParameter("itemqty")
				// + i);
				int itemqty = (prodItemVO.getItemqty());
				// prodItemVO.setItemqty(itemqty);
				// buylist.add(prodItemVO);
				total += (price * itemqty);
			}
			String amount = String.valueOf(total);

			// System.out.println("印出加總金額="+amount);

			session.setAttribute("amount", amount);
			session.setAttribute("shoppingcart", buylist);
			request.setAttribute("pageFrom", "CHECKOUT");
			String url = "/frontend/ORDERITEM/additem.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
		if ("DELETE".equals(action)) {
			Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
					.getAttribute("shoppingcart");

			String del = request.getParameter("del");
			int d = Integer.parseInt(del);
			buylist.removeElementAt(d);

			session.setAttribute("shoppingcart", buylist);
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProdItemVO prodItemVO = buylist.get(i);
				int price = prodItemVO.getPrice();
				int itemqty = (prodItemVO.getItemqty());
				total += (price * itemqty);
			}
			String amount = String.valueOf(total);

			// System.out.println("印出加總金額="+amount);

			session.setAttribute("amount1", amount);
			String url = null;

			if (buylist.size() != 0) {
				url = "/frontend/ORDERITEM/additem.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			} else {
				url = "/frontend/ORDERITEM/market.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		}
		if ("COMPLETE_ORDERS".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			List<String> ststusMsgs = new LinkedList<String>();
			request.setAttribute("ststusMsgs", ststusMsgs);
			try {
				String ordaddr = new String(request.getParameter("ordaddr")
						.trim());
				String ordtel = new String(request.getParameter("memtel")
						.trim());
				Timestamp ordgotime = null;
				Timestamp ordarrtime = null;
				Timestamp orddeltime = null;
				Integer ordstate = 0;
				Integer memno_int = new Integer(request.getParameter("memno")
						.trim());
				String memno = memno_int.toString();
				Integer empno = 0;

				OrderVO orderVO = new OrderVO();
				orderVO.setOrdaddr(ordaddr);
				orderVO.setOrdtel(ordtel);
				orderVO.setOrdgotime(ordgotime);
				orderVO.setOrdarrtime(ordarrtime);
				orderVO.setOrddeltime(orddeltime);
				orderVO.setOrdstate(ordstate);
				orderVO.setMemno(memno);
				orderVO.setEmpno(empno);

				Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
						.getAttribute("shoppingcart");
				Vector<ProdItemVO> list = new Vector<ProdItemVO>();
				for (int i = 0; i < buylist.size(); i++) {
					ProdItemVO prodItemVO = buylist.get(i);
					prodItemVO.setItemqty(prodItemVO.getItemqty());
					String itemmemo = new String(request.getParameter(
							"itemmemo").trim());
					prodItemVO.setItemmemo(itemmemo);
					prodItemVO.setProno(prodItemVO.getProno());
					prodItemVO.setPrice(prodItemVO.getPrice());
					list.add(prodItemVO);

					// 根據傳入的商品編號查詢商品
					ProductService proSvc = new ProductService();
					ProductVO productVO = proSvc.getOneProduct(prodItemVO
							.getProno());
					// 扣掉庫存
					Integer prono = prodItemVO.getProno();
					Integer quantity = (productVO.getQuantity() - prodItemVO
							.getItemqty());
					productVO = proSvc.updateByPrimaryKey(prono, quantity);
				}
				if (request.getParameter("update") != null) {
					try {
						MemVO memVO = (MemVO) session.getAttribute("memVO");
						Integer memno2 = memVO.getMemno();
						String memid = memVO.getMemid();
						String mempassword = memVO.getMempassword();

						String memname = null;
						if (request.getParameter("memname").trim() != null
								|| request.getParameter("memname").trim()
										.length() > 0) {
							memname = request.getParameter("memname").trim();
						} else {
							errorMsgs.add("姓名不得留白");
						}

						String memidno = memVO.getMemidno();
						String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						String mememail = null;
						if (!(request.getParameter("mememail").trim())
								.matches(EMAIL_PATTERN)) {
							errorMsgs.add("E-Mail格式錯誤");

						} else if ((request.getParameter("mememail").trim()) == null
								|| (request.getParameter("mememail").trim())
										.length() <= 0) {
							errorMsgs.add("E-Mail不得留白");

						} else {
							mememail = request.getParameter("mememail").trim();
						}

						java.sql.Date membirth = memVO.getMembirth();

						String memadd = null;
						if (request.getParameter("ordaddr").trim() != null
								|| request.getParameter("ordaddr").trim()
										.length() > 0) {
							memadd = request.getParameter("ordaddr").trim();
						} else {
							errorMsgs.add("地址不得留白");
						}
						Integer memsex = memVO.getMemsex();

						String memtel = null;
						String telReg = "^09[0-9]{8}$";
						if (!(request.getParameter("memtel").trim())
								.matches(telReg)) {
							errorMsgs.add("手機號碼格式錯誤");
						} else if ((request.getParameter("memtel").trim()) == null
								|| (request.getParameter("memtel").trim())
										.length() <= 0) {
							errorMsgs.add("手機號碼不得留白");
						} else {
							memtel = request.getParameter("memtel").trim();
						}
						Integer memstate = memVO.getMemstate();
						memVO.setMemno(memVO.getMemno());
						memVO.setMemid(memid);
						memVO.setMempassword(mempassword);
						memVO.setMemname(memname);
						memVO.setMemidno(memidno);
						memVO.setMememail(mememail);
						memVO.setMembirth(membirth);
						memVO.setMemadd(memadd);
						memVO.setMemsex(memsex);
						memVO.setMemtel(memtel);
						memVO.setMemstate(memstate);
						if (!errorMsgs.isEmpty()) {
							System.out.println("偵測到錯誤!");
							String url = "/frontend/ORDERITEM/checkout.jsp";
							RequestDispatcher failureView = request
									.getRequestDispatcher(url);
							failureView.forward(request, response);
							return;
						}
						MemService memSvc = new MemService();
						memVO = memSvc.updateMem(memno2, memid, mempassword,
								memname, memidno, mememail, membirth, memadd,
								memsex, memtel, memstate);
					} catch (Exception e) {
						errorMsgs.add("輸入資料錯誤:" + e.getMessage());
						RequestDispatcher failureView = request
								.getRequestDispatcher("/frontend/ORDERITEM/checkout.jsp");
						failureView.forward(request, response);
						e.printStackTrace();
					}
				}

				// 查詢會員名及信箱
				MemService mService = new MemService();
				MemVO memVO = mService.getOneMem(new Integer(memno));
				String memname = new String(memVO.getMemname());
				String mememail = new String(memVO.getMememail()); // 取出下訂單會員的Email
				request.setAttribute("mememail", mememail);
				request.setAttribute("memname", memname);
				if (!errorMsgs.isEmpty()) {
					String url = "/frontend/ORDERITEM/checkout.jsp";
					RequestDispatcher failureView = request
							.getRequestDispatcher(url);
					failureView.forward(request, response);
					return;
				}
				// 開始插入資料，根據自增主鍵
				OrderService ordSvc = new OrderService();
				ordSvc.addOrdersByGenKey(orderVO, list);

				if (!errorMsgs.isEmpty()) {
					String url = "/frontend/ORDERITEM/checkout.jsp";
					RequestDispatcher failureView = request
							.getRequestDispatcher(url);
					failureView.forward(request, response);
					return;
				}

				// 查詢完成準備轉交
				
				String url = "/frontend/ORDERITEM/orderSuccesed.do?action=COMPLETE";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/frontend/ORDERITEM/checkout.jsp");
				failureView.forward(request, response);
				e.printStackTrace();
			}
		}

	}
}
