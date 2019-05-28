$(document).ready(function(){
	
	var $header = $('header');          
          var isTop = 'home-top';
          $(window).on('scroll', function() {
            if ($(window).scrollTop() === 0) {
              $header.removeClass(isTop);
            } else {
              $header.addClass(isTop);
            }
          });		  
	var aa=$(".gnb").is(":visible");
	console.log(aa);
	$(".menu-toggle-btn").click(function(){
		$(".gnb").stop().slideToggle("fast");
	});	
	        var swiper = new Swiper({		
            el: '.swiper-container',			
            initialSlide: 3,
			spaceBetween: 30,			
            slidesPerView: 1,
            centeredSlides: true,
			effect: 'fade',
			/* 전환 시 페이드효과 */			
			loop: true,
            slideToClickedSlide: true,	
            grabCursor: true, 
			/* 마우스클릭으로 이동중 모션을 잡고있을수있음 */
			// direction: 'vertical',
			 /* 상하로 움직이기 */			 
			 autoplay: {
				delay: 2200,
				disableOnInteraction: false,
				/* 사용자 조작 시 자동플레이를 멈추는기능 */
			},			 			 
            scrollbar: {
              // el: '.swiper-scrollbar',
			  /* 하단에 이동위치 막대가 표시됨 */
			  // hide: true,
			  /*이동 하고 나서 잠시후 막대가 사라짐 */
            },			
            mousewheel: {
              enabled: true,
            },			
            keyboard: {
              enabled: true,
            },			
            pagination: {
              el: '.swiper-pagination',
			  dynamicBullets: true,
			  type: 'progressbar',
			  /* 다이내믹한 모양 */
			  /* 기존 블릿이 없어지고 상단에 진행바가 나타남 */
			  clickable: true,
			  /* 블릿 클릭시 해당페이지로 이동 */
            },			
            navigation: {
              nextEl: '.swiper-button-next',
              prevEl: '.swiper-button-prev',
            },
			/* 패럴렉스 효과도 할 수 있음. 인터넷페이지 참조 */
        });
});