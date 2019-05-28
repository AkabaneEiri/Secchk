$(document).ready(function(){
	$('#Checklist_code').keyup(function(){
		if(document.CheckCode.Checklist_code.value.length >= 8){
			var code = new Object();
			var ctlg_cd = getParameterByName('ctlg_cd');
			var ctlg_itm_cd = document.CheckCode.Checklist_code.value;
			code.code1 = $('#Checklist_code').val();
			
			var jsonString = JSON.stringify(code);
			console.log(jsonString);
			
			$.ajax({
				url:'./ManageCheckList_Ajax_json.do?cltg_cd='+ctlg_cd+"&ctlg_itm_cd="+ctlg_itm_cd, //request 보낼 서버의 경로
				type:'post', // 메소드(get, post, put 등)
				data: {"jsonString":jsonString}, //보낼 데이터
				success: function(result) {
				//서버로부터 정상적으로 응답이 왔을 때 실행 
					var obj = jQuery.parseJSON(result);
					var resultinfo = document.getElementById("codeCheck");
					var Accept = 'OK';
					if(obj.code1 == Accept){
						resultinfo.setAttribute('class', 'badge badge-primary');
						document.CheckCode.codeCheck_result.value= "사용가능";
						document.button_form.submit.disabled = false;
					}else{
						resultinfo.setAttribute('class', 'badge badge-danger');
						document.CheckCode.codeCheck_result.value= obj.code1;
						document.button_form.submit.disabled = 'disabled';
					}
				},
				error: function(err) {
				//서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
					alert("error! : "+err);
				}
			});
		}
	})
})
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Insert_Cancle(){
	document.write("");
	ctlg_cd = getParameterByName('ctlg_cd');
	location.href = "ManageCheckListItem.do?ctlg_cd="+ctlg_cd;
}

function Insert_Submit(){
	Code = document.CheckCode.Checklist_code.value;
	Name = document.ChecnName.Checklist_name.value;
	ctlg_cd = getParameterByName('ctlg_cd');
	
	location.href="ManageCheckListItem_Result.do?Ctlg_cd="+ctlg_cd+"&Code="+Code+"&Name="+Name;
}
