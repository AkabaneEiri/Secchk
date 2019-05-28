jQuery(document).ready(function($){	

	$(".header .logo").css("opacity","1");
	
	$("#tabmenu1 dd:not(:first)").css("display","none");
	$("#tabmenu1>li:first").addClass("on");
	$("#tabmenu1>li:first a").addClass("on");
		
	$("#tabmenu1>li").on("click", function(){
		$("#tabmenu1>li").removeClass("on");
		$("#tabmenu1>li>a").removeClass("on");
		$("#tabmenu1 dd:visible").hide();
		var index=$("#tabmenu1 li").index(this);
		var $dds = $("#tabmenu1").find("dd");
		$dds.eq(index).show();				
		
		$(this).addClass("on");
			$(this).children("a").addClass("on");
			return false;
		});
	
	//open (or close) submenu items in the lateral menu. Close all the other open submenu items.
	$('.item-has-children').children('a').on('click', function(event){
		event.preventDefault();
		$(this).toggleClass('submenu-open').next('.sub-menu').slideToggle(200).end().parent('.item-has-children').siblings('.item-has-children').children('a').removeClass('submenu-open').next('.sub-menu').slideUp(200);
	});
});
