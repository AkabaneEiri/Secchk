var idcheck2=false; //아이디 중복확인
var keycheck2=false; //인트라넷 인증키 확인

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
		var SamePass_0 = 0; //동일문자 카운트
        var SamePass_1 = 0; //연속성(+) 카운드
        var SamePass_2 = 0; //연속성(-) 카운드
        var stair = 0;
        var pw = document.getElementById("password").value;
		if (password.value == "")
			{ 
				complexity.name = "cannot";
				complexity.style.color = "#FF3D90";
			}
		else 
			{
	        for(var i=0; i < pw.length; i++) {
          var chr_pass_0;
          var chr_pass_1;
          var chr_pass_2;
          if(i >= 2) {
              chr_pass_0 = pw.charCodeAt(i-2);
              chr_pass_1 = pw.charCodeAt(i-1);
              chr_pass_2 = pw.charCodeAt(i);
               //동일문자 카운트
              if((chr_pass_0 == chr_pass_1) && (chr_pass_1 == chr_pass_2)) {
                 SamePass_0++;
               } 
               else {
                SamePass_0 = 0;
                }
               //연속성(+) 카운드
              if(chr_pass_0 - chr_pass_1 == 1 && chr_pass_1 - chr_pass_2 == 1) {
                  SamePass_1++;
               }
               else {
                SamePass_1 = 0;
               }
               //연속성(-) 카운드
              if(chr_pass_0 - chr_pass_1 == -1 && chr_pass_1 - chr_pass_2 == -1) {
                  SamePass_2++;
               } 
               else {
                SamePass_2 = 0;
               }  
          }     
     }
	        if (charPassword.length < minPasswordLength)
			{
				complexity.value = ("비밀번호는 " + minPasswordLength + " 자 이상이여야 합니다.");
				complexity.name = "cannot";
				complexity.style.color = "#FF3D90";
			}
		else if(SamePass_0 > 0) {
	            complexity.value = ("동일문자를 3자 이상 연속 입력할 수 없습니다.");
	      		complexity.name = "cannot";
	      		complexity.style.color = "#FF3D90";
	      		stair = 1;
	          }
	        else if(SamePass_1 > 0 || SamePass_2 > 0 ) {
	            complexity.value = ("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
	      		complexity.name = "cannot";
	      		complexity.style.color = "#FF3D90";
	      		stair = 2;
	          } 
	         else if(num.Upper == 0)
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
		        else if(stair == 1){
		            complexity.value = ("동일문자를 3자 이상 연속 입력할 수 없습니다.");
		      		complexity.name = "cannot";
		      		complexity.style.color = "#FF3D90";
		        }
		        else if(stair == 2){
		            complexity.value = ("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
		      		complexity.name = "cannot";
		      		complexity.style.color = "#FF3D90";
		        }
		        else if(stair == 1 || stair == 2)
		        	{
		        		stair = 0;
		        	}
			else{
				if (score<50)
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



window.addEventListener("load", function() {
	setTimeout(scrollTo, 0, 0, 1);
}, false); // url 바 숨기기

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Midify_Submit(){
	
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
	var acc_state_info_select		= document.getElementById("acc_state_info");
	
	var rnkcd						= rnkcd_select.options[rnkcd_select.selectedIndex].value;
	var athrt						= athrt_select.options[athrt_select.selectedIndex].value;
	var montr						= montr_select.options[montr_select.selectedIndex].value;
	
	var acc_state_info				= acc_state_info_select.options[acc_state_info_select.selectedIndex].value;
	
	// 190522 add by seungwon //
	var ip							= $("#ip").val();
	if ($("#athrt option:selected").val() == "B2" || $("#athrt option:selected").val() == "B3") {
		if ($("#ip").val().trim() == "") {
			alert("IP를 입력해주십시오.");
			return false;
		}
	}
	////////////////////////////
	
	Modifyconfirm = confirm("기재한 사항으로 변경하시겠습니까?")
	if(Modifyconfirm)
		{
			if(password!= "")
				{
					if(passwordalert.name == "cannot")
						{
							alert("비밀번호를 확인해 주십시오");
							return false;
						}
					else if(password == password_check)
						{
							location.href="MemberModify_result.do?&stmt="+encodeURI(stmt)+"&rspofc_nm="+encodeURI(rspofc_nm)+"&srvno="+encodeURI(srvno)+"&password="+encodeURI(password)+"&rnkcd="+encodeURI(rnkcd)+"&prtbl_telno="+encodeURI(prtbl_telno)+"&incdt_idtf_cd="+encodeURI(incdt_idtf_cd)+"&athrt="+encodeURI(athrt)+"&montr="+encodeURI(montr)+"&acc_state_info="+encodeURI(acc_state_info)+"&comip="+encodeURI(ip);
						}
					else
						{
							alert("비밀번호가 다릅니다.")
						}
				}
			else
				{
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
				code.acc_state_info 			= acc_state_info
				code.ip							= ip
				
				var jsonString = JSON.stringify(code);

			 $.ajax({
				 	url: "MemberModify_Ajax.do",						//request보낼 경로
				 	type: "post",																//메소드(post로 적용)
				 	data: {"jsonString":jsonString}, 											//보낼 데이터
				 	success : whenSuccessModify,													//성공 시 값 보낼 펑션
				 	error: whenError
			 });
//				location.href="MemberModify_result.do?&stmt="+encodeURI(stmt)+"&rspofc_nm="+encodeURI(rspofc_nm)+"&srvno="+encodeURI(srvno)+"&password="+encodeURI(password)+"&rnkcd="+encodeURI(rnkcd)+"&prtbl_telno="+encodeURI(prtbl_telno)+"&incdt_idtf_cd="+encodeURI(incdt_idtf_cd)+"&athrt="+encodeURI(athrt)+"&montr="+encodeURI(montr)+"&acc_state_info="+encodeURI(acc_state_info)+"&comip="+encodeURI(ip);
				}
		}
	else
		{
			alert("취소하셨습니다.")
		}
}


function Modify_Cancle(){
	backconfirm = confirm("취소하시겠습니까?");
	
	if(backconfirm)
		{
			//alert("취소하셨습니다.");
			location.href="Userlist.do";
//			history.back();

		}
}
	function access(){
		var iframe = document.getElementById("iframe");
		var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
		console.log(innerDoc.body)
	}

	function clickTdEvent(tdObj){
		
		var Name = tdObj.innerText;
		var Code = tdObj.id;

		document.Modify_value.incdt_idtf_cd.value=Name;
//		document.TaskSearch.searchCode.value=Code;	
	}
	$(document).ready(function(){
	})
function submit_troops(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	var submit_value = document.getElementById("incdt_idtf_cd");
	var Troops_value = innerDoc.getElementById("TroopsSearch").value;
	
	submit_value.value = Troops_value;
}
function whenSuccessModify(res){
	console.log(res);
	var obj = JSON.parse(res);
	var result = obj.result;
	
	if(result == "fail")
		{
			alert("사용자 정보 수정에 실패하였습니다. 입력 정보를 확인해 주십시오");
		}
	else
		{
			alert("사용자 정보 수정에 성공하였습니다.");
			location.href = "Userlist.do";
		}
}
function whenError(res){
	console.log(res);
}
