/*$(function(){

	var userAgent = window.navigator.userAgent;
	var mobileos = ["iPhone","iPod","Android","BlackBerry","Windows CE", "Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"];
	var j = -1;
	
	if(userAgent != "")
	{
		for(i=0; i<userAgent.length; i++)
		{
              if(userAgent.indexOf(mobileos[i]) > -1)
              {
              	j = 0;
              }         
        }         
         
        if(j == -1)
        {
        	alert('모바일만 접속 가능합니다.');
        	location.href = "http://www.army.mil.kr";
        }
	}	
})*/

function changeRegis(){  //회원가입페이지로

	$("html").addClass("ui-loading");
//	location.href='./key_check.jsp';
	location.href='./register.jsp';
}

function law(){
	
	$("html").addClass("ui-loading");
	location.href='http://mobile.law.go.kr';	
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
	  alert("["+ name +"] 공백없이 입력해주십시오");
	  return false;
	 }
	
	 if(num < 0 || eng < 0 || spe < 0 ){
	  alert("["+ name +"] 한·영, 숫자, 특수문자가 혼합된 9자리 이상이어야 합니다.");
	  return false;
	 }
	 
	 return true;
	 
}

function logInUser() {
	var user_id = $('#srvno').val();
	var user_pw = $('#pw').val();
	
	if(srvno == '') {
		alert("아이디를 입력하여 주십시오");
		return false;
	}
	
	if(pw == '') {
		alert('비밀번호를 입력하여 주십시오.');		
		return false;		
	}
	
	//user_pw = Sha256.hash(pw);	
	//user_id = aesHelper.encrypt(srvno);
	
	$.ajax({
		url : "loginAction.do",
		type : "post",
		data : {srvno:user_id, pw:user_pw},
		success:function(data) {
			var strAjax = "";
			var value = $.trim(data);
			
			if(value == "-1") {
				strAjax = "해당 군번은 사용자로 등록되어있지 않습니다.";
			}
			else if(value == "-2") {
				strAjax = "비밀번호를 확인하시길 바랍니다. \n(5회이상 오류시 로그인이 제한됩니다.)\n";
			}
			else if(value == "1") {
				strAjax = "1";
				location.href="main.jsp"; // 메인페이지 이동		
			}
			else if(value == "-3") {
				strAjax="비밀번호 5회 오류로 접속을 제한합니다.\n";
			}
			else if(value == "-4") {
				user_id = user_id.replace(/&/g,"!amp");
				user_id = user_id.replace(/#/g,"!sharp");
				user_id = user_id.replace(/%/g,"!percent");
				user_id = user_id.replace(/\\/g,"!backslash");
				user_id = user_id.replace(/\+/g,"%2B");
				user_id = user_id.replace("/&/g","%26");
				
				strAjax="비밀번호 변경일이 30일 지났습니다. \n비밀번호를 변경해주십시요.";
				alert(strAjax);
				location.href="pwdch.jsp?srvno=" + user_id; // 메인페이지 이동		
			}
			
			if(value != "0" && value != "-4") {
				alert(strAjax);
				return false;
			}
			$("#srvno").val("");
			$("#pw").val("");
		},
		error: function(request, status, error) {

			//$("html").removeClass("ui-loading");
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

		}
	});		
}

//로그아웃
function logOutUser() {
	if(confirm("로그아웃하시겠습니까?"))
	{
		$("html").addClass("ui-loading");
		$.ajax({
			url : "../WEB-INF/jsp/util/ajax_user.jsp?action=logout",
			type : "post",
			success:function(result) {				
				location.href="login.jsp"; // 로그인페이지 이동			 
			},
			error: function(request, status, error) {

				$("html").removeClass("ui-loading");
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

			}
		});
	}
}

//사용자 등록버튼 눌렀을 때 
function btnAddRecord()
{
	var srvno = $("#srvno").val(); //군번
	var pw = $("#pw").val(); //비밀번호
	var pwchk = $("#pwchk").val(); //비밀번호확인
	var email = $("#email").val(); //메일주소
	var fulnm = $("#fulnm").val(); //성명
	var hp = $("#hp").val(); //전화번호
	
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|\\\'\";:\/?]/gi); 
	var SamePass_0 = 0; //동일문자 카운트
	var SamePass_1 = 0; //연속성(+) 카운트
	var SamePass_2 = 0; //연속성(-) 카운트
	
	//아이디 체크
//	if(pw ==""){alert("아이디를 입력하세요."); return false}
//	else if(srvno.length < 5){alert("아이디는 5자 이상이어야 합니다."); return false}

	//비밀번호 체크
	if(pw ==""){alert("비밀번호를 입력하세요."); return false;}
	else if((pw.length < 9 || pw.length > 12)&&(num < 0 || eng < 0 || spe < 0 )){
		alert("비밀번호는 영문,숫자,특수문자 조합으로 9~12자리이어야 합니다.");
		$("#pw").val("");
		$("#pw").focus();
		return false;
	}
	else if(pw.search(/\s/) != -1){
		alert("비밀번호는 공백없이 입력해주세요.");
		return false;
	}
	
	for(var i=0; i < pw.length; i++){
		var char_pass_0;
		var char_pass_1;
		var char_pass_2;
		
		if(i>=2){
			char_pass_0 = pw.charCodeAt(i-2);
			char_pass_1 = pw.charCodeAt(i-1);
			char_pass_2 = pw.charCodeAt(i);
		
			//동일문자 카운트
			if((char_pass_0 == char_pass_1) && (char_pass_1 == char_pass_2)){
				SamePass_0++;
			}else{
				SamePass_0 = 0;
			}
				
			//연속성(+) 카운트
			if((char_pass_0 - char_pass_1) == 1 && (char_pass_1 - char_pass_2) == 1){
				SamePass_1++;
			}else{
				SamePass_1 = 0;
			}	
		
			//연속성(-) 카운트
			if((char_pass_0 - char_pass_1) == -1 && (char_pass_1 - char_pass_2) == -1){
				SamePass_2++;
			}else{
				SamePass_2 = 0;
			}		
		}
		
		if(SamePass_0 > 0){
			alert("비밀번호는 동일문자를 3자 이상 연속 입력할 수 없습니다.");
			return false;
		}
		
		if(SamePass_1 > 0 || SamePass_2 > 0){
			alert("비밀번호의 영문,숫자는 오름차순, 내림차순 연속 입력할 수 없습니다.");
			return false;
		}
	}
	
	//비밀번호확인 체크
	if(pwchk ==""){alert("비밀번호 확인을 입력하세요."); return false;}	
	else if(pw != pwchk){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다. 다시 입력해 주세요.");
		$("#pwchk").val("");
		$("#pwchk").focus();
		return false;
	}
	
	//이메일 체크
	var mchk = /^[a-zA-Z0-9]*$/;
	if(email ==""){alert("이메일을 입력하세요."); return false;}
	else if(email !='' && !mchk.test(email)){
		alert("이메일 형식이 올바르지 않습니다.");	
		$("#email").val("");
		$("#email").focus();
		return false;
	}
	
	//이름 체크
	var reg = /^[\ㄱ-ㅎ ㅏ-ㅣ 가-힣]*$/;
	if(fulnm ==""){alert("이름을 입력하세요."); return false;}
	else if(fulnm !='' && !reg.test(fulnm)){
		alert("이름에 한글만 입력이 가능합니다.");
		$("#fulnm").val("");
		$("#fulnm").focus();
		return false;
	}
	
	//전화번호 체크
	var reg1 = /^[0-9-]*$/;
	if(hp ==""){alert("전화번호를 입력하세요."); return false;}
	else if(hp.length < 7){
		alert("전화번호는 7자 이상이어야 합니다.");
		$("#hp").val("");
		$("#hp").focus();
		return false;
	}else if(hp !='' && !reg1.test(hp)){
		alert("숫자만 입력이 가능합니다.");
		$("#hp").val("");
		$("#hp").focus();
		return false;
	}
		
	//동의 체크
	if($("#agree").prop("checked") == false){alert("사용자 인증을 위한 개인정보 사용 동의를 해주십시오."); return false;}	

	srvno = aesHelper.encrypt(srvno);
	fulnm = aesHelper.encrypt(fulnm);
//	pw = aesHelper.encrypt(pw);
	var pw = Sha256.hash($("#pw").val());
	email = aesHelper.encrypt(email);
	hp = aesHelper.encrypt(hp); 
	if(confirm("등록하시겠습니까?"))
	{
		$("html").addClass("ui-loading");
		  $.ajax({
			url : "../action/ajax_user.jsp?action=user_add",
			type : "post",
			dataType : "json",
			data : {srvno:srvno, fulnm:fulnm, pw:pw, email:email, hp:hp},
			success:function(result) {
				if(result.check =="y")
				{
					$("#srvno").val(""); //군번
					$("#fulnm").val(""); //성명
					$("#pw").val(""); //비밀번호
					$("#pwchk").val(""); //비밀번호확인
					$("#email").val(""); //이메일
					$("#hp").val(""); //전화번호
					alert("등록되었습니다.");
					location.href="./login.jsp";					
				}
				else
				{
					$("html").removeClass("ui-loading");
					alert("이미 등록된 사용자입니다.");
					$("#srvno").val(""); //군번
					$("#fulnm").val(""); //성명
					$("#pw").val(""); //비밀번호
					$("#pwchk").val(""); //비밀번호확인
					$("#email").val(""); //이메일
					$("#hp").val(""); //전화번호
				}
			},
			error: function(request, status, error) {

				alert(request.responseText);

				}
		  });
	}
}

function pwdch(srvno)
{
	var oripw = $("#oripw").val(); //패스워드
	var chpw = $("#chpw").val(); //패스워드
	var chpwcf = $("#chpwcf").val(); //패스워드
	
	if(oripw == "")
	{
		alert("현재 비밀번호를 입력하십시오.");
		return false;
	}
	
	if(chpw != chpwcf)
	{
		alert("변경될 비밀번호와 변경될 비밀번호 확인이 일치하지 않습니다.");
		
		return false;
	}
	
	if(chpw == oripw)
	{		
		alert("이전 비밀번호와 동일하게 설정할 수 없습니다.");		
		
		return false;
	}
	
	if(chkPwd(chpw,'변경할 비밀번호') && chkPwd(chpwcf,'변경할 비밀번호 확인'))
	{		
		oripw =  Sha256.hash(oripw);
		chpw = Sha256.hash(chpw);
		srvno = aesHelper.encrypt(srvno);
		if(confirm("변경하시겠습니까?"))
		{
			  $("html").addClass("ui-loading");
			  $.ajax({
				url : "../action/ajax_user.jsp?action=user_edit",
				type : "post",
				data : {srvno:srvno, oripw:oripw, chpw:chpw},
				success:function(result) {
					if($.trim(result) == 1)
					{
						$("#oripw").val(""); //군번
						$("#chpw").val(""); //성명
						$("#chpwcf").val(""); //성명
						alert("비밀번호가 변경되었습니다.");
						location.href="./login.jsp";
					}
					else if($.trim(result) == 0)
					{
						alert("현재 비밀번호가 맞지 않습니다.\n다시 입력해주십시오.");
					}
				},
				error: function(request, status, error) {
					$("html").removeClass("ui-loading");
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

				}
			  });
		}
	}
}

function btnBack()
{
	$("html").addClass("ui-loading");
	location.href="./login.jsp";
}

function SearchtoMain()
{
	$("#c1, #c4").prop("checked",true);
	$("#c1, #c4").prop("checked",true).checkboxradio('refresh');
	$("#c2, #c3, #c5, #c6, #all").prop("checked",false);
	$("#c2, #c3, #c5, #c6, #all").prop("checked",false).checkboxradio('refresh');
	$("#word").val("");
	$("#word2").val("");
	$("#word3").val("");
	$.mobile.changePage('main.jsp');
}

function SearchRtoSearch()
{
	$("#c1, #c4").prop("checked",true);
	$("#c1, #c4").prop("checked",true).checkboxradio('refresh');
	$("#c2, #c3, #c5, #c6, #all").prop("checked",false);
	$("#c2, #c3, #c5, #c6, #all").prop("checked",false).checkboxradio('refresh');
	$("#word").val("");
	$("#word2").val("");
	$("#word3").val("");
	$.mobile.changePage('search.jsp');
}

//규정 검색기능
function btnSearchWord()
{
	var c1 =0, c2 = 0, c3= 0, c4=0, c5=0, c6=0, all=0;

	if(!($("#c1").prop("checked") || $("#c2").prop("checked") || $("#c3").prop("checked")))
	{
		alert("검색하실 행정규칙을 선택하십시오.");
		
		return false;
	}
	if(!($("#c4").prop("checked") || $("#c5").prop("checked")|| $("#c6").prop("checked")))
	{
		alert("검색하실 조건을 선택하십시오.");
		
		return false;
	}		
		
	if($("#plus2").css("display") == "none")
	{
		if($("#word3").val().trim() == "")
		{
			alert("검색어를 입력해 주십시오.");
			
			return false;
		}
		else if($("#word3").val().length <2)
		{
			alert("검색어를 2자 이상 입력해 주십시오.");
			
			return false;
		}
	}
	
	if($("#plus").css("display") == "none")
	{
		if($("#word2").val().trim() == "")
		{
			alert("검색어를 입력해 주십시오.");
			
			return false;
		}
		else if($("#word2").val().length <2)
		{
			alert("검색어를 2자 이상 입력해 주십시오.");
			
			return false;
		}
	}
	
	if($("#word").val().trim() == "")
	{
		alert("검색어를 입력해 주십시오.");
		
		return false;
	}
	else if($("#word").val().length <2)
	{
		alert("검색어를 2자 이상 입력해 주십시오.");
		
		return false;
	}
	
	if($("#c1").prop("checked"))
	{
		c1 = 1;
	}
	if($("#c2").prop("checked"))
	{
		c2 = 1;
	}
	if($("#c3").prop("checked"))
	{
		c3 = 1;
	}
	if($("#c4").prop("checked"))
	{
		c4 = 1;
	}
	if($("#c5").prop("checked"))
	{
		c5 = 1;
	}
	
	if($("#c6").prop("checked"))
	{
		c6 = 1;
	}
		
	var word = $("#word").val();
	var word2 = $("#word2").val();
	var word3 = $("#word3").val();
	
	word = textChange(word);
	word2 = textChange(word2);
	word3 = textChange(word3);
	
	$("#c1").prop("checked",false);
	$("#c2").prop("checked",false);
	$("#c3").prop("checked",false);
	$("#c4").prop("checked",false);
	$("#c5").prop("checked",false);
	$("#c6").prop("checked",false);
	$("#word").val("");

    $.mobile.changePage("search_result.jsp?c1="+c1 + "&c2="+ c2 + "&c3=" + c3 + "&c4=" + c4 + "&c5=" + c5 + "&c6=" + c6 + "&word="+word + "&word2=" + word2 + "&word3=" + word3); //로그인페이지 이동	 
}

function textChange(text)
{
    text.replace("&","!amp");
    text.replace("#","!sharp");
    text.replace("%","!percent");
    text.replace("\\","!backslash");
    text.replace("+","!plus");
    
    return text;
}
