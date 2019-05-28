$(function(){
	
	$(".loginBg img").height(document.body.clientHeight).css("width",document.body.clientWidth);	
	$(".login .id").width(document.body.clientWidth*0.8);
	$(".login .password").width(document.body.clientWidth*0.8);
	$(".login #login, .login #join").width(document.body.clientWidth*0.8/2-15);
	

	$("#srvno").keyup(function(event){
	    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	         var inputVal = $(this).val(); 
	       // $(this).val(inputVal.replace(/[^0-9-]/gi,''));
	        $(this).val($(this).val().replace(/[\{\}\[\]\/?.,;:|\)*~`!^\_+<>@\#$%&\\\=\(\'\"]/gi,''));
	    }
	});
	
	$("#join").click(function() {
		location.href="key_check.do";
	});
	
var flag = 0;
	
	$("form[name=login]").submit(function() {

		if(flag == 0) {
			
			var srvno = $("input[name=srvno]").val();
			var pw = $("input[name=pw]").val();
			
			
			
			if($.trim(srvno) == '')
			{
				alert("아이디를 입력하십시오.");
				$("input[name=srvno]").focus();
				return false;
			}
			else if($.trim(pw) == '')
			{
				alert("비밀번호를 입력하십시오.");
				$("input[name=pw]").focus();
				return false;
			}
			
			$.ajax({
				url : "loginAction.do",
				type : "post",
				data : {srvno:srvno, pw:pw, user_dvs_cd:user_dvs_cd},
				dataType : "json",
				success:function(result) {		
										
					if(result.code == 0)
					{
						location.replace("main.do");
					}
					else if(result.code == 1)
					{
						alert("로그인 5회 실패로 해당계정 접속이 제한됩니다.");
					}
					else if(result.code == 2)
					{
						alert("아이디(군번) 또는 비밀번호를 정확히 입력하십시오. \n\n <오류횟수:"+result.errnum+"회> \n\n 5회 이상이면 해당계정은 사용중지됩니다.");
					} else if (result.code == 2.5) {
						alert("비밀번호가 초기화 되었습니다. \n\n비밀번호 변경화면으로 이동합니다.");		
						
						flag = 1;
						
						$("form[name=login]").attr("action","userpwchng_keycheck.do").submit();
						
					} else if(result.code == 3)
					{
						alert("비밀번호 변경하신 후 30일이 경과했습니다. \n\n비밀번호 변경화면으로 이동합니다.");
						
						flag = 1;
						
						$("form[name=login]").attr("action","userpwchng_keycheck.do").submit();
												
					}
					else if(result.code == 5)
					{
						alert("비밀번호 변경하신 후 30일이 경과했습니다. \n\n비밀번호 변경하신 후 30일이 경과하면 비밀번호를 변경해야 합니다. \n\n다음 경과시 비밀번호 변경화면으로 이동되오니 이점 참고하시길 바랍니다.");
						
						flag = 1;
						
						$("form[name=login]").attr("action","login_numtm_update.do").submit();						
						
					}
					else if(result.code == 4)
					{
						
						location.replace("main.do");
						
					} else if(result.code == 6) {
						
						alert("계정 장기간(90일) 미사용으로 인해 로그인이 제한됩니다. \n\n 관리자에게 문의하십시오.");
						
					}
				},
				error:function(result) {				
					//alert("code : " + result.status + "\n messages:" + result.responseText+ "\n error: " + result.error);
					alert("통신 간에 오류가 발생했습니다. 관리자에게 문의하십시오.");
				}
			});			
			
			return false;
			
		} else {
			
			return true;
			
		}
				
	});
	
	$("input[name=srvno]").focus(function() { 
		$(this).css("border","1px solid #12dbe8");		 		
	})
	 
	$("input[name=pw]").focus(function() { 
		$(this).css("border","1px solid #00aaff");				
	})
		
	$("input[name=srvno], input[name=pw]").focusout(function() { 
		$(this).css("border","1px solid #cccccc"); 
	});
	
});


function onlyNum()
{
	if(event.keyCode >= 48 && event.keyCode <= 57) {
		event.returnValue=true;
	}
	else {
		event.returnValue=false;
	}	
}

function chkPwd(str, name){
	 var pw = str;
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|\\\'\";:\/?]/gi); 
	
	 if(pw.length < 9 || pw.length > 12){
	  alert("["+ name +"] 9자리 ~ 12자리 이내로 입력해주십시오.");
	  return false;
	 }
	 
	 if(pw.search(/\s/) != -1){
	  alert("["+ name +"] 공백업이 입력해주십시오");
	  return false;
	 }
	
	 if(num < 0 || eng < 0 || spe < 0 ){
	  alert("["+ name +"] 한·영, 숫자, 특수문자가 혼합된 9자리 이상이어야 합니다.");
	  return false;
	 }
	 
	 return true;	 
}