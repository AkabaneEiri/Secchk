$(document).ready(function(){
	$("#opn").bind("keyup", checkVal);
});
function checkVal(){
	var opn = document.getElementById("opn");
	var res = document.getElementById("opn_res");
	var opn_length = opn.value.length; 
	if(opn_length > 100)
		{
			opn_res.value = opn_length+"/80";
			opn.value = opn.value.slice(0,80);
		}
	else
		{
			opn_res.value = opn_length+"/80";
		}
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Approval_Approve(){
	var state_cd ='승인완료';
	var seq= getParameterByName('seq');
	var opn = document.Approve_Value.Value_opn.value;
	
	var ApproveConfirm = confirm('해당 요청을 승인하시겠습니까?')
	
	if(ApproveConfirm)
		{
			location.href ="CheckApprovlaResult_Result.do?state_cd="+encodeURI(state_cd)+"&seq="+seq+"&opn="+encodeURI(opn);
		}
	else{
		alert("취소하셨습니다.");
	}
	
} 				

function	Approval_Deny(){
	var state_cd ='반려';
	var seq= getParameterByName('seq');
	var opn = document.Approve_Value.Value_opn.value;

	var DenyConfirm = confirm('해당 요청을 반려하시겠습니까?')
	
	if(DenyConfirm)
		{
			location.href ="CheckApprovlaResult_Result.do?state_cd="+encodeURI(state_cd)+"&seq="+seq+"&opn="+encodeURI(opn);
		}
	else{
		alert("취소하셨습니다.");
	}
}
function	Approval_Cancle(){
	location.href="CheckApproval.do";
}