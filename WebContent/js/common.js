
$(function(){
	$("#topBtn").click(function(){
		scrollTop(0);
		return false;
	});
});

var loading_msg = '<img src="../images/_tw/loading.gif">&nbsp;&nbsp;資料處理中....';
var loading_img = '<img src="../images/_tw/vc_loading.gif">';
var loading_bar = '<img src="../images/_tw/loadingAnimation.gif">';
var waitInfo = "正在检查中...";

var msgDlg = '<div id=\"contentDiv\"><div id=\"closeBtn\"></div><div id=\"msgArea\"></div><div id=\"subBtn\"></div></div>';

function Id(id) {
	return document.getElementById(id);
}
//首字母大寫
function upperFirst(word)
{
	return word.substring(0,1).toUpperCase( ) + word.substring(1);
}
//轉換成小寫
function lowerWord(word)
{
	return word.toLowerCase ();
}
//轉換成大寫
function upperWord(word)
{
	return word.toUpperCase ();
}

function getCheckVal(objname, flag)
{
	var objs = document.getElementsByName(objname);
	var len = objs.length;
	var val = '';
	var selObj;
	for (var i=0; i<len; i++) {
		if (objs[i].checked) {
			selObj = objs[i];
			val = objs[i].value;
			break;
		}
	}
	if (flag) {
		return selObj;
	}
	return val;
}

function isInt(val) {    
	var reg = /^[0-9]*$/;    
	return reg.test(val);    
} 

/******************************************************************************
 * 重刷認證碼
 *****************************************************************************/
function reset_code(){
	$.ajax({
		url: "../lib/index_ajax.php",
        type: "POST",
        data: {
			action: "reset_code"
		},
		dataType: "json",
        success: function(data) {
			$('#code_img').attr("src","../lib/authimg.php?code_src="+data["code"]);
			$('#code_img').attr("alt",data["code"]);
			$('#code_img').attr("title",data["code"]);
			$('#ck_code_src').val(data["code"]);
        }
	});
}

/******************************************************************************
 * 背景遮罩函式
 *****************************************************************************/
//建立遮罩
function CreateMaskDIV(){
	$("body").css("overflow","hidden");
	$("body").height($(window).height());
	$.fn.dialog=function(){
		this.MaskDiv=function()
		{
			//創建遮罩背景
            $("body").append("<div ID='MaskDiv'></div>");
            $("body").find("#MaskDiv").width($("body").width()).height($("body").height()).css({position:"absolute",filter:"alpha(opacity=80)",MozOpacity:"0.90",top:$().scrollTop(),left:$().scrollLeft(),zIndex:"10000"});
		}
		this.MaskDiv();
	}
	$("body").dialog();
	$("#MaskDiv").append('<div id="dvUpdate" class="t43" style="width:350px;margin-left:'+((Number($(window).width()) / 2)-175)+'px;margin-top:'+((Number($(window).height())/2)-100)+'px; border: 1px #F28B84 solid;background-color:#FFFFFF;"></div>');
} 
//刪除遮罩     
function closeMaskDIV(){
	$("#MaskDiv").remove();
	$("body").css("overflow","auto");
}

/******************************************************************************
 * 背景遮罩函式-搭配 box_css 使用
 *****************************************************************************/
$.clientCoords = function() {
	var dimensions = {width: 0, height: 0};
	if (document.documentElement) {
		dimensions.width = document.documentElement.scrollWidth;
		dimensions.height = document.documentElement.scrollHeight;
	} else if (window.innerWidth && window.innerHeight) {
		dimensions.width = window.innerWidth;
		dimensions.height = window.innerHeight;
	}
	return dimensions;
}	
showFadeUp = function ( elm ) {
	//$(".modalHide").hide();
	$("#fadeUpContent").removeClass();
	$("#fadeUpContent").addClass("fadeUp");		
	if(elm == ""){
		var ctntDiv = $(this).attr("id");
	}else{
		var ctntDiv = elm;
	}
	$(".fadeUp").hide();
	$("#fadeContainer").hide();
	$("#fadeContainer").addClass("transparent");
	$("#fadeContainer").css("width", $.clientCoords().width);
	$("#fadeContainer").css("height", $.clientCoords().height);
	$("#fadeContainer").show();
	setPosition(ctntDiv.replace(/Link/,"Content"));
	ctntDiv = "#" + ctntDiv.replace(/Link/,"Content");
	//$(ctntDiv).css("left", ($.clientCoords().width - $(ctntDiv).width())/2);
	$(ctntDiv).fadeIn("slow");
	return false;
}
hideFadeCtn = function(){
	//$(".modalHide").show();
	$("#fadeContainer").hide();
	$("#fadeContainer").removeClass();
	//$("#fadeCtn").fadeOut("slow");
	$('.fadeUp').fadeOut("slow")	
	return false;
}
function addEvent(elm, evType, fn, useCapture){
	//x-browser event handling function written by Scott Andrew
	if(elm.addEventListener){
		elm.addEventListener(evType, fn, useCapture);
		return true;
	}else if(elm.attachEvent){
			var r = elm.attachEvent('on' + evType, fn);
			//EventCache.add(elm, evType, fn);
			return r;
	}else{
			elm['on' + evType] = fn;
	}
}
function addClass(el, className){
	if (hasClass(el, className)) { return; } // already present
	el.className = [el.className, className].join(' ');
}
function hasClass(el, className){
	var re = new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)');
    return re.test(el.className);
}

function setPosition(posObj, setY) {	
    var yScroll;
	if (self.innerHeight) {
		windowHeight = self.innerHeight;
	}	else {
		windowHeight = document.documentElement.clientHeight;
	}
	if (! setY) {
		if (self.pageYOffset) {
			yScroll = self.pageYOffset;
		} else if (document.documentElement && document.documentElement.scrollTop){// Explorer 6 Strict
			yScroll = document.documentElement.scrollTop;
		} else if (document.body) {
			  yScroll = document.body.scrollTop;
		}
	} else {
		yScroll = 0;
	}
	var posX = (parseInt(document.documentElement.clientWidth) - parseInt($("#"+posObj).width()))/2;
	var posY = yScroll + (parseInt(windowHeight) - parseInt($("#"+posObj).height()))/2;
	if(parseInt(posX) < 0) posX = 50;
	$("#"+posObj).css({left: posX + 'px'});
	if(parseInt(posY) < 0) posY = 50;
	$("#"+posObj).css({top: posY + 'px'});
}

//等比縮圖
var reSizeImage = function(ow, oh, max_x, max_y ){

	var attr = {
		width: 0,
		height: 0,
		fix: 0,
		fixleft: 0
	};

	if (ow > oh && ow > max_x){
		attr.width = max_x;
		attr.height = widthChanged(attr.width,ow,oh);
	}else if (oh > ow && oh > max_y){
		attr.height = max_y;
		attr.width = heightChanged(attr.height,ow,oh);
	}else if (attr.width == 0 && attr.height == 0){
		attr.height = ow;
		attr.width = oh;
	}

	if ((max_y-attr.height) >0){
		attr.fix = Math.round((max_y-attr.height)/2);
		if (attr.fix < 0){
			attr.fix = 0;
		}
	}else{
		attr.fix = 0;
	}

	if ((max_x-attr.width) >0){
		attr.fixleft = Math.round((max_x-attr.width)/2);
		if (attr.fixleft < 0){
			attr.fixleft = 0;
		}
	}else{
		attr.fixleft = 0;
	}

	return attr;

}
var widthChanged = function(setwidth,originalWidth,originalHeight) {
	if (!isInt(originalWidth)) originalWidth = 0;
	if (!isInt(originalHeight)) originalHeight = 0;
	if (!isInt(setwidth)) setwidth = originalWidth;
	return Math.round(setwidth / originalWidth * originalHeight);
};
var heightChanged = function(setheight,originalWidth,originalHeight) {
	if (!isInt(originalWidth)) originalWidth = 0;
	if (!isInt(originalHeight)) originalHeight = 0;
	if (!isInt(setheight)) setheight = originalHeight;
	return Math.round(setheight / originalHeight * originalWidth);
};

function ShowMsg(t, msg, u, o){
	
	if(t == 'msg'){
		if(o != "") $('#'+o).html(msgDlg);
		$('#closeBtn').show();
		if(u != "")
			$('#subBtn').html('<input type="button" class="icon" value="確定" onClick="location.href=\''+u+'\'">');
		else
			$('#closeBtn').html('<input type="button" class="icon" value="關閉" onClick="hideFadeCtn();">');
		$('#msgArea').html(msg);
	}else if(t == 'url'){
		if(msg != "") alert(msg);
		location.href=u;
	}
}

//iframe
var IframeForm = {
	create: function () {
		var iframeObj;
		var ifName = "ifpost";
		var tempDiv = document.createElement('div');  
		tempDiv.style.display = "none";
		document.body.appendChild(tempDiv);   
		tempDiv.innerHTML='<iframe id="'+ifName+'" name="'+ifName+'" style="display:none;"></iframe>';   
		return ifName;
	}
}

//置頂
function scrollTop(moveTo){
	body=(window.opera)? (document.compatMode=="CSS1Compat"? $('html') : $('body')) : $('html,body');
	body.animate({"scrollTop": moveTo}, 500);
}
/******************************************************************************
 * 檔案下載數
 *****************************************************************************/
function dl_download(id,o,dir){

	$.ajax({
		url: "../lib/index_ajax.php",
        type: "POST",
        data: {
			action: "dl_download",
			id: id
		},
		dataType: "json",
        success: function(data) {
			if(data.result == 'true'){
				$("#dl_text"+id).html(data.dlTime);
			}
        }
	});
}