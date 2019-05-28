$(function(){
	
	$(".loginBg img").height(document.body.clientHeight).css("width",document.body.clientWidth);	
	$(".login .id").width(document.body.clientWidth*0.8);
	$(".login .password").width(document.body.clientWidth*0.8);
	$(".login #login, .login #join").width(document.body.clientWidth*0.8/2-15);
	

	$("#srvno").keyup(function(event){
	    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	         var inputVal = $(this).val(); 
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