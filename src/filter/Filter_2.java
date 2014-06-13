package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Filter_2 implements Filter{

	private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//�i���o session�j
		HttpSession session = req.getSession();
		// �i�q session �P�_��user�O�_�n�J�L�j
		Object empNo = session.getAttribute("empNo");
		if(empNo == null){
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/empLogin.jsp");
			return;
		}else{
			chain.doFilter(req, res);
		}
		
	}

	
}
