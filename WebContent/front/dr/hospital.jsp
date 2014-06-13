<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*" %>
<%	MemVO memVO = (MemVO)session.getAttribute("memVO");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
<title>iPET Cares</title>
<link href="<%= request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath()%>/css/box_css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= request.getContextPath()%>/js/jquery-1.4.2.js"></script>  
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/jquery.fancybox-1.3.4.css" media="screen" />
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/

		$("a#example1").fancybox();
		$("#various3").fancybox({
			'width'				: '75%',
			'height'			: '75%',
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
	});
</script>

</head>

<body bgcolor='white'>

	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<c:if test="${memVO == null}"><%@ include file="/header.jsp" %></c:if>
        	<c:if test="${memVO != null}"><%@ include file="/loginHeader.jsp" %></c:if>
			<%@ include file="/ad.jsp" %>   
    	
  		</div>
		</div>
		
		<table style="margin-left:70px; margin-right:30px">
		<td>
			<h3><font color="blue">壹、醫院簡介</font></h3>
			<span style="font-size:16px;">動物不會說話，我們只能用我們醫療團隊最專業、最真誠的心，為您的寵物做最適合的醫療服務。<br>
			為了提升醫療品質，本院醫師群將不定期至國外參加各類醫學研討課程，不斷進修，以提升自我專業，為您最心愛的寵物提供最新最優質的醫療；同時我們亦不斷提升本院之診療設備，以期給寵物們一個最完整的服務。<br>
			醫院不再只是個冰冷的醫療場所，iPetCares動物醫院 竭誠歡迎您，對寵物飼養或行為上有任何疑問時，隨時歡迎您的蒞臨請教喔！</span>
			<p>
			<span style="color:#0000ff;"><span style="font-size: 18px;"><strong>貳、門診介紹</strong></span></span></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">小動物內科</span></strong></span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; 一般門診及特別門診，每週共有約30診次。目前除提供一般門診外並提供皮膚、內分泌、腫瘤、血液、消化、呼吸、心血管、泌尿、生殖、神經等系統之特別門診。亦於上班時間提供傳染病及緊急處置門診服務。
			並有傳染病隔離診察室於一樓、內科治療室、超音波檢查室、內視鏡室與呼吸分析室。現有設備如：小動物超音波掃描儀及彩色都卜勒超音波掃描儀、都卜勒血壓機、電子振盪式血壓機、小動物心電圖儀、消化道內視鏡、小動物鼻腔內視鏡、小動物氣管鏡、腹腔鏡、小動物專用氣體麻醉機、超音波噴霧機、
			氧氣製造機、小動物呼吸分析儀、血氧機、冷光免疫分析儀、耳道內視鏡、外耳道檢查套組、全耳道灌洗治療器、小動物保溫套組、點滴加溫器、點滴注射控制器、微量注射控制器、小動物專用生理監視儀等。
			</span></strong></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">小動物外科</span></strong></span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; 本科主要提供小動物（以犬貓為主﹚高品質的外科醫療服務，臨床服務包括接受初診醫療及由各地獸醫診所轉診動物。外科獸醫師群十分重視教學訓練及研究、並致力於發展出對動物更佳更先進之診斷和醫療方法。</span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;外科醫療團隊包括教職獸醫師、公職獸醫師、住院獸醫師、實習獸醫師、獸醫系實習學生及技術助理。外科醫療服務目前有一般外科/軟組織外科、骨科/整形重建外科、眼科、牙科、腫瘤外科、神經科以及針灸/神經復健科。</span></p>
			<p>
			<span style="font-size:16px;">● 一般外科/軟組織外科由資深獸醫師帶領，診治胸腔、腹腔、泌尿系統等外科疾病。</span></p>
			<p>
			<span style="font-size:16px;">● 骨科/整形重建外科由教授獸醫師帶領，診治骨折、關節疾病以及創傷重建。</span></p>
			<p>
			<span style="font-size:16px;">● 眼科由教授獸醫師帶領，診治一般眼科疾病以及眼科轉診病例，提供專業診斷及治療。</span></p>
			<p>
			<span style="font-size:16px;">● 神經科由神經專科獸醫師帶領，診治腦、脊椎、周邊神經及肌肉疾病。</span></p>
			<p>
			<span style="font-size:16px;">● 針灸/神經復健科由經國際認證針灸獸醫師帶領，以中獸醫方式處理癱瘓病例及疼痛治療。</span></p>
			<p>
			<strong style="color: rgb(0, 0, 255); font-size: 18px;">參、服務須知</strong></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">掛號服務</span></strong></span></p>
			<p>
			<span style="font-size:16px;">●現場掛號：週一至週五：上午8：30至11：00，</span><span style="font-size:16px;">下午1：30至3：30。</span></p>
			<p>
			<span style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 週六、日及國定假日不看診。</span></p>
			<p>
			<span style="font-size:16px;">●初診掛號：請飼主攜帶飼主之身份證件，如未帶證件，需繳納保證金500元（請於一個月內攜帶證件到院，驗畢後歸還保證金﹚，否則恕不受理掛號。</span></p>
			<p>
			<span style="font-size:16px;">●預約掛號：請撥：02-2739-6828#1120，1140或1141，僅接受複診及自隔日起「兩星期」內之門診預約掛號。預約上午門診者，請於上午10：30前於掛號櫃臺完成報到；預約下午門診者，請於下午15：00前於掛號櫃臺完成報到。</span></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">小動物住、出院服務</span></strong></span></p>
			<p>
			<span style="font-size:16px;">● 住院設有普通病房(五樓)與隔離病房(一樓)，二十四小時皆有醫護人員值班。</span></p>
			<p>
			<span style="font-size:16px;">● 動物住院前應先經本院診治醫師診斷，並開具「住院通知單」，依規定繳交預付金，完成住院手續後，由本院醫療人員陪同至住院室報到。</span></p>
			<p>
			<span style="font-size:16px;">● 住院期間，每週需結帳一次。</span></p>
			<p>
			<span style="font-size:16px;">● 出院前須經診治醫師認可，開具出院通知單，並繳清費用辦妥手續後，憑單至住院室接動物出院。</span></p>
			<p>
			<span style="font-size:16px;">● 為維護病房醫療環境品質與安全考量，除規定時間外，其餘時間不開放探病。</span></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">場地租借服務</span></strong></span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; 本院地下大型會議廳可容納兩百人，內有電視投影機、幻燈機、電化教學設備及桌上麥克風，十分適合教學及講座辦理，詳情請洽總務室（02-2739-6828#3110或3110）</span></p>

		</td>
		</table>
	
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>