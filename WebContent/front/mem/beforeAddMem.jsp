<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|����Ʒs�W - addMem.jsp</title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath()%>/images/icon_1r_48.png" />
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
	
	function check(){
		var agree = document.getElementById("agree");
		var form1 = document.getElementsByName("form1")[0];
		if(agree.checked == false){
			alert("���Ŀ�P�N����");
		}else{
			form1.submit();
		}
	}
</script>

</head>

<body bgcolor='white'>
	<div id="wrapper">
  		<div id="main_bg">
    	<div id="main_bg01">
        	<div id="apDiv13"></div>
			<%@ include file="/header.jsp" %>
			<%@ include file="/ad.jsp" %>   
    	
  		</div>
		</div>
	<div align='center' width='400'>
	<textarea rows="35" cols="70">
�˷R�� �Τ�z�n�G

�b�A�����ϥ�[iPET Cares](�H�U²��[iPET Cares]�����v)�e�A�Х��Ծ\���ϥΪ̪A�ȱ��ڡ]�H�U�٬��u���A�ȱ��ڡv�^

�����ڤ��ت��A�b��ɤO�O�@�|���b�ϥΥ����A�Ȯɪ��v�q�A�ýT�{�����P�|���������v�Q�q�����Y�A�����ϥα���

��z�ϥΥ��A�ȱ��ڮɡA�Y��ܱz�w�\Ū�B�A�ѨæP�N���A�ȱ��ڤ��Ҧ����e�P���w�A�ç����������A�Ȳ{���P���ӭl�ͪ��A�ȶ��ؤΤ��e�C

�������v�����ɶ����ݭn�ӭ׭q���ܧ󥻪A�ȱ��ڤ����e�A�è��N���e�����e�A�ק�ᤧ�A�ȱ��ڱN�|���i�󥻯��A�����t��q���z�A��ĳ�z�H�ɪ`�N�ӵ��ק���ܧ�C

�Y�z�����ק���ܧ󤧫��~��ϥΥ��A�ȡA�Y�����z�w�g�\Ū�B�A�ѥB�P�N�����ק���ܧ�ᤧ�A�ȱ��ڤ��e�C

�Y�z���P�N�W�z�A�ȱ��ڭק��ܧ�覡�A�Τ��������A�ȱ��ڥ��@���ڡA�z���ߧY����ϥΥ��A�ȡC

�p�G�z�����G�Q���A�����ŦX�W�z�W�w�~�A������z���a��(�κ��@�H)�\Ū�B�A�ѨæP�N���A�ȱ��ڤ��Ҧ����e�Ψ��ק��ܧ�A�~�i�H���U�|���B�ϥΩ��~��ϥΥ��A�ȡC

���U�q��

�z�P�N�ëO�ҥH�U�ƶ��G

�̥��������U���ܡA���ѥ��T���ӤH��ơ]�H�U²�١u���U��ơv�^�A�B�L���е��U���Ʊ��C

�Y�z���ѥ�����~�B����B�L�ɩΤ�����Ψ�~�ɩʪ���ơF�Ϊ̥������z���h�ëe�z��Ƭ����~�B����B�L�ɩΤ�����Ψ�~�ɩʪ��A�������v�Ȱ��βפ�z���b���A�éڵ��z��{�b�M���ӨϥΥ����������Υ��󳡤��C

�z�P�N�ҵn���ίd�s���ӤH��ơA�����i�H�b���n�X�z�d��Φ��U�󥻯��A�Ȥ����ѡA �`���B�B�z�B�O�s�B�ǻ��Ψϥ� �z�����U��ơ]�]�A�������󴣨Ѥ��F�������B�q�kĵ��ΦX�@�t�ӡ^�C ���p�v�F�� �������z�ҵn���ίd�s���ӤH��ơA
�b����o�z���P�N�H�e�A���Ȩѥ����Υ��������~�ȦX�@�٦��䤺���B�̷ӭ�өһ������ϥΥت��M�d��[�H�ϥΡA���D�ƥ������B�Ψ̷Ӭ����k�߳W�w�A�_�h�������|�N�z���ӤH��ƴ��ѵ��ĤT�H�B�β��@��L�ت��ϥΡC
�����|���w�ɳq���|���Ҧb��쪺���󥻪A�Ȥ��̷s�����]�]�t�������ʡB�P�P��׵��^�A�|���i��ܩڵ��C �z�P�N�b �U�C�����p�U�A�������i��|���ѱz���ӤH��Ƶ����������A�ΥD�i���v�Q���I�`�ô��ܥq�k����������󤧲ĤT�H�G 
���k�ߤ��W�w�B�Ψ��q�k�����P��L���v�������k�w�{�Ǥ��n�D�F �b��污�p�U�����@��L�|���Ψ�L�ĤT�H���X�k�v�q�F �����@�|���A�Ȩt�Ϊ����`�B�@�F �|���z�L���A�ȻP����ӫ~�B�ʶR�ӫ~�B�I���ث~�A�]�Ӳ��ͪ����y�B���y���n��T�F 
�ϥΪ̦�����H�ϬF���k�O�Υ��ϥα��ڤ����ΡC �|���b���B�K�X�Φw�� �P�N���@�z���b���αK�X�����K�w���A�z�|�b�C���s�u������A�n�X�ð���ϥΡC��z�o�{�z���b���αK�X�D��s�ΩΦw�����D�o�ͮɡA�z�|�ߧY�q�������C
�p�G�����L�k���ѨϥιL�{�O�_�����H�ϥΤ����ΡA�����惡�ҭP���l�`�A�����t�d�C �z�P�N�z���b���B�K�X�η|���v�q���Ȩѱz�ӤH�ϥΡA���o��ɡB�����L�H�λP�L�H�@�ΡC �ϥΪ̳W�d�P�欰 �z�P�N���_�ΥL�H�W�q�ϥΥ����C 
�z�P�N���ѻP����D�k�ت��ΥH����D�k�覡�ϥΥ����A�ÿ�u��a�����k�W�κ��ں����ϥγW�d�C �z�P�N���|�Q�Υ����i������B�V�d�B�¯١B�T���B�z�Z�B����B�H�ϹD�w�P���@���Ǥ���r�B�Ϥ��Υ���Φ����ɮש󥻯��C 
�z�P�N���Ǽ�����q���f�r�ί}�a�ʵ{���X�C �z�P�N���ϥδc�N�覡�i��ק�B�R���B�z�Z�����C �z�P�N���ǰe���󥼸g�\�i���ӷ~��Ʃμs�i����������L �ϥΪ̡C �z�P�N��u���z�]���v�A�����N�ϥΥ��g���v���Ϥ��Υ���Φ����ɮש󥻯��C 
�z�P�N���w�ۨϥΡB�ק�B���s�B�����B�o��B�i���٭�u�{�B�ѽs�ΤϦV��Ķ �����W���{���H�ΩҦ����e�A�]�A��������ۧ@�B�Ϥ��B�ɮסB�����[�c�B�����]�p���A���ѥ����Ψ�L�v�Q�H�̪k�֦��䴼�z�]���v�A�]�A��������Ӽ��v�B�M�Q�v�B
�ۧ@�v�P�M���޳N���C ������ƤޥΤ��\��A�z�P�N�z���@�~�o�G��A�Y�P�N�L�H�ޥαz���@�~�ܥL�H�@�~���C��z�R���ӧ@�~��A�w�Q�ޥΪ��@�~�N�|�~��s�b�󥻯��C �Ч@�֦̾��ۧ@�����z�]���v�B��O�ۧ@�����L�H�ϵۧ@�v�k�Ψ䥦�k�W�C
���o�Ч@�̳\�i�A�z���o���ק�B���s�B��K���������Ʃ���Q�欰�C�Y�z���A�ΫI�v�����ơA�������v����z�ϥ� �����A�è����|�����C ��H�ϥ��A�ȱ��ڦ欰���B�m �z�P�N�����o�̨�P�_�û{�w�z�w�g�H�ϥ��A�ȱ��ڤ����ơA
�������v�פ�έ���z�ϥΥ��A�ȡA�è����|�����C �z�P�N�����i�L����N�z���@�~�U�[�C �K�d�ө� �����o�ͨt�Τ��_�A�]�A��������O�i�B�I�u�B�ɯšB�G�٩ΤѨa���i�ܤO���]���A�]�\�|�y���z����ƿ򥢡B�ϥΤ��K�Ψ�L�l���A
�����N���I������v�P�d���C�бz�ϥΥ����ɡA�ۦ�Ĩ��ƥ����I�C �������O�Ҩt�ε{�����o�Ϳ��~�λ�ê�����ơA�ϥΪ̦ۦ�Ĩ��ƥ����I�C ���������ɨ�A�L�׬O�_�����i�άO�q���ϥΪ̡A�����v�ק�βפ����A�ȶ��ءA�H�ΰ���ϥΪ̨ϥ��v�Q�C
</textarea>
</div>	
<div align='center'>
	<input type="checkbox" name="agree" id="agree" >�w�A�ѤW�z���e�A�åB�P�N��u�Ҧ��W�w
</div>
<div align='center'>
	<form method="post" action="<%= request.getContextPath()%>/front/mem/addMem.jsp" name="form1">
		<input type="button" value="�e�X" onclick="check()">
	</form>
</div>
		
	<div id="main_bg02"></div>
	<%@ include file="/footer.jsp" %>
	</div>
</body>
</html>