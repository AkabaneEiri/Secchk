// login form check function
$(function(){
	
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