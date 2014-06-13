<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lost.model.*" %>
<%--
	LostService lostSvc = new LostService();
	List<LostVO> list = lostSvc.getAll();
	
	List<Integer> xx = null;
	for(LostVO lostVO : list){
		if(lostVO.getLostno() instanceof Integer){
			xx.add(lostVO.getLostno());
		}
	}
	
	pageContext.setAttribute("list", list);
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title></title>

<script language="javascript">
$(function(){
	$("#lose_panel").show();
	$("#aud_panel").hide();
	$(".panel_toggle").click(function(){
		var item_panel = $(this).attr('item');
		var src = $(this).children('img').attr('src');
		if( src.match('plus') == 'plus' ){
			var newsrc = src.replace(/plus/,'minus');
		}else{
			var newsrc = src.replace(/minus/,'plus');
		}
		$(this).children('img').attr('src',newsrc);
		$("#"+item_panel+"_panel").slideToggle(200, function() {
			return false;
		});
		return false;
	});
	
});

function loadTab(obj,n){
	
	var layer;
	eval('layer=\'S'+n+'\'');
	//將 Tab 標籤樣式設成 Blur 型態
	var tabsF=document.getElementById('tabsF').getElementsByTagName('li');
	for (var i=0;i<tabsF.length;i++){
		tabsF[i].setAttribute('id',null);
		
	eval('document.getElementById(\'S'+(i+1)+'\').style.display=\'none\'')
	}

	//變更分頁標題樣式
	obj.parentNode.setAttribute('id','current');
	document.getElementById(layer).style.display='inline';
}
</script>

<link href="<%= request.getContextPath()%>/css/menuTab.css" rel="stylesheet" type="text/css">
</head>

<body>

<div id="main_box">
        	<div id="main_column">
            <div class="index_main_box02"><img src="<%= request.getContextPath()%>/images/index_main_bg01.jpg" width="510" height="19"></div>
            <div class="index_main_box01">
              <div class="index_main_t">
                <div class="index_main_icon">
                  <table border="0" cellpadding="0" cellspacing="0">
                    <tbody>
					<tr>
                      <td><a href="#" class="panel_toggle" item="lose"><img src="<%= request.getContextPath()%>/images/m-minus.jpg" alt="" name="indexmainicon" width="16" height="16" border="0" id="indexmainicon" onload=""></a></td>
                    </tr>
                    </tbody>
				  </table>
                </div>
				<img src="<%= request.getContextPath()%>/images/20140509-11.jpg">
			  </div>
              <div class="index_main_t_info" id="lose_panel">
				<p style="font-size: 18px; font-weight: bold;">
					<strong style="font-size: large;">活動期間: 5月10日(六)~8月15日(五)</strong>
					<span style="font-size: 12px; font-weight: normal;">&nbsp;&nbsp;</span>
					<span style="font-size: 12px; font-weight: normal;">&nbsp;<br></span>
					<a href="#" target="_self">
					<img src="<%= request.getContextPath()%>/images/2014pethappysummer.jpg" width="400" height="400" style="font-size: 12px; font-weight: normal;" align="absMiddle" alt="">
					</a>
				</p>
				<p>
					<span style="color: rgb(255, 255, 255);">
					  <span style="background-color: rgb(51, 204, 204);">
						<span style="font-size: medium;"><b>寵物快樂一夏<br></b>
						</span>
					  </span>
					</span>
					<span style="font-size: x-large;">
						<b>防蟲戰鬥防護圈，夏日除蟲有三寶<br></b>
					</span>
					<span style="font-size: medium;"><b>夏日除蟲有三寶：<br>
							  內篩檢．外除蟲．黴菌預防+治療三方到位<br></b>
					</span>
					<span style="color: rgb(255, 0, 0);">活動期間：即日起至8/15止</span><br>
						台灣海島型氣侯造成環境潮濕悶熱，蚊蟲、跳蚤、壁蝨、黴菌滋生，危害著家中寵物的健康！<br>
						迎戰夏天，全國動物醫院連鎖體系提醒您，市售防蟲滴劑多未具有殺死心絲蟲成蟲或幼絲蟲，故在投藥前最好可為寶貝做好內寄生蟲篩檢及皮毛黴菌診斷，確認是否已有感染，除去後，每月再以外用除蟲滴劑保護寵物，三方到位塑造防蟲戰鬥防護圈，讓寵物快樂一夏！
				</p>
				<p>
					<span style="font-size: small;"><a href="<%= request.getContextPath()%>/front/news/news.do?action=getOne_For_Display&newsno=6012" target="_self" style="font-size: large;">看活動詳情</a>
					</span>
				</p>
			  </div>
            </div>
            <div class="index_main_box02"><img src="<%= request.getContextPath()%>/images/index_main_bg03.jpg" width="510" height="18"></div>
            <div class="index_main_box02"><img src="<%= request.getContextPath()%>/images/index_main_bg01.jpg" width="510" height="19"></div>
            <div class="index_main_box01">
              <div class="index_main_t">
                <div class="index_main_icon">
                  <table border="0" cellpadding="0" cellspacing="0">
                    <tbody>
					<tr>
                      <td><a href="#" class="panel_toggle" item="aud">
						  <img src="<%= request.getContextPath()%>/images/m-plus.jpg" alt="" name="indexmainicon" width="16" height="16" border="0" id="indexmainicon" onload="">
						  </a>
					  </td>
                    </tr>
					</tbody>
				  </table>
                </div>
              <img src="<%= request.getContextPath()%>/images/tainan24h.jpg">
			  </div>
              <div class="index_main_t_info" id="aud_panel" style="display: none;">
			  <h2>
			  <div>
				<p><span style="font-size: x-small;">全國動物醫院&nbsp;
				   <span style="color: rgb(68, 68, 68); font-family: Verdana, Arial, sans-serif; letter-spacing: 1px; text-align: center;">【</span>永康分院
				   <span style="color: rgb(68, 68, 68); font-family: Verdana, Arial, sans-serif; letter-spacing: 1px; text-align: center;">】</span>於3月1日起提供夜間照護及24h精實急診服務
				   <span style="font-family: 微軟正黑體, sans-serif; font-weight: bold;">，</span>24h堅守岡位為您的毛小孩把關守門
				   <span style="font-family: 微軟正黑體, sans-serif; font-weight: bold;">。</span>&nbsp;</span>
				   <a href="#" target="_blank"><img src="<%= request.getContextPath()%>/images/24h-01.jpg" width="400" height="452" alt=""></a><br>&nbsp;
				</p>
				<p style="font-size: 12px; font-weight: normal;">&nbsp;</p>
				<p style="font-size: 12px; font-weight: normal;">&nbsp;</p>
			  </div>
			  </h2>
			  </div>
            </div>
            <div class="index_main_box02">
				<img src="<%= request.getContextPath()%>/images/index_main_bg03.jpg" width="510" height="18">
			</div>
        	</div>
		<div id="right_column">
          <div class="right_box01">
          	<h3 style="font-family:微軟正黑體  margin-top:20px; margin-bottom:-10px;">寵物失蹤協尋<a href="<%= request.getContextPath()%>/front/lost/listAllLost.jsp"><span style="margin-left:10px;font-size:16px">更多>></span></a></h3>
				<div class="right_box03">
				  <ul>
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5001" title="【失蹤】【桃園縣】中山東路＜好樂迪外面＞白色貴賓：公">【桃園縣】中山東路</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5002" title="【台北縣】柴犬 板橋大觀路二段遺失柴犬">【台北縣】板橋遺失柴犬</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5003" title="【台北縣】白貴賓 {新北市中永和}白貴賓走失!!">【台北縣】白貴賓走失</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5004" title="【彰化縣】請協尋~黑長毛臘腸~Duly~">【彰化縣】黑長毛臘腸</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5006" title="【全台灣】尋找愛貓">【全台灣】尋找愛貓</a></li>
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5007" title="【宜蘭縣】狗狗走失了">【宜蘭縣】狗狗走失了</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5008" title="【高雄市】高雄鳳山五甲 4/15 拾獲玄鳳鸚鵡">【高雄市】玄鳳鸚鵡</a></li>
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5009" title="【台北縣】在八里拾獲三公斤大黑母兔">【台北縣】大黑母兔</a></li>
				  </ul>
				</div>
          </div>
          <div class="right_box02">
           	 <h3 style="font-family:微軟正黑體  margin-top:20px; margin-bottom:-10px;";>人氣商品</h3>
				<div class="right_box04">
				  <ul>
					<li><img src="<%= request.getContextPath()%>/product/3.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=3" title="足球運動服-紐西蘭9號-E">足球運動服</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/4.jpg">			
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=3" title="小熊立領點點襯衫-B">小熊立領衫</a></li>
					<li><img src="<%= request.getContextPath()%>/product/8.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=4" title="足球運動服-法國7號-E">足球運動服</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/10.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=9" title="細條紋可愛豬(藍)-A">條紋可愛豬</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/13.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=3" title="足球運動服-紐西蘭9號-E">足球運動服</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/15.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=4" title="小熊立領點點襯衫-B">小熊立領衫</a></li>
					<li><img src="<%= request.getContextPath()%>/product/6.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=1" title="足球運動服-法國7號-E">足球運動服</a></li>
					
				  </ul>
				</div>
    	  </div>
        <br>
        <br>
        <br>
        <br>

		</div>

</body>
</html>