var idcheck2=false; //아이디 중복확인
var keycheck2=false; //인트라넷 인증키 확인

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

	var stmt						= document.Modify_value.stmt.value;
	var rspofc_nm				= document.Modify_value.rspofc_nm.value;
	var srvno						= document.Modify_value.srvno.value;
	var password				= document.Modify_value.password.value;
	var rnkcd						= document.Modify_value.rnkcd.value;
	var password_check	= document.Modify_value.password_check.value;
	var prtbl_telno				= document.Modify_value.prtbl_telno.value;
	var incdt_idtf_cd			= document.Modify_value.incdt_idtf_cd.value;
	var athrt					= document.Modify_value.athrt.value;
	var montr			= document.Modify_value.montr.value;
	
	Modifyconfirm = confirm("기재한 사항으로 변경하시겠습니까?")
	if(Modifyconfirm)
		{
			if(password == password_check)
				{
					document.write("");
					location.href="MemberModify_result.do?&stmt="+stmt+"&rspofc_nm="+rspofc_nm+"&srvno="+srvno+"&password="+password+"&rnkcd="+rnkcd+"&prtbl_telno="+prtbl_telno+
					"&incdt_idtf_cd="+incdt_idtf_cd+"&athrt="+athrt+"&montr="+montr;
				}
			else{
				alert("비밀번호가 다릅니다.")
			}
		}
	else{
		alert("취소하셨습니다.")
	}
}


function Modify_Cancle(){
	backconfirm = confirm("취소하시겠습니까?");
	
	if(backconfirm)
		{
			alert("취소하셨습니다.")
			document.write("");
			location.href="Userlist.do";
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
