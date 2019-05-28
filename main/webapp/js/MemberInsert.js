$(document).ready(function(){
	var strPassword;
	var charPassword;
	var complexity = document.getElementById("pwalert");
	
	var password 		= document.getElementById("password");
	var passwordalert 	= document.getElementById("pwalert");
	
	var minPasswordLength = 9;
	var baseScore = 0, score = 0;
	
	var num = {};
	num.Excess = 0;
	num.Upper = 0;
	num.Numbers = 0;
	num.Symbols = 0;

	var bonus = {};
	bonus.Excess = 3;
	bonus.Upper = 4;
	bonus.Numbers = 5;
	bonus.Symbols = 5;
	bonus.Combo = 0; 
	bonus.FlatLower = 0;
	bonus.FlatNumber = 0;
	
	outputResult();
	
	$("#password").bind("keyup", checkVal);

	function checkVal()
	{
		init();
		
		if (charPassword.length >= "0")
		{
			baseScore = 50;	
			analyzeString();	
			calcComplexity();		
		}
		else
		{
			baseScore = 0;
		}
		
		outputResult();
	}
	
	function init()
	{
		strPassword= password.value;
		charPassword = strPassword.split("");
			
		num.Excess = 0;
		num.Upper = 0;
		num.Numbers = 0;
		num.Symbols = 0;
		bonus.Combo = 0; 
		bonus.FlatLower = 0;
		bonus.FlatNumber = 0;
		baseScore = 0;
		score =0;
	}
	function analyzeString ()
	{	
		for (i=0; i<charPassword.length;i++)
		{
			if (charPassword[i].match(/[A-Z]/g)) {num.Upper++;}
			if (charPassword[i].match(/[0-9]/g)) {num.Numbers++;}
			if (charPassword[i].match(/(.*[!,@,#,$,%,^,&,*,?,_,~])/)) {num.Symbols++;} 
		}
		
		num.Excess = charPassword.length - minPasswordLength;
		
		if (num.Upper && num.Numbers && num.Symbols)
		{
			bonus.Combo = 25; 
		}

		else if ((num.Upper && num.Numbers) || (num.Upper && num.Symbols) || (num.Numbers && num.Symbols))
		{
			bonus.Combo = 15; 
		}
		
		if (strPassword.match(/^[\sa-z]+$/))
		{ 
			bonus.FlatLower = -15;
		}
		
		if (strPassword.match(/^[\s0-9]+$/))
		{ 
			bonus.FlatNumber = -35;
		}
	}
	
	function calcComplexity()
	{
		score = baseScore + (num.Excess*bonus.Excess) + (num.Upper*bonus.Upper) + (num.Numbers*bonus.Numbers) + (num.Symbols*bonus.Symbols) + bonus.Combo + bonus.FlatLower + bonus.FlatNumber;
		
	}	
	
	function outputResult()
	{
		var complexity = document.getElementById("pwalert");
		
		if (password.value == "")
			{ 
				complexity.value = ("비밀번호는 " + minPasswordLength + " 자 이상이여야 합니다.");
				complexity.name = "cannot";
				complexity.style.color = "#FF3D90";
			}
		else 
			{
				if(num.Upper == 0)
				{
					complexity.value = ("대문자가 포함되어야 합니다.");
					complexity.name = "cannot";
					complexity.style.color = "#FF3D90";
				}
				else if(num.Numbers == 0)
					{
						complexity.value = ("숫자가 포함되어야 합니다");
						complexity.name = "cannot";
						complexity.style.color = "#FF3D90";
					}
				else if(num.Symbols == 0)
					{
						complexity.value = ("특수문자가 포함되어야 합니다");
						complexity.name = "cannot";
						complexity.style.color = "#FF3D90";
					}
			else{
				if (charPassword.length < minPasswordLength)
					{
						complexity.value = ("비밀번호는 " + minPasswordLength + " 자 이상이여야 합니다.");
						complexity.name = "cannot";
						complexity.style.color = "#FF3D90";
					}
				else if (score<50)
					{
						complexity.value = ("보안 위험");
						complexity.name = "ok";
						complexity.style.color = "#D449FF";
					}
				else if (score>=50 && score<75)
					{
						complexity.value = ("평균");
						complexity.name = "ok";
						complexity.style.color = "#683DFF";
					}
				else if (score>=75 && score<100)
					{
						complexity.value = ("보안 높음");
						complexity.name = "ok";
						complexity.style.color = "#3072FF";
					}
				else if (score>=100)
					{
						complexity.value = ("보안 매우 높음");
						complexity.name = "ok";
						complexity.style.color = "#24DDFF";
					}
			}
		}
	}
	
	
})


function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}

function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	var Code = tdObj.id;

	document.InsertForm.incdt_idtf_cd.value=Name;
//		document.TaskSearch.searchCode.value=Code;	
}
function Insert_Cancle(){

	//document.write("");
	//window.location.replace('Userlist.do');
	history.back();
}
function checksrvno()
{
	var srvno 						= document.getElementById("srvno").value;
	if(srvno == "")
		{
			alert("군번을 입력하여 주십시요");
		}
	else{
		var i;
		var Member = new Object();
		Member.Srvno = srvno;
		
		var jsonString = JSON.stringify(Member);
		
		$.ajax({
			url:"Member_srvno_Ajax.do",
			type:"post",
			data:{"jsonString":jsonString},
			success:whenSuccess,
			error:whenErrorreulst
		});
	}
}
function whenSuccess(obj)
{
	var res = JSON.parse(obj);
	
		if(res.result == "ok")
			{
				alert("사용하실 수 있는 군번입니다.");
				document.getElementById("srvno").name = "srvno_checked";
			}
		else
			{
				alert("사용하실 수 없는 군번입니다.");
			}
}
function whenErrorreulst(obj)
{
	console.log("fail : " + obj);
}
function Insert_Submit()
{
	
	var stmt 						= document.getElementById("stmt").value;
	var rspofc_nm 					= document.getElementById("rspofc_nm").value;
	var srvno 						= document.getElementById("srvno").value;
	var password 					= document.getElementById("password").value;
	var rnkcd_select				= document.getElementById("rnkcd");
	var password_check				= document.getElementById("password_check").value;		
	var prtbl_telno 				= document.getElementById("prtbl_telno").value;
	var incdt_idtf_cd 				= document.getElementById("incdt_idtf_cd").value;
	var athrt_select				= document.getElementById("athrt");
	var montr_select				= document.getElementById("montr");
	var passwordalert 				= document.getElementById("pwalert");
	
	var rnkcd						= rnkcd_select.options[rnkcd_select.selectedIndex].value;
	var athrt						= athrt_select.options[athrt_select.selectedIndex].value;
	var montr						= montr_select.options[montr_select.selectedIndex].value;
	
	// 190522 add by seungwon //
	var ip							= $("#ip").val();
	if ($("#athrt option:selected").val() == "B2" || $("#athrt option:selected").val() == "B3") {
		if ($("#ip").val().trim() == "") {
			alert("IP를 입력해주십시오.");
			return false;
		}
	}
	////////////////////////////

	 if (password != password_check)
		{
		alert("비밀번호가 일치하지 않습니다.");
			return false;
		}	
	 else if(stmt=="")
		 {
		 	alert("이름을 입력해 주십시오");
		 	document.getElementById("stmt").focus();
		 }
	 else if(rspofc_nm=="")
		 {
		 	alert("직책을 입력해 주십시오");
		 	document.getElementById("rspofc_nm").focus();
		 }
	 else if(document.getElementById("srvno").name == "srvno")
		 {
		 	if(srvno=="")
			 {
			 	alert("군번을 입력해 주십시오");
			 	document.getElementById("srvno").focus();
			 }
		 	else
		 		{
		 			alert("중복 확인을 해 주십시오");
		 		}
		 }
	 else if(passwordalert.name == "cannot")
		{
			alert("비밀번호를 확인해 주십시오");
			return false;
		}
	
	 else if(password=="")
		 {
		 	alert("비밀번호를 입력해 주십시오");
		 	document.getElementById("password_check").focus();
		 }
	 else if(rnkcd=="")
		 {
		 	alert("계급을 선택해 주십시오");
		 	document.getElementById("rnkcd").focus();
		 }
	 else if(password_check != password)
		 {
		 alert("비밀번호가 서로 다릅니다.");
		 document.getElementById("password_check").focus();
		 }
	 else if(incdt_idtf_cd=="")
		 {
		 	alert("소속부대을 선택해 주십시오");
		 	document.getElementById("incdt_idtf_cd").focus();
		 }
	 else if(athrt=="")
		 {
		 	alert("사용자 정보 선택해 주십시오");
		 	document.getElementById("athrt").focus();
		 }
	else 
	{
		if(confirm("해당 정보로 사용자를 등록하시겠습니까?"))
		{
			Member_Insert_Submit();
//			location.href="Member_Insert_Result.do?&stmt="+encodeURI(stmt)+"&rspofc_nm="+encodeURI(rspofc_nm)+"&srvno="+encodeURI(srvno)+"&password="+encodeURI(password)+"&rnkcd="+encodeURI(rnkcd)+"&prtbl_telno="+encodeURI(prtbl_telno)+"&incdt_idtf_cd="+encodeURI(incdt_idtf_cd)+"&athrt="+encodeURI(athrt)+"&montr="+encodeURI(montr)+"&comip="+encodeURI(ip);
		}
		else
			{
			return false;
			}
		
	}

}			

function Member_Insert_Submit(){
	var stmt 						= document.getElementById("stmt").value;
	var rspofc_nm 					= document.getElementById("rspofc_nm").value;
	var srvno 						= document.getElementById("srvno").value;
	var password 					= document.getElementById("password").value;
	var rnkcd_select				= document.getElementById("rnkcd");
	var password_check				= document.getElementById("password_check").value;		
	var prtbl_telno 				= document.getElementById("prtbl_telno").value;
	var incdt_idtf_cd 				= document.getElementById("incdt_idtf_cd").value;
	var athrt_select				= document.getElementById("athrt");
	var montr_select				= document.getElementById("montr");
	var passwordalert 				= document.getElementById("pwalert").value;
	
	var rnkcd						= rnkcd_select.options[rnkcd_select.selectedIndex].value;
	var athrt						= athrt_select.options[athrt_select.selectedIndex].value;
	var montr						= montr_select.options[montr_select.selectedIndex].value;
	
	var ip							= $("#ip").val();
//	if ($("#athrt option:selected").val() == "B2" || $("#athrt option:selected").val() == "B3") {
//		if ($("#ip").val().trim() == "") {
//			alert("IP를 입력해주십시오.");
//			return false;
//		}
//	}
	
	var code = new Object();
	code.stmt 						= stmt
	code.rspofc_nm 					= rspofc_nm
	code.srvno 						= srvno
	code.password 					= password
	code.rnkcd_select				= rnkcd
	code.password_check				= password_check		
	code.prtbl_telno 				= prtbl_telno
	code.incdt_idtf_cd 				= incdt_idtf_cd
	code.athrt_select				= athrt
	code.montr_select				= montr
	code.passwordalert 				= pwalert
	code.ip							= ip
	
	var jsonString = JSON.stringify(code);

 $.ajax({
	 	url: "MemberInsert_Ajax.do",						//request보낼 경로
	 	type: "post",																//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 											//보낼 데이터
	 	success : whenSuccessInsert,													//성공 시 값 보낼 펑션
	 	error: whenError
 });
	
	
//	location.href="Member_Insert_Result.do?&stmt="+encodeURI(stmt)+"&rspofc_nm="+encodeURI(rspofc_nm)+"&srvno="+encodeURI(srvno)+"&password="+encodeURI(password)+"&rnkcd="+encodeURI(rnkcd)+"&prtbl_telno="+encodeURI(prtbl_telno)+"&incdt_idtf_cd="+encodeURI(incdt_idtf_cd)+"&athrt="+encodeURI(athrt)+"&montr="+encodeURI(montr)+"&comip="+encodeURI(ip);
}
function submit_troops(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	var submit_value = document.getElementById("incdt_idtf_cd");
	var Troops_value = innerDoc.getElementById("TroopsSearch").value;
	
	submit_value.value = Troops_value;
}

// 190522 add by seungwon
function onChange_Athrt() {
	$("#ip").val("");
	
	if ($("#athrt option:selected").val() == "B2" || $("#athrt option:selected").val() == "B3") {
		$("#tr_ip").removeClass("display_none");
	}
	else {
		$("#tr_ip").addClass("display_none");
	}
}
function whenSuccessInsert(res){
	console.log(res);
	var obj = JSON.parse(res);
	var result = obj.result;
	
	if(result == "fail")
		{
			alert("사용자 등록에 실패하였습니다. 입력 정보를 확인해 주십시오");
		}
	else
		{
			alert("사용자 등록에 성공하였습니다.");
			location.href = "Userlist.do";
		}
}
function whenError(res){
	console.log(res);
	alert("사용자 등록에 실패하였습니다. 입력 정보를 확인해 주십시오");
}