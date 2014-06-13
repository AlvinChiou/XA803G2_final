package com.news.model;

import java.util.List;

public class NewsService {
	private NewsDAO_interface dao;

	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews(String newstitle, Integer newstype, String newscontent,
			byte[] newspic, java.sql.Date newspotime, Integer empno) {

		NewsVO newsVO = new NewsVO();

		newsVO.setNewstitle(newstitle);
		newsVO.setNewstype(newstype);
		newsVO.setNewscontent(newscontent);
		newsVO.setNewspic(newspic);
		newsVO.setNewspotime(newspotime);
		newsVO.setEmpno(empno);
		dao.insert(newsVO);

		return newsVO;
	}

	public NewsVO updateNews(Integer newsno, String newstitle, Integer newstype,
			String newscontent, byte[] newspic, java.sql.Date newspotime,
			Integer empno) {

		NewsVO newsVO = new NewsVO();

		newsVO.setNewsno(newsno);
		newsVO.setNewstitle(newstitle);
		newsVO.setNewstype(newstype);
		newsVO.setNewscontent(newscontent);
		newsVO.setNewspic(newspic);
		newsVO.setNewspotime(newspotime);
		newsVO.setEmpno(empno);
		dao.update(newsVO);

		return newsVO;
	}

	public void deleteNews(Integer newsno) {
		dao.delete(newsno);
	}

	public NewsVO getOneNews(Integer newsno) {
		return dao.findByPrimaryKey(newsno);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
	public List<NewsVO> listNews_Bytype(Integer newstype){
		return dao.listNews_ByType(newstype);
	}
}
