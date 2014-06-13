package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.*;

public class Filter_1 implements Filter {

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
		//【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object memid = session.getAttribute("memid");
		if(memid == null){
			
			
			if ( req.getParameter("aptDate") != null && req.getParameter("aptPeriod") != null) {
				req.getSession().setAttribute( "aptDate", request.getParameter("aptDate"));
				req.getSession().setAttribute( "aptPeriod", request.getParameter("aptPeriod"));

			}


			System.out.println( "aptDateInF" + req.getParameter("aptDate"));
			System.out.println( "aptPeriodInF" + req.getParameter("aptPeriod"));
			
			
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/memLogin.jsp");
			
		
			return;
		}else{
			chain.doFilter(req, res);
		}
	}


}
