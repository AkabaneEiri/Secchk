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
    var state_cd ='D2';
    var seq= getParameterByName('seq');
    var rvw_opn = document.Approve_Value.Value_opn.value;
    var itm_cd = document.Approve_Value.Value_ctlg_itm_cd.value;
    var ctlg_cd = document.Approve_Value.Value_type.value;
    var ctlg_itm_ctnt = document.Approve_Value.Value_ctnt.value;
    var ctlg_itm_cd_1 = document.Approve_Value.Value_ctlg_itm_cd_1.value;
    var rsn = document.Approve_Value.Value_rsn.value;
    
    var ApproveConfirm = confirm('해당 요청을 승인하시겠습니까?')
    
    if(ApproveConfirm)
        {
        var ResultUrl = "CheckRequest_Result.do?state_cd="+encodeURI(state_cd)+"&seq="+seq+"&rvw_opn="+encodeURI(rvw_opn)+"&isNew="+encodeURI(itm_cd)+"&ctlg_cd="+encodeURI(ctlg_cd)+"&ctlg_itm_ctnt="+encodeURI(ctlg_itm_ctnt)+"&cltg_itm_cd_1="+encodeURI(ctlg_itm_cd_1);
            location.href = ResultUrl;
        }
    else{
        alert("취소하셨습니다.");
    }
    
}                 
function    Approval_Deny(){
    var state_cd ='D3';
    var seq= getParameterByName('seq');
    var rvw_opn = document.Approve_Value.Value_opn.value;
    var ctlg_itm_cd_1 = "";
 
    var DenyConfirm = confirm('해당 요청을 반려하시겠습니까?')
    
    if(DenyConfirm)
        {
            var ResultUrl = "CheckRequest_Result.do?state_cd="+encodeURI(state_cd)+"&seq="+seq+"&rvw_opn="+encodeURI(rvw_opn);
            location.href = ResultUrl;
        }
    else{
        alert("취소하셨습니다.");
    }
}
function    Approval_Cancle(){
    location.href="CheckRequestedCheckList.do";
}
