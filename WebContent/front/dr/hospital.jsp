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
			<h3><font color="blue">���B��|²��</font></h3>
			<span style="font-size:16px;">�ʪ����|���ܡA�ڭ̥u��Χڭ������ζ��̱M�~�B�̯u�۪��ߡA���z���d�����̾A�X�������A�ȡC<br>
			���F���������~��A���|��v�s�N���w���ܰ�~�ѥ[�U����Ǭ�Q�ҵ{�A���_�i�סA�H���ɦۧڱM�~�A���z�̤߷R���d�����ѳ̷s���u�誺�����F�P�ɧڭ̥礣�_���ɥ��|���E���]�ơA�H�����d���̤@�ӳ̧��㪺�A�ȡC<br>
			��|���A�u�O�ӦB�N���������ҡAiPetCares�ʪ���| �ܸ��w��z�A���d���}�i�Φ欰�W������ðݮɡA�H���w��z���Y�{�бг�I</span>
			<p>
			<span style="color:#0000ff;"><span style="font-size: 18px;"><strong>�L�B���E����</strong></span></span></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">�p�ʪ�����</span></strong></span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; �@����E�ίS�O���E�A�C�g�@����30�E���C�ثe�����Ѥ@����E�~�ô��ѥֽ��B�����c�B�~�F�B��G�B���ơB�I�l�B�ߦ�ޡB�c���B�ʹޡB���g���t�Τ��S�O���E�C���W�Z�ɶ����ѶǬV�f�κ��B�m���E�A�ȡC
			�æ��ǬV�f�j���E��ǩ�@�ӡB����v���ǡB�W���i�ˬd�ǡB������ǻP�I�l���R�ǡC�{���]�Ʀp�G�p�ʪ��W���i���y���αm�ⳣ�R�ǶW���i���y���B���R�Ǧ������B�q�l�������������B�p�ʪ��߹q�ϻ��B���ƹD������B�p�ʪ���Ĥ�����B�p�ʪ������B������B�p�ʪ��M�ή���¾K���B�W���i�Q�����B
			���s�y���B�p�ʪ��I�l���R���B�����B�N���K�̤��R���B�չD������B�~�չD�ˬd�M�աB���չD��~�v�����B�p�ʪ��O�ŮM�աB�I�w�[�ž��B�I�w�`�g����B�L�q�`�g����B�p�ʪ��M�ΥͲz�ʵ������C
			</span></strong></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">�p�ʪ��~��</span></strong></span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; ����D�n���Ѥp�ʪ��]�H���߬��D�~���~�誺�~�������A�ȡA�{�ɪA�ȥ]�A������E�����ΥѦU�a�~��E����E�ʪ��C�~���~��v�s�Q�������оǰV�m�ά�s�B�íP�O��o�i�X��ʪ���Χ���i���E�_�M������k�C</span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;�~�������ζ��]�A��¾�~��v�B��¾�~��v�B��|�~��v�B����~��v�B�~��t��߾ǥͤΧ޳N�U�z�C�~�������A�ȥثe���@��~��/�n��´�~��B����/��έ��إ~��B����B����B�~�F�~��B���g��H�ΰw�b/���g�_����C</span></p>
			<p>
			<span style="font-size:16px;">�� �@��~��/�n��´�~��Ѹ�`�~��v�a��A�E�v�ݵġB���ġB�c���t�ε��~��e�f�C</span></p>
			<p>
			<span style="font-size:16px;">�� ����/��έ��إ~��ѱб��~��v�a��A�E�v����B���`�e�f�H�γж˭��ءC</span></p>
			<p>
			<span style="font-size:16px;">�� ����ѱб��~��v�a��A�E�v�@�벴��e�f�H�β�����E�f�ҡA���ѱM�~�E�_�Ϊv���C</span></p>
			<p>
			<span style="font-size:16px;">�� ���g��ѯ��g�M���~��v�a��A�E�v���B��աB�P�䯫�g�Φ٦ׯe�f�C</span></p>
			<p>
			<span style="font-size:16px;">�� �w�b/���g�_����Ѹg��ڻ{�Ұw�b�~��v�a��A�H���~��覡�B�z���ȯf�Ҥίk�h�v���C</span></p>
			<p>
			<strong style="color: rgb(0, 0, 255); font-size: 18px;">�ѡB�A�ȶ���</strong></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">�����A��</span></strong></span></p>
			<p>
			<span style="font-size:16px;">���{�������G�g�@�ܶg���G�W��8�G30��11�G00�A</span><span style="font-size:16px;">�U��1�G30��3�G30�C</span></p>
			<p>
			<span style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �g���B��ΰ�w���餣�ݶE�C</span></p>
			<p>
			<span style="font-size:16px;">����E�����G�й}�D��a�}�D�������ҥ�A�p���a�ҥ�A��ú�ǫO�Ҫ�500���]�Щ�@�Ӥ뤺��a�ҥ��|�A�粦���k�٫O�Ҫ��~�A�_�h�������z�����C</span></p>
			<p>
			<span style="font-size:16px;">���w�������G�м��G02-2739-6828#1120�A1140��1141�A�ȱ����ƶE�Φ۹j��_�u��P���v�������E�w�������C�w���W�Ȫ��E�̡A�Щ�W��10�G30�e�󱾸��d�O��������F�w���U�Ȫ��E�̡A�Щ�U��15�G00�e�󱾸��d�O��������C</span></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">�p�ʪ���B�X�|�A��</span></strong></span></p>
			<p>
			<span style="font-size:16px;">�� ��|�]�����q�f��(����)�P�j���f��(�@��)�A�G�Q�|�p�ɬҦ����@�H���ȯZ�C</span></p>
			<p>
			<span style="font-size:16px;">�� �ʪ���|�e�����g���|�E�v��v�E�_�A�ö}��u��|�q����v�A�̳W�wú��w�I���A������|�����A�ѥ��|�����H�����P�ܦ�|�ǳ���C</span></p>
			<p>
			<span style="font-size:16px;">�� ��|�����A�C�g�ݵ��b�@���C</span></p>
			<p>
			<span style="font-size:16px;">�� �X�|�e���g�E�v��v�{�i�A�}��X�|�q����A��ú�M�O�ο짴�����A�̳�ܦ�|�Ǳ��ʪ��X�|�C</span></p>
			<p>
			<span style="font-size:16px;">�� �����@�f���������ҫ~��P�w���Ҷq�A���W�w�ɶ��~�A��l�ɶ����}�񱴯f�C</span></p>
			<p>
			<span style="color:#800000;"><strong><span style="font-size: 16px;">���a���ɪA��</span></strong></span></p>
			<p>
			<span style="font-size:16px;">&nbsp; &nbsp; &nbsp; &nbsp; ���|�a�U�j���|ĳ�U�i�e�Ǩ�ʤH�A�����q����v���B�ۿO���B�q�Ʊоǳ]�Ƥή�W���J���A�Q���A�X�оǤ����y��z�A�Ա��Ь��`�ȫǡ]02-2739-6828#3110��3110�^</span></p>

		</td>
		</table>
	
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>