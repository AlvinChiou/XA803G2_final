package com.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ProductServlet extends HttpServlet {
	String folderPath;

	public void init() throws ServletException {
		String folderName = getInitParameter("createPictureFolder").toString();
		folderPath = (getServletContext().getRealPath(folderName)).toString();
		File imgDir = new File(folderPath);
		if (!imgDir.exists()) {
			imgDir.mkdirs();
			log("��Ƨ�:" + folderName + "��l�Ƨ���!");
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// String action = request.getParameter("action");
		MultipartRequest multi = null;
		String contentType = request.getContentType();
		String action = null;
		/*
		 * �ˬd����e�i�Ӫ�contentType�O�_��multipart/form-data
		 * �p�G�O�A�h�ϥ�MultipartRequest�B�̰ѼơC�_�h�����ϥ�request.getParameter() �B�z�ѼơC
		 */
		if ("listItem".equals(request.getParameter("action"))) {
			action = request.getParameter("action");
			String pageFrom = request.getParameter("pageFrom");
			request.setAttribute("pageFrom", pageFrom);
		} else {
			if (contentType != null
					&& contentType.startsWith("multipart/form-data")) {
				multi = new MultipartRequest(request, folderPath,
						10 * 1024 * 1024, "UTF-8");
				action = multi.getParameter("action");
			} else {
				action = request.getParameter("action");
			}
		}

		if ("getOne_For_Display".equals(action) || "listItem".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// �����ШD�Ѽ�
				Integer prono = new Integer(request.getParameter("prono"));
				// String url = null;
				if (prono == null) {
					errorMsgs.add("�п�J���~�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/PRODUCT/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				// Integer prono = null;
				try {
					// prono = new String.(str);
				} catch (Exception e) {
					errorMsgs.add("���~�s���榡�����T");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/PRODUCT/select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				// �}�l�d�߰ӫ~���
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);
				// String relatedProducts = productVO.getRelatedProducts();
				// String relatedProducts_ary [] = relatedProducts.split(",");
				//
				// if(relatedProducts!=null){
				// int i = 0;
				// for(String token : relatedProducts_ary){
				// i+=1;
				// request.setAttribute("relatedProducts"+i, token);
				// }//���X�����ӫ~�s��
				// request.setAttribute("relatedProducts", relatedProducts);
				// }else{
				// request.setAttribute("relatedProducts", null);
				// }
				if (productVO == null) {
					errorMsgs.add("�d�L���");
				}
				// �N���~�T����椩select_page.jsp
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/PRODUCT/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				// �d�ߧ����A�N���G��椩listOneProduct.jsp
				request.setAttribute("productVO", productVO);// �]�d�ߵ��G�]�JproductVO����A�۸�ƮwproductVO����s�Jrequest
				String url = null;
				if ("getOne_For_Display".equals(action)) {
					url = "/backend/PRODUCT/listOneProduct.jsp";
				} else if ("listItem".equals(action)) {
					url = "/frontend/ORDERITEM/item.jsp";
				}
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);

				// �H�U�B�̨�L�i����~
			} catch (Exception e) {
				String url = null;
				if ("getOne_For_Display".equals(action)) {
					url = "/backend/PRODUCT/listOneProduct.jsp";
				} else if ("listItem".equals(action)) {
					url = "/frontend/ORDERITEM/item.jsp";
				}
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher(url);
				failureView.forward(request, response);
			}
		}

		// �Ӧ�listAllProduct.jsp �� /PRODUCTITEM/listAllProductItem.jsp���ШD
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String requestURL = request.getParameter("requestURL");

			try {
				// �����ШD�Ѽ�
				Integer prono = new Integer(request.getParameter("prono"));

				// �}�l�d�߸��
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);

				// �d�ߧ����A���d�ߵ��G
				request.setAttribute("productVO", productVO);
				String url = "/backend/PRODUCT/update_product_input.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				// ��L�i�઺���~�B��
			} catch (Exception e) {
				errorMsgs.add("�ק��ƨ��X�ɥ���" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			// MultipartRequest multi = new MultipartRequest(request,
			// folderPath, 10*1024*1024, "Big5");
			String requestURL = multi.getParameter("requestURL");
			ProductService proSvc = new ProductService();
			try {
				Integer prono = new Integer(multi.getParameter("prono"));
				String productname = multi.getParameter("productname").trim();
				String category = multi.getParameter("category").trim();
				Integer price = new Integer(multi.getParameter("price").trim());

				File file1 = multi.getFile("image1");
				File file2 = multi.getFile("image2");
				File file3 = multi.getFile("image3");
				byte[] image1;
				byte[] image2;
				byte[] image3;
				if (file1 == null) {
					ProductVO productVO = proSvc.getOneProduct(prono);
					image1 = productVO.getImage1();
				} else {
					FileInputStream fis = new FileInputStream(file1);
					int leng = fis.available();
					image1 = new byte[leng];
					fis.read(image1);
					fis.close();
				}
				if (file2 == null) {
					ProductVO productVO = proSvc.getOneProduct(prono);
					image2 = productVO.getImage2();
				} else {
					FileInputStream fis = new FileInputStream(file2);
					int leng = fis.available();
					image2 = new byte[leng];
					fis.read(image2);
					fis.close();
				}
				if (file3 == null) {
					ProductVO productVO = proSvc.getOneProduct(prono);
					image3 = productVO.getImage3();
				} else {
					FileInputStream fis = new FileInputStream(file3);
					int leng = fis.available();
					image3 = new byte[leng];
					fis.read(image3);
					fis.close();
				}
				Integer quantity = null;
				try {
					quantity = new Integer(multi.getParameter("quantity")
							.trim());
				} catch (NumberFormatException e) {
					quantity = 0;
					errorMsgs.add("�ӫ~�ƶq�ж�g�Ʀr");
				}
				Integer minimumquantity = null;
				try {
					minimumquantity = new Integer(multi.getParameter(
							"minimumquantity").trim());
				} catch (NumberFormatException e) {
					minimumquantity = 0;
					errorMsgs.add("�ӫ~�̧C�ƶq�ж�g�Ʀr");
				}

				Integer status = new Integer(multi.getParameter("status"));
				String keyword = multi.getParameter("keyword").trim();
				String description = multi.getParameter("description");
				String relatedProducts1 = multi
						.getParameter("relatedProducts1").trim();
				String relatedProducts2 = multi
						.getParameter("relatedProducts2").trim();
				String relatedProducts3 = multi
						.getParameter("relatedProducts3").trim();
				String relatedProducts = relatedProducts1 + ","
						+ relatedProducts2 + "," + relatedProducts3;
				Integer priority = new Integer(multi.getParameter("priority"));
				Double discount = null;
				try {
					discount = new Double(multi.getParameter("discount").trim());
				} catch (NumberFormatException e) {
					discount = 1.0;
					errorMsgs.add("�ӫ~�馩�ƽж�g�Ʀr");
				}
				Integer score = null;
				try {
					score = new Integer(multi.getParameter("score").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�ӫ~�����ж�g�Ʀr");
				}
				String ordno = multi.getParameter("ordno");

				ProductVO productVO = new ProductVO();
				productVO.setProno(prono);
				productVO.setProductname(productname);
				productVO.setCategory(category);
				productVO.setPrice(price);
				productVO.setImage1(image1);
				productVO.setImage2(image2);
				productVO.setImage3(image3);
				productVO.setQuantity(quantity);
				productVO.setMinimumquantity(minimumquantity);
				productVO.setStatus(status);
				productVO.setKeyword(keyword);
				productVO.setRelatedProducts(relatedProducts);
				productVO.setPriority(priority);
				productVO.setDiscount(discount);
				productVO.setScore(score);
				productVO.setDescription(description);
				if (!errorMsgs.isEmpty()) {
					System.out.println("A");
					request.setAttribute("productVO", productVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/PRODUCT/update_product_input.jsp");
					failureView.forward(request, response);
					return;
				}
				// �}�l�ק���
				// ProductService proSvc = new ProductService();
				productVO = proSvc.updateProduct(prono, productname, category,
						price, image1, image2, image3, quantity,
						minimumquantity, status, keyword, relatedProducts,
						priority, discount, score, description);

				// �ק粒���ǳ����
				if (requestURL.equals("/backend/PRODUCT/listAllPorduct.jsp")) {
					request.setAttribute("listAllProduct.jsp",
							proSvc.getOneProduct(prono));
				}
				if (requestURL
						.equals("/backend/PRODUCT/listPorduct_ByCompositeQuery.jsp")) {
					HttpSession session = request.getSession();
					@SuppressWarnings("unchecked")
					Map<String, String[]> map = (Map<String, String[]>) session
							.getAttribute("map");
					List<ProductVO> list = proSvc.getAll(map);
					request.setAttribute("listPorduct_ByCompositeQuery", list);
				}
				request.setAttribute("productVO", productVO);
				String url = requestURL;
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				// System.out.println("B");
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/PRODUCT/update_product_input.jsp");
				failureView.forward(request, response);
				// e.printStackTrace();
			}
		}
		if ("insert".equals(action)) { // �Ӧ�addProduct.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// MultipartRequest multi = new MultipartRequest(request,
			// folderPath, 10*1024*1024, "Big5");
			request.setAttribute("errorMsgs", errorMsgs);

			try {

				// UUID uniqueKey = UUID.randomUUID();
				// String prono = uniqueKey.toString().toUpperCase();
				// System.out.println("----------------------------------");
				// System.out.println("uniqueKey="+uniqueKey);
				// System.out.println("String prono="+prono);
				// System.out.println("----------------------------------");

				String productname = null;
				if (multi.getParameter("productname").length() != 0) {
					productname = multi.getParameter("productname");
				} else {
					productname = multi.getParameter("productname");
					errorMsgs.add("�п�J�ӫ~�W��");
				}
				String category = null;
				if (multi.getParameter("category").length() != 0) {
					category = multi.getParameter("category");
				} else {
					category = multi.getParameter("category");
					errorMsgs.add("�п�J�ӫ~����");
				}
				Integer price = null;
				if (multi.getParameter("price").length() != 0) {
					try {
						price = new Integer(multi.getParameter("price"));
					} catch (NumberFormatException e) {
						price = new Integer(multi.getParameter("price"));
						errorMsgs.add("����ж�J���");
					}
				} else {
					price = new Integer(multi.getParameter("price"));
					errorMsgs.add("�п�J�ӫ~����");
				}

				byte[] image1 = null;
				if (multi.getFile("image1").length() != 0) {
					FileInputStream fis = new FileInputStream(
							multi.getFile("image1"));
					int len = fis.available();
					image1 = new byte[len];
					fis.read(image1);
					fis.close();
				}

				byte[] image2 = null;
				if (multi.getFile("image2").length() != 0) {
					FileInputStream fis = new FileInputStream(
							multi.getFile("image2"));
					int len = fis.available();
					image2 = new byte[len];
					fis.read(image2);
					fis.close();
				}

				byte[] image3 = null;
				if (multi.getFile("image3").length() != 0) {
					FileInputStream fis = new FileInputStream(
							multi.getFile("image3"));
					int len = fis.available();
					image3 = new byte[len];
					fis.read(image3);
					fis.close();
				}
				Integer quantity = null;
				if (multi.getParameter("quantity").length() != 0) {
					try {
						quantity = new Integer(multi.getParameter("quantity"));
					} catch (NumberFormatException e) {
						quantity = new Integer(multi.getParameter("quantity"));
						errorMsgs.add("�ӫ~�ƶq���������");
					}
				} else {
					quantity = new Integer(multi.getParameter("quantity"));
					errorMsgs.add("�ӫ~�ƶq���o����");
				}
				Integer minimumquantity = null;
				if (multi.getParameter("minimumquantity").length() != 0) {
					try {
						minimumquantity = new Integer(
								multi.getParameter("minimumquantity"));
					} catch (NumberFormatException e) {
						minimumquantity = new Integer(
								multi.getParameter("minimumquantity"));
						errorMsgs.add("�ӫ~�w���ƶq���������");
					}
				} else {
					minimumquantity = new Integer(
							multi.getParameter("minimumquantity"));
					errorMsgs.add("�ӫ~�w���ƶq���o����");
				}
				Integer status = null;
				if (multi.getParameter("status").length() != 0) {
					status = new Integer(multi.getParameter("status"));
				} else {
					status = new Integer(multi.getParameter("status"));
					errorMsgs.add("�п�ܰӫ~���A");
				}

				String keyword = multi.getParameter("keyword");
				String description = multi.getParameter("description");
				String relatedProducts1 = multi
						.getParameter("relatedProducts1").trim();
				String relatedProducts2 = multi
						.getParameter("relatedProducts2").trim();
				String relatedProducts3 = multi
						.getParameter("relatedProducts3").trim();
				String relatedProducts = relatedProducts1 + ","
						+ relatedProducts2 + "," + relatedProducts3;
				Integer priority = new Integer(multi.getParameter("priority"));

				Double discount = null;
				if (multi.getParameter("discount").length() != 0) {
					try {
						discount = new Double(multi.getParameter("discount"));
					} catch (NumberFormatException e) {
						discount = new Double(multi.getParameter("discount"));
						errorMsgs.add("�ӫ~�馩�ƥ������p��");
					}
				} else if (multi.getParameter("discount").length() == 0) {
					discount = new Double(multi.getParameter("discount"));
					errorMsgs.add("�ӫ~�馩�ƥ�����g");
				}
				Integer score = new Integer(multi.getParameter("score"));
				ProductVO productVO = new ProductVO();
				productVO.setProductname(productname);
				productVO.setCategory(category);
				productVO.setPrice(price);
				productVO.setImage1(image1);
				productVO.setImage2(image2);
				productVO.setImage3(image3);
				productVO.setQuantity(quantity);
				productVO.setMinimumquantity(minimumquantity);
				productVO.setStatus(status);
				productVO.setKeyword(keyword);
				productVO.setDescription(description);
				productVO.setRelatedProducts(relatedProducts);
				productVO.setPriority(priority);
				productVO.setDiscount(discount);
				productVO.setScore(score);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("productVO", productVO);
					System.out.println("�Ӧ�ProductServlet��productVO="
							+ productVO);

					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/PRODUCT/addProduct.jsp");
					failureView.forward(request, response);
					return;
				}
				// �}�l�s�W���
				ProductService proSvc = new ProductService();
				productVO = proSvc.addProduct(productname, category, price,
						image1, image2, image3, quantity, minimumquantity,
						status, keyword, relatedProducts, priority, discount,
						score, description);
				// �s�W�����A�ǳ����Success view
				String url = "/backend/PRODUCT/listAllProduct.jsp";
				RequestDispatcher succseeView = request
						.getRequestDispatcher(url);
				succseeView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/PRODUCT/addProduct.jsp");
				failureView.forward(request, response);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				// �����ШD�Ѽ�
				Integer prono = new Integer(request.getParameter("prono"));
				// �}�l�R�����
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);
				proSvc.deleteProduct(prono);

				// �R�������ǳ����Success view
				if (requestURL.equals("/backend/PRODUCT/listAllPorduct.jsp")) {
					request.setAttribute("listAllPorduct",
							proSvc.getOneProduct(prono));
				}
				if (requestURL
						.equals("/backend/PRODUCT/listPorduct_ByCompositeQuery.jsp")) {
					HttpSession session = request.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session
							.getAttribute("map");
					List<ProductVO> list = proSvc.getAll(map);
					request.setAttribute("listPorduct_ByCompositeQuery", list);
				}
				String url = requestURL;
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher fialureView = request
						.getRequestDispatcher(requestURL);
				fialureView.forward(request, response);
			}
		}
		if ("CHANGE_PRODUCTS_STATUS".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				// �����ШD�Ѽ�
				Integer prono = null;
				String added [] = request.getParameterValues("added");
				String pronos[] = request.getParameterValues("selectedproduct");
				// System.out.println("pronos="+pronos[0]);
				ProductService proSvc = new ProductService();
				ProductVO productVO = new ProductVO();
				if (pronos != null) {					
					for (int i = 0; i < pronos.length; i++) {
						prono = new Integer(pronos[i]);
						Integer status = 2;
						productVO = proSvc.getOneProduct(prono);
						productVO.setProno(prono);
						productVO.setStatus(status);
						productVO = proSvc.updateStatusByPrimaryKey(prono, status);
					}
				}
				if(added != null){
					Integer prono2 = null;
					for (int i = 0; i < added.length; i++) {
						prono2 = new Integer(added[i]);
						Integer status = 1;
						productVO = proSvc.getOneProduct(prono2);
						productVO.setProno(prono2);
						productVO.setStatus(status);
						productVO = proSvc.updateStatusByPrimaryKey(prono2, status);
					}
				}
				if (requestURL.equals("/backend/PRODUCT/listAllPorduct.jsp")) {
					request.setAttribute("listAllPorduct",
							proSvc.getOneProduct(prono));
				}
				if (requestURL
						.equals("/backend/PRODUCT/listPorduct_ByCompositeQuery.jsp")) {
					HttpSession session = request.getSession();
					Map<String, String[]> map = (Map<String, String[]>) session
							.getAttribute("map");
					List<ProductVO> list = proSvc.getAll(map);
					request.setAttribute("listPorduct_ByCompositeQuery", list);
				}
				String url = requestURL;
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:�z�Ŀ諸���ؤ����]�t�w�Q�ʶR���ӫ~");
				RequestDispatcher fialureView = request
						.getRequestDispatcher(requestURL);
				fialureView.forward(request, response);
			}
		}

		if ("listPorduct_ByCompositeQuery".equals(action)
				|| "CompositeQueryFromMarket".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�N��J����ରMap **********************************/
				HttpSession session = request.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session
						.getAttribute("map");
				if (request.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) request
							.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) request.getParameterMap();
				}
				/*************************** 2.�}�l�ƦX�d�� ***************************************/
				ProductService proSvc = new ProductService();
				List<ProductVO> list = proSvc.getAll(map);
				System.out.println(list.size());
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				request.setAttribute("listPorduct_ByCompositeQuery", list);
				String url = null;
				if ("listPorduct_ByCompositeQuery".equals(action)) {
					url = "/backend/PRODUCT/listPorduct_ByCompositeQuery.jsp";
				} else if ("CompositeQueryFromMarket".equals(action)) {
					url = "/frontend/ORDERITEM/SearchProduct.jsp";
				}
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/PRODUCT/select_page.jsp");
				failureView.forward(request, response);

			}
		}

	}
}