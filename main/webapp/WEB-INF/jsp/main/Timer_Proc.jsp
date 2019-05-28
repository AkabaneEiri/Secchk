<%
Object obj = session.getAttribute("loginedUser");
String athrt = "";
if(session.getAttribute("SS_ATHRT") != null) {
	athrt = ( (String) session.getAttribute("SS_ATHRT") ).trim();
}

if(obj != null) {
	if(athrt.equals("B2")) {
%>
<script>
$(document).ready(function(){
	
	var loginDate = <%=session.getAttribute("loginDate")%>;
	//console.log("loginDate : " + loginDate);
	
	var timer = setInterval(function() {		
		
		var now = new Date().getTime();
		//console.log("now : " + now);
		
		var distance = 0;
		
		if (sessionStorage.getItem("curTime") == null)
			distance = now - loginDate;
		else
			distance = now - sessionStorage.getItem("curTime");
		
		//console.log("dis : " + distance);
		
		if (distance > 600000) {
			////Ajax////
			var obj = new Object(); //컨트롤러의 리턴값을 저장할 객체

				$.ajax({
					url:'./InfomationAjax.do', //request 보낼 서버의 경로
					type:'post', // 메소드(get, post, put 등)
					data: {}, //보낼 데이터
					success: function(result) {
						//서버로부터 정상적으로 응답이 왔을 때 실행 
						obj = jQuery.parseJSON(result);
						if(obj.cntRqstUgcy == 0 && obj.cntRqstAct == 0 && obj.cntRqstChk == 0) {
							console.log("Ajax No Data.");
						}else{
							window.open("Infomation.do", "popup", "width=500, height=250, resizable=no");
							sessionStorage.setItem("curTime", now);
						}
					},
					error: function(err) {
						//서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
						console.log("ajax : false");
					}
				});
			////Ajax finish ////
		}
	}, 1000)
});
</script>
<%
	}
}
%>