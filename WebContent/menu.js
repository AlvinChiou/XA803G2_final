
$(function(){
	$('#menu').children('li').click(function(){
		$('#menu').find('ul').removeClass('active');
		$(this).find('ul').toggleClass('active');	
	});
});
