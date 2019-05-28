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
			document.write("");
			location.href ="CheckApprovlaResult_Result.do?state_cd="+state_cd+"&seq="+seq+"&opn="+opn;
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
			document.write("");
			location.href ="CheckApprovlaResult_Result.do?state_cd="+state_cd+"&seq="+seq+"&opn="+opn;
		}
	else{
		alert("취소하셨습니다.");
	}
}
function	Approval_Cancle(){
	document.write("");
	location.href="CheckApproval.do";
}