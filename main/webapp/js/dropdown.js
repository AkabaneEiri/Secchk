$(document).ready(function(){
	if(window.innerWidth >= 1100) {
		$("#top_nav").removeClass("gnb");
		$("#top_nav").addClass("top-menu");
		$("#top_nav").css({
			display: "block"
		})
	}
	else {
		$("#top_nav").removeClass("top-menu");
		$("#top_nav").addClass("gnb");
		$("#top_nav").css({
			display: "none"
		})
	}
	
	$(window).resize( alterMenuCSS );		
})

function alterMenuCSS() {
	if (window.innerWidth >= 1100) {
		$("#top_nav").removeClass("gnb");
		$("#top_nav").addClass("top-menu");
		$("#top_nav").css({
			display: "block"
		})
		
		$(".submenu-open").removeClass("submenu-open");
		$(".sub-menu").css({ display: "none" })
		
	} else {
		$("#top_nav").removeClass("top-menu");
		$("#top_nav").addClass("gnb");
		$("#top_nav").css({
			display: "none"
		})
		
		$(".submenu-open").removeClass("submenu-open");
		$(".sub-menu").css({ display: "none" })
	}
}