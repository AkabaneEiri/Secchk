var idcheck2=false; //아이디 중복확인
var keycheck2=false; //인트라넷 인증키 확인

$(document).ready(function() {
	
	$("form[name=keyCheck]").submit(function() { 
		
		var ID = $("input[name=srvno]");
		var key = $("input[name=certno]");
				
		if (ID.val() == null || ID.val() == "") {
			alert('군번을 입력하십시오.');
			$("#srvno").focus();
			return false;
		} else if ($("#idcheck font").attr("color") == "red") {
			
			alert("이미 가입된 군번입니다.")
			$("#srvno").focus();
			return false;
			
		} else if (key.val() == null || key.val() == "") {
			alert('현역실명인증 키를 입력하십시오.');
			$("#certno").focus();
			return false;
		} else if (key.val().length != 6) {
			alert('현역실명인증 키는 6자리이어야 합니다.');
			$("#certno").focus();
			return false;
		}
			
	})
	
	$("#srvno").keyup(function(event){
	    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	        var inputVal = $(this).val();
	        $(this).val(inputVal.replace(/[^0-9-]/gi,''));
	        $(this).val($(this).val().replace(/[\{\}\[\]\/?.,;:|\)*~`!^\_+<>@\#$%&\\\=\(\'\"]/gi,''));
	    }
	});

	$("#certno").keyup(function(event){
	    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	        var inputVal = $(this).val();
	        $(this).val(inputVal.replace(/[^0-9]/gi,''));
	        $(this).val($(this).val().replace(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi,''));
	    }
	});	
	
//	$("input, #join, #idcheck").width(document.body.clientWidth*0.8);
//	
//	$("#srvno").on("change paste keyup",function(){
//		$.ajax({
//			url : "../jsp/id_check.jsp",
//			type : "post",
//			data : ({ID : $("input[name=srvno]").val()}),
//			success : function(data) {
//								
//				if (jQuery.trim(data) == 'NO') {
//					$('#idcheck').html("<font color=red>이미 가입된 군번입니다.</font>");
//					$('#idcheck').show();
//					idcheck2=false;
//				} else if(jQuery.trim(data) == 'RETIRE'){
//					$('#idcheck').html("<font color=red>전역한 군번입니다.</font>");
//					$('#idcheck').show();
//					idcheck2=false;
//				} else if(jQuery.trim(data) == 'YES'){
//					$('#idcheck').html("<font color=blue>사용 가능한 군번입니다.</font>");
//					$('#idcheck').show();
//					idcheck2=true;
//				} else {
//					$('#idcheck').hide();
//				}
//			}
//		});
//	});
	
});