$(document).ready(function() {	

	var MouseEventObj = new Object();

	function addEvent(obj, evt, fn) {
		if (obj.addEventListener) {
			obj.addEventListener(evt, fn, false);
		} else if (obj.attachEvent) {
			obj.attachEvent("on" + evt, fn);
		}
	}

	addEvent(document, "mouseout", function(e) {
		e = e ? e : window.event;
		var from = e.relatedTarget || e.toElement;
		if (!from) {
			MouseEventObj.clientX = e.clientX;
			MouseEventObj.clientY = e.clientY;
		}
	});

	addEvent(document, "mouseover", function(e) {
		e = e ? e : window.event;
		var from = e.relatedTarget || e.toElement;
		if (from) {
			MouseEventObj.clientX = e.clientX;
			MouseEventObj.clientY = e.clientY;
		}
	});

	window.onbeforeunload = function(e) {
		e = e ? e : window.event;
		if (e.clientX && e.clientY) {
			MouseEventObj.clientX = e.clientX;
			MouseEventObj.clientY = e.clientY;
		}

		if ((MouseEventObj.clientY < 0)
				|| (e.altKey)
				|| (e.ctrlKey)) {
			console.log(MouseEventObj);
			
			$.ajax({
				url : "logoutAjax.do", // request보낼 경로
				type : "post", // 메소드(post로 적용)
				data : {}, // 보낼 데이터
				contnetType : "application/json; charset=utf-8",
				success : function() {}, // 성공 시 값 보낼 펑션
				error : function() {}
			});
		}
	}
})