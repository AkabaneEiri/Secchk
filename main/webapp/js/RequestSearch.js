$(document).ready(function(){
	var actvt_date 	= document.getElementById("actvt_date");
	var rqst_date 	= document.getElementById("rqst_date");
	var rqstr_srvno = document.getElementById("rqstr_srvno");
	var actvt_type 	= document.getElementById("actvt_type");
	var state_cd 	= document.getElementById("state_cd");
	
	var param_actvt_date = getParameterByName("actvt_date");
	var param_rqst_date= getParameterByName("rqst_date");
	var param_rqstr_srvno= getParameterByName("rqstr_srvno");
	var param_actvt_type= getParameterByName("actvt_type");
	var param_state_cd= getParameterByName("state_select");

	actvt_date = param_actvt_date;
	rqst_date.value = param_rqst_date;
	rqstr_srvno.value = param_rqstr_srvno;
	actvt_type.value = param_actvt_type;
	
	$("#state_cd").val(param_state_cd).prop("selected", true);		// 대분류값 선택
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
