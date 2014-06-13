package com.order.model;
import java.util.*;

import com.product.model.ProductVO;
import com.productitem.controller.ShoppingServlet;
import com.productitem.model.ProdItemVO;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(String ordno);
	public OrderVO findByPrimaryKey(String ordno);
	public List<OrderVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	//public List<OrderVO> getAll(Map<String, String[]> map);
	
	//�d�߬Y�ӷ|�����q��(�@��h)
	//public Set<OrderVO> getOrderByMemNo(String memNo);
	public void insertWithOrderItems(OrderVO orderVO, Vector<ProdItemVO> list);
	public Set<ProdItemVO> getProdItemByOrdno(String ordno);
	public void updateStatus(OrderVO orderVO);
	public Set<OrderVO> getOrdersByGoTime( String date );

	public Set<OrderVO> findByMemNo(String memno);
}
