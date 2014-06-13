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
	//�N Tab ���Ҽ˦��]�� Blur ���A
	var tabsF=document.getElementById('tabsF').getElementsByTagName('li');
	for (var i=0;i<tabsF.length;i++){
		tabsF[i].setAttribute('id',null);
		
	eval('document.getElementById(\'S'+(i+1)+'\').style.display=\'none\'')
	}

	//�ܧ�������D�˦�
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
					<strong style="font-size: large;">���ʴ���: 5��10��(��)~8��15��(��)</strong>
					<span style="font-size: 12px; font-weight: normal;">&nbsp;&nbsp;</span>
					<span style="font-size: 12px; font-weight: normal;">&nbsp;<br></span>
					<a href="#" target="_self">
					<img src="<%= request.getContextPath()%>/images/2014pethappysummer.jpg" width="400" height="400" style="font-size: 12px; font-weight: normal;" align="absMiddle" alt="">
					</a>
				</p>
				<p>
					<span style="color: rgb(255, 255, 255);">
					  <span style="background-color: rgb(51, 204, 204);">
						<span style="font-size: medium;"><b>�d���ּ֤@�L<br></b>
						</span>
					  </span>
					</span>
					<span style="font-size: x-large;">
						<b>���ξ԰����@��A�L�鰣�Φ��T�_<br></b>
					</span>
					<span style="font-size: medium;"><b>�L�鰣�Φ��T�_�G<br>
							  ���z�ˡD�~���ΡD��߹w��+�v���T����<br></b>
					</span>
					<span style="color: rgb(255, 0, 0);">���ʴ����G�Y��_��8/15��</span><br>
						�x�W���q����J�y�����Ҽ���e���A�A�ΡB���D�B�����B��ߴ��͡A�M�`�ۮa���d�������d�I<br>
						��ԮL�ѡA����ʪ���|�s����t�����z�A���⨾�κw���h���㦳�����ߵ��Φ��ΩΥ����ΡA�G�b���īe�̦n�i���_�����n���H���οz�ˤΥ֤���߶E�_�A�T�{�O�_�w���P�V�A���h��A�C��A�H�~�ΰ��κw���O�@�d���A�T�����y���ξ԰����@��A���d���ּ֤@�L�I
				</p>
				<p>
					<span style="font-size: small;"><a href="<%= request.getContextPath()%>/front/news/news.do?action=getOne_For_Display&newsno=6012" target="_self" style="font-size: large;">�ݬ��ʸԱ�</a>
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
				<p><span style="font-size: x-small;">����ʪ���|&nbsp;
				   <span style="color: rgb(68, 68, 68); font-family: Verdana, Arial, sans-serif; letter-spacing: 1px; text-align: center;">�i</span>�ñd���|
				   <span style="color: rgb(68, 68, 68); font-family: Verdana, Arial, sans-serif; letter-spacing: 1px; text-align: center;">�j</span>��3��1��_���ѩ]�����@��24h����E�A��
				   <span style="font-family: �L�n������, sans-serif; font-weight: bold;">�A</span>24h��u���쬰�z����p�ħ����u��
				   <span style="font-family: �L�n������, sans-serif; font-weight: bold;">�C</span>&nbsp;</span>
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
          	<h3 style="font-family:�L�n������  margin-top:20px; margin-bottom:-10px;">�d�����ܨ�M<a href="<%= request.getContextPath()%>/front/lost/listAllLost.jsp"><span style="margin-left:10px;font-size:16px">��h>></span></a></h3>
				<div class="right_box03">
				  <ul>
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5001" title="�i���ܡj�i��鿤�j���s�F���զn�֭}�~���֥զ�Q���G��">�i��鿤�j���s�F��</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5002" title="�i�x�_���j��� �O���j�[���G�q�򥢮��">�i�x�_���j�O���򥢮��</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5003" title="�i�x�_���j�նQ�� {�s�_�����éM}�նQ������!!">�i�x�_���j�նQ������</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5004" title="�i���ƿ��j�Ш�M~�ª���þ�z~Duly~">�i���ƿ��j�ª���þ�z</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5006" title="�i���x�W�j�M��R��">�i���x�W�j�M��R��</a></li>
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5007" title="�i�y�����j���������F">�i�y�����j���������F</a></li>			
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5008" title="�i�������j������s���� 4/15 �B��Ȼ��x�M">�i�������j�Ȼ��x�M</a></li>
					<li><a href="<%= request.getContextPath()%>/front/lost/lost.do?action=getOne_For_Display&lostno=5009" title="�i�x�_���j�b�K���B��T����j�¥���">�i�x�_���j�j�¥���</a></li>
				  </ul>
				</div>
          </div>
          <div class="right_box02">
           	 <h3 style="font-family:�L�n������  margin-top:20px; margin-bottom:-10px;";>�H��ӫ~</h3>
				<div class="right_box04">
				  <ul>
					<li><img src="<%= request.getContextPath()%>/product/3.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=3" title="���y�B�ʪA-�æ���9��-E">���y�B�ʪA</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/4.jpg">			
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=3" title="�p���߻��I�IŨ�m-B">�p���߻�m</a></li>
					<li><img src="<%= request.getContextPath()%>/product/8.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=4" title="���y�B�ʪA-�k��7��-E">���y�B�ʪA</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/10.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=9" title="�ӱ����i�R��(��)-A">�����i�R��</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/13.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=3" title="���y�B�ʪA-�æ���9��-E">���y�B�ʪA</a></li>			
					<li><img src="<%= request.getContextPath()%>/product/15.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=4" title="�p���߻��I�IŨ�m-B">�p���߻�m</a></li>
					<li><img src="<%= request.getContextPath()%>/product/6.jpg">
					<a href="<%= request.getContextPath()%>/backend/PRODUCT/product.do?action=listItem&&prono=1" title="���y�B�ʪA-�k��7��-E">���y�B�ʪA</a></li>
					
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