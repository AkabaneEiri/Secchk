var idcheck2=false; //아이디 중복확인
var keycheck2=false; //인트라넷 인증키 확인

window.addEventListener("load", function() {
	setTimeout(scrollTo, 0, 0, 1);
}, false); // url 바 숨기기

function chkPwd(str, name, name1){
	 var pw = str;
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|\\\'\";:\/?]/gi); 
	
	 if(pw.length < 9 || pw.length > 12){
		 alert("["+ name +"] 9자리 ~ 12자리 이내로 입력해주십시오.");		  
		 $("input[name=" + name1 + "]").focus();		  
		 return false;
	 }
	 
	 if(pw.search(/\s/) != -1){
		 alert("["+ name +"] 공백없이 입력해주십시오");
		 $("input[name=" + name1 + "]").focus();	  
		 return false;
	 }
	
	 if(num < 0 || eng < 0 || spe < 0 ){
		 alert("["+ name +"] 한·영, 숫자, 특수문자가 혼합된 9자리 이상이어야 합니다.");
		 $("input[name=" + name1 + "]").focus();
		 return false;
	 }
	 
	 for(var i=0; i<pw.length-2; i++) {
		 
		 var fc = pw.charAt(i);
		 var cnt = 0;
		 
		 if(pw.charAt(i+1) == fc) {
			 
			 cnt = cnt + 1;
			 
		 }
		 
		 if (pw.charAt(i+2) == fc) {
			 
			 cnt = cnt + 1;
			 
		 }
		 		 
		 if(cnt == 2) {
			 
			 alert("["+ name +"] 동일한 문자를 3회 연속된 비밀번호는 사용하실 수 없습니다.");
			 
			 $("input[name=" + name1 + "]").focus();
			 return false;
			 
		 }
		 
	 }
	 
	 return true;	 
}

$(document).ready(function() {
	
	$("#cancel").click(function() {
		location.href="login.do"
	});
	
	$("form[name=form]").submit(function(){
		
		var password = $("input[name=password]").val();
		var password_check = $("input[name=password_check]").val();
		var rank = $("select[name=rank]").val();
		var name = $("input[name=name]").val();

		if ($.trim(password) == "") {
			alert('비밀번호를 입력하세요');
			document.form.password.focus();
			return false;
		}
		else if(chkPwd($.trim(password),"비밀번호", "password") && chkPwd($.trim(password_check),"비밀번호 확인", "password_check"))
		{
			if(password != password_check)
			{
				alert("비밀번호와 비밀번호 확인이 동일하지 않습니다.");				
				return false;
			}
			
			if (rank == null || rank == "") {
				alert('계급을 선택하세요');
				document.form.rank.focus();
				return false;
			}
			
			if ($.trim(name) == "") {
				alert('이름을 입력하세요');
				document.form.name.focus();
				return false;
			}
			
		} else {
			return false;
		}
		
	})
	
	$("input, select, #join").width(document.body.clientWidth*0.8);
	
});